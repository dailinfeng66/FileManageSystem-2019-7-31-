package com.hearkensummertask.hearkensummertask.utils;

import com.hearkensummertask.hearkensummertask.bean.TemporaryUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class SendQQMailUtil {
    @Value("${qqMail}")
    String qqmail;
    @Value("${stmtcode}")
    String stmtcode;

    public void sendmail(TemporaryUser temporaryUser) throws AddressException, MessagingException {
        Properties properties = new Properties();

        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "false");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);   //这个是mail的session并不是httpsession
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress(qqmail));
        // 设置收件人邮箱地址
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{

                new InternetAddress(temporaryUser.getEmail())
//                ,new InternetAddress("xxx@qq.com"),
//                new InternetAddress("xxx@qq.com")
        });
        //message.setRecipient(Message.RecipientType.TO, new InternetAddress("xxx@qq.com"));//一个收件人
        // 设置邮件标题
        message.setSubject("文档管理系统");
        // 设置邮件内容
        message.setText("激活链接:http://" + getServerIp.getIp() + ":9696/activate?uuid=" + temporaryUser.getUuid() + "&userid=" + temporaryUser.getUserid());
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户

        transport.connect(qqmail, stmtcode);// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}