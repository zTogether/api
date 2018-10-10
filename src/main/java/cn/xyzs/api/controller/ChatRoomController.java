package cn.xyzs.api.controller;

import cn.xyzs.api.service.ChatRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chatRoom")
public class ChatRoomController {

    @Resource
    private ChatRoomService chatRoomService;

    /**
     *
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 9:21
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getServicePersonalInfoByCtrCode")
    public Map<String ,Object> getServicePersonalInfoByCtrCode(String ctrCode){
        return chatRoomService.getServicePersonalInfoByCtrCode(ctrCode);
    }

    /**
     * 添加聊天记录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 16:49
     * @param: [ctrCode, userId, chatingContent, contentType]
     * @return: void
     */
    @ResponseBody
    @RequestMapping("/addChattingRecords")
    public Map<String ,Object> addChattingRecords (String ctrCode , String userId ,String sendDate ,String chatingContent , String contentType){
        return chatRoomService.addChattingRecords(ctrCode,userId,sendDate,chatingContent,contentType);
    }

    /**
     * 获取离线消息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 17:07
     * @param: [lastSendDate, ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getOfflineMessage")
    public Map<String ,Object> getOfflineMessage (String userId ,String ctrCode , String [] sendDates){
        return chatRoomService.getOfflineMessage(userId,ctrCode,sendDates);
    }

    /**
     * 获取离线消息(2)
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 17:07
     * @param: [lastSendDate, ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getOfflineMessageByDateNode")
    public Map<String ,Object> getOfflineMessageByDateNode (String ctrCode ,String dateNode ,String selectFlag){
        return chatRoomService.getOfflineMessageByDateNode(ctrCode,dateNode,selectFlag);
    }
}
