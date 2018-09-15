package cn.xyzs.api.service;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AppSystemUpdateService {

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
       /* try{
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }*/
        return resultMap;
    }
}
