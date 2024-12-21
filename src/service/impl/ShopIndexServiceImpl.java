package service.impl;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Tea;
import dao.TeaDao;
import dao.impl.TeaDaoImpl;
import net.sf.json.JSONObject;
import service.ShopIndexService;

/**
 * 商店首页服务实现类
 * @author czl 0129
 */
public class ShopIndexServiceImpl implements ShopIndexService {
    
    private final TeaDao teaDao;
    
    /**
     * 构造方法
     * 初始化TeaDao
     */
    public ShopIndexServiceImpl() {
        this.teaDao = new TeaDaoImpl();
    }
    
    @Override
    public void getShopIndexData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json"); 
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();
        
        List<Tea> recTeas = teaDao.teaList(4);
        json.put("recTeas", recTeas);
        
        List<Tea> newTeas = teaDao.newTeas(4);
        json.put("newTeas", newTeas);
        
        List<String> imgSrc = teaDao.findRecommendTeaImages();
        session.setAttribute("imgSrc", imgSrc);
        
        PrintWriter pw = response.getWriter();
        pw.print(json);
        pw.flush();
    }
}