package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商店首页服务接口
 * @author czl 0129
 */
public interface ShopIndexService {
    /**
     * 获取商店首页数据
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @throws Exception 处理过程中的异常
     */
    void getShopIndexData(HttpServletRequest request, HttpServletResponse response) throws Exception;
}