package com.hearkensummertask.hearkensummertask.service;

import com.hearkensummertask.hearkensummertask.bean.User;
import com.hearkensummertask.hearkensummertask.dao.UserDao;
import com.hearkensummertask.hearkensummertask.utils.SendQQMailUtil;
import com.hearkensummertask.hearkensummertask.utils.getServerIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhoneLoginService {
    @Autowired
    UserDao userDao;
    @Autowired
    SendSms sendSms;
    @Autowired
    SendQQMailUtil sendQQMailUtil;

    public Boolean sendmessage(String userid) {
        Boolean flag = false;
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");  //不要横杠的
        uuid = uuid.substring(0, 5);
        System.out.println(uuid.length());
        try {
            User user = userDao.findByUserid(userid);
            user.setYzm(uuid);
            userDao.save(user);
            /**
             * 再发送短信
             */
            String smscontent = "激活链接:http://" + getServerIp.getIp() + ":9696/phonelogin?uuid=" + uuid + "&userid=" + userid;
//            String smscontent = "hedoudoushizhu";
            sendSms.sendYzm(smscontent, user.getUserid());  //发送短信


        } catch (Exception e) {
            System.out.println(e);
            System.out.println(" 电话登录的时候 出错  类名为 PhoneLoginService ");
        }
        return flag;
    }

    public Boolean judgeUserPhone(String uuid, String userid) {
        Boolean flag = false;
        try {
            User user = userDao.findByUserid(userid);
            if (uuid.equals(user.getYzm())) {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
            System.out.println(e);
            System.out.println("judgeUserPhone   判断电话号码出了错误");
        }
        return flag;
    }
}
