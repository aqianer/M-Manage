package top.aqianer.manage.io;

import lombok.Data;

@Data
public class RegisterForm {
    private String email;
    private String verifycode;
    private String registertype;
    private String username;
    private String phone;
    private String password;
    private String principal;
    private String labAddress;
    private String labName;
}
