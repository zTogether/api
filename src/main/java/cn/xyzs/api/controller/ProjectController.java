package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyPrjService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/prj")
public class ProjectController {
    @Resource
    private XyPrjService xyPrjService;

    @ResponseBody
    @RequestMapping("/rgList")
    public Map<String,Object> getRgList(String ctrCode,String bjdCode){
        return xyPrjService.getRgList(ctrCode,bjdCode);
    }



}
