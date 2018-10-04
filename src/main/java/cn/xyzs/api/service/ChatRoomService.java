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
public class ChatRoomService {

    @Resource
    private XyCustomerInfoMapper xyCustomerInfoMapper;
    @Resource
    private XyPgMapper xyPgMapper;

    /**
     *
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 9:21
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getServicePersonalInfoByCtrCode(String ctrCode){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> servicePersonalInfoList = new ArrayList<>();
            Map<String ,Object> servicePersonalInfoMap = xyCustomerInfoMapper.getServicePersonalInfoByCtrCode(ctrCode);
            //家装顾问
            Map<String ,Object> tempInfoMap = new HashMap<>();
            tempInfoMap.put("id",servicePersonalInfoMap.get("CTR_WAITER"));
            tempInfoMap.put("name",servicePersonalInfoMap.get("CTR_WAITER_NAME"));
            tempInfoMap.put("roleName","家装顾问");
            servicePersonalInfoList.add(tempInfoMap);
            //设计师
            tempInfoMap = new HashMap<>();
            tempInfoMap.put("id",servicePersonalInfoMap.get("CTR_SJS"));
            tempInfoMap.put("name",servicePersonalInfoMap.get("CTR_SJS_NAME"));
            tempInfoMap.put("roleName","设计师");
            servicePersonalInfoList.add(tempInfoMap);
            //执行总监
            tempInfoMap = new HashMap<>();
            tempInfoMap.put("id",servicePersonalInfoMap.get("CTR_GCJL"));
            tempInfoMap.put("name",servicePersonalInfoMap.get("CTR_GCJL_NAME"));
            tempInfoMap.put("roleName","执行总监");
            servicePersonalInfoList.add(tempInfoMap);
            //材料督导
            tempInfoMap = new HashMap<>();
            tempInfoMap.put("id",servicePersonalInfoMap.get("CTR_CLDD"));
            tempInfoMap.put("name",servicePersonalInfoMap.get("CTR_CLDD_NAME"));
            tempInfoMap.put("roleName","材料督导");
            servicePersonalInfoList.add(tempInfoMap);
            //区域老总
            tempInfoMap = new HashMap<>();
            tempInfoMap.put("id",servicePersonalInfoMap.get("CTR_AREA_MA"));
            tempInfoMap.put("name",servicePersonalInfoMap.get("CTR_AREA_MA_NAME"));
            tempInfoMap.put("roleName","区域老总");
            servicePersonalInfoList.add(tempInfoMap);
            //合约成效人
            tempInfoMap = new HashMap<>();
            tempInfoMap.put("id",servicePersonalInfoMap.get("CTR_OWENER"));
            tempInfoMap.put("name",servicePersonalInfoMap.get("CTR_OWENER_NAME"));
            tempInfoMap.put("roleName","合约成效人");
            servicePersonalInfoList.add(tempInfoMap);
            //工人
            List<Map<String ,Object>> grInfoList = xyPgMapper.getGrInfoListByCtrCode(ctrCode);
            for (Map<String, Object> map : grInfoList) {
                tempInfoMap = new HashMap<>();
                tempInfoMap.put("id",map.get("PG_GR"));
                tempInfoMap.put("name",map.get("GR_NAME"));
                tempInfoMap.put("roleName","工人");
                servicePersonalInfoList.add(tempInfoMap);
            }
            code = "200";
            msg = "成功";
            obj.put("servicePersonalInfoList",servicePersonalInfoList);

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
