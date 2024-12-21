package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.OrderSubService;
import service.impl.OrderSubServiceImpl;

/**
 * 订单前台的一些请求
 * @author czl 0129
 */
@WebServlet(name = "OrderServlet", urlPatterns = { "/OrderServlet" })
public class OrderSubServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final OrderSubService orderSubService;
    
    /**
     * 构造方法
     * 初始化OrderSubService
     */
    public OrderSubServlet() {
        this.orderSubService = new OrderSubServiceImpl();
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
            String action = request.getParameter("action");
            switch(action) {
                case "subOrder":
                    orderSubService.subOrder(request, response);
                    break;
                case "list":
                    orderSubService.myOrderList(request, response);
                    break;
                case "ship":
                    orderSubService.orderShip(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}