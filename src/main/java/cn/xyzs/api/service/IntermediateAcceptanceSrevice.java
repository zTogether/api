package cn.xyzs.api.service;

import cn.xyzs.api.mapper.DateMapper;
import cn.xyzs.api.mapper.XyPgWaiterMapper;
import cn.xyzs.api.mapper.XyPgYsMapper;
import cn.xyzs.api.mapper.XyValMapper;
import cn.xyzs.api.pojo.XyPgWaiter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IntermediateAcceptanceSrevice {

    @Resource
    private XyPgYsMapper xyPgYsMapper;

    @Resource
    private DateMapper dateMapper;

    @Resource
    private XyPgWaiterMapper xyPgWaiterMapper;

    @Resource
    private XyValMapper xyValMapper;

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

    /**
     * 验收提交申请
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/28 14:36
     * @param: [ctrCode, ysGz, opUserId, zxyMark, custMark]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> inspectionSubmitApply(String ctrCode, String ysGz ,String opUserId, String zxyMark){
        Map<String, Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Integer count = xyPgYsMapper.getCountByCtrCodeAndYszt(ctrCode,ysGz);
            if (count > 0){
                code = "300";
                msg = "未达到验收标准";
            } else {
                if ("10".equals(ysGz) || "21".equals(ysGz)){
                    String sysDate = dateMapper.getSysDate();
                    xyPgYsMapper.addYanshou(ctrCode,ysGz,opUserId,"1",zxyMark,null,sysDate);
                    xyPgWaiterMapper.updateYsDate(ctrCode,sysDate,ysGz);
                } else {
                    xyPgYsMapper.addYanshouB(ctrCode,ysGz,opUserId,"0",zxyMark,null);
                }
                code = "200";
                msg = "验收成功";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 获取允许验收的工种
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/28 16:17
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public Map<String ,Object> getSllowYsGz(String ctrCode){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> sllowYsGzList = xyValMapper.getSllowYsGz(ctrCode);
            obj.put("sllowYsGzList",sllowYsGzList);
            code = "200";
            msg = "验收成功";
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
