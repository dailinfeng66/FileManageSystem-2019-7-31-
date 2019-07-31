package com.hearkensummertask.hearkensummertask.utils;

import java.net.InetAddress;

/**
 * 得到服务器Ip  进行页面跳转
 */
public class getServerIp {
    public static String getIp(){
        String ip = null;
        InetAddress ia=null;
        try {
            ia=ia.getLocalHost();
            ip=ia.getHostAddress();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ip;
    }
}
