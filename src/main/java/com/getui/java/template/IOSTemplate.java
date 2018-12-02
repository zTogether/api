package com.getui.java.template;

import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class IOSTemplate {
	public static final String appId = "rIFco3v16Q9g5F9iaziqq5";
	public static final String appKey = "J4ld7A4yNy92m5WwIkypi1";
	public static final String masterSecret = "WBGDEIhq3Z7Ec3tjPpaRa1";
//	public static final String CID = "0a632fa856b47f50d122f3bdd9b0e7c2";
	public static final String CID = "47AB51883E7F1AE92F6E42737CFA8966B21E59E26CB345F96191D06086BC5AE6";
	public static TransmissionTemplate getTemplate() {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    template.setTransmissionContent("透传内容");
	    template.setTransmissionType(2);
	    APNPayload payload = new APNPayload();
	    payload.setBadge(1);
	    payload.setContentAvailable(1);
	    payload.setSound("default");
	    payload.setCategory("$由客户端定义");
	    //简单模式APNPayload.SimpleMsg 
	    payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));
	    //字典模式使用下者
	    //payload.setAlertMsg(getDictionaryAlertMsg());
	    template.setAPNInfo(payload);
	    return template;
	}
	private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(){
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody("body");
	    alertMsg.setActionLocKey("ActionLockey");
	    alertMsg.setLocKey("LocKey");
	    alertMsg.addLocArg("loc-args");
	    alertMsg.setLaunchImage("launch-image");
	    // IOS8.2以上版本支持
	    alertMsg.setTitle("Title");
	    alertMsg.setTitleLocKey("TitleLocKey");
	    alertMsg.addTitleLocArg("TitleLocArg");
	    return alertMsg;
	}
	
	public static void main(String[] args) {
		getTemplate();
		getDictionaryAlertMsg();
	}
}
