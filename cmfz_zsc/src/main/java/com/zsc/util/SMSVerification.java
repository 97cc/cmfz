package com.zsc.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 知识温度
 * 内容：
 */
public class SMSVerification {

    /**
     * 获取验证码
     * @param phone  手机号
     * @param code   验证码
     * @return
     */
    public static String SMSVerification(String phone,String code){

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4Fn6CUuGi5RYY7vCFm92", "DMYTjQckEfJsgKNRNTCR4Y8n6MNWdZ");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "知识温度");
        request.putQueryParameter("TemplateCode", "SMS_180053652");
        request.putQueryParameter("TemplateParam", "{\"code\":"+code+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            System.out.println(data);
            return data;
        } catch (ServerException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        } catch (ClientException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }

    }


}
