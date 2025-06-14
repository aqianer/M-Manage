package top.aqianer.manage.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;
    
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(200, "操作成功", data);
    }
    
    public static CommonResult<?> error(Integer code, String message) {
        return new CommonResult<>(code, message, null);
    }
}