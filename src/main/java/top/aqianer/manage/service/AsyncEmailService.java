package top.aqianer.manage.service;

public interface AsyncEmailService {

    void sendAsync(String destEmail);

    boolean verifyEmailCode(String email, String emailcode);

}
