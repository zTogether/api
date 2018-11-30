package cn.xyzs.api.test;

import cn.xyzs.common.util.Base64ImageUtils;
import cn.xyzs.common.util.HttpUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.HttpClientUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FaceAPITest {
    public static void main(String[] args) {
        //  getToKenTest() ;
//        faceDetecttest();
        Date d = new Date(1543444951357L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(d));
    }

    //获取token
    public static void getToKenTest() throws Exception {

        //使用其测试百度云API---获取token
        //url: http://console.bce.baidu.com/ai

        String APPID ="10201***"; //管理中心获得

        //百度人脸识别应用apikey
        String API_KEY = "WVsGWvDxyn55hW8P*******"; //管理中心获得

        //百度人脸识别应用sercetkey
        String SERCET_KEY = "z0uSr16LGyTNIMRRnP6f8jCL*****"; //管理中心获得

        //百度人脸识别token 有效期一个月
        String TOKEN = null;


        String access_token_url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials"
                +"&client_id="+API_KEY +"&client_secret="+SERCET_KEY;

        String json =  HttpUtil.doPost(access_token_url,"");

        System.out.println(json);


        //得到token = 24.1d786b9cdbdd8ac7cf55d56c7f38372b.2592000.1509244497.282335-10201425



    }

    //使用token调用API
    public static void faceDetecttest(){

        String token = "24.1ae7a9e57ad1c76823095af0169f73cd.2592000.1536199419.282335-11640984";

        String Filepath = "D:\\fileTest\\xyzs\\20180807094818.jpg";
        String image = Base64ImageUtils.GetImageStrFromPath(Filepath);
        String url = "https://aip.baidubce.com/rest/2.0/face/v1/detect?access_token="+token;

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("image", image);
        bodys.put("face_fields", "age,beauty,expression,gender,glasses,race,qualities");

        try {
            String json =  HttpUtil.doPost(url,"");
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
