package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单实体类
 * @author czl 0129
 */
public class Order {
    /**
     * 订单编号
     */
    private int orderId;
    
    /**
     * 订单号
     */
    private String orderNum;
    
    /**
     * 用户编号
     */
    private int userId;
    
    /**
     * 订单日期
     */
    private String orderDate;
    
    /**
     * 订单金额
     */
    private double money;
    
    /**
     * 订单状态
     */
    private int orderStatus;
    
    /**
     * 订单项列表
     */
    private List<OrderItem> oItem = new ArrayList<>();
    
    /**
     * 订单所属用户
     */
    private User user = new User();

    /**
     * 默认构造方法
     */
    public Order() {
    }

    /**
     * 根据Map构造订单对象
     * @param map 包含订单信息的Map对象
     */
    public Order(Map<String, Object> map) {
        this.setOrderId((int) map.get("orderId"));
        this.setOrderNum((String) map.get("orderNum"));
        this.setUserId((int) map.get("userId"));
        this.setOrderDate((String) map.get("orderDate"));
        this.setMoney((double) map.get("money"));
        this.setOrderStatus((int) map.get("orderStatus"));
    }

    /**
     * 获取订单编号
     * @return 订单编号
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * 设置订单编号
     * @param orderId 订单编号
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单号
     * @return 订单号
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * 设置订单号
     * @param orderNum 订单号
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取用户编号
     * @return 用户编号
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     * @param userId 用户编号
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 获取订单日期
     * @return 订单日期
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * 设置订单日期
     * @param orderDate 订单日期
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 获取订单状态
     * @return 订单状态
     */
    public int getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态
     * @param orderStatus 订单状态
     */
    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取订单项列表
     * @return 订单项列表
     */
    public List<OrderItem> getoItem() {
        return oItem;
    }

    /**
     * 设置订单项列表
     * @param oItem 订单项列表
     */
    public void setoItem(List<OrderItem> oItem) {
        this.oItem = oItem;
    }

    /**
     * 获取订单金额
     * @return 订单金额
     */
    public double getMoney() {
        return money;
    }

    /**
     * 设置订单金额
     * @param money 订单金额
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * 获取订单所属用户
     * @return 用户对象
     */
    public User getUser() {
        return user;
    }

    /**
     * 设置订单所属用户
     * @param user 用户对象
     */
    public void setUser(User user) {
        this.user = user;
    }
}