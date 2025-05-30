package top.aqianer.manage.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import top.aqianer.manage.service.EmailService;


@Service
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendVerificationCode(String destEmail, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(destEmail);
            helper.setSubject("【实验室管理】您的验证码已就绪");
            String htmlContent = "<div style='max-width: 600px; margin: 0 auto; font-family: -apple-system, BlinkMacSystemFont, sans-serif;'>"
                    + "<div style='text-align: center; margin-bottom: 24px;'>"
                    + "<h1 style='color: #24292f; font-size: 24px;'>实验室管理系统</h1></div>"
                    + "<div style='background: #f6f8fa; padding: 32px; border-radius: 6px;'>"
                    + "<p style='color: #24292f; margin-bottom: 16px;'>您好！</p>"
                    + "<p style='color: #57606a;'>您正在尝试进行账户验证，以下是您的验证码：</p>"
                    + "<div style='background: #ffffff; padding: 24px; margin: 24px 0; border-radius: 4px; border: 1px solid #d0d7de; text-align: center;'>"
                    + "<code style='font-size: 32px; letter-spacing: 8px; color: #0969da; font-weight: 600;'>" + code + "</code></div>"
                    + "<p style='color: #57606a; font-size: 14px;'>该验证码将在 <strong>5分钟</strong> 后失效</p>"
                    + "<hr style='border-color: #d0d7de; margin: 24px 0;'>"
                    + "<p style='color: #57606a; font-size: 12px;'>请勿将验证码透露给他人。如非本人操作，请忽略本邮件。</p></div>"
                    + "<div style='text-align: center; margin-top: 32px; color: #57606a; font-size: 12px;'>"
                    + "<p>© 2024 实验室管理系统</p></div></div>";

            helper.setText(htmlContent, true); // 关键参数：true表示发送HTML格式

            mailSender.send(message); // 保持原调用方式
        } catch (MessagingException e) {
            throw new RuntimeException("邮件发送失败", e);
        }
    }
}
