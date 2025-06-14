package top.aqianer.manage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        model.addAttribute("registrationForm", new RegisterForm());
        model.addAttribute("registerResponse", new RegisterResponse());
        return "register";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) authentication.getPrincipal();
        // 添加用户信息和未读消息数
        model.addAttribute("username", details.getUsername());
        return "home";
    }
}
