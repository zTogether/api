package cn.xyzs.api.controller;

import cn.xyzs.api.service.IntermediateAcceptanceSrevice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/IntermediateAcceptance")
public class IntermediateAcceptanceController {

    @Resource
    private IntermediateAcceptanceSrevice intermediateAcceptanceSrevice;

    /**
     * 根据ctrCode获取派工验收表里的信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/24 15:32
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getPgYsList")
    public Map<String ,Object> getPgYsList(String ctrCode){
        return intermediateAcceptanceSrevice.getPgYsList(ctrCode);
    }
}
