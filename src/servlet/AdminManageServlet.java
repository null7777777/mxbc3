package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Admin;
import model.PageBean;
import service.AdminService;
import service.impl.AdminServiceImpl;

import net.sf.json.JSONObject;

/**
 * 管理员管理Servlet
 * @author czl 0129
 */
@WebServlet("/jsp/AdminManageServlet")
public class AdminManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ADMINLIST_PATH = "adminManage/adminList.jsp";
    private static final String ADMINADD_PATH = "adminManage/adminAdd.jsp";
    private static final String ADMINEDIT_PATH = "adminManage/adminEdit.jsp";
    
    private final AdminService adminService;
    
    public AdminManageServlet() {
        this.adminService = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch(action) {
            case "list":
                adminList(request, response);
                break;
            case "add":
                adminAdd(request, response);
                break;
            case "update":
                adminUpdate(request, response);
                break;
            case "edit":
                adminEdit(request, response);
                break;
            case "del":
                adminDel(request, response);
                break;
            case "batDel":
                adminBatDel(request, response);
                break;
            case "find":
                adminFind(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    /**
     * 查询用户列表
     */
    private void adminList(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        int curPage = 1;
        String page = request.getParameter("page");
        if(page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        
        PageBean pageBean = new PageBean(curPage, maxSize, adminService.teaReadCount());
        
        request.setAttribute("adminList", adminService.userList(pageBean));
        request.setAttribute("pageBean", pageBean);
        
        request.getRequestDispatcher(ADMINLIST_PATH).forward(request, response);
    }
    
    /**
     * 增加用户
     */
    private void adminAdd(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        Admin admin = new Admin(
            request.getParameter("userName"),
            request.getParameter("passWord"),
            request.getParameter("name")
        );
        
        if(adminService.findUser(admin.getUserName())) {
            request.setAttribute("adminMessage", "用户添加失败！用户名已存在");
            request.getRequestDispatcher(ADMINADD_PATH).forward(request, response);
            return;
        }
        
        if(adminService.userAdd(admin)) {
            request.setAttribute("adminMessage", "用户添加成功！");
            adminList(request, response);
        } else {
            request.setAttribute("adminMessage", "用户添加失败！");
            request.getRequestDispatcher(ADMINADD_PATH).forward(request, response);
        }
    }
    
    /**
     * 更新用户信息
     */
    private void adminUpdate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Admin admin = new Admin(
            Integer.parseInt(request.getParameter("id")),
            request.getParameter("passWord"),
            request.getParameter("name")
        );
        
        if(adminService.userUpdate(admin)) {
            request.setAttribute("adminMessage", "用户更新成功");
            adminList(request, response);
        } else {
            request.setAttribute("adminMessage", "用户更新失败");
            request.setAttribute("adminInfo", adminService.findUser(admin.getId()));
            request.getRequestDispatcher(ADMINEDIT_PATH).forward(request, response);
        }
    }

    /**
     * 修改用户
     */
    private void adminEdit(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("adminInfo", adminService.findUser(Integer.valueOf(id)));
        request.getRequestDispatcher(ADMINEDIT_PATH).forward(request, response);
    }
    
    /**
     * 删除用户
     */
    private void adminDel(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        if(adminService.delUser(id)) {
            request.setAttribute("adminMessage", "用户已删除");
        } else {
            request.setAttribute("adminMessage", "用户删除失败");
        }
        adminList(request, response);
    }
    
    /**
     * 批量删除用户
     */
    private void adminBatDel(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String ids = request.getParameter("ids");
        
        if(adminService.batDelUser(ids)) {
            request.setAttribute("adminMessage", "用户已批量删除");
        } else {
            request.setAttribute("adminMessage", "用户批量删除失败");
        }
        adminList(request, response);
    }
    
    /**
     * Ajax查找用户名是否存在
     */
    private void adminFind(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String userName = request.getParameter("param");
        JSONObject json = new JSONObject();
        
        if(adminService.findUser(userName)) {
            json.put("info", "用户名已存在");
            json.put("status", "n");
        } else {
            json.put("info", "用户名可以使用");
            json.put("status", "y");
        }
        response.getWriter().write(json.toString());
    }
}