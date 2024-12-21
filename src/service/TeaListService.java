package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 奶茶列表服务接口
 * @author czl 0129
 */
public interface TeaListService {
    /**
     * 获取奶茶列表信息
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @throws Exception 处理过程中的异常
     */
    void getTeaList(HttpServletRequest request, HttpServletResponse response) throws Exception;
    
    /**
     * 根据搜索名称获取奶茶列表
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param searchName 搜索名称
     * @throws Exception 处理过程中的异常
     */
    void getTeaListByName(HttpServletRequest request, HttpServletResponse response, String searchName) throws Exception;
}