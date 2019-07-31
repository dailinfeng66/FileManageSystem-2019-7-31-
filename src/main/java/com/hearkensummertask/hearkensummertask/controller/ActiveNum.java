package com.hearkensummertask.hearkensummertask.controller;

import com.hearkensummertask.hearkensummertask.service.ActivateService;
import com.hearkensummertask.hearkensummertask.service.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.util.Map;

@Controller
public class ActiveNum {
    @Autowired
    ActivateService activateService;
    @RequestMapping("/activate")
    public String asd(@PathParam("uuid") String uuid, @PathParam("userid") String userid, Map<String, Object> map) {

        boolean flag = false;
        try {
            flag = activateService.activeservice(uuid, userid);
            if (flag) {
                WebSocket.sendMessageTo("activatesuccess", userid);
            } else {
                WebSocket.sendMessageTo("false", userid);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ActiveNum 这个类出了问题");
//            e.printStackTrace();
        }
        if (flag) {   //激活成功
            map.put("result", "激活成功!");
        } else {  //激活失败
            map.put("result", "激活失败!");
        }
        return "activateResult";
    }

}
