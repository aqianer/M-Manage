package top.aqianer.manage.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.aqianer.manage.io.RegisterForm;
import top.aqianer.manage.io.RegisterRequest;

public interface UserService extends UserDetailsService {
    void registerUser(RegisterRequest registerRequest);

    void register(RegisterForm form);

    String loginUser(String email, String pwd);

    Long getLabIdByEmail(String email);

    UserDetails loadUserById(Long userId);
}
