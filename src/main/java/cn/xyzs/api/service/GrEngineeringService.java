package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyPgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

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
            Map<String,Object> newMap = groupByAddr(notApplyEngineeringList);
            code = "200";
            msg = "";
            obj.put("notApplyEngineeringList",newMap);
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
            Map<String,Object> newMap = groupByAddr(applyEngineeringList);
            code = "200";
            msg = "";
            obj.put("applyEngineeringList",newMap);
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
            Map<String,Object> newMap = groupByAddr(grgzMainLsit);
            code = "200";
            msg = "";
            obj.put("grgzMainLsit",newMap);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }
    
    public Map<String, Object> groupByAddr(List<Map<String,Object>> list){
        String ctrCode = "";
        Map<String,Object> grByAddr = new LinkedHashMap<>();
        for (Map<String, Object> map : list) {
            ctrCode = String.valueOf(map.get("CTR_CODE"));
            List<Map<String,Object>> addrList = new ArrayList<>();
            if(grByAddr.containsKey(ctrCode)){
                List<Map<String,Object>> l = (List<Map<String,Object>>) grByAddr.get(ctrCode);
                l.add(map);
                grByAddr.put(ctrCode,l);
            }else{
                addrList.add(map);
                grByAddr.put(ctrCode,addrList);
            }
        }
        return grByAddr;
    }

}
