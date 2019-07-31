package com.hearkensummertask.hearkensummertask.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class SendSms {

    @Value("${templateCode}")
    String templateCode;
    @Value("${accessKeyid}")
    String accessKeyid;
    @Value("${secret}")
    String secret;

    public void sendYzm(String code,String phone){

        DefaultProfile profile = DefaultProfile.getProfile(
                "default",
                accessKeyid,
                secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putBodyParameter("SignName","fileManageSy");
        request.putBodyParameter("TemplateCode",templateCode);
        request.putQueryParameter("TemplateParam",  "{\"code\":\"" + code + "\"}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
