package cn.xyzs.api.controller;

import cn.xyzs.api.service.FunctionalControlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/functionalControl")
public class FunctionalControlController {

    @Resource
    private FunctionalControlService functionalControlService;

    /**
     * 一键报价是否启用（0：不启用，1：启用）
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/29 9:55
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/autoBjIsStart")
    public Map<String ,Object> autoBjIsStart(){
        return functionalControlService.autoBjIsStart();
    }
}
