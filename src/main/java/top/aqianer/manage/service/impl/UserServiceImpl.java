package top.aqianer.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.aqianer.manage.exception.BusinessException;
import top.aqianer.manage.io.RegisterForm;
import top.aqianer.manage.io.RegisterRequest;
import top.aqianer.manage.model.LabMember;
import top.aqianer.manage.model.Laboratory;
import top.aqianer.manage.repository.LabRepository;
import top.aqianer.manage.repository.MemberRepository;
import top.aqianer.manage.service.UserService;
import top.aqianer.manage.util.JwtUtil;
import top.aqianer.manage.util.MD5Util;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LabRepository labRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(RegisterRequest registerRequest) {
        // 将DTO转换为实体
        LabMember member = new LabMember();
        member.setPhone(registerRequest.getPhonenumber());
        member.setPassword(passwordEncoder.encode(registerRequest.getPwd()));
        member.setMemberName(registerRequest.getUsername());
        member.setEmail(registerRequest.getEmail());
        if (!"member".equals(registerRequest.getRegistertype())) {
            // 将DTO转换为实体
            member.setPermission(LabMember.Permission.valueOf("admin"));
            member.setMemberName(registerRequest.getLabinfo().getLabadmin());
            try {
                LabMember savedMember = memberRepository.save(member);
                Laboratory laboratory = new Laboratory();
                laboratory.setSchool(registerRequest.getLabinfo().getLablocation());
                laboratory.setLabName(registerRequest.getLabinfo().getLabname());
                laboratory.setRegistrant_member_id(savedMember.getId());
                labRepository.save(laboratory);
            } catch (DataAccessException e) {
                throw new BusinessException("注册失败: " + e.getCause().getMessage(), e);
            }
        } else {
            // 成员注册分支也放入事务
            memberRepository.save(member);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterForm form) {
        // 将DTO转换为实体
        LabMember member = new LabMember();
        member.setPhone(form.getPhone());
        member.setPassword(passwordEncoder.encode(form.getPassword()));
        member.setMemberName(form.getUsername());
        member.setEmail(form.getEmail());
        if (!"member".equals(form.getRegistertype())) {
            // 将DTO转换为实体
            member.setPermission(LabMember.Permission.valueOf("admin"));
            //TODO:
            member.setMemberName(form.getPrincipal());
            try {
                LabMember savedMember = memberRepository.save(member);
                Laboratory laboratory = new Laboratory();
                laboratory.setSchool(form.getLabAddress());
                laboratory.setLabName(form.getLabName());
                laboratory.setRegistrant_member_id(savedMember.getId());
                labRepository.save(laboratory);
            } catch (DataAccessException e) {
                throw new BusinessException("注册失败: " + e.getCause().getMessage(), e);
            }
        } else {
            // 成员注册分支也放入事务
            member.setPermission(LabMember.Permission.valueOf("member"));
            memberRepository.save(member);
        }
    }

    @Override
    public String loginUser(String account, String pwd) {
        // 判断账号类型（手机号/邮箱）
        boolean isPhone = account.matches("^1[3-9]\\d{9}$");

        // 根据类型查询用户
        LabMember user = isPhone ?
                memberRepository.findByPhone(account)
                        .orElseThrow(() -> new BusinessException("手机号未注册")) :
                memberRepository.findByEmail(account)
                        .orElseThrow(() -> new BusinessException("邮箱未注册"));

        // 验证密码
        if (!passwordEncoder.matches(pwd, user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        // 生成JWT Token
        String token = JwtUtil.generateToken(user.getEmail(), user.getPermission().name());

        // 存储到Redis（设置与JWT相同的过期时间）
        String redisKey = "token:" + user.getEmail();
        redisTemplate.opsForValue().set(
                redisKey,
                token,
                JwtUtil.expiration,
                TimeUnit.MILLISECONDS
        );
        return token;
    }

    /**
     * 新增实验室ID查询方法
     *
     * @param email 邮箱
     * @return 实验室id
     */
    @Override
    public Long getLabIdByEmail(String email) {
        LabMember member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("用户不存在"));
        return member.getLaboratory() == null ? null : member.getLaboratory().getId();
    }


    @Override
    public UserDetails loadUserById(Long userId) {
        LabMember member = memberRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("用户ID不存在: " + userId));

        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getMemberName())
                .password(member.getPassword())
                .roles(member.getPermission().name())
                .build();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LabMember member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessException("用户不存在"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getMemberName())
                .password(member.getPassword())
                .roles(member.getPermission().name())
                .build();
    }
}
