package service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.TeaDao;
import dao.impl.TeaDaoImpl;
import service.TeaDetailService;

/**
 * 奶茶详情页面服务实现类
 * @author czl 0129
 */
public class TeaDetailServiceImpl implements TeaDetailService {
    
    private static final String DETAIL_PATH = "jsp/tea/teadetails.jsp";
    private final TeaDao teaDao;
    
    /**
     * 构造方法
     * 初始化TeaDao
     */
    public TeaDetailServiceImpl() {
        this.teaDao = new TeaDaoImpl();
    }
    
    @Override
    public void getTeaDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int teaId = Integer.parseInt(request.getParameter("teaId"));
        request.setAttribute("teaInfo", teaDao.findTeaById(teaId));
        request.getRequestDispatcher(DETAIL_PATH).forward(request, response);
    }
}