package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyPgYsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IntermediateAcceptanceSrevice {

    @Resource
    private XyPgYsMapper xyPgYsMapper;

    /**
     * 根据ctrCode获取派工验收表里的信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/24 15:32
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getPgYsList(String ctrCode){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> pgYsList = xyPgYsMapper.getXyPgYsListByCtrCode(ctrCode);
            obj.put("pgYsList",pgYsList);
            code = "200";
            msg = "成功";
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
