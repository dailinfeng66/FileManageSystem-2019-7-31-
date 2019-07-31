package com.hearkensummertask.hearkensummertask.controller;

import com.hearkensummertask.hearkensummertask.service.ChangeUserPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class changePassword {
    @Autowired
    ChangeUserPass changeUserPass;
    @RequestMapping("/jumpChangepassword")
    public String changepassword(){
        return "changepass";
    }
    @RequestMapping("/changepass")
    @ResponseBody
    public boolean changepass(HttpServletRequest request){
            String userid = request.getParameter("userid");
            String pass = request.getParameter("password");
            changeUserPass.changeUserPass(userid,pass);

            return true;
    }


}
