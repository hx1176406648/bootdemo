package com.test.bootdemo.PointCut;

import com.test.bootdemo.Annotation.UserAnnotation;
import org.springframework.stereotype.Component;

//切点

@Component
public class SelectPointCut {

    @UserAnnotation(module = "www.baidu.com???")
    public void Logger(String event) throws Exception {
        System.out.println("用户业务执行 : " + "------------" + "     " + event);
     //   throw new Exception();
        return;
    }

    public void test(){
        System.out.println("成功？失败？");
    }

}
