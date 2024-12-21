package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import service.CartService;
import service.impl.CartServiceImpl;

/**
 * 购物车的相关操作
 * @author czl 0129
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CartService cartService;
    
    /**
     * 构造方法
     * 初始化CartService
     */
    public CartServlet() {
        this.cartService = new CartServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch(action) {
            case "add":
                addTOCart(request, response);
                break;
            case "changeIn":
                changeIn(request, response);
                break;
            case "delItem":
                delItem(request, response);
                break;
            case "delAll":
                delAll(request, response);
                break;
        }
    }

    /**
     * 清空购物车
     */
    private void delAll(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        Cart shopCart = (Cart) request.getSession().getAttribute("shopCart");
        if (shopCart != null) {
            cartService.clearCart(shopCart);
            request.getSession().removeAttribute("shopCart");
        }
        response.sendRedirect("jsp/tea/cart.jsp");
    }

    /**
     * 购物项删除
     */
    private void delItem(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        int teaId = Integer.parseInt(request.getParameter("id"));
        Cart shopCart = (Cart) request.getSession().getAttribute("shopCart");
        if (shopCart != null) {
            cartService.deleteItem(shopCart, teaId);
        }
        response.sendRedirect("jsp/tea/cart.jsp");
    }

    /**
     * 更改购物项数量
     */
    private void changeIn(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int teaId = Integer.parseInt(request.getParameter("teaId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Cart shopCart = (Cart) request.getSession().getAttribute("shopCart");
        
        String result = cartService.changeQuantity(shopCart, teaId, quantity);
        response.getWriter().print(result);
    }

    /**
     * 添加商品到购物车，并实时显示购物车中商品数量
     */
    private void addTOCart(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String teaId = request.getParameter("teaId");
        Cart shopCart = (Cart) request.getSession().getAttribute("shopCart");
    
        if (shopCart == null) {
            shopCart = new Cart();
            request.getSession().setAttribute("shopCart", shopCart);
        }
        
        int totalQuantity = cartService.addToCart(shopCart, teaId);
        response.getWriter().print(totalQuantity);
    }
}