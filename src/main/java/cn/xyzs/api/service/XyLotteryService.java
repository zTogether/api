package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyLotteryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XyLotteryService {
    @Resource
    private XyLotteryMapper xyLotteryMapper;

    public Map<String,Object> getLotNum(){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try{
            List<Map<String,Object>> list = xyLotteryMapper.getLotNum();
            obj.put("lotNum",list);
            code = "200";
            msg = "成功";
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
