package com.hearkensummertask.hearkensummertask.service;

import com.hearkensummertask.hearkensummertask.bean.TemporaryUser;
import com.hearkensummertask.hearkensummertask.bean.User;
import com.hearkensummertask.hearkensummertask.dao.TemPoraryUserDao;
import com.hearkensummertask.hearkensummertask.dao.UserDao;
import com.hearkensummertask.hearkensummertask.utils.SendQQMailUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class JudgeUserPass {
    @Autowired
    UserDao userDao;
    @Autowired
    TemPoraryUserDao temPoraryUserDao;
    @Autowired
    SendQQMailUtil sendQQMailUtil;
    public boolean judgeuserpass(String userid,String userpass){
        boolean result = false;
        User user = userDao.findByUserid(userid);
        try{
            if(user.getPassword().equals(userpass)){
                result=true;
            }
        }catch (NullPointerException e){
            System.out.println(e);
            return false;
        }

        return result;

    }

    public boolean judgeUserIsExist(String userid){
        boolean result = false;
        User user = userDao.findByUserid(userid);
        if (user==null){
            result = true;
        }
        return result;
    }

    public boolean register(TemporaryUser temporaryUser){
        boolean result = true;

        try{
            //生成UUID
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");  //不要横杠的
            temporaryUser.setUuid(uuid);
            temPoraryUserDao.save(temporaryUser);   //写到中间表

            sendQQMailUtil.sendmail(temporaryUser);

        }catch (Exception e){
            System.out.println(e);
            result = false;
        }

        return result;
    }
}
