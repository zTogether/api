package cn.xyzs.api.util;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetuiUtil {
    public static final String appId = "rIFco3v16Q9g5F9iaziqq5";
    public static final String appKey = "J4ld7A4yNy92m5WwIkypi1";
    public static final String masterSecret = "WBGDEIhq3Z7Ec3tjPpaRa1";
//    public static final String CID = "f55274e072f41c79357f97c7d08f98e4";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";

    public static List<String> CIDs = new ArrayList<>();

    /**
     * 单推送
     * @param title
     * @param text
     * @param CID
     * @throws IOException
     */
    public static void pushmsg(String title,String text,String CID) throws IOException {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        push.connect();
        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle(title);
        template.setText(text);

        template.setUrl("http://getui.com");

        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }

    /**
     * 多客户端推送
     * @param title
     * @param text
     * @throws IOException
     */
    public static void pushClients(String title,String text) throws IOException {
        for (String cid:CIDs) {
            pushmsg(title,text,cid);
        }
    }


}
