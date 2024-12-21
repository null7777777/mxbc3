package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PageBean;
import service.CatalogService;
import service.impl.CatalogServiceImpl;

import net.sf.json.JSONObject;

/**
 * 商品分类servlet
 * @author czl 0129
 */
@WebServlet("/jsp/CatalogServlet")
public class CatalogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CATALOGLIST_PATH = "teaManage/catalogList.jsp";
    private static final String CATALOGADD_PATH = "teaManage/catalogAdd.jsp";
    
    private final CatalogService catalogService;
    
    public CatalogServlet() {
        this.catalogService = new CatalogServiceImpl();
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
        switch(action) {
            case "list":
                catalogList(request, response);
                break;
            case "add":
                catalogAdd(request, response);
                break;
            case "del":
                catalogDel(request, response);
                break;
            case "batDel":
                catalogBatDel(request, response);
                break;
            case "find":
                catalogFind(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }
    }

    /**
     * 商品分类的批量删除
     */
    private void catalogBatDel(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String ids = request.getParameter("ids");

        if(catalogService.catalogBatDelById(ids)) {
            request.setAttribute("teaMessage", "分类已批量删除");
        } else {
            request.setAttribute("teaMessage", "分类删除失败");
        }
        catalogList(request, response);
    }

    /**
     * 商品分类的删除
     */
    private void catalogDel(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int catalogId = Integer.parseInt(request.getParameter("id"));
        
        if(catalogService.catalogDel(catalogId)) {
            request.setAttribute("catalogMessage", "该分类已删除");
        } else {
            request.setAttribute("catalogMessage", "该分类删除失败");
        }
        catalogList(request, response);
    }

    /**
     * 商品分类的添加
     */
    private void catalogAdd(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String catalogName = request.getParameter("catalogName");
        
        if(catalogService.catalogAdd(catalogName)) {
            request.setAttribute("catalogMessage", "增加分类成功");
            catalogList(request, response);
        } else {
            request.setAttribute("catalogMessage", "增加分类失败");
            request.getRequestDispatcher(CATALOGADD_PATH).forward(request, response);
        }
    }

    /**
     * 获取分类列表
     */
    private void catalogList(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int curPage = 1;
        String page = request.getParameter("page");
        if(page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        
        PageBean pb = new PageBean(curPage, maxSize, catalogService.catalogReadCount());
        
        request.setAttribute("pageBean", pb);
        request.setAttribute("catalogList", catalogService.catalogList(pb));
        request.getRequestDispatcher(CATALOGLIST_PATH).forward(request, response);
    }

    /**
     * ajax查找商品分类是否存在
     */
    private void catalogFind(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String catalogName = request.getParameter("param");
        JSONObject json = new JSONObject();
        
        if(catalogService.findCatalogByCatalogName(catalogName)) {
            json.put("info", "该分类已存在");
            json.put("status", "n");
        } else {
            json.put("info", "输入正确");
            json.put("status", "y");
        }
        response.getWriter().write(json.toString());
    }
}