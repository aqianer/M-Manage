package top.aqianer.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.aqianer.manage.service.AsyncEmailService;
import top.aqianer.manage.service.EmailService;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncEmailServiceImpl implements AsyncEmailService {
    @Autowired
    private EmailService emailServiceImpl;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Async
    public void sendAsync(String destEmail) {
        String code = generateCode(6);
        saveCode(destEmail, code);
        emailServiceImpl.sendVerificationCode(destEmail, code);
    }

    @Override
    public boolean verifyEmailCode(String email, String emailcode) {
        // TODO :比较redis中未过期的key为email的value是否为emailcode
        String code = redisTemplate.opsForValue().get(email); // 修复方法调用语法
        if (code != null && code.equals(emailcode)) {
            redisTemplate.delete(email); // 验证成功后删除验证码
            return true;
        }
        return false;
    }

    private String generateCode(int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    private void saveCode(String key, String code) {
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES); // 修复方法调用语法
    }

}
