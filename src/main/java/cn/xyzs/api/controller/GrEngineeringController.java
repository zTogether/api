package cn.xyzs.api.controller;

import cn.xyzs.api.service.GrEngineeringService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/grEngineering")
public class GrEngineeringController {

    @Resource
    private GrEngineeringService grEngineeringService;

    /**
     * 工人获取自己所有的工地信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/12 10:54
     * @param: [grId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getGrEngineering")
    public Map<String ,Object> getGrEngineering(String grId){
        return grEngineeringService.getGrEngineering(grId);
    }
}
