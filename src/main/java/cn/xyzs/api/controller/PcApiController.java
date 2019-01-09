package cn.xyzs.api.controller;

import cn.xyzs.api.service.MvSysSmsService;
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

    @Resource
    private MvSysSmsService mvSysSmsService;

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


    /**
     * 发送获取提醒
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/24 11:27
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/sendActivityReminder")
    public Map<String ,Object> sendActivityReminder(){
        return mvSysSmsService.sendActivityReminder();
    }

    /**
     *
     * @Description: 自定义短信接口
     * @author: GeWeiliang
     * @date: 2019\1\9 0009 13:28
     * @param: [phoneNum, param, templateId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/sendMsgModel")
    public Map<String,Object> msgModel(String phoneNum,String param,int templateId){
        return mvSysSmsService.snedMsgModel(phoneNum,param,templateId);
    }
}
