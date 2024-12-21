package service.impl;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import model.Order;
import model.PageBean;
import service.OrderManageService;
import java.util.List;

/**
 * 订单管理服务实现类
 * @author czl 0129
 */
public class OrderManageServiceImpl implements OrderManageService {
    
    private final OrderDao orderDao;
    
    /**
     * 构造方法
     * 初始化OrderDao
     */
    public OrderManageServiceImpl() {
        this.orderDao = new OrderDaoImpl();
    }
    
    @Override
    public boolean orderAdd(Order order) {
        return orderDao.orderAdd(order);
    }
    
    @Override
    public int findOrderIdByOrderNum(String orderNum) {
        return orderDao.findOrderIdByOrderNum(orderNum);
    }
    
    @Override
    public long orderReadCount() {
        return orderDao.orderReadCount();
    }
    
    @Override
    public long orderReadCount(String ordernum) {
        return orderDao.orderReadCount(ordernum);
    }
    
    @Override
    public long orderReadCount(int userId) {
        return orderDao.orderReadCount(userId);
    }
    
    @Override
    public long orderReadCountByStatus(int status) {
        return orderDao.orderReadCountByStatus(status);
    }
    
    @Override
    public long orderReadCountByStatus(int status, String ordernum) {
        return orderDao.orderReadCountByStatus(status, ordernum);
    }
    
    @Override
    public List<Order> orderList(PageBean pageBean) {
        return orderDao.orderList(pageBean);
    }
    
    @Override
    public List<Order> orderList(PageBean pageBean, String ordernum) {
        return orderDao.orderList(pageBean, ordernum);
    }
    
    @Override
    public List<Order> orderList(PageBean pageBean, int userId) {
        return orderDao.orderList(pageBean, userId);
    }
    
    @Override
    public List<Order> orderListByStatus(PageBean pb, int status) {
        return orderDao.orderListByStatus(pb, status);
    }
    
    @Override
    public List<Order> orderListByStatus(PageBean pb, int status, String ordernum) {
        return orderDao.orderListByStatus(pb, status, ordernum);
    }
    
    @Override
    public Order findOrderByOrderId(int orderId) {
        return orderDao.findOrderByOrderId(orderId);
    }
    
    @Override
    public boolean orderStatus(int orderId, int status) {
        return orderDao.orderStatus(orderId, status);
    }
    
    @Override
    public boolean deleteOrder(int orderid) {
        return orderDao.deleteOrder(orderid);
    }
    
    @Override
    public boolean deleteOrderItem(int orderid) {
        return orderDao.deleteOrderItem(orderid);
    }
}