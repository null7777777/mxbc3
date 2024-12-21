package service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Tea;
import model.PageBean;
import dao.TeaDao;
import dao.impl.TeaDaoImpl;
import service.TeaListService;

/**
 * 奶茶列表服务实现类
 * @author czl 0129
 */
public class TeaListServiceImpl implements TeaListService {
    
    private static final int MAX_LIST_SIZE = 12;
    private static final String TEALIST_PATH = "jsp/tea/tealist.jsp";
    private final TeaDao teaDao;
    
    /**
     * 构造方法
     * 初始化TeaDao
     */
    public TeaListServiceImpl() {
        this.teaDao = new TeaDaoImpl();
    }
    
    /**
     * 获取奶茶列表信息
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @throws Exception 处理过程中的异常
     */
    @Override
    public void getTeaList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        
        PageBean pb;
        List<Tea> teaList = new ArrayList<>();
        String catalogIdStr = request.getParameter("catalogId");
        
        if(catalogIdStr != null) {
            int catalogId = Integer.parseInt(catalogIdStr);
            pb = new PageBean(curPage, MAX_LIST_SIZE, teaDao.teaReadCount(catalogId));
            teaList = teaDao.teaList(pb, catalogId);
            
            if(!teaList.isEmpty()) {
                request.setAttribute("title", teaList.get(0).getCatalog().getCatalogName());
            }
        } else {
            pb = new PageBean(curPage, MAX_LIST_SIZE, teaDao.teaReadCount());
            teaList = teaDao.teaList(pb);
            request.setAttribute("title", "所有商品");
        }

        request.setAttribute("pageBean", pb);
        request.setAttribute("teaList", teaList);
        request.getRequestDispatcher(TEALIST_PATH).forward(request, response);
    }
    
    /**
     * 根据搜索名称获取奶茶列表
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param searchName 搜索名称
     * @throws Exception 处理过程中的异常
     */
    @Override
    public void getTeaListByName(HttpServletRequest request, HttpServletResponse response, String searchName) throws Exception {
        int curPage = 1;
        String page = request.getParameter("page");
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        
        PageBean pb;
        List<Tea> teaList = new ArrayList<>();
        
        if(searchName == null || searchName.isEmpty()) {
            pb = new PageBean(curPage, MAX_LIST_SIZE, teaDao.teaReadCount());
            teaList = teaDao.teaList(pb);
        } else {
            pb = new PageBean(curPage, MAX_LIST_SIZE, teaDao.teaReadCount(searchName));
            teaList = teaDao.teaList(pb, searchName);
        }
        
        request.setAttribute("title", "所有商品");
        request.setAttribute("pageBean", pb);
        request.setAttribute("teaList", teaList);
        request.getRequestDispatcher(TEALIST_PATH).forward(request, response);
    }
}