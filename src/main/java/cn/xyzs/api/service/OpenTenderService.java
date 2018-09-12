package cn.xyzs.api.service;

import cn.xyzs.api.mapper.VwXyPgWaiterMapper;
import cn.xyzs.api.mapper.XyGcbGrxxMapper;
import cn.xyzs.api.mapper.XyPgMapper;
import cn.xyzs.api.mapper.XyPgWaiterMapper;
import cn.xyzs.api.pojo.VwXyPgWaiter;
import cn.xyzs.api.pojo.XyPgWaiter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenTenderService {
    @Resource
    private VwXyPgWaiterMapper vwXyPgWaiterMapper;

    @Resource
    private XyPgWaiterMapper xyPgWaiterMapper;

    @Resource
    private XyPgMapper xyPgMapper;

    @Resource
    private XyGcbGrxxMapper xyGcbGrxxMapper;

    /**
     * 获取派工开单信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/7 14:01
     * @param: [pgId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getOpenTenderInfo(String grId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            //获取可投标list
            List<Map<String ,Object>> vwXyPgWaiters = vwXyPgWaiterMapper.getVwXyPgWaiters(grId);
            //获取投标历史纪录list
            List<Map<String, Object>> failureTenders = xyPgWaiterMapper.getTenderHistoryList(grId,"抢单失败");
            List<Map<String, Object>> registeredTenders = xyPgWaiterMapper.getTenderHistoryList(grId,"已报名");
            List<Map<String, Object>> constructionSiteIngList = xyPgWaiterMapper.getConstructionSiteIngList(grId);
            List<Map<String, Object>> constructionSiteList = xyPgWaiterMapper.getConstructionSiteList(grId);
            code = "200";
            msg = "成功";
            obj.put("vwXyPgWaiters",vwXyPgWaiters);
            obj.put("constructionSiteIngList",constructionSiteIngList);
            obj.put("constructionSiteList",constructionSiteList);
            obj.put("failureTenders",failureTenders);
            obj.put("registeredTenders",registeredTenders);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 报名
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:31
     * @param: [grId, pgId, endDate, ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> signUp( String grId, String pgId, String endDate,String ctrCode){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            xyPgWaiterMapper.addXyPgWaiterInfo(grId,pgId,endDate,ctrCode,"已报名");
            code = "200";
            msg = "报名成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 抢单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:32
     * @param: [pgId, grId, endDate, ctrCode, grGz]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> grabSingle(String pgId,String grId, String endDate,String ctrCode,String grGz){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Map<String ,Object> xyPgInfo =  xyPgMapper.getXyPgInfoByPgId(pgId);
            if (xyPgInfo != null){
                String PG_GR = String.valueOf(xyPgInfo.get("PG_GR"));
                if (PG_GR == null || "".equals(PG_GR) || "null".equals(PG_GR)){
                    xyPgMapper.updatePgGr(pgId,grId);
                    xyPgWaiterMapper.addXyPgWaiterInfo(grId,pgId,endDate,ctrCode,"抢单成功");
                    xyGcbGrxxMapper.updateGrabSingleLevel(grId);
                    Integer constructionSiteIngCount = xyPgWaiterMapper.getConstructionSiteIngCount(grId);
                    if ("10".equals(grGz) || "21".equals(grGz)){
                        if (constructionSiteIngCount == 5){
                            xyPgWaiterMapper.deleteRegisteredTenders(grId);
                        }
                    } else {
                        if (constructionSiteIngCount == 1){
                            xyPgWaiterMapper.deleteRegisteredTenders(grId);
                        }
                    }
                    code = "200";
                    msg = "抢单成功";
                } else {
                    code = "300";
                    msg = "抢单失败";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

}
