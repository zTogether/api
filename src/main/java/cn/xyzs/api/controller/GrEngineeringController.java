package cn.xyzs.api.controller;

import cn.xyzs.api.service.GrEngineeringService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/grEngineering")
public class GrEngineeringController {

    @Resource
    private GrEngineeringService grEngineeringService;

    /**
     * 工人获取未申请工资的工地信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/12 10:54
     * @param: [grId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getNotApplyEngineeringList")
    public Map<String ,Object> getNotApplyEngineeringList(String grId){
        return grEngineeringService.getNotApplyEngineeringList(grId);
    }

    /**
     * 工人获取已申请工资的工地信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/14 13:38
     * @param: [grId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getApplyEngineeringList")
    public Map<String ,Object> getApplyEngineeringList(String grId){
        return grEngineeringService.getApplyEngineeringList(grId);
    }

    /**
     * 工人获取已发放工资的工地信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/14 13:38
     * @param: [grId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getGrgzMainLsit")
    public Map<String ,Object> getGrgzMainLsit(String grId){
        return grEngineeringService.getGrgzMainLsit(grId);
    }
}
