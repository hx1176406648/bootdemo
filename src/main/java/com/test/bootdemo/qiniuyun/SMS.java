package com.test.bootdemo.qiniuyun;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

/**
 * @createTime : 2020/10/21 13:56
 * @autho : hx
 * @describe : null
 */
public class SMS {
    public static void main( String[] args ) {

        //短信下发
        try {

            Credential cred = new Credential("AKIDrS3PUIs9I9jKgeIm11kfpY0BMincEUKv", "TM6D5fy3h6JFjzygz2qUrt8CzGIBoB7m");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);

            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {"+8615207042201"};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setTemplateID("749808");
            req.setSign("模仿主流的技术");

            String[] templateParamSet1 = {"000000"};
            req.setTemplateParamSet(templateParamSet1);

            req.setSmsSdkAppid("1400364493");

            SendSmsResponse resp = client.SendSms(req);

            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

        /*try{

            Credential cred = new Credential("AKIDrS3PUIs9I9jKgeIm11kfpY0BMincEUKv", "TM6D5fy3h6JFjzygz2qUrt8CzGIBoB7m");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);

            PullSmsSendStatusRequest req = new PullSmsSendStatusRequest();
            req.setLimit(5L);
            req.setSmsSdkAppid("1400364493");

            PullSmsSendStatusResponse resp = client.PullSmsSendStatus(req);

            System.out.println(PullSmsSendStatusResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }*/

    }
}
