package top.aqianer.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.aqianer.manage.io.RegisterForm;
import top.aqianer.manage.io.RegisterResponse;

@Controller
public class PageController {

    @RequestMapping("/login")
    public String login() {
        return "login"; // 返回Thymeleaf模板名称
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        model.addAttribute("registerResponse",new RegisterResponse());
        return "register";
    }
}
