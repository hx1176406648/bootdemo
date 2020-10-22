package com.test.bootdemo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.PullSmsReplyStatusByPhoneNumberRequest;
import com.tencentcloudapi.sms.v20190711.models.PullSmsReplyStatusByPhoneNumberResponse;
import com.test.bootdemo.PointCut.SelectPointCut;
import com.test.bootdemo.dao.UserDAO;
import com.test.bootdemo.query.TestQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootdemoApplicationTests {
    @Autowired
    private SelectPointCut selectPointCut;

    @Resource
    private UserDAO userDAO;

    @Test
    public void contextLoads() {
        try {
            selectPointCut.Logger("Blog Home");

     //       selectPointCut.test();
    //        throw new Exception();
        } catch (Exception e) {
            System.out.println("a exception be there");
        }
    }

    @Test
    public void test(){
        TestQuery tq = new TestQuery();
        tq.setTestFiled("5678");

        userDAO.testQuery(tq);
    }

    @Test
    public void test4() throws ParseException {
        Credential cred = new Credential("AKIDrS3PUIs9I9jKgeIm11kfpY0BMincEUKv", "TM6D5fy3h6JFjzygz2qUrt8CzGIBoB7m");

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        SmsClient client = new SmsClient(cred, "", clientProfile);
        Long time = System.currentTimeMillis() / 1000;


        PullSmsReplyStatusByPhoneNumberRequest req = new PullSmsReplyStatusByPhoneNumberRequest();
        req.setSendDateTime(time);
        req.setOffset(0L);
        req.setPhoneNumber("+8615207042201");
        req.setLimit(1L);
        req.setSmsSdkAppid("1400364493");
        System.out.println(JSONUtil.toJsonStr(req));

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


        /*userDAO.deleteTest(1);*/
    }

    @Test
    public void interval(){
        DecimalFormat df = new DecimalFormat("0.000");
        Date date = new Date(120,3,4,0,0,0);
        float low = 3.0f;
        float high = 10.0f;

        List<JsonData> list = new LinkedList<>();
        for(int i = 0;i < 96;i++){
            JsonData jsonData = new JsonData();
            float f = ((new Random()).nextFloat()) * 7 + 3;
            jsonData.setReadingTime(DateUtil.formatDateTime(new Date(date.getTime() + 1000 * 60 * 15 * i)));
            jsonData.setValue(String.valueOf(df.format(f)));
            list.add(jsonData);
        }

        System.out.println(JSONUtil.toJsonStr(list));
    }
}
