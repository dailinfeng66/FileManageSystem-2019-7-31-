package com.hearkensummertask.hearkensummertask.service;

import com.hearkensummertask.hearkensummertask.bean.TemporaryUser;
import com.hearkensummertask.hearkensummertask.bean.User;
import com.hearkensummertask.hearkensummertask.dao.TemPoraryUserDao;
import com.hearkensummertask.hearkensummertask.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivateService {
    @Autowired
    TemPoraryUserDao temPoraryUserDao;
    @Autowired
    UserDao userDao;

    public Boolean activeservice(String uuid, String userid) {
        Boolean flag = false;
        /**
         * 从数据库获取UUID和userid  看看是否匹配
         */
        try {
//            TemporaryUser temporaryUser1 = temPoraryUserDao.findByUserid(userid);
            TemporaryUser temporaryUser = temPoraryUserDao.getOne(userid);
            System.out.println("\n\ntempUSer\n"+temporaryUser+"\n\n");
            if (temporaryUser.getUuid().equals(uuid)) {
                //到了这里说明是客户本人  先在要做的操作就是  把这个数据写入正式的表
                User user = new User();
                user.setUserid(userid);
                user.setPassword(temporaryUser.getPassword());
                user.setEmail(temporaryUser.getEmail());

                userDao.save(user);
                /**
                 * 再删除临时表中的内容
                 */

                temPoraryUserDao.delete(temporaryUser);


                flag = true;
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println(" ActivateService 这个类出的bug  ");
        }

        return flag;
    }
}
