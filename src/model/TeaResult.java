package model;

/**
 * 茶品操作结果封装类
 * @author czl 0129
 */
public class TeaResult {
    private boolean success;      // 操作是否成功
    private String message;       // 结果信息
    private Object data;         // 携带的数据

    public TeaResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public TeaResult(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // 创建成功结果的静态方法
    public static TeaResult success(String message) {
        return new TeaResult(true, message);
    }

    public static TeaResult success(String message, Object data) {
        return new TeaResult(true, message, data);
    }

    // 创建失败结果的静态方法
    public static TeaResult failure(String message) {
        return new TeaResult(false, message);
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}