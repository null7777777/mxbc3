package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.TeaDetailService;
import service.impl.TeaDetailServiceImpl;

/**
 * 奶茶的详情页面
 * @author czl 0129
 */
@WebServlet("/teadetail")
public class TeaDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final TeaDetailService teaDetailService;
    
    /**
     * 构造方法
     * 初始化TeaDetailService
     */
    public TeaDetailServlet() {
        this.teaDetailService = new TeaDetailServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            teaDetailService.getTeaDetail(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}