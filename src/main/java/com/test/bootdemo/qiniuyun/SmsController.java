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
import com.test.bootdemo.dao.SmsDAO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    @Resource
    SmsDAO smsDAO;

    private static Map<String, String> map = new ConcurrentHashMap<>();

    @PostMapping("/testSms")
    public String testSms(String userName, String phone, String checkCode) {
        if (map.get(phone) == null || !map.remove(phone).equals(checkCode)) {
            return "验证码错误";
        } else {
            if (System.currentTimeMillis() % 2 == 0) {
                try {
                    Long time = System.currentTimeMillis() / 1000l;
                    sendMessage("751842", phone, "发送选择性格成功");

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(30 * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            int count = 0;
                            while (true) {
                                try {
                                    Thread.sleep(1 * 1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                count++;
                                if(count > 180){
                                    break;
                                }

                                try {
                                    PullSmsReplyStatusByPhoneNumberResponse resp = pullReply(time, phone);
                                    if (resp.getPullSmsReplyStatusSet().length == 0) {
                                        continue;
                                    } else {
                                        System.out.println("应答内容" + resp.getPullSmsReplyStatusSet()[0].getReplyContent());
                                        switch (resp.getPullSmsReplyStatusSet()[0].getReplyContent()) {
                                            //数据库操作
                                            case "1":
                                                addInfo(userName, phone, "热情");
                                                break;
                                            case "2":
                                                addInfo(userName, phone, "高冷");
                                                break;
                                            case "3":
                                                addInfo(userName, phone, "可爱");
                                                break;
                                            case "4":
                                                addInfo(userName, phone, "活泼");
                                                break;
                                            case "5":
                                                addInfo(userName, phone, "内向");
                                                break;
                                            case "6":
                                                addInfo(userName, phone, "憨憨");
                                                break;
                                            case "7":
                                                addInfo(userName, phone, "当然是帅气阳光魅力十足而又充满自信的我啦");
                                                break;
                                            case "8":
                                                Long time2 = System.currentTimeMillis() / 1000l;
                                                sendMessage("752377", phone, "发送自定义性格成功");
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(30 * 1000);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }

                                                        int count2 = 0;
                                                        while (true) {
                                                            try {
                                                                Thread.sleep(1 * 1000);
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            count2++;
                                                            if(count2 > 180){
                                                                break;
                                                            }

                                                            try {
                                                                PullSmsReplyStatusByPhoneNumberResponse resp = pullReply(time2, phone);
                                                                if (resp.getPullSmsReplyStatusSet().length == 0) {
                                                                    continue;
                                                                } else {
                                                                    System.out.println("应答内容" + resp.getPullSmsReplyStatusSet()[0].getReplyContent());
                                                                    //数据库操作
                                                                    addInfo(userName, phone, resp.getPullSmsReplyStatusSet()[0].getReplyContent());
                                                                    break;
                                                                }
                                                            } catch (TencentCloudSDKException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                }).start();
                                                break;
                                            default:
                                                sendMessage("752189", phone, "选择性格未定义");
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
            } else {
                try {
                    Long time = System.currentTimeMillis() / 1000l;
                    sendMessage("751870", phone, "发送选择性格成功");

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(30 * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            int count = 0;
                            while (true) {
                                try {
                                    Thread.sleep(1 * 1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                count++;
                                if(count > 180){
                                    break;
                                }

                                try {
                                    PullSmsReplyStatusByPhoneNumberResponse resp = pullReply(time, phone);
                                    if (resp.getPullSmsReplyStatusSet().length == 0) {
                                        continue;
                                    } else {
                                        System.out.println("应答内容" + resp.getPullSmsReplyStatusSet()[0].getReplyContent());
                                        //数据库操作
                                        switch (resp.getPullSmsReplyStatusSet()[0].getReplyContent()) {
                                            //数据库操作
                                            case "1":
                                                addInfo(userName, phone, "real man");
                                                break;
                                            case "2":
                                                addInfo(userName, phone, "玄宗再世！江山看不尽，最美镜中人");
                                                break;
                                            case "3":
                                                addInfo(userName, phone, "英雄所见略同，real me");
                                                break;
                                            case "4":
                                                addInfo(userName, phone, "是条情真意切的汉子");
                                                break;
                                            case "5":
                                                addInfo(userName, phone, "加油，奥里给，今天又是充满希望的一天");
                                                break;
                                            case "6":
                                                addInfo(userName, phone, "有了牵挂，人生才有意义");
                                                break;
                                            case "7":
                                                addInfo(userName, phone, "有我，世界更精彩");
                                                break;
                                            case "8":
                                                addInfo(userName, phone, "李煜也没你愁啊。薄纸难书深愁，生活需要正能量");
                                                break;
                                            case "9":
                                                addInfo(userName, phone, "你可真会做梦");
                                                break;
                                            case "10":
                                                addInfo(userName, phone, "原来是你，这是你的哪一面");
                                                break;
                                            default:
                                                sendMessage("752189", phone, "选择性格未定义");
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
            }

            return "已进入短信测试";
        }
    }

    @PostMapping("/sendCode")
    public String sendCode(@NotNull String phone) {
        if (phone.equals("") || phone.length() != 11) {
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

            map.put(phone, code);
            SendSmsResponse resp = client.SendSms(req);

            if (resp.getSendStatusSet()[0].getCode().equalsIgnoreCase("OK")) {
                return "发送成功";
            }

            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

        return "发送失败";
    }

    private String getCode() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            stringBuffer.append(new Random().nextInt(10));
        }

        return stringBuffer.toString();
    }

    private void sendMessage(String templateId, String phone, String memo) throws TencentCloudSDKException {
        Credential cred = new Credential("AKIDrS3PUIs9I9jKgeIm11kfpY0BMincEUKv", "TM6D5fy3h6JFjzygz2qUrt8CzGIBoB7m");

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        SmsClient client = new SmsClient(cred, "", clientProfile);

        SendSmsRequest req = new SendSmsRequest();
        String[] phoneNumberSet1 = {"+86" + phone};
        req.setPhoneNumberSet(phoneNumberSet1);

        req.setTemplateID(templateId);
        req.setSign("模仿主流的技术");
        req.setSmsSdkAppid("1400364493");

        SendSmsResponse resp = client.SendSms(req);

        if (resp.getSendStatusSet()[0].getCode().equalsIgnoreCase("OK")) {
            System.out.println(memo);
        }
    }

    private PullSmsReplyStatusByPhoneNumberResponse pullReply(Long time, String phone) throws TencentCloudSDKException {
        Credential cred = new Credential("AKIDrS3PUIs9I9jKgeIm11kfpY0BMincEUKv", "TM6D5fy3h6JFjzygz2qUrt8CzGIBoB7m");

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        SmsClient client = new SmsClient(cred, "", clientProfile);

        PullSmsReplyStatusByPhoneNumberRequest req = new PullSmsReplyStatusByPhoneNumberRequest();
        req.setSendDateTime(time - 3l);
        req.setEndDateTime(System.currentTimeMillis() / 1000l);
        req.setOffset(0L);
        req.setPhoneNumber("+86" + phone);
        req.setLimit(1L);
        req.setSmsSdkAppid("1400364493");

        PullSmsReplyStatusByPhoneNumberResponse resp = client.PullSmsReplyStatusByPhoneNumber(req);
        return resp;
    }

    private static void addInfo(String name, String phone, String character) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO `how2java`.`sms`(`name`, `phone`, `character`) VALUES (?,?,?);";
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8&serverTimezone=UTC",
                        "root", "123456");
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, character);
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
