package service.impl;

import net.sf.json.JSONObject;
import utils.ImageCode;
import service.CodeService;
import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * 验证码服务实现类
 * @author czl 0129
 */
public class CodeServiceImpl implements CodeService {
    
    @Override
    public String generateImageCode(int width, int height, ServletOutputStream outputStream) throws IOException {
        return ImageCode.getImageCode(width, height, outputStream);
    }
    
    @Override
    public String checkCode(String inputCode, String sessionCode) {
        JSONObject json = new JSONObject();
        if (sessionCode.equals(inputCode)) {
            json.put("info", "验证码正确");
            json.put("status", "y");
        } else {
            json.put("info", "验证码输入不正确");
            json.put("status", "n");
        }
        return json.toString();
    }
}