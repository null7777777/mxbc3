package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;
import model.PageBean;
import service.OrderManageService;
import service.impl.OrderManageServiceImpl;

/**
 * 订单管理
 * @author czl 0129
 */
@WebServlet("/jsp/OrderManageServlet")
public class OrderManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ORDERLIST_PATH = "orderManage/orderlist.jsp";
    private static final String ORDERDETAIL_PATH = "orderManage/orderDetail.jsp";
    private static final String ORDEROP_PATH = "orderManage/orderOp.jsp";
    
    private final OrderManageService orderManageService;
    
    /**
     * 构造方法
     * 初始化OrderManageService
     */
    public OrderManageServlet() {
        this.orderManageService = new OrderManageServiceImpl();
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
                orderList(request, response);
                break;
            case "detail":
                orderDetail(request, response);
                break;
            case "processing":
                orderProcessing(request, response);
                break;
            case "ship":
                orderShip(request, response);
                break;
            case "seach":
                seachOrder(request, response);
                break;
            case "seach1":
                seachOrder1(request, response);
                break;
            case "delete":
                deleteOrder(request, response);
                break;
        }
    }

    /**
     * 删除订单
     */
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String orderid = request.getParameter("id");
        if(orderid != null && !orderid.isEmpty()) {
            orderManageService.deleteOrderItem(Integer.valueOf(orderid));
            orderManageService.deleteOrder(Integer.valueOf(orderid));
        }
        orderList(request, response);
    }

    /**
     * 根据订单名查询订单数
     */
    private void seachOrder(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        String ordername = request.getParameter("ordername");
        PageBean pb;
        
        if(ordername != null && !ordername.isEmpty()) {
            pb = new PageBean(curPage, maxSize, orderManageService.orderReadCount(ordername));
            request.setAttribute("orderList", orderManageService.orderList(pb, ordername));
        } else {
            pb = new PageBean(curPage, maxSize, orderManageService.orderReadCount());
            request.setAttribute("orderList", orderManageService.orderList(pb));
        }
        
        request.setAttribute("pageBean", pb);
        request.getRequestDispatcher(ORDERLIST_PATH).forward(request, response);
    }

    /**
     * 根据订单状态统计订单数
     */
    private void seachOrder1(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        String ordername = request.getParameter("ordername");
        PageBean pb;
        
        if(ordername != null && !ordername.isEmpty()) {
            pb = new PageBean(curPage, maxSize, orderManageService.orderReadCountByStatus(1, ordername));
            request.setAttribute("orderList", orderManageService.orderListByStatus(pb, 1, ordername));
        } else {
            pb = new PageBean(curPage, maxSize, orderManageService.orderReadCountByStatus(1));
            request.setAttribute("orderList", orderManageService.orderListByStatus(pb, 1));
        }
        
        request.setAttribute("pageBean", pb);
        request.getRequestDispatcher(ORDERLIST_PATH).forward(request, response);
    }

    /**
     * 订单列表
     */
    private void orderList(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        PageBean pb = new PageBean(curPage, maxSize, orderManageService.orderReadCount());
        request.setAttribute("orderList", orderManageService.orderList(pb));
        request.setAttribute("pageBean", pb);
        request.getRequestDispatcher(ORDERLIST_PATH).forward(request, response);
    }

    /**
     * 订单详情
     */
    private void orderDetail(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String orderId = request.getParameter("id");
        Order order = orderManageService.findOrderByOrderId(Integer.parseInt(orderId));
        request.setAttribute("orderInfo", order);
        request.getRequestDispatcher(ORDERDETAIL_PATH).forward(request, response);
    }

    /**
     * 处理订单
     */
    private void orderProcessing(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String orderId = request.getParameter("id");
        orderManageService.orderStatus(Integer.parseInt(orderId), 2);  // 假设2是处理中状态
        orderList(request, response);
    }

    /**
     * 订单发货
     */
    private void orderShip(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String orderId = request.getParameter("id");
        orderManageService.orderStatus(Integer.parseInt(orderId), 3);  // 假设3是已发货状态
        orderList(request, response);
    }
}