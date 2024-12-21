package service;

import model.Cart;
import model.Tea;

/**
 * 购物车服务接口
 * @author czl 0129
 */
public interface CartService {
    /**
     * 添加商品到购物车
     * @param cart 购物车对象
     * @param teaId 商品ID
     * @return 购物车中商品总数量
     */
    int addToCart(Cart cart, String teaId);
    
    /**
     * 更改购物车中商品数量
     * @param cart 购物车对象
     * @param teaId 商品ID
     * @param quantity 数量
     * @return 包含更新后数据的JSON字符串
     */
    String changeQuantity(Cart cart, int teaId, int quantity);
    
    /**
     * 从购物车中删除商品项
     * @param cart 购物车对象
     * @param teaId 商品ID
     */
    void deleteItem(Cart cart, int teaId);
    
    /**
     * 清空购物车
     * @param cart 购物车对象
     */
    void clearCart(Cart cart);
}