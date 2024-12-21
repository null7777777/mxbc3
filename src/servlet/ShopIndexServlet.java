package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ShopIndexService;
import service.impl.ShopIndexServiceImpl;

/**
 * 商店首页
 * @author czl 0129
 */
@WebServlet("/ShopIndex")
public class ShopIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ShopIndexService shopIndexService;
    
    /**
     * 构造方法
     * 初始化ShopIndexService
     */
    public ShopIndexServlet() {
        this.shopIndexService = new ShopIndexServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            shopIndexService.getShopIndexData(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}