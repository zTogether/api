package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyPdcaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    /**
     *
     * @Description: 获取每周的工作问题，需要的资源，总经理意见
     * @author: GeWeiliang
     * @date: 2018\11\24 0024 11:10
     * @param: [userId, pdcaId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getWeekContent(String userId,String pdcaId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new LinkedHashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try{
            List list = xyPdcaMapper.getWeekContent(userId,pdcaId);
            obj.put("weekContent",list);
            code = "200";
            msg = "成功";
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    public Map<String,Object> getWeekSummary(String pdcaId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try{
            List sumList = xyPdcaMapper.getWeekSummary(pdcaId);
            obj.put("SNP",sumList);
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

    public Map<String,Object> getWeekPlan(String pdcaId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try{
            List planList = xyPdcaMapper.getWeekPlan(pdcaId);
            obj.put("SNP",planList);
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

    public Map<String,Object> getDayContent(String pdcaId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> dayList = new LinkedHashMap<>();
        try{
            List<Map<String,Object>> list = xyPdcaMapper.getDayContent(pdcaId);
            for (int i=0;i<list.size();i++){
                Map<String,Object> map = list.get(i);
                List contentList = new ArrayList();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = dateFormat.format(map.get("DATES"));
                map.put("DATES",date);
                if(dayList.containsKey(map.get("DATES"))){
                    List<Map<String,Object>> existList =(List<Map<String,Object>>) dayList.get(map.get("DATES"));
                    existList.add(map);
                    dayList.put(map.get("DATES").toString(),existList);
                }else{
                    contentList.add(map);
                    dayList.put(map.get("DATES").toString(),contentList);
                }
            }
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",dayList);
        }
        return resultMap;
    }

    public Map<String,Object> addPdcaList(String pdcaId,String week,String PSummary,String res){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String date = dateFormat.format(d);
            xyPdcaMapper.addPdcaList(pdcaId,week,date,PSummary,res);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    public Map<String,Object> deletePdcaList(String pdcaId,String week,String res){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            xyPdcaMapper.deletePdcaList(pdcaId,week,res);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    public Map<String,Object> updatePdcaPcontent(String pdcaId,String week,String res,String content){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String date = dateFormat.format(d);
            xyPdcaMapper.updatePdcaPcontent(pdcaId,week,res,content);
            xyPdcaMapper.updateLastDate(date,pdcaId);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    public Map<String,Object> updatePsummary(String pdcaId,String week,String content){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String date = dateFormat.format(d);
            xyPdcaMapper.updatePdcaSummary(pdcaId,week,content);
            xyPdcaMapper.updateLastDate(date,pdcaId);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    public Map<String,Object> addSumCol(String pdcaId,String res){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try {
            xyPdcaMapper.addSumCol(pdcaId,res);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    public Map<String,Object> addPlanCol(String pdcaId,String res){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            xyPdcaMapper.addPlanCol(pdcaId,res);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }
    public Map<String,Object> updateWeekSummary(String pdcaId,String content,String res){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String date = dateFormat.format(d);
            xyPdcaMapper.updateWeekSummary(pdcaId,content,res);
            xyPdcaMapper.updateLastDate(date,pdcaId);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    public Map<String,Object> updateWeekPlan(String pdcaId,String content,String res){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String date = dateFormat.format(d);
            xyPdcaMapper.updateWeekPlan(pdcaId,content,res);
            xyPdcaMapper.updateLastDate(date,pdcaId);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    public Map<String,Object> updatePdca(String pdcaId,String position,String opinion,String PResources,String issue){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String date = dateFormat.format(d);
            xyPdcaMapper.updatePdca(pdcaId,position,PResources,opinion,issue);
            xyPdcaMapper.updateLastDate(date,pdcaId);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    public Map<String,Object> delelteWeekSum(String pdcaId,String res){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            xyPdcaMapper.deleteWeekSum(pdcaId,res);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    public Map<String,Object> deleteWeekPlan(String pdcaId,String res){
            String code = "500";
            String msg = "系统异常";
            Map<String,Object> resultMap = new HashMap<>();
            try{
                xyPdcaMapper.deleteWeekPlan(pdcaId,res);
                code = "200";
                msg = "成功";
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                resultMap.put("code",code);
                resultMap.put("msg",msg);
            }
            return resultMap;
    }
}
