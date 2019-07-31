package com.hearkensummertask.hearkensummertask.controller;

import com.alibaba.fastjson.JSONObject;
import com.hearkensummertask.hearkensummertask.bean.TemporaryUser;
import com.hearkensummertask.hearkensummertask.bean.User;
import com.hearkensummertask.hearkensummertask.service.JudgeUserPass;
import com.hearkensummertask.hearkensummertask.service.PhoneLoginService;
import com.hearkensummertask.hearkensummertask.utils.imageYZM;
import org.apache.xpath.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

@Controller
public class Loginlogic {
    @Autowired
    JudgeUserPass judgeUserPass;
    @Autowired
    imageYZM imageyzm;
    @Autowired
    PhoneLoginService phoneLoginService;

    @RequestMapping("/login")
    public void login(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        /**
         * 查询数据库判断账号密码是否正确
         */

        boolean result = judgeUserPass.judgeuserpass(userid, password);
        String correct = "{\"result\":" + result + "}";
        String jsonresult = JSONObject.toJSON(correct).toString();
        if(result){
            request.getSession().setAttribute("userid",userid);
            request.getSession().setAttribute("userlogin",true);
        }

//        System.out.println(jsonresult);
        out.print(jsonresult);
//        System.out.println(userid + password);


    }

    @RequestMapping("/success")   //登录成功后的页面跳转
    public String redirectToSuccess(Map<String,Object> map,HttpServletRequest request) {
        map.put("userid",request.getSession().getAttribute("userid"));
//        System.out.println("进到了success");

        return "success";
    }

    @RequestMapping("/judgeuserid")
    public void judgeuserid(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String userid = request.getParameter("userid");
        boolean resule = judgeUserPass.judgeUserIsExist(userid);
        String correct = "{\"result\":" + resule + "}";
        String jsonresult = JSONObject.toJSON(correct).toString();

        System.out.println(jsonresult);

        out.print(jsonresult);
    }

    @RequestMapping("/register")   //注册
    public void register(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

//        User user = new User(userid, password,email);
        TemporaryUser temporaryUser = new TemporaryUser(userid, password,email);
        System.out.println(temporaryUser);
        boolean result = judgeUserPass.register(temporaryUser);

        String correct = "{\"result\":" + result + "}";
        String jsonresult = JSONObject.toJSON(correct).toString();

        out.print(jsonresult);
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("userlogin",false);
        return "index";
    }

    @RequestMapping({"/","index.html","index"}) //设置首页
    public String welcomePage()  {

        return "index.html";
    }

    @RequestMapping("/getCheckCode")
    public void getcheckcode(HttpServletResponse response,HttpServletRequest request)throws IOException{
        BufferedImage bi = imageyzm.getimgYZM(request);
        ImageIO.write(bi,"JPG",response.getOutputStream());
    }
    @ResponseBody
    @RequestMapping("/justCheckCode")
    public String  justCheckcode(HttpServletRequest request){
        String  flag = "false";
        String ccByb = request.getParameter("checkcode");
        String ccByc = (String) request.getSession().getAttribute("checkCode");
        ccByb = ccByb.toLowerCase();
//        System.out.println("session取到的checkcode"+request.getSession().getAttribute("checkCode"));
//        System.out.println(ccByb+"\t"+ccByc);
        ccByc = ccByc.toLowerCase();


        if(ccByb.equals(ccByc)){
            flag = "true";
        }
        return flag;

    }

    @RequestMapping("/toPhone")  //跳转到手机登录
    public String tophone(){


        return "phoneLogin";
    }





    @ResponseBody
    @RequestMapping("/Sendsms")
    public String send(HttpServletRequest request){
        String userid = request.getParameter("userid");
        if(phoneLoginService.sendmessage(userid)){
            return "短信激活码发送成功!";
        }

        return "短信激活码发送失败!";
    }



    @RequestMapping("/phonelogin")
    public String loginByPhone(@PathParam("uuid")String uuid,@PathParam("userid")String userid,
                               HttpServletRequest request,Map<String,Object> map){
        if(phoneLoginService.judgeUserPhone(uuid,userid)){  //验证通过
            request.getSession().setAttribute("userid",userid);
            request.getSession().setAttribute("userlogin",true);
            map.put("result","登录成功!");
        }else {  //验证不通过
        map.put("result","登录失败!");
        }
        return "activateResult";
    }





}
