package service.impl;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartItem;
import model.Order;
import model.OrderItem;
import model.PageBean;
import model.User;
import dao.TeaDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.impl.TeaDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import service.OrderSubService;
import utils.DateUtil;
import utils.RanUtil;

/**
 * 订单前台服务实现类
 * @author czl 0129
 */
public class OrderSubServiceImpl implements OrderSubService {
    
    private static final int MAX_LIST_SIZE = 5;
    private static final String CART_PATH = "jsp/tea/cart.jsp";
    private static final String ORDER_PAY_PATH = "jsp/tea/ordersuccess.jsp";
    
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final TeaDao teaDao;
    
    /**
     * 构造方法
     * 初始化各个Dao
     */
    public OrderSubServiceImpl() {
        this.orderDao = new OrderDaoImpl();
        this.orderItemDao = new OrderItemDaoImpl();
        this.teaDao = new TeaDaoImpl();
    }
    
    @Override
    public void orderShip(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String orderId = request.getParameter("id");
        
        if(orderDao.orderStatus(Integer.parseInt(orderId), 3)) {
            request.setAttribute("orderMessage", "一个订单操作成功");
        } else {
            request.setAttribute("orderMessage", "一个订单操作失败");
        }
        myOrderList(request, response);
    }
    
    @Override
    public void myOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User)request.getSession().getAttribute("landing");
        if(user == null) {
            response.sendRedirect("jsp/tea/reg.jsp?type=login");
        } else {
            int curPage = 1;
            String page = request.getParameter("page");
            if (page != null) {
                curPage = Integer.parseInt(page);
            }
            PageBean pb = new PageBean(curPage, MAX_LIST_SIZE, orderDao.orderReadCount(user.getUserId()));
            
            List<Order> orderList = orderDao.orderList(pb, user.getUserId());    
            
            for(Order order : orderList) {
                //通过订单编号查询订单项集合
                order.setoItem(orderItemDao.findItemByOrderId(order.getOrderId()));
                for(OrderItem oi : order.getoItem()) {
                    //通过商品id获取商品对象
                    oi.setTea(teaDao.findTeaById(oi.getTeaId()));
                }
            }

            request.setAttribute("pageBean", pb);
            request.setAttribute("orderList", orderList);        
            request.getRequestDispatcher("jsp/tea/myorderlist.jsp").forward(request, response);
        }
    }
    
    @Override
    public void subOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("shopCart");
        User user = (User) session.getAttribute("landing");
        
        String orderNum = RanUtil.getOrderNum(); //生成的订单号
        String orderDate = DateUtil.show(); //生成订单日期
        Order order = new Order();
        
        //给订单对象属性赋值
        order.setOrderNum(orderNum); //订单号
        order.setOrderDate(orderDate); //订单日期
        order.setMoney(cart.getTotPrice()); //订单价格
        order.setOrderStatus(1); //订单状态为1：未付款
        order.setUserId(user.getUserId()); //用户id
        
        if(orderDao.orderAdd(order)) {
            //订单保存成功通过订单号获取订单编号，订单项留用
            order.setOrderId(orderDao.findOrderIdByOrderNum(orderNum));
            
            //保存订单项
            for(Map.Entry<Integer, CartItem> meic : cart.getMap().entrySet()) {
                OrderItem oi = new OrderItem();
                oi.setTeaId(meic.getKey());
                oi.setQuantity(meic.getValue().getQuantity());
                oi.setOrderId(order.getOrderId());
                orderItemDao.orderAdd(oi);
            }
            
            //订单项保存结束清空购物车，返回订单提交成功
            session.removeAttribute("shopCart");
            request.setAttribute("orderNum", order.getOrderNum());
            request.setAttribute("money", order.getMoney());
            request.getRequestDispatcher(ORDER_PAY_PATH).forward(request, response);
        } else {
            request.setAttribute("suberr", "订单提交失败，请重新提交");
            request.getRequestDispatcher(CART_PATH).forward(request, response);
        }
    }
}