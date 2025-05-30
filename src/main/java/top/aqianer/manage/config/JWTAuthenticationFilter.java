package top.aqianer.manage.config;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.aqianer.manage.exception.BusinessException;
import top.aqianer.manage.service.UserService;
import top.aqianer.manage.util.JwtUtil;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        try {
            if (token != null && JwtUtil.validateToken(token)) {
                Long userId = JwtUtil.extractUserId(token);
                UserDetails userDetails = userServiceImpl.loadUserById(userId);
                
                if (userDetails == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用户不存在");
                    return;
                }

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (BusinessException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "无效的访问凭证");
            return;
        } catch (JwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "令牌验证失败");
            return;
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统服务异常");
            return;
        }
        chain.doFilter(request, response);
    }
}