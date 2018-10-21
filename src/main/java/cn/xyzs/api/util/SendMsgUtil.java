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
    private static final int templateId1 = 193979; //验证码短信模板ID                  尊敬的客户您好，您有一个礼品待领取，请于收到短信起三天内联系我司工作人员，逾期作废，领取码{1}。回T退订
    private static final int templateId2 = 203858;//礼品码短信模板ID                   {1}为您的验证码，如非本人操作，请忽略本短信。
    private static final int templateId3 = 213921; //通知执行员派单成功短信模板ID      您好！{1}（档案号）{2}工程已成功派单，施工工长{3}，电话{4}，请您尽快联系交底！
    private static final int templateId4 = 214246;//抢单成功提醒短信模板ID             恭喜您抢单成功！档案号{1}，执行总监{2}，电话{3}，请尽快联系交底！
    // 签名
    private static final String smsSign = "江苏轩辕装饰工程有限公司"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
    // 需要发送短信的手机号码
    //private static final String[] phoneNumbers = {};


    /**
     * 发送短验证码（单发）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/14 11:24
     * @param: [verificationCode, phone],sendType(0：短信验证码   1：礼品码)
     * @return: java.lang.String
     */
    public static String sendMsg(String sendType ,String []params ,String phone){
        //定义code初始值
        String code = "500";
        String msg = "发送失败";
        try {
            int templateId = 0;
            if ("0".equals(sendType)){
                templateId = templateId1;
            } else if ("1".equals(sendType)){
                templateId = templateId2;
            } else if ("2".equals(sendType)){
                templateId = templateId3;
            } else if ("3".equals(sendType)){
                templateId = templateId4;
            }
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
            int templateId = 0;
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
        String []params = {"2018000111","2018-10-21","葛伟亮","15250992995"};
        String code = sendMsg("3",params,"15895838592");
        if ("200".equals(code)){
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }

       /* System.out.println(getVerificationCode());*/

    }
}
