package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyLotteryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/lottery")
public class XyLotteryController {
    @Resource
    private XyLotteryService xyLotteryService;

    @ResponseBody
    @RequestMapping("/getLotNum")
    public Map<String,Object> getLotNum(){
       return   xyLotteryService.getLotNum();
    }
}
