package top.aqianer.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.aqianer.manage.exception.BusinessException;
import top.aqianer.manage.io.RegisterForm;
import top.aqianer.manage.io.RegisterRequest;
import top.aqianer.manage.io.RegisterResponse;
import top.aqianer.manage.service.AsyncEmailService;
import top.aqianer.manage.service.UserService;

import java.util.Map;

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

//    @PostMapping("/api/register/v1")
//    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest registerRequest) {
//        try {
//            System.out.println(registerRequest);
//            if (!asyncEmailServiceImpl.verifyEmailCode(registerRequest.getEmail(), registerRequest.getVerifycode())) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(Map.of("code", 400, "message", "验证码错误"));
//            }
//            userServiceImpl.registerUser(registerRequest);
//            return ResponseEntity.ok(Map.of("code", 200, "message", "注册成功"));
//        } catch (BusinessException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(Map.of("code", 400, "message", e.getMessage()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("code", 500, "message", "系统异常"));
//        }
//    }

    @PostMapping("/api/register/v1")
    public String register(@ModelAttribute RegisterForm form, RedirectAttributes redirectAttributes) {
        try {
            RegisterResponse registerResponse = new RegisterResponse();
            System.out.println(form);
            if (!asyncEmailServiceImpl.verifyEmailCode(form.getEmail(), form.getVerifycode())) {
                // TODO : 根据返回的对象报错提示
                registerResponse.setStatus(HttpStatus.BAD_REQUEST);
                registerResponse.setCode(400);
                registerResponse.setMessage("验证码错误");
            }else {
                userServiceImpl.register(form);
                registerResponse.setStatus(HttpStatus.OK);
                registerResponse.setCode(200);
                registerResponse.setMessage("注册成功");
            }
            redirectAttributes.addFlashAttribute("registerResponse",registerResponse);
            return "redirect:/register";
        } catch (BusinessException e) {
            RegisterResponse registerResponse = new RegisterResponse(HttpStatus.BAD_REQUEST,400,e.getMessage());
            redirectAttributes.addFlashAttribute("registerResponse",registerResponse);
            return "redirect:/register";
        } catch (Exception e) {
            RegisterResponse registerResponse = new RegisterResponse(HttpStatus.INTERNAL_SERVER_ERROR,500,"系统异常");
            redirectAttributes.addFlashAttribute("registerResponse",registerResponse);
            return "redirect:/register";
        }
    }
}
