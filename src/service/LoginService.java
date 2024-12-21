package service;

import model.LoginResult;
import javax.servlet.http.HttpSession;

/**
 * 登录服务接口
 * @author czl 0129
 */
public interface LoginService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @param session HTTP会话
     * @return 登录验证结果
     */
    LoginResult login(String username, String password, HttpSession session);
    
    /**
     * 用户退出登录
     * @param session HTTP会话
     */
    void logout(HttpSession session);
}