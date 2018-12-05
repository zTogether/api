package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyEffectiveInfoMapper;
import cn.xyzs.common.pojo.XyEffectiveInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XyEffectiveInfoService {

    @Resource
    private XyEffectiveInfoMapper xyEffectiveInfoMapper;

    /**
     * 添加有效信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 14:44
     * @param: [xyEffectiveInfo]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> addEffectiveInfo(XyEffectiveInfo xyEffectiveInfo){
        Map<String, Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            xyEffectiveInfoMapper.addEffectiveInfo(xyEffectiveInfo);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resultMap.put("code",code);
        resultMap.put("msg",msg);
        return resultMap;
    }

    /**
     * 修改有效信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 14:44
     * @param: [xyEffectiveInfo]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> updateEffectiveInfo(XyEffectiveInfo xyEffectiveInfo){
        Map<String, Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            xyEffectiveInfoMapper.updateEffectiveInfo(xyEffectiveInfo);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resultMap.put("code",code);
        resultMap.put("msg",msg);
        return resultMap;
    }

    /**
     * 删除有效信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 14:45
     * @param: [xyEffectiveInfo]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> deleteEffectiveInfoByEffectiveInfoId(String effectiveInfoId){
        Map<String, Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            xyEffectiveInfoMapper.deleteEffectiveInfoByEffectiveInfoId(effectiveInfoId);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resultMap.put("code",code);
        resultMap.put("msg",msg);
        return resultMap;
    }

    /**
     * 获取有效信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 14:47
     * @param: [submitUserId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getEffectiveInfoBySubmitUserId(String submitUserId){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> effectiveInfoList = xyEffectiveInfoMapper.getEffectiveInfoBySubmitUserId(submitUserId);
            code = "200";
            msg = "成功";
            obj.put("effectiveInfoList",effectiveInfoList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resultMap.put("code",code);
        resultMap.put("msg",msg);
        resultMap.put("resultData",obj);
        return resultMap;
    }

}
