package cn.xyzs.api.controller;

import cn.xyzs.api.service.AppSystemUpdateService;
import cn.xyzs.common.util.MD5Util;
import cn.xyzs.api.ws.been.ClientMessage;
import cn.xyzs.api.ws.service.SendService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/App/appSystemUpdate")
public class AppSystemUpdateController{

    @Resource
    private AppSystemUpdateService appSystemUpdateService;
    @Resource
    private SendService sendService;


    /**
     * 获取app当前最新版本
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/15 15:15
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getAppSystemVersion")
    public Map<String ,Object> getAppSystemVersion(){
        return appSystemUpdateService.getAppSystemVersion();
    }

    /**
     * 推送app更新信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/7 9:11
     * @param: [message]
     * @return: void
     */
    @ResponseBody
    @RequestMapping("/pushAppUpdateMsg")
    public void pushAppUpdateMsg(ClientMessage message){
        sendService.templateTest(message.getGroupid(), JSON.toJSONString(message));
    }

}
