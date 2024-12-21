package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.GetCatalogService;
import service.impl.GetCatalogServiceImpl;

/**
 * 获取分类项
 * @author czl 0129
 */
@WebServlet("/GetCatalog")
public class GetCatalogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final GetCatalogService getCatalogService;
    
    /**
     * 构造方法
     * 初始化GetCatalogService
     */
    public GetCatalogServlet() {
        this.getCatalogService = new GetCatalogServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/json"); 
        String result = getCatalogService.getCatalogWithSize();
        response.getWriter().append(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}