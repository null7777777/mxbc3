package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.TeaListService;
import service.impl.TeaListServiceImpl;

/**
 * 奶茶的列表，以分类名形式显示
 * @author czl 0129
 */
@WebServlet("/TeaList2")
public class TeaList2Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final TeaListService teaListService;
    
    /**
     * 构造方法
     * 初始化TeaListService
     */
    public TeaList2Servlet() {
        this.teaListService = new TeaListServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String searchName = new String(request.getParameter("seachname").getBytes("iso-8859-1"), "utf-8");
            String searchName1 = request.getParameter("seachname");
            teaListService.getTeaListByName(request, response, searchName1);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}