package service;

import model.Tea;
import model.PageBean;
import java.util.List;

/**
 * 茶品管理服务接口
 * 提供茶品的增删改查功能
 * @author czl 0129
 */
public interface TeaManageService {
    /**
     * 获取茶品列表
     * @param pageBean 分页参数对象
     * @return 茶品列表
     */
    List<Tea> getTeaList(PageBean pageBean);
    
    /**
     * 新增茶品
     * @param tea 茶品信息
     * @return 添加是否成功
     */
    boolean addTea(Tea tea);
    
    /**
     * 更新茶品
     * @param tea 茶品信息
     * @return 更新是否成功
     */
    boolean updateTea(Tea tea);
    
    /**
     * 删除茶品
     * @param teaId 茶品ID
     * @return 删除是否成功
     */
    boolean deleteTea(int teaId);
    
    /**
     * 根据ID查询茶品
     * @param teaId 茶品ID
     * @return 茶品信息
     */
    Tea getTeaById(int teaId);
    
    /**
     * 获取茶品总数
     * @return 茶品总数
     */
    long getTeaCount();
}