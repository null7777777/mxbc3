package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LoginOutService;
import service.impl.LoginOutServletImpl;

/**
 * 注销登录Servlet
 * @author czl 0129
 */
@WebServlet("/LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String LOGIN_PATH = "jsp/admin/login.jsp";
    
    private final LoginOutService loginService;
    
    public LoginOutServlet() {
        this.loginService = new LoginOutServletImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 调用service层处理退出登录逻辑
        loginService.logout(request.getSession());
        // 重定向到登录页面
        response.sendRedirect(LOGIN_PATH);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}