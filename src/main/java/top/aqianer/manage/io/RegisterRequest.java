package top.aqianer.manage.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String verifycode;
    private String registertype;
    private String username;
    private String phonenumber;
    private String pwd;
    private LabInfo labinfo;

    @JsonProperty("phonenumber")
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * 嵌套的实验室信息类
     */
    @Data
    public static class LabInfo {
        private String labname;
        private String labadmin;
        private String lablocation;
    }
}
