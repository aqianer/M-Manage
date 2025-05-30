package top.aqianer.manage.service;

public interface EmailService {
    void sendVerificationCode(String destEmail, String code);
}
