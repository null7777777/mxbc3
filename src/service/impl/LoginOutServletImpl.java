package service.impl;

import javax.servlet.http.HttpSession;
import service.LoginOutService;

/**
 * 登录服务实现类
 * @author czl 0129
 */
public class LoginOutServletImpl implements LoginOutService {
    
    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("admin_user");
    }
}