package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单前台服务接口
 * @author czl 0129
 */
public interface OrderSubService {
    /**
     * 处理订单发货
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @throws Exception 处理过程中的异常
     */
    void orderShip(HttpServletRequest request, HttpServletResponse response) throws Exception;
    
    /**
     * 获取我的订单列表
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @throws Exception 处理过程中的异常
     */
    void myOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception;
    
    /**
     * 提交订单处理
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @throws Exception 处理过程中的异常
     */
    void subOrder(HttpServletRequest request, HttpServletResponse response) throws Exception;
}