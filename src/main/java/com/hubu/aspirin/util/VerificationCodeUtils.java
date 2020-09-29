package com.hubu.aspirin.util;

import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.enums.ExceptionEnum;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author alex
 */
public class VerificationCodeUtils {
    // 在header里的名字
    private static final String HEADER_NAME = "verificationCode";
    // 在session里的key
    private static final String SESSION_KEY = "VERIFICATION_CODE";
    // 随机数对象
    private static final Random RANDOM = new Random();
    // 验证码的宽
    private static final int WIDTH = 165;
    // 验证码的高
    private static final int HEIGHT = 45;
    // 验证码中夹杂的干扰线数量
    private static final int LINE_COUNT = 30;
    // 验证码字符个数
    private static final int CHARACTER_COUNT = 4;
    // 验证码字符集
    private static final String ALL_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWSYZ";

    //字体的设置
    private static Font getFont() {
        return new Font("Times New Roman", Font.ROMAN_BASELINE, 40);
    }

    //颜色的设置
    private static Color getRandomColor(int frontColor, int backgroundColor) {

        frontColor = Math.min(frontColor, 255);
        backgroundColor = Math.min(backgroundColor, 255);

        int r = frontColor + RANDOM.nextInt(backgroundColor - frontColor - 16);
        int g = frontColor + RANDOM.nextInt(backgroundColor - frontColor - 14);
        int b = frontColor + RANDOM.nextInt(backgroundColor - frontColor - 12);

        return new Color(r, g, b);
    }

    // 干扰线的绘制
    private static void drawLine(Graphics g) {
        int x = RANDOM.nextInt(WIDTH);
        int y = RANDOM.nextInt(HEIGHT);
        int xl = RANDOM.nextInt(20);
        int yl = RANDOM.nextInt(10);
        g.drawLine(x, y, x + xl, y + yl);

    }

    // 随机字符的获取
    private static String getRandomString(int num) {
        num = num > 0 ? num : ALL_CHARACTERS.length();
        return String.valueOf(ALL_CHARACTERS.charAt(RANDOM.nextInt(num)));
    }

    // 字符串的绘制
    private static String drawString(Graphics g, String randomStr, int i) {
        g.setFont(getFont());
        g.setColor(getRandomColor(108, 190));
        String rand = getRandomString(RANDOM.nextInt(ALL_CHARACTERS.length()));
        randomStr += rand;
        g.translate(RANDOM.nextInt(3), RANDOM.nextInt(6));
        g.drawString(rand, 40 * i + 10, 25);
        return randomStr;
    }

    // 生成随机图片
    public static void getRandomCodeImage() {
        HttpServletRequest request = ServletUtils.getRequest();
        HttpServletResponse response = ServletUtils.getResponse();
        HttpSession session = request.getSession();
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expire", "0");
        response.setHeader("Pragma", "no-cache");

        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(getRandomColor(105, 189));
        g.setFont(getFont());
        // 干扰线
        for (int i = 0; i < LINE_COUNT; i++) {
            drawLine(g);
        }
        // 随机字符
        String randomStr = "";
        for (int i = 0; i < CHARACTER_COUNT; i++) {
            randomStr = drawString(g, randomStr, i);
        }
        g.dispose();
        // 将验证码放入session
        session.setAttribute(SESSION_KEY, randomStr);
        try {
            // 将图片以png格式返回,返回的是图片
            ImageIO.write(image, "PNG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkCode() {
        String verificationCode = ServletUtils.getHeader(HEADER_NAME);
        if (verificationCode == null) {
            throw new KnownException(ExceptionEnum.VERIFICATION_CODE_MISMATCH);
        }
        HttpSession session = ServletUtils.getSession();
        String sessionCode = String.valueOf(session.getAttribute(SESSION_KEY));
        if (sessionCode == null) {
            throw new KnownException(ExceptionEnum.VERIFICATION_CODE_MISMATCH);
        }
        // toLowerCase() 不区分大小写进行验证码校验
        String rightCode = sessionCode.toLowerCase();
        String receivedCode = verificationCode.toLowerCase();
        boolean isVerified = StringUtils.isNotEmpty(rightCode) && StringUtils.isNotEmpty(receivedCode) && rightCode.equals(receivedCode);
        if (!isVerified) {
            throw new KnownException(ExceptionEnum.VERIFICATION_CODE_MISMATCH);
        }
        // 校验成功就清除保存的验证码
        session.removeAttribute(SESSION_KEY);
    }
}




