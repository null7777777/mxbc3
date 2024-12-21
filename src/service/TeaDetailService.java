package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 奶茶详情页面服务接口
 * @author czl 0129
 */
public interface TeaDetailService {
    /**
     * 获取奶茶详细信息
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @throws Exception 处理过程中的异常
     */
    void getTeaDetail(HttpServletRequest request, HttpServletResponse response) throws Exception;
}