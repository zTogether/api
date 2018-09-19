package cn.xyzs.api.controller;

import cn.xyzs.api.service.AppSystemUpdateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/appSystemUpdate")
public class AppSystemUpdateController{

    @Resource
    private AppSystemUpdateService appSystemUpdateService;

    /**
     * 获取app当前最新版本
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/15 15:15
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getAppSystemVersion")
    public Map<String ,Object> getAppSystemVersion(){
        return appSystemUpdateService.getAppSystemVersion();
    }
}
