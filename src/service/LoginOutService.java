package service;

import javax.servlet.http.HttpSession;

/**
 * 登录服务接口
 * @author czl 0129
 */
public interface LoginOutService {
    /**
     * 用户退出登录
     * @param session HTTP会话
     */
    void logout(HttpSession session);
}