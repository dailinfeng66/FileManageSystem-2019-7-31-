package com.hearkensummertask.hearkensummertask.utils;

//import org.springframework.boot.web.servlet.server.Session;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
//import javax.websocket.Session;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
@Service
public class imageYZM {

    public BufferedImage getimgYZM(HttpServletRequest request){
        BufferedImage bi = new BufferedImage(68,22,BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();//画笔对象
        Color c = new Color(200,150,255);//颜色对象
        g.setColor(c);//给画笔赋上颜色
        g.fillRect(0,0,430,30);//画一个外框
        char [] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random r = new Random();
        int len = ch.length,index;
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<4;i++){
            index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
            g.drawString(ch[index]+"",(i*15)+3,18);//画随机字符
            sb.append(ch[index]);
        }

        String checkCode = sb.toString();  //然后把这个写入数据库
        request.getSession().setAttribute("checkCode",checkCode);
//        System.out.println("刚生成的checkcode"+checkCode);


        return bi;
//        ImageIO.write(bi,"JPG",response.getOutputStream());
    }
}
