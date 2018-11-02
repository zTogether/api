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
     * 根据CtrCode和Jd获取jdJs（在聊天页面使用）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/27 14:25
     * @param: [ctrCode, jd]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getJdjsVByCtrCodeAndJd")
    public Map<String ,Object> getJdjsVByCtrCodeAndJd(String ctrCode , String jd){
        return chatRoomService.getJdjsVByCtrCodeAndJd(ctrCode,jd);
    }
}
