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
            List<Map<String ,Object>> notApplyEngineeringList = xyPgMapper.getNotApplyEngineeringList(grId);
            List<Map<String ,Object>> applyEngineeringList = xyPgMapper.getApplyEngineeringList(grId);
            List<Map<String ,Object>> endApplyEngineeringList = new ArrayList<>();
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
