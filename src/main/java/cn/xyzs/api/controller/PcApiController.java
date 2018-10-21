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
    public Map<String ,Object> sendGiftCode(String phone,String giftCode){
        return pcApiService.sendGiftCode(phone,giftCode);
    }

    /**
     *  http://192.168.11.51:8081/pcApi/sendPgMsg?ctrCode=&gongZhong=&gongZhang=&tel=&phone=
     * @Description: 派工通知短信
     * @author: GeWeiliang
     * @date: 2018\10\21 0021 11:18
     * @param: [pgId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/sendPgMsg")
    public Map<String,Object> sendPgMsg(String pgId){
        return pcApiService.sendPgMsg(pgId);
    }

}
