package cn.xyzs.api.controller;

import cn.xyzs.api.pojo.MvChatGroup;
import cn.xyzs.api.service.MvChatGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/chat")
public class MvChatController {
    @Resource
    private MvChatGroupService mvChatGroupService;

    @RequestMapping("/getGroup")
    @ResponseBody
    public Map<String,Object> queryChatGroup(MvChatGroup chatGroup){
        return mvChatGroupService.queryChatGroup(chatGroup);
    }
}
