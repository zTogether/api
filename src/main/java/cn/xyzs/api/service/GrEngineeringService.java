package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyPgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GrEngineeringService {

    @Resource
    private XyPgMapper xyPgMapper;

    public Map<String ,Object> getGrEngineering(String grId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> grEngineeringLsit = xyPgMapper.getMyPrjList(grId);
            code = "200";
            msg = "修改成功";
            obj.put("grEngineeringLsit",grEngineeringLsit);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }
}
