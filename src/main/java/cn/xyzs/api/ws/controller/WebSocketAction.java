package cn.xyzs.api.ws.controller;

import cn.xyzs.api.ws.been.ClientMessage;
import cn.xyzs.api.ws.been.ServerMessage;
import cn.xyzs.api.ws.radis.RedisOperation;
import cn.xyzs.api.ws.service.SendService;
import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/websocket")
public class WebSocketAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    protected SendService sendService;

    @Resource
    private RedisOperation redisOperation;

    @MessageMapping("/xysend/")
    @SendTo("/*")
    public void sendDemo(ClientMessage message) {
        System.err.println("群组："+message.getGroupid());
        logger.info("接收到了"+message.getUserid()+"信息" + message.getContext()+"类型"+message.getMsgtype());
        //存储redis，并且推送给所有群组
        redisOperation.lset(message.getGroupid(),JSON.toJSONString(message));

        sendService.templateTest(message.getGroupid(),JSON.toJSONString(message));
    }

    @SubscribeMapping("/*")
    public ServerMessage sub() {
        logger.info("XXX用户订阅了我。。。");
        return new ServerMessage("感谢你订阅了我。。。");
    }

    @RequestMapping("send.do")
    @ResponseBody
    public String index(String group,String message){
        sendService.templateTest(group,message);
        return "1";
    }

}

