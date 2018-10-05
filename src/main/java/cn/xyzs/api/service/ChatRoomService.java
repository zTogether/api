package cn.xyzs.api.service;

import cn.xyzs.api.mapper.MvChattingRecordsMapper;
import cn.xyzs.api.mapper.XyCustomerInfoMapper;
import cn.xyzs.api.mapper.XyPgMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private MvChattingRecordsMapper mvChattingRecordsMapper;

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

    /**
     * 添加聊天记录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 16:49
     * @param: [ctrCode, userId, chatingContent, contentType]
     * @return: void
     */
    @Transactional
    public Map<String ,Object> addChattingRecords (String ctrCode , String userId , String chatingContent , String contentType){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            mvChattingRecordsMapper.addChattingRecords(ctrCode,userId,chatingContent,contentType);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 获取离线消息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 17:07
     * @param: [lastSendDate, ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getOfflineMessage (String userId ,String ctrCode , String [] sendDates){
        List<String> sendDateList = new ArrayList<>();
        if (sendDates == null){
            sendDateList.add ("9999-12-12 12:12:12");
        } else {
            for (int i = 0; i <sendDates.length ; i++) {
                sendDateList.add(sendDates[i]);
            }
        }
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            //离线消息
            List<Map<String ,Object>> offlineMessageList = mvChattingRecordsMapper.getOfflineMessage(userId,ctrCode,sendDateList);
            obj.put("offlineMessageList",offlineMessageList);
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
