package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyPgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GrEngineeringService {

    @Resource
    private XyPgMapper xyPgMapper;

    /**
     * 工人获取自己所有的工地信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/12 10:54
     * @param: [grId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getGrEngineering(String grId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> grEngineeringLsit = xyPgMapper.getMyPrjList(grId);
            List<Map<String ,Object>> notApplyEngineeringList = new ArrayList<>();
            List<Map<String ,Object>> applyEngineeringList = new ArrayList<>();
            List<Map<String ,Object>> endApplyEngineeringList = new ArrayList<>();
            for (Map<String, Object> map : grEngineeringLsit) {
                if ("0".equals(String.valueOf(map.get("PG_MONEY_YN")))){
                    notApplyEngineeringList.add(map);
                } else if ("1".equals(String.valueOf(map.get("PG_MONEY_YN")))){
                    applyEngineeringList.add(map);
                } else if ("2".equals(String.valueOf(map.get("PG_MONEY_YN")))){
                    endApplyEngineeringList.add(map);
                }
            }
            code = "200";
            msg = "修改成功";
            obj.put("notApplyEngineeringList",notApplyEngineeringList);
            obj.put("applyEngineeringList",applyEngineeringList);
            obj.put("endApplyEngineeringList",endApplyEngineeringList);
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
