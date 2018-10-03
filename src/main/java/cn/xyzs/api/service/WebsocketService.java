package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyCustomerInfoMapper;
import cn.xyzs.api.mapper.XyPgMapper;
import cn.xyzs.api.util.SpringUtil;
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
