package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyCustomerInfoMapper;
import cn.xyzs.api.mapper.XyPgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebsocketService {

    @Resource
    private XyPgMapper xyPgMapper;
    @Resource
    private XyCustomerInfoMapper xyCustomerInfoMapper;

    /**
     * 获取用户所包含的服务人员（接待人员，设计师，执行总监，材料导购，区域老总，合约成效人，以及所有的施工工人）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 8:56
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     */
    public List<Map<String ,String >> getUserLsit(String ctrCode){
        List<Map<String ,String>> userIdList = new ArrayList<>();
        try {
            Map<String ,String> tempUserIdList = null;
            tempUserIdList = xyCustomerInfoMapper.getServicePersonalByCtrCode(ctrCode);
            for (String s : tempUserIdList.keySet()) {
                Map<String ,String> tempMap = new HashMap<>();
                tempMap.put("userId" ,s);
                userIdList.add(tempMap);
            }
            List<Map<String ,String>> tempGrIdList = xyPgMapper.getGrIdS(ctrCode);
            for (Map<String, String> map : tempGrIdList) {
                Map<String ,String> tempMap = new HashMap<>();
                tempMap.put("userId" ,map.get("PG_GR"));
                userIdList.add(tempMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIdList;
    }
}
