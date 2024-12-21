package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CodeService;
import service.impl.CodeServiceImpl;

/**
 * 验证码的生成
 * @author czl 0129
 */
@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CodeService codeService;
    
    /**
     * 构造方法
     * 初始化CodeService
     */
    public CodeServlet() {
        this.codeService = new CodeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("code".equals(action)) {
            getCode(request, response);
        }
        if ("ckCode".equals(action)) {
            ckCode(request, response);
        }
    }

    /**
     * 根据session保存的验证码进行校验
     */
    private void ckCode(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String code = request.getParameter("param");
        String ck_code = (String) request.getSession().getAttribute("checkCode");
        String result = codeService.checkCode(code, ck_code);
        response.getWriter().write(result);
    }

    /**
     * 生成验证码，并保存到会话的属性中
     */
    private void getCode(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        // 设置浏览器不缓存验证码图片
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ServletOutputStream outputStream = response.getOutputStream();
        String rands = codeService.generateImageCode(70, 30, outputStream);
        //将生成的随机四个字符保存在session范围checkCode属性
        request.getSession().setAttribute("checkCode", rands);
        
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}