package com.test.bootdemo.qiniuyun;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.PullSmsReplyStatusByPhoneNumberRequest;
import com.tencentcloudapi.sms.v20190711.models.PullSmsReplyStatusByPhoneNumberResponse;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @createTime : 2020/10/22 14:35
 * @autho : hx
 * @describe : null
 */
@RestController
public class SmsController {
    private static Map<String,String> map = new ConcurrentHashMap<>();

    @PostMapping("/testSms")
    public String testSms(String userName,String phone,String checkCode){
        if(!map.get(phone).equals(checkCode)){
            return "验证码错误";
        }else {
            if(System.currentTimeMillis() % 2 == 0){
                try {

                    Credential cred = new Credential("AKIDrS3PUIs9I9jKgeIm11kfpY0BMincEUKv", "TM6D5fy3h6JFjzygz2qUrt8CzGIBoB7m");

                    HttpProfile httpProfile = new HttpProfile();
                    httpProfile.setEndpoint("sms.tencentcloudapi.com");

                    ClientProfile clientProfile = new ClientProfile();
                    clientProfile.setHttpProfile(httpProfile);

                    SmsClient client = new SmsClient(cred, "", clientProfile);

                    SendSmsRequest req = new SendSmsRequest();
                    String[] phoneNumberSet1 = {"+86" + phone};
                    req.setPhoneNumberSet(phoneNumberSet1);

                    req.setTemplateID("751842");
                    req.setSign("模仿主流的技术");
                    req.setSmsSdkAppid("1400364493");

                    Long time = System.currentTimeMillis() / 1000;
                    SendSmsResponse resp = client.SendSms(req);

                    if(resp.getSendStatusSet()[0].getCode().equalsIgnoreCase("OK")){
                        System.out.println("发送选择性格成功");
                    }

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            PullSmsReplyStatusByPhoneNumberRequest req = new PullSmsReplyStatusByPhoneNumberRequest();
                            req.setSendDateTime(time);
                            req.setOffset(0L);
                            req.setPhoneNumber("+86" + phone);
                            req.setLimit(1L);
                            req.setSmsSdkAppid("1400364493");
                            System.out.println(req);

                            while (true){
                                try {
                                    Thread.sleep(1 * 1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    PullSmsReplyStatusByPhoneNumberResponse resp = client.PullSmsReplyStatusByPhoneNumber(req);
                                    if(resp.getPullSmsReplyStatusSet().length == 0){
                                        continue;
                                    }else {
                                        System.out.println("应答内容" + resp.getPullSmsReplyStatusSet()[0].getReplyContent());
                                        switch (resp.getPullSmsReplyStatusSet()[0].getReplyContent()){
                                            //数据库操作
                                            case "1":
                                                break;
                                            case "2":
                                                break;
                                            case "3":
                                                break;
                                            case "4":
                                                break;
                                            case "5":
                                                break;
                                            case "6":
                                                break;
                                            case "7":
                                                break;
                                            case "8":
                                                break;
                                        }


                                        break;
                                    }
                                } catch (TencentCloudSDKException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();


                } catch (TencentCloudSDKException e) {
                    System.out.println(e.toString());
                }
            }else {

            }

            return "已进入短信测试";
        }
    }

    @PostMapping("/sendCode")
    public String sendCode(@NotNull String phone){
        if(phone.equals("") || phone.length() != 11){
            return "请输入正确的手机号";
        }

        try {

            Credential cred = new Credential("AKIDrS3PUIs9I9jKgeIm11kfpY0BMincEUKv", "TM6D5fy3h6JFjzygz2qUrt8CzGIBoB7m");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);

            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {"+86" + phone};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setTemplateID("749808");
            req.setSign("模仿主流的技术");

            String code = getCode();
            String[] templateParamSet1 = {code};
            req.setTemplateParamSet(templateParamSet1);

            req.setSmsSdkAppid("1400364493");

            map.put(phone,code);
            SendSmsResponse resp = client.SendSms(req);

            if(resp.getSendStatusSet()[0].getCode().equalsIgnoreCase("OK")){
                return "发送成功";
            }

            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

        return "发送失败";
    }

    private String getCode(){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            stringBuffer.append(new Random().nextInt(10));
        }

        return stringBuffer.toString();
    }
}
