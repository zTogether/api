package cn.xyzs.api.controller;

import cn.xyzs.api.service.ChatGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/chatGroup")
public class ChatGroupController {

    @Resource
    private ChatGroupService chatGroupService;

    /**
     * 获取用户所属的所有聊天分组
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/18 17:56
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getChatGroupByUserTel")
    public Map<String ,Object> getChatGroupByUserId(String userTel) {
        return chatGroupService.getChatGroupByUserTel(userTel);
    }

    /**
     * 根据条件和userId获取用户群组
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/19 15:20
     * @param: [userId, condition]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getChatGroupByConditionAndUserTel")
    public Map<String ,Object> getChatGroupByConditionAndUserTel(String userTel , String condition) {
        return chatGroupService.getChatGroupByConditionAndUserTel(userTel,condition);
    }
}
