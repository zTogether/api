package cn.xyzs.api.service;

import cn.xyzs.api.mapper.MvSysConfigMapper;
import cn.xyzs.common.pojo.MvSysConfig;
import cn.xyzs.common.util.PropertiesUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AppSystemUpdateService {

    @Resource
    private MvSysConfigMapper mvSysConfigMapper;

    /**
     * 获取app当前最新版本
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/15 15:13
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getAppSystemVersion(){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            String appSystemVersionIos = PropertiesUtil.getSourcingValueBykey("APP_SYSTEM_VERSION_IOS");
            String appSystemVersionAndroid = PropertiesUtil.getSourcingValueBykey("APP_SYSTEM_VERSION_ANDROID");
            obj.put("appSystemVersionIos",appSystemVersionIos);
            obj.put("appSystemVersionAndroid",appSystemVersionAndroid);
            code = "200";
            msg = "成功";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }
}
