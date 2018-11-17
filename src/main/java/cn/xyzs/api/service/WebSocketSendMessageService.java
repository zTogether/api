package cn.xyzs.api.service;

import cn.xyzs.api.ws.been.ClientMessage;
import cn.xyzs.api.ws.service.SendService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class WebSocketSendMessageService {

    @Resource
    private SendService sendService;

    /**
     * 使用webSocket发送信息
     * @Description:（context    groupid      userid     msgtype ）
     * @author: zheng shuai
     * @date: 2018/11/17 10:58
     * @param: [clientMessage]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> sendMessage(ClientMessage clientMessage){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            sendService.templateTest(clientMessage.getGroupid(), JSON.toJSONString(clientMessage));
            code = "200";
            msg = "发送成功";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }
}
