package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Tea;
import model.PageBean;
import service.TeaManageService;
import service.impl.TeaManageServiceImpl;
import java.util.List;

/**
 * 茶品管理Servlet
 * 处理茶品相关的请求，包括：列表展示、添加、删除、修改等操作
 * @author czl 0129
 */
@WebServlet("/jsp/TeaManageServlet")
public class TeaManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /** 每页显示的记录数 */
    private static final int MAX_LIST_SIZE = 10;
    /** 列表页面路径 */
    private static final String LIST_PATH = "tealist.jsp";
    /** 添加页面路径 */
    private static final String ADD_PATH = "teaadd.jsp";
    /** 更新页面路径 */
    private static final String UPDATE_PATH = "teaupdate.jsp";
    
    /** 茶品管理服务 */
    private final TeaManageService teaManageService;
    
    /**
     * 构造方法
     * 初始化TeaManageService
     */
    public TeaManageServlet() {
        this.teaManageService = new TeaManageServiceImpl();
    }
    
    /**
     * 处理GET请求
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        // 根据action参数执行相应的操作
        if ("list".equals(action)) {
            listTeas(request, response);
        } else if ("add".equals(action)) {
            addTea(request, response);
        } else if ("delete".equals(action)) {
            deleteTea(request, response);
        } else if ("preUpdate".equals(action)) {
            preUpdateTea(request, response);
        } else if ("update".equals(action)) {
            updateTea(request, response);
        } else {
            listTeas(request, response);
        }
    }

    /**
     * 显示茶品列表
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     */
    private void listTeas(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 获取当前页码
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        
        // 创建分页对象并查询数据
        long totalCount = teaManageService.getTeaCount();
        PageBean pageBean = new PageBean(curPage, MAX_LIST_SIZE, totalCount);
        List<Tea> teaList = teaManageService.getTeaList(pageBean);
        
        // 设置属性并跳转到列表页面
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("teaList", teaList);
        request.getRequestDispatcher(LIST_PATH).forward(request, response);
    }
    
    /**
     * 添加茶品
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     */
    private void addTea(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Tea tea = createTeaFromRequest(request);
        
        if (teaManageService.addTea(tea)) {
            listTeas(request, response);
        } else {
            request.setAttribute("message", "添加失败");
            request.getRequestDispatcher(ADD_PATH).forward(request, response);
        }
    }
    
    /**
     * 删除茶品
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     */
    private void deleteTea(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int teaId = Integer.parseInt(request.getParameter("teaId"));
        
        if (teaManageService.deleteTea(teaId)) {
            listTeas(request, response);
        } else {
            request.setAttribute("message", "删除失败");
            listTeas(request, response);
        }
    }
    
    /**
     * 准备更新茶品
     * 查询茶品信息并转发到更新页面
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     */
    private void preUpdateTea(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int teaId = Integer.parseInt(request.getParameter("teaId"));
        Tea tea = teaManageService.getTeaById(teaId);
        
        request.setAttribute("tea", tea);
        request.getRequestDispatcher(UPDATE_PATH).forward(request, response);
    }
    
    /**
     * 更新茶品信息
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     */
    private void updateTea(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Tea tea = createTeaFromRequest(request);
        
        if (teaManageService.updateTea(tea)) {
            listTeas(request, response);
        } else {
            request.setAttribute("message", "更新失败");
            request.getRequestDispatcher(UPDATE_PATH).forward(request, response);
        }
    }
    
    /**
     * 从请求参数中创建茶品对象
     * @param request HTTP请求对象
     * @return 茶品对象
     */
    private Tea createTeaFromRequest(HttpServletRequest request) {
        Tea tea = new Tea();
        String teaId = request.getParameter("teaId");
        if (teaId != null && !teaId.isEmpty()) {
            tea.setTeaId(Integer.parseInt(teaId));
        }
        tea.setTeaName(request.getParameter("teaName"));
        tea.setPrice(Double.parseDouble(request.getParameter("price")));
        tea.setDescription(request.getParameter("description"));
        
        // 设置分类ID
        String catalogId = request.getParameter("catalogId");
        if (catalogId != null && !catalogId.isEmpty()) {
            tea.setCatalogId(Integer.parseInt(catalogId));
        }
        
        // 设置图片ID
        String imgId = request.getParameter("imgId");
        if (imgId != null && !imgId.isEmpty()) {
            tea.setImgId(Integer.parseInt(imgId));
        }
        
        // 设置是否推荐
        String recommend = request.getParameter("recommend");
        if (recommend != null) {
            tea.setRecommend(Boolean.parseBoolean(recommend));
        }
        
        return tea;
    }

    /**
     * 处理POST请求
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}