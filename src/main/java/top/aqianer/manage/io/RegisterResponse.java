package top.aqianer.manage.io;

import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
public class RegisterResponse {
    private HttpStatusCode status;
    private Integer code;
    private String message;

    public RegisterResponse() {
    }

    public RegisterResponse(HttpStatusCode status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
