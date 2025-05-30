package top.aqianer.manage.exception;

public class BusinessException extends RuntimeException {
    // 新增单参数构造方法
    public BusinessException(String message) {
        super(message);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}