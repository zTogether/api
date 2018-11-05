package cn.xyzs.api.service;

import cn.xyzs.api.mapper.MvSysSmsMapper;
import cn.xyzs.api.mapper.XyPgMapper;
import cn.xyzs.api.pojo.MvSysSms;
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

    @Resource
    private MvSysSmsMapper mvSysSmsMapper;

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
       String code1 = "500";
       String code2 = "500";
       String msg = "系统异常";
       try{
           Integer isSendMsg = xyPgMapper.isSendMsg(pgId);
           if (isSendMsg <= 1){
               Map<String,Object> obj =  xyPgMapper.getPgMsg(pgId);
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
                   //如果发送成功
                   msg = "发送成功";
                   if ("200".equals(code1)){
                       MvSysSms mvSysSms = new MvSysSms();
                       mvSysSms.setTel(zxyTel);
                       mvSysSms.setSmsContent("您好！"+ctrCode+"（档案号）"+gzName+"已成功派单，工长"+grName+"，电话"+grTel+"，请您尽快联系交底！");
                       mvSysSms.setSendStatus(code1);
                       mvSysSmsMapper.addMvSysSmsInfo(mvSysSms);
                   }
                   if ("200".equals(code2)){
                       MvSysSms mvSysSms = new MvSysSms();
                       mvSysSms.setTel(grTel);
                       mvSysSms.setSmsContent("恭喜您抢单成功！档案号"+ctrCode+"，执行总监"+zxyName+"，电话"+zxyTel+"，请尽快联系交底！");
                       mvSysSms.setSendStatus(code2);
                       mvSysSmsMapper.addMvSysSmsInfo(mvSysSms);
                   }
               }
           } else {
               code1 = "200";
               code2 = "200";
           }
       }catch (SQLException e) {
           e.printStackTrace();
       }
       resultMap.put("code1",code1);
       resultMap.put("code2",code2);
       resultMap.put("msg",msg);
       return resultMap;
   }

}

