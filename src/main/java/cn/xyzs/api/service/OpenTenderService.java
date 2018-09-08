package cn.xyzs.api.service;

import cn.xyzs.api.mapper.VwXyPgWaiterMapper;
import cn.xyzs.api.mapper.XyPgWaiterMapper;
import cn.xyzs.api.pojo.VwXyPgWaiter;
import cn.xyzs.api.pojo.XyPgWaiter;
import org.springframework.stereotype.Service;

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
            List<Map<String, Object>> succesTenders = xyPgWaiterMapper.getTenderHistoryList(grId,"抢单成功");
            List<Map<String, Object>> failureTenders = xyPgWaiterMapper.getTenderHistoryList(grId,"抢单失败");
            List<Map<String, Object>> registeredTenders = xyPgWaiterMapper.getTenderHistoryList(grId,"已报名");
            code = "200";
            msg = "成功";
            obj.put("vwXyPgWaiters",vwXyPgWaiters);
            obj.put("succesTenders",succesTenders);
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
}
