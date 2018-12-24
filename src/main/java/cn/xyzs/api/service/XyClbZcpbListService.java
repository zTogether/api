package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyClbZcpbListMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XyClbZcpbListService {
    @Resource
    private XyClbZcpbListMapper xyClbZcpbListMapper;

    /**
     *
     * @Description: 获取一级目录
     * @author: GeWeiliang
     * @date: 2018\12\23 0023 10:04
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getFirstMl(String ctrCode,String zcType){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        List<Map<String,Object>> newList = new ArrayList<>();
        try {
            List<Map<String,Object>> list = xyClbZcpbListMapper.getFirstMl(ctrCode,zcType);
            String mlName = "";
            for (Map<String, Object> map : list) {
                if(!String.valueOf(map.get("ZCPB_ML")).equals(mlName)){
                    mlName = String.valueOf(map.get("ZCPB_ML"));
                    newList.add(map);
                }
            }
            obj.put("firstMl",newList);
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

    /**
     *
     * @Description: 获取主材清单
     * @author: GeWeiliang
     * @date: 2018\12\24 0024 13:58
     * @param: [ctrCode, mlName, zcType]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getZcpbList(String ctrCode,String mlName,String zcType){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try{
            List<Map<String,Object>> list = xyClbZcpbListMapper.getZcpbList(ctrCode,mlName,zcType);
            for (Map<String, Object> map : list) {
                String value = String.valueOf(map.get("ZCPB_DC"));
                if("11".equals(value)){
                    map.put("ZCPB_DC", "后付费");
                }else if("12".equals(value)){
                    map.put("ZCPB_DC", "自由代购");
                }else if("13".equals(value)){
                    map.put("ZCPB_DC", "客户自购");
                }else if("10".equals(value)){
                    map.put("ZCPB_DC", "无此项目");
                }else if("22".equals(value)){
                    map.put("ZCPB_DC", "自由代购");
                }else if("23".equals(value)){
                    map.put("ZCPB_DC", "客户自购");
                }else if("20".equals(value)){
                    map.put("ZCPB_DC", "无此项目");
                }
            }
            //选择项目的总计
            String zj = xyClbZcpbListMapper.selectedZj(ctrCode,mlName,zcType);
            obj.put("xmZj",zj);
            obj.put("zcpbList",list);
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