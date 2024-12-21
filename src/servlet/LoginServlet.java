package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LoginService;
import service.impl.LoginServiceImpl;
import model.LoginResult;

/**
 * 用户登录Servlet
 * @author czl 0129
 */
@WebServlet("/jsp/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String MAIN_PATH = "index.jsp";
    private static final String LOGIN_PATH = "login.jsp";
    
    private final LoginService loginService;
    
    public LoginServlet() {
        this.loginService = new LoginServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("userName");
        String password = request.getParameter("passWord");
        
        // 调用service层处理登录
        LoginResult result = loginService.login(username, password, request.getSession());
        
        if (result.isSuccess()) {
            // 登录成功，重定向到主页
            response.sendRedirect(MAIN_PATH);
        } else {
            // 登录失败，转发回登录页面并显示错误信息
            request.setAttribute("infoList", result.getErrorMessages());
            request.getRequestDispatcher(LOGIN_PATH).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}