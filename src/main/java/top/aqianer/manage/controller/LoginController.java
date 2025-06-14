package top.aqianer.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import top.aqianer.manage.exception.BusinessException;
import top.aqianer.manage.io.RegisterForm;
import top.aqianer.manage.io.RegisterResponse;
import top.aqianer.manage.service.AsyncEmailService;
import top.aqianer.manage.service.UserService;

@Controller
public class LoginController {
    @Autowired
    private AsyncEmailService asyncEmailServiceImpl;

    @Autowired
    private UserService userServiceImpl;

    @PostMapping("/send-code")
    public ResponseEntity<String> sendCode(@RequestParam String email) {
        System.out.println("====sendCode============sendCode===");
        asyncEmailServiceImpl.sendAsync(email);
        return ResponseEntity.ok("验证码已发送");
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterForm form) {
        try {
            RegisterResponse registerResponse = new RegisterResponse();
            
            if (!asyncEmailServiceImpl.verifyEmailCode(form.getEmail(), form.getVerifycode())) {
                registerResponse.setStatus(HttpStatus.BAD_REQUEST);
                registerResponse.setCode(400);
                registerResponse.setMessage("验证码错误");
                return ResponseEntity.badRequest().body(registerResponse);
            }
            
            userServiceImpl.register(form);
            registerResponse.setStatus(HttpStatus.OK);
            registerResponse.setCode(200);
            registerResponse.setMessage("注册成功");
            return ResponseEntity.ok(registerResponse);
            
        } catch (BusinessException e) {
            RegisterResponse registerResponse = new RegisterResponse(
                HttpStatus.BAD_REQUEST, 400, e.getMessage());
            return ResponseEntity.badRequest().body(registerResponse);
        } catch (Exception e) {
            RegisterResponse registerResponse = new RegisterResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, 500, "系统异常");
            return ResponseEntity.internalServerError().body(registerResponse);
        }
    }
}
