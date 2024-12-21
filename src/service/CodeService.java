package service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * 验证码服务接口
 * @author czl 0129
 */
public interface CodeService {
    /**
     * 生成图片验证码
     * @param width 图片宽度
     * @param height 图片高度
     * @param outputStream 输出流
     * @return 生成的验证码字符串
     * @throws IOException IO异常
     */
    String generateImageCode(int width, int height, ServletOutputStream outputStream) throws IOException;

    /**
     * 校验验证码
     * @param inputCode 用户输入的验证码
     * @param sessionCode session中保存的验证码
     * @return 校验结果的JSON字符串
     */
    String checkCode(String inputCode, String sessionCode);
}