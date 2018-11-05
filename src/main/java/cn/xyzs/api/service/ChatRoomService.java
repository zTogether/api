package cn.xyzs.api.service;

import cn.xyzs.api.mapper.*;
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
    private VwXyJdjsMapper vwXyJdjsMapper;

    /**
     * 根据ctrCode获取所有服务人员信息
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
            List<Map<String ,Object>> tempList = xyCustomerInfoMapper.getChatMemberInfoLsitByCtrCode(ctrCode);
            List<Map<String ,Object>> servicePersonalInfoList = new ArrayList<>();
            Integer repetitionCount = 0;
            String repetitionUserId = "";
            for (Map<String, Object> map : tempList) {
                repetitionCount = 0;
                for (Map<String, Object> tempMap : tempList) {
                    if (String.valueOf(map.get("USER_ID")).equals(String.valueOf(tempMap.get("USER_ID")))){
                        repetitionCount ++;
                        if (repetitionCount > 1){
                            repetitionUserId = String.valueOf(map.get("USER_ID"));
                        }
                    }
                }
                if (repetitionCount > 1){
                    break;
                }
            }
            Integer index = 0;
            if (!"".equals(repetitionUserId) && repetitionUserId != null){
                Map<String ,Object> tempMap = new HashMap<>();
                for (Map<String, Object> map : tempList) {
                    if (repetitionUserId.equals(String.valueOf(map.get("USER_ID")))){
                        if (index == 0) {
                            tempMap.put("USER_ROLE_NAME",map.get("USER_ROLE_NAME"));
                            tempMap.put("USER_ID",map.get("USER_ID"));
                            tempMap.put("USER_NAME",map.get("USER_NAME"));
                            tempMap.put("GROUP_ID",map.get("GROUP_ID"));
                        } else {
                            String tempStr = String.valueOf(tempMap.get("USER_ROLE_NAME")) + "/" + String.valueOf(map.get("USER_ROLE_NAME"));
                            tempMap.put("USER_ROLE_NAME",tempStr);
                        }
                        index ++;
                    } else {
                        servicePersonalInfoList.add(map);
                    }
                }
                servicePersonalInfoList.add(tempMap);
            } else {
                servicePersonalInfoList = tempList;
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


    /**
     * 根据CtrCode和Jd获取jdJs（在聊天页面使用）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/27 14:25
     * @param: [ctrCode, jd]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getJdjsVByCtrCodeAndJd(String ctrCode , String jd){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Map<String ,Object> jdjs = vwXyJdjsMapper.getJdjsVByCtrCodeAndJd(ctrCode,jd);
            obj.put("jdjs",jdjs);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("resultData",obj);
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

}
