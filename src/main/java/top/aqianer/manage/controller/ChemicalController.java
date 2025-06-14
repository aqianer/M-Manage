package top.aqianer.manage.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.aqianer.manage.model.Drug;
import top.aqianer.manage.service.ChemicalService;
import top.aqianer.manage.service.UserService;
import top.aqianer.manage.vo.Chemical;

import java.util.List;

@Controller
@RequestMapping("/chemicals")
public class ChemicalController {

    @Autowired
    ChemicalService chemicalServiceImpl;

    @Autowired
    UserService userServiceImpl;

    @GetMapping("/list")
    public ResponseEntity<?> list(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 添加未认证检查
        if (authentication instanceof AnonymousAuthenticationToken) {
            System.out.println("===============================");
            return ResponseEntity.badRequest().body("认证失败");
        }
        String email = authentication.getName();
        Long labId = userServiceImpl.getLabIdByEmail(email);
        List<Chemical> chemicals = null;
        if (labId != null) {
            chemicals = chemicalServiceImpl.findAll(labId);
        }
        return ResponseEntity.ok(chemicals);
    }


    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> add(@Valid @RequestBody Drug drug, BindingResult result) {
        System.out.println("===============================");
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        try {
            Drug saved = chemicalServiceImpl.save(drug);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器错误");
        }
    }


}