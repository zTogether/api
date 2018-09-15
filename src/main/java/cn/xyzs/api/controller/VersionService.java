package cn.xyzs.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/version")
public class VersionService {

    @ResponseBody
    @RequestMapping("/versionTest")
    public Map<String ,Object> versionTest(){
        Map<String ,Object> resultMap = new HashMap<>();
        resultMap.put("version" ,"1.1");
        return resultMap;
    }
}
