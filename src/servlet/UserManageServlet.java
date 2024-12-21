package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import model.PageBean;
import service.UserManageService;
import service.impl.UserManageServiceImpl;
import net.sf.json.JSONObject;

/**
 * 用户管理Servlet
 * 处理用户相关的请求，包括：列表展示、添加、删除、修改等操作
 * @author czl 0129
 */
@WebServlet("/jsp/UserManageServlet")
public class UserManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String USERLIST_PATH = "userManage/userList.jsp";
    private static final String USERADD_PATH = "userManage/userAdd.jsp";
    private static final String USEREDIT_PATH = "userManage/userEdit.jsp";
    private static final String USERDETAIL_PATH = "userManage/userDetail.jsp";
    
    private final UserManageService userManageService;
    
    public UserManageServlet() {
        this.userManageService = new UserManageServiceImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        
        switch(action) {
            case "list":
                userList(request, response);
                break;
            case "add":
                userAdd(request, response);
                break;
            case "update":
                userUpdate(request, response);
                break;
            case "edit":
                userEdit(request, response);
                break;
            case "del":
                userDel(request, response);
                break;
            case "batDel":
                userBatDel(request, response);
                break;
            case "find":
                adminFind(request, response);
                break;
            case "detail":
                detail(request, response);
                break;
            case "seach":
                seachUser(request, response);
                break;
        }
    }

    private void userList(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        PageBean pageBean = new PageBean(curPage, maxSize, userManageService.getUserCount());
        request.setAttribute("userList", userManageService.getUserList(pageBean));
        request.setAttribute("pageBean", pageBean);
        request.getRequestDispatcher(USERLIST_PATH).forward(request, response);
    }

    private void userAdd(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        User user = createUserFromRequest(request);
        if (userManageService.addUser(user)) {
            response.sendRedirect(request.getContextPath() + "/jsp/admin/UserManageServlet?action=list");
        } else {
            request.setAttribute("userMessage", "添加用户失败");
            request.getRequestDispatcher(USERADD_PATH).forward(request, response);
        }
    }

    private void userUpdate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        User user = createUserFromRequest(request);
        if (userManageService.updateUser(user)) {
            response.sendRedirect(request.getContextPath() + "/jsp/admin/UserManageServlet?action=list");
        } else {
            request.setAttribute("userMessage", "更新用户失败");
            request.getRequestDispatcher(USEREDIT_PATH).forward(request, response);
        }
    }

    private void userEdit(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userManageService.getUserById(Integer.parseInt(id));
        request.setAttribute("user", user);
        request.getRequestDispatcher(USEREDIT_PATH).forward(request, response);
    }

    private void userDel(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        JSONObject result = new JSONObject();
        result.put("success", userManageService.deleteUser(id));
        response.getWriter().print(result.toString());
    }

    private void userBatDel(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String ids = request.getParameter("ids");
        JSONObject result = new JSONObject();
        result.put("success", userManageService.batchDeleteUsers(ids));
        response.getWriter().print(result.toString());
    }

    private void adminFind(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // TODO: 实现管理员查找功能
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("userInfo", userManageService.getUserById(Integer.parseInt(id)));
        request.getRequestDispatcher(USERDETAIL_PATH).forward(request, response);
    }

    private void seachUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        String username = request.getParameter("username");
        PageBean pageBean;
        
        if (username != null && !username.isEmpty()) {
            pageBean = new PageBean(curPage, maxSize, userManageService.getUserCount(username));
            request.setAttribute("userList", userManageService.getUserList(pageBean, username));
        } else {
            pageBean = new PageBean(curPage, maxSize, userManageService.getUserCount());
            request.setAttribute("userList", userManageService.getUserList(pageBean));
        }
        
        request.setAttribute("pageBean", pageBean);
        request.getRequestDispatcher(USERLIST_PATH).forward(request, response);
    }

    /**
     * 从请求参数中创建用户对象
     * @param request HTTP请求对象
     * @return 用户对象
     */
    private User createUserFromRequest(HttpServletRequest request) {
        User user = new User();
        
        // 处理ID
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            user.setUserId(Integer.parseInt(id));
        }
        
        // 设置基本信息
        user.setUserName(request.getParameter("userName"));
        user.setUserPassWord(request.getParameter("passWord"));
        user.setName(request.getParameter("name"));
        user.setSex(request.getParameter("sex"));
        
        // 处理年龄
        String age = request.getParameter("age");
        if (age != null && !age.isEmpty()) {
            user.setAge(Integer.parseInt(age));
        }
        
        // 设置其他信息
        user.setTell(request.getParameter("tell"));
        user.setAddress(request.getParameter("address"));
        user.setEnabled(request.getParameter("enabled"));
        
        return user;
    }
}