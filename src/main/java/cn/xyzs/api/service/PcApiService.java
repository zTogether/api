package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyPgMapper;
import cn.xyzs.api.util.SendMsgUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PcApiService {
    @Resource
    private XyPgMapper xyPgMapper;

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

   public Map<String,Object> sendPgMsg(String pgId){
       Map<String, Object> resultMap = new HashMap<>();
       Map<String,Object> obj = new HashMap<>();
       String code1 = "500";
       String code2 = "500";
       String msg = "系统异常";
       try{
           obj = xyPgMapper.getPgMsg(pgId);
       }catch (SQLException e) {
           e.printStackTrace();
       }
       String ctrCode = obj.get("CTR_CODE").toString();
       String zxyName = obj.get("ZXY_NAME").toString();
       String zxyTel = obj.get("ZXY_TEL").toString();
       String grName = obj.get("GR_NAME").toString();
       String grTel = obj.get("GR_TEL").toString();
       String gzName = obj.get("GZNAME").toString();

       String []params1 = {ctrCode,gzName,grName,grTel};
       String []params2 = {ctrCode,zxyName,zxyTel};

       code1 = SendMsgUtil.sendMsg("2" , params1 ,zxyTel);
       code2 = SendMsgUtil.sendMsg("3",params2,grTel);
       if ("200".equals(code1)&&"200".equals(code2)){
           msg = "发送成功";
       }
       resultMap.put("code1",code1);
       resultMap.put("code2",code2);
       resultMap.put("msg",msg);
       return resultMap;
   }

}

