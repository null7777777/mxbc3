package service;

/**
 * 商品分类服务接口
 * @author czl 0129
 */
public interface GetCatalogService {
    /**
     * 获取所有分类信息及每个分类的商品数量
     * @return 包含分类信息的JSON字符串
     */
    String getCatalogWithSize();
}