package top.aqianer.manage.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import top.aqianer.manage.exception.BusinessException;
import top.aqianer.manage.model.LabMember;
import top.aqianer.manage.repository.MemberRepository;

@Component
public class JwtUtil {
    private static String secret;
    public static long expiration;

    @Autowired
    static MemberRepository memberRepository;

    public static Long extractUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSecret())
                .parseClaimsJws(token)
                .getBody();

        // 检查Redis中是否存在有效token
        String email = claims.getSubject();
        LabMember member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("用户不存在"));
        return member.getId();
    }

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        JwtUtil.redisTemplate = redisTemplate;
    }

    // 需要注入RedisTemplate（添加在类顶部）
    private static StringRedisTemplate redisTemplate;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JwtUtil.secret = secret;
    }

    @Value("${jwt.expiration}")
    public void setExpiration(long expiration) {
        JwtUtil.expiration = expiration;
    }

    public static byte[] getSecret() {
        return java.util.Base64.getDecoder().decode(secret);
    }

    public static boolean validateToken(String bearer) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSecret())
                    .parseClaimsJws(bearer)
                    .getBody();

            // 检查Redis中是否存在有效token
            String email = claims.getSubject();
            String redisKey = "token:" + email;
            String storedToken = redisTemplate.opsForValue().get(redisKey);

            return bearer.equals(storedToken) && claims.getExpiration().after(new Date());
        } catch (ExpiredJwtException ex) {
            // Token已过期
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            // 无效Token（签名无效/Token篡改/格式错误）
            return false;
        }
    }

    // 保持原有generateToken方法不变
    public static String generateToken(String subject, String role) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}