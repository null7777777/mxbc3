package service.impl;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import model.Admin;
import model.LoginResult;
import service.LoginService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 * 登录服务实现类
 * @author czl 0129
 */
public class LoginServiceImpl implements LoginService {
    private final AdminDao adminDao;
    
    public LoginServiceImpl() {
        this.adminDao = new AdminDaoImpl();
    }
    
    @Override
    public LoginResult login(String username, String password, HttpSession session) {
        List<String> errorMessages = new ArrayList<>();
        
        // 输入验证
        if (username == null || username.trim().isEmpty()) {
            errorMessages.add("用户名不能为空");
        }
        if (password == null || password.trim().isEmpty()) {
            errorMessages.add("密码不能为空");
        }
        
        // 如果有验证错误，直接返回
        if (!errorMessages.isEmpty()) {
            return new LoginResult(false, errorMessages);
        }
        
        // 验证登录
        Admin admin = new Admin(username, password);
        if (adminDao.userLogin(admin)) {
            // 登录成功，设置会话
            session.setAttribute("adminUser", admin);
            return new LoginResult(true, errorMessages);
        } else {
            // 登录失败
            errorMessages.add("用户名或密码错误!请重新输入");
            return new LoginResult(false, errorMessages);
        }
    }
    
    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("adminUser");
    }
}