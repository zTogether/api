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
     *
     * @Description: 通知执行员派单成功短信
     * @author: GeWeiliang
     * @date: 2018\10\21 0021 11:18
     * @param: [ctrCode(档案号), gongZhong(工种), gongZhang(工长), tel(工长电话), phone(执行员电话)]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/sendPgMsg")
    public Map<String,Object> sendPgMsg(String ctrCode,String gongZhong,String gongZhang,String tel,String phone){
        return pcApiService.sendPgMsg(ctrCode,gongZhong,gongZhang,tel,phone);
    }

    /**
     *
     * @Description: 抢单成功提醒短信
     * @author: GeWeiliang
     * @date: 2018\10\21 0021 11:28
     * @param: [ctrCode, date(进场日期), exDir(执行总监), tel(执行总监电话), phone(抢单者电话)]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/sendQdMsg")
    public Map<String,Object> sendQdMsg(String ctrCode,String date,String exDir,String tel,String phone){
        return pcApiService.sendQdMsg(ctrCode,date,exDir,tel,phone);
    }
}
