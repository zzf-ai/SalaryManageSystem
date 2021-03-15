package com.zzf.Util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/*
 *
 *@author:zzf
 *@time:2020-12-14
 *
 */
public class ValidateCode {
    public static final String VALIDATECODE="validateCode";
    private Random random=new Random();
    //随机字符串
    private String randString="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    //图片宽高
    private int width=80;
    private int height=26;
    //干扰线
    private int lineSize=40;
    //字符数量
    private int stringNum=4;



    //输出图片
    public void getRandomCode(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        //具有缓冲的Image类，描述图像信息
        BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        //绘制图像的Graph对象
        Graphics g=image.getGraphics();
        g.fillRect(0,0,width,height);
        g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
        g.setColor(getRandomColor(160,200));
        //绘制干扰线
        for (int i=0;i<=lineSize;i++){
            drowLine(g);
        }
        //绘制随机字符串
        String randomString="";
        for (int i=1;i<=stringNum;i++){
            randomString=picString(g,randomString,i);
        }
        session.removeAttribute(VALIDATECODE);
        session.setAttribute(VALIDATECODE,randomString);
        g.dispose();
        try{
            //将内存中的图片通过流的形式输出到客户端
            ImageIO.write(image,"JPEG",response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取随机颜色
    private Color getRandomColor(int i, int j) {
        if(i>255) {
            i=255;
        }
        if(j>255) {
            j=255;
        }
        int r=i+random.nextInt(j-i-16);
        int g=i+random.nextInt(j-i-14);
        int b=i+random.nextInt(j-i-18);
        return new Color(r,g,b);
    }

    /**
     *绘制字符串
     */
    private String picString(Graphics g,String randomString,int i){
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        String rand= getRandomString(random.nextInt(randString.length()));
        randomString+=rand;
        g.translate(random.nextInt(3),random.nextInt(3));
        g.drawString(rand,13*i,16);
        return randomString;
    }

    //获取随机字符串
    private String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

    private Font getFont() {
        return new Font("Fixedsys",Font.CENTER_BASELINE,18);
    }

    /**
     * 绘制干扰线
     * @param g
     */
    private void drowLine(Graphics g) {
        int x=random.nextInt(width);
        int y=random.nextInt(height);
        int x1=random.nextInt(13);
        int y1=random.nextInt(15);
        g.drawLine(x,y,x+x1,y+y1);
    }

}
