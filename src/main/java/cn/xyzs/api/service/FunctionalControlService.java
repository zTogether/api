package cn.xyzs.api.service;

import cn.xyzs.common.util.PropertiesUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FunctionalControlService {

    /**
     * 一键报价是否启用（0：不启用，1：启用）
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/29 9:55
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> autoBjIsStart(){
        Map<String, Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            String autoBjIsStart = PropertiesUtil.getSourcingValueBykey("AUTO_BJ_IS_START");
            if ("0".equals(autoBjIsStart)){
                code = "400";
                msg = "一键报价暂未启用";
            } else {
                code = "200";
                msg = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }
}
