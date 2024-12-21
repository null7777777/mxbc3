package service;

import java.util.List;
import model.Order;
import model.PageBean;

/**
 * 订单管理服务接口
 * @author czl 0129
 */
public interface OrderManageService {
    /**
     * 增加一个订单记录
     * @param order 订单对象
     * @return 是否添加成功
     */
    boolean orderAdd(Order order);

    /**
     * 查找订单编号通过订单号
     * @param orderNum 订单号
     * @return 订单编号
     */
    int findOrderIdByOrderNum(String orderNum);

    /**
     * 统计总订单数
     * @return 订单总数
     */
    long orderReadCount();

    /**
     * 根据订单号统计总订单数
     * @param ordernum 订单号
     * @return 订单总数
     */
    long orderReadCount(String ordernum);

    /**
     * 统计总订单数(by userId)
     * @param userId 用户ID
     * @return 订单总数
     */
    long orderReadCount(int userId);

    /**
     * 统计总订单数(by orderStatus)
     * @param status 订单状态
     * @return 订单总数
     */
    long orderReadCountByStatus(int status);

    /**
     * 统计总订单数(by orderStatus and ordernum)
     * @param status 订单状态
     * @param ordernum 订单号
     * @return 订单总数
     */
    long orderReadCountByStatus(int status, String ordernum);

    /**
     * 获得订单列表（分页）,条件用户id
     * @param pageBean 分页对象
     * @return 订单列表
     */
    List<Order> orderList(PageBean pageBean);

    /**
     * 获得订单列表（分页）,条件订单号
     * @param pageBean 分页对象
     * @param ordernum 订单号
     * @return 订单列表
     */
    List<Order> orderList(PageBean pageBean, String ordernum);

    /**
     * 获得订单列表（分页）,条件用户id
     * @param pageBean 分页对象
     * @param userId 用户ID
     * @return 订单列表
     */
    List<Order> orderList(PageBean pageBean, int userId);

    /**
     * 获得订单列表（分页）,条件orderStatus
     * @param pb 分页对象
     * @param status 订单状态
     * @return 订单列表
     */
    List<Order> orderListByStatus(PageBean pb, int status);

    /**
     * 获得订单列表（分页）,条件orderStatus ordernum
     * @param pb 分页对象
     * @param status 订单状态
     * @param ordernum 订单号
     * @return 订单列表
     */
    List<Order> orderListByStatus(PageBean pb, int status, String ordernum);

    /**
     * 查找订单编号通过订单号
     * @param orderId 订单ID
     * @return 订单对象
     */
    Order findOrderByOrderId(int orderId);

    /**
     * 更改订单状态
     * @param orderId 订单ID
     * @param status 订单状态
     * @return 是否更新成功
     */
    boolean orderStatus(int orderId, int status);

    /**
     * 删除订单
     * @param orderid 订单ID
     * @return 是否删除成功
     */
    boolean deleteOrder(int orderid);

    /**
     * 删除订单项
     * @param orderid 订单ID
     * @return 是否删除成功
     */
    boolean deleteOrderItem(int orderid);
}