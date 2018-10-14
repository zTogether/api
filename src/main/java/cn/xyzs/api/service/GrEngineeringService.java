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
     * 工人获取未申请工资的工地信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/12 10:54
     * @param: [grId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getNotApplyEngineeringList(String grId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> notApplyEngineeringList = xyPgMapper.getNotApplyEngineeringList(grId);
            code = "200";
            msg = "";
            obj.put("notApplyEngineeringList",notApplyEngineeringList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 工人获取已申请工资的工地信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/12 10:54
     * @param: [grId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getApplyEngineeringList(String grId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> applyEngineeringList = xyPgMapper.getApplyEngineeringList(grId);
            code = "200";
            msg = "";
            obj.put("applyEngineeringList",applyEngineeringList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 工人获取已发放工资的工地信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/12 10:54
     * @param: [grId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getGrgzMainLsit(String grId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> grgzMainLsit = xyPgMapper.getGrgzMainLsit(grId);
            for (Map<String, Object> map : grgzMainLsit) {
                map.put("endApplyEngineeringList",xyPgMapper.getEndApplyEngineeringList(String .valueOf(map.get("GRGZ_ID"))));
            }
            code = "200";
            msg = "";
            obj.put("grgzMainLsit",grgzMainLsit);
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
