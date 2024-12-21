package service.impl;

import dao.TeaDao;
import dao.impl.TeaDaoImpl;
import model.Cart;
import model.CartItem;
import model.Tea;
import net.sf.json.JSONObject;
import service.CartService;

/**
 * 购物车服务实现类
 * @author czl 0129
 */
public class CartServiceImpl implements CartService {
    
    private final TeaDao teaDao;
    
    /**
     * 构造方法
     * 初始化TeaDao
     */
    public CartServiceImpl() {
        this.teaDao = new TeaDaoImpl();
    }
    
    @Override
    public int addToCart(Cart cart, String teaId) {
        Tea tea = teaDao.findTeaById(Integer.parseInt(teaId));
        cart.addTea(tea);
        return cart.getTotQuan();
    }
    
    @Override
    public String changeQuantity(Cart cart, int teaId, int quantity) {
        CartItem item = cart.getMap().get(teaId);
        item.setQuantity(quantity);
        
        JSONObject json = new JSONObject();
        json.put("subtotal", item.getSubtotal());
        json.put("totPrice", cart.getTotPrice());
        json.put("totQuan", cart.getTotQuan());
        json.put("quantity", item.getQuantity());
        
        return json.toString();
    }
    
    @Override
    public void deleteItem(Cart cart, int teaId) {
        if (cart.getMap().containsKey(teaId)) {
            cart.getMap().remove(teaId);
        }
    }
    
    @Override
    public void clearCart(Cart cart) {
        cart.getMap().clear();
    }
}