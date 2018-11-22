package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyPdcaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XyPdcaService {

    @Resource
    private XyPdcaMapper xyPdcaMapper;

    /**
     * 根据userId获取所有的报表
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/10 9:56
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getPdcaByUserId(String userId ,String beginDate ,String endDate) {
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String msg = "系统异常";
        String code = "500";
        try {
            if ("".equals(beginDate) || beginDate == null){
                beginDate = "2008-01-01 00:00:00";
                endDate = "2100-12-31 23:59:59";
            }
            List<Map<String ,Object>> pdcaList = xyPdcaMapper.getPdcaByUserId(userId,beginDate,endDate);
            code = "200";
            msg = "成功";
            obj.put("pdcaList",pdcaList);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取下级报表
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/10 17:15
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getSubordinatePdca(String userId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String msg = "系统异常";
        String code = "500";
        try {
            List<Map<String ,Object>> subordinatePdcaList = xyPdcaMapper.getSubordinatePdca(userId);
            code = "200";
            msg = "成功";
            obj.put("subordinatePdcaList",subordinatePdcaList);
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
