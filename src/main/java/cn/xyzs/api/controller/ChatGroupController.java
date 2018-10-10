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
}
