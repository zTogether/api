package cn.xyzs.api.controller;

import cn.xyzs.api.service.ChatGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/chatGroup")
public class ChatGroupController {

    @Resource
    private ChatGroupService chatGroupService;

    /**
     * 工人聊天群，获取工人工地
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/30 11:15
     * @param: [grId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @ResponseBody
    @RequestMapping("/getGrConstructionSite")
    public Map<String ,Object> getGrConstructionSite(String grId ,String startNum ,String endNum){
        return chatGroupService.getGrConstructionSite(grId,startNum,endNum);
    }

    /**
     * 工人聊天群，根据条件获取工人工地
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/30 13:46
     * @param: [grId, ctrTel, ctrName, ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @ResponseBody
    @RequestMapping("/getGrConstructionSiteByCondition")
    public Map<String ,Object> getGrConstructionSiteByCondition(String grId , String condition ){
        return chatGroupService.getGrConstructionSiteByCondition(grId,condition);
    }

    /**
     * 获取用户所属的所有聊天分组
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/18 17:56
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getChatGroupByUserId")
    public Map<String ,Object> getChatGroupByUserId(String userId) {
        return chatGroupService.getChatGroupByUserId(userId);
    }

    /**
     * 根据userId获取聊天群(分页)
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/19 14:56
     * @param: [userId, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getChatGroupListByUserIdLimit")
    public Map<String ,Object> getChatGroupListByUserIdLimit(String userId) {
        return chatGroupService.getChatGroupListByUserIdLimit(userId);
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
    @RequestMapping("/getChatGroupByConditionAndUserId")
    public Map<String ,Object> getChatGroupByConditionAndUserId(String userId , String condition) {
        return chatGroupService.getChatGroupByConditionAndUserId(userId,condition);
    }
}
