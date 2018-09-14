package cn.xyzs.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;

public class SendMsgUtil {

    // 短信应用SDK AppID
    private static final int appid = 1400139290; // 1400开头
    // 短信应用SDK AppKey
    private static final String appkey = "823a0c51470a517207285607784680db";
    // 短信模板ID，需要在短信应用中申请
    private static final int templateId = 193979; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
    // 签名
    private static final String smsSign = "江苏轩辕装饰工程有限公司"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
    // 需要发送短信的手机号码
    //private static final String[] phoneNumbers = {};


    /**
     * 发送短验证码（单发）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/14 11:24
     * @param: [verificationCode, phone]
     * @return: java.lang.String
     */
    public static String sendMsg(String verificationCode ,String phone){
        //定义code初始值
        String code = "500";
        String msg = "发送失败";
        try {
            String[] params = {verificationCode};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone, templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            JSONObject resultJson = JSON.parseObject(result.toString());
            String resultCode = resultJson.getString("result");
            String resultMsg = resultJson.getString("errmsg");
            System.out.println(result.toString());
            if ("0".equals(resultCode)){
                code = "200";
                msg = "发送成功";
            } else {
                code = resultCode;
                msg = resultMsg;
            }
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 发送短信（群发，不能使用）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/14 14:22
     * @param: []
     * @return: java.lang.String
     */
    public static String massTexting(){
        try {
            //手机号
            String[] phoneNumbers = {};
            //参数
            String[] params = {"5678"};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            String resultCode = JSON.parseObject(result.toString()).getString("result");
            System.out.println(result.toString());
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }

        return null;
    }

    public static String  getVerificationCode() {
        Integer authCodeNew = 0;
        authCodeNew = (int)Math.round(Math.random() * (9999-1000) + 1000);
        return String.valueOf(authCodeNew);
     }


    public static void main(String args[]) {
        /*String code = sendMsg("1234","15250992995");
        if ("200".equals(code)){
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }*/

        System.out.println(getVerificationCode());
    }
}
