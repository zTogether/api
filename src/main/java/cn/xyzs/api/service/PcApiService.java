package cn.xyzs.api.service;

import cn.xyzs.api.util.SendMsgUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PcApiService {

    public Map<String ,Object> sendGiftCode(String phone,String giftCode){
        Map<String, Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        String []params = {giftCode};
        code = SendMsgUtil.sendMsg("1" , params ,phone);
        if ("200".equals(code)){
            msg = "发送成功";
        }
        resultMap.put("code",code);
        resultMap.put("msg",msg);
        return resultMap;
    }

    /**
     *
     * @Description: tel:工长电话,phone:执行总监电话
     * @author: GeWeiliang
     * @date: 2018\10\21 0021 11:15
     * @param: [ctrCode, gongZhong, gongZhang, tel, phone]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> sendPgMsg(String ctrCode,String gongZhong,String gongZhang,String tel,String phone){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        String []params = {ctrCode,gongZhong,gongZhang,tel};
        code = SendMsgUtil.sendMsg("2" , params ,phone);
        if ("200".equals(code)){
            msg = "发送成功";
        }
        resultMap.put("code",code);
        resultMap.put("msg",msg);
        return resultMap;
    }

    /**
     *
     * @Description:
     * @author: GeWeiliang
     * @date: 2018\10\21 0021 11:26
     * @param: [ctrCode, exDir(执行总监), tel(执行总监电话), phone(抢单者电话)]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> sendQdMsg(String ctrCode,String date,String exDir,String tel,String phone){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        date = String.format(date, "yyyy-MM-dd");
        String []params = {ctrCode,date,exDir,tel};
        code = SendMsgUtil.sendMsg("3" , params ,phone);
        if ("200".equals(code)){
            msg = "发送成功";
        }
        resultMap.put("code",code);
        resultMap.put("msg",msg);
        return resultMap;
    }
}

