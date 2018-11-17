package cn.xyzs.api.controller;

import cn.xyzs.api.service.WebSocketSendMessageService;
import cn.xyzs.api.ws.been.ClientMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/webSocketSendMessage")
public class WebSocketSendMessageController {

    @Resource
    private WebSocketSendMessageService webSocketSendMessageService;

    /**
     * 使用webSocket发送信息
     * @Description:（context    groupid      userid     msgtype ）
     * @author: zheng shuai
     * @date: 2018/11/17 10:58
     * @param: [clientMessage]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/sendMessage")
    public Map<String ,Object> sendMessage(ClientMessage clientMessage){
        return webSocketSendMessageService.sendMessage(clientMessage);
    }

}
