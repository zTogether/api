package cn.xyzs.api.controller;

import cn.xyzs.api.service.OpenTenderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/openTender")
public class OpenTenderController {

    @Resource
    private OpenTenderService openTenderService;

    @ResponseBody
    @RequestMapping("/getOpenTenderInfo")
    public Map<String ,Object> getOpenTenderInfo(String grId){
        return openTenderService.getOpenTenderInfo(grId);
    }
}
