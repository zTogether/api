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

}
