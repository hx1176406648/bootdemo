package com.test.bootdemo.qiniuyun;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

/**
 *   类作用描述：上传图片到服务器
 *   创建时间：2018/12/1 17:54
 *   构造方法参数：
 *   修改时间：2018/12/1 17:54
 *   创建者：ZENG
 *   类全限定名称：com.hyxiaojingyu.common.QiniuUpload
 **/
public class QiniuUpload {
    public static final String ACCESS_KEY = VariableName.accessKey; // 你的access_key
    public static final String SECRET_KEY = VariableName.secretKey; // 你的secret_key
    public static final String BUCKET_NAME = VariableName.bucket; // 你的secret_key


    /**
     * 上传到七牛云
     * @param file 上传的图片
     * @return 七牛云中图片的名字
     */
    public static String uploadQiniu(MultipartFile file) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.huadong());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = ACCESS_KEY;
        String secretKey = SECRET_KEY;
        //存储空间的名字
        String bucket = BUCKET_NAME;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put("C:\\Users\\user\\Desktop\\teset.jpg", "teset.jpg", upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){
        uploadQiniu(null);
    }
}