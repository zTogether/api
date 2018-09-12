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

    /**
     * 获取开标抢单的信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:18
     * @param: [grId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getOpenTenderInfo")
    public Map<String ,Object> getOpenTenderInfo(String grId){
        return openTenderService.getOpenTenderInfo(grId);
    }

    /**
     * 报名
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:19
     * @param: [grId, pgId, endDate, ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/signUp")
    public Map<String ,Object> signUp( String grId, String pgId, String endDate ,String ctrCode){
        return openTenderService.signUp(grId,pgId,endDate,ctrCode);
    }

    /**
     * 抢单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:19
     * @param: [pgId, grId, endDate, ctrCode, grGz]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/grabSingle")
    public Map<String ,Object> grabSingle(String pgId,String grId,String endDate,String ctrCode,String grGz){
        return openTenderService.grabSingle(pgId,grId,endDate,ctrCode,grGz);
    }
}
