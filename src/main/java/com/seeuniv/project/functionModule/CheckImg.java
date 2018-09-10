package com.seeuniv.project.functionModule;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class CheckImg {

    private static final String checkWord = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";


    /**
     * 验证码显示的颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    private static Color getRandomColor(int fc, int bc) {
        Random random = new Random();
        Color randomColor = null;
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        randomColor = new Color(r, g, b);
        return randomColor;
    }


    /**
     * 在浏览器上显示图片
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public static void drawImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        /***********************************************************************************************************/
        int width = 150, height = 50;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandomColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        g.setColor(getRandomColor(160, 200));
        for (int i = 0; i < 130; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        //验证码的字符串
        String strCode = "";
        for (int i = 0; i < 5; i++) {
            char word = checkWord.charAt(random.nextInt(62));
            strCode = strCode + word;
            g.setColor(new Color(15 + random.nextInt(120), 15 + random.nextInt(120), 15 + random.nextInt(120)));
            g.drawString(Character.toString(word), 25 * i + 15, 35);
        }
        request.getSession().setAttribute("checkCode", strCode);
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
