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
 * 奶茶的列表
 * @author czl 0129
 */
@WebServlet("/TeaList")
public class TeaListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final TeaListService teaListService;
    
    /**
     * 构造方法
     * 初始化TeaListService
     */
    public TeaListServlet() {
        this.teaListService = new TeaListServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if(action == null) {
                action = "list";
            }
            switch(action) {
                case "d":
                    break;
                case "list":
                    teaListService.getTeaList(request, response);
                    break;
            }
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