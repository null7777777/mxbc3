package model;

import java.util.List;

/**
 * 登录结果数据类
 * @author czl 0129
 */
public class LoginResult {
    private final boolean success;
    private final List<String> errorMessages;
    
    public LoginResult(boolean success, List<String> errorMessages) {
        this.success = success;
        this.errorMessages = errorMessages;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public List<String> getErrorMessages() {
        return errorMessages;
    }
}