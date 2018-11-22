package cn.xyzs.api.ws.service;

import cn.xyzs.api.ws.been.ServerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    //客户端只要订阅了/topic/subscribeTest主题，调用这个方法即可
    public void templateTest(String group,String message) {
        messagingTemplate.convertAndSend("/ws/"+group, new ServerMessage(message));
    }

}
