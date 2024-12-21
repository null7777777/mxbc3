package service;

import model.Catalog;
import model.PageBean;
import java.util.List;

/**
 * 商品分类服务接口
 * @author czl 0129
 */
public interface CatalogService {
    /**
     * 获取商品分类信息
     * @return 分类列表
     */
    List<Catalog> getCatalog();

    /**
     * 获取商品分类信息（分页）
     * @param pb 分页参数
     * @return 分页后的分类列表
     */
    List<Catalog> catalogList(PageBean pb);

    /**
     * 统计分类总数
     * @return 分类总数
     */
    long catalogReadCount();

    /**
     * 分类删除
     * @param catalogId 分类ID
     * @return 是否删除成功
     */
    boolean catalogDel(int catalogId);

    /**
     * 分类批量删除
     * @param ids 分类ID串
     * @return 是否删除成功
     */
    boolean catalogBatDelById(String ids);

    /**
     * 查找分类名称是否存在
     * @param catalogName 分类名称
     * @return 是否存在
     */
    boolean findCatalogByCatalogName(String catalogName);

    /**
     * 增加分类
     * @param catalogName 分类名称
     * @return 是否添加成功
     */
    boolean catalogAdd(String catalogName);
}