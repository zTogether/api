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

    @ResponseBody
    @RequestMapping("/signUp")
    public Map<String ,Object> signUp( String grId, String pgId, String endDate ,String ctrCode){
        return openTenderService.signUp(grId,pgId,endDate,ctrCode);
    }

    @ResponseBody
    @RequestMapping("/grabSingle")
    public Map<String ,Object> grabSingle(String pgId,String grId,String endDate,String ctrCode,String grGz){
        return openTenderService.grabSingle(pgId,grId,endDate,ctrCode,grGz);
    }
}
