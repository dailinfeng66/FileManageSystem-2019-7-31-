package com.hearkensummertask.hearkensummertask.service;

import com.hearkensummertask.hearkensummertask.bean.User;
import com.hearkensummertask.hearkensummertask.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeUserPass {
    @Autowired
    UserDao userDao;
    public void changeUserPass(String userid,String  pass){
        User user = userDao.findByUserid(userid);
        user.setPassword(pass);
        userDao.save(user);
    }


}
