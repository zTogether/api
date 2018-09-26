package cn.xyzs.api.controller;

import cn.xyzs.api.service.PcApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/pcApi")
public class PcApiController {

    @Resource
    private PcApiService pcApiService;

    @ResponseBody
    @RequestMapping("/sendGiftCode")
    public Map<String ,Object> sendGiftCode(String phone){
        return pcApiService.sendGiftCode(phone);
    }

}
