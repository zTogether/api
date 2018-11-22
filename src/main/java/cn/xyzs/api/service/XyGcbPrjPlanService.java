package cn.xyzs.api.service;

import cn.xyzs.api.mapper.DateMapper;
import cn.xyzs.api.mapper.XyGcbPrjPlanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class XyGcbPrjPlanService {
    @Resource
    private XyGcbPrjPlanMapper xyGcbPrjPlanMapper;

    @Resource
    private DateMapper dateMapper;

    /**
     *
     * @Description: 根据档案号展示工地日程
     * @author: GeWeiliang
     * @date: 2018\11\10 0010 9:13
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getPrjPlan(String ctrCode){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> everyDay = new LinkedHashMap<>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String dd = dateFormat.format(d);
            Date nowDate = dateFormat.parse(dd);
            List<Map<String,Object>> planList = xyGcbPrjPlanMapper.getGcbPrjPlan(ctrCode);
            for (int i=0;i<planList.size();i++){
                Map<String,Object> map = planList.get(i);
                Date date1 = dateFormat.parse(map.get("DAYS").toString());
                String date = dateFormat.format(map.get("DAYS"));
                if(map.containsKey("EDIT_DATE")){
                    String opDate = dateFormat.format(map.get("EDIT_DATE"));
                    map.put("opDate",opDate);
                }
                map.put("DAYS",date);
                int compareTo = date1.compareTo(nowDate);
                map.put("compareDate",compareTo);
                List<Map<String,Object>> dateList = new ArrayList<>();
                if(everyDay.containsKey(map.get("DAYS"))){
                    List<Map<String,Object>> existList =(List<Map<String,Object>>) everyDay.get(map.get("DAYS"));
                    existList.add(map);
                    everyDay.put(map.get("DAYS").toString(),existList);
                }else{
                    dateList.add(map);
                    everyDay.put(map.get("DAYS").toString(),dateList);
                }
            }
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",everyDay);
        }
        return resultMap;
    }


    /**
     *
     * @Description: 确认日程完成
     * @author: GeWeiliang
     * @date: 2018\11\10 0010 9:40
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> toEnsure(String rowId,String userId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = dateMapper.getSysDate();
            Date editDate = dateFormat.parse(d);
            xyGcbPrjPlanMapper.toEnsure(editDate,rowId,userId);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("msg",msg);
            resultMap.put("code",code);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 添加日程备注
     * @author: GeWeiliang
     * @date: 2018\11\10 0010 9:53
     * @param: [rowId, mark]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> addMark(String rowId,String mark){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            xyGcbPrjPlanMapper.addPrjMark(rowId,mark);
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

    /**
     *
     * @Description: 获取一个日程
     * @author: GeWeiliang
     * @date: 2018\11\10 0010 10:12
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getOnePlan(String rowId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date d = new Date();
            String dd = dateFormat.format(d);
            Date nowDate = dateFormat.parse(dd);
            Map<String,Object> onePlan = xyGcbPrjPlanMapper.getOnePlan(rowId);
            Date date = dateFormat.parse(onePlan.get("DAYS").toString());
            int compareTo = date.compareTo(nowDate);
            onePlan.put("compareDate",compareTo);
            obj.put("onePlan",onePlan);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
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
     * @Description: 是否代购
     * @author: GeWeiliang
     * @date: 2018\11\11 0011 17:16
     * @param: [rowId, content]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> isDG(String rowId,String content,String userId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = dateMapper.getSysDate();
            Date date = dateFormat.parse(d);
            xyGcbPrjPlanMapper.isDaiGou(date,rowId,content,userId);
            if (content.equals("无需要")){
                List<String> prjIdList = xyGcbPrjPlanMapper.getConPrj(rowId);
                for (String prjId : prjIdList) {
                    xyGcbPrjPlanMapper.toEnsure(date,prjId,userId);
                }
            }
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 单个量尺单
     * @author: GeWeiliang
     * @date: 2018\11\16 0016 11:07
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getLcd(String rowId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try{
            List lcdList = xyGcbPrjPlanMapper.getLcd(rowId);
            obj.put("lcdList",lcdList);
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
     * @Description: 添加量尺单
     * @author: GeWeiliang
     * @date: 2018\11\12 0012 17:43
     * @param: [mbId, prjId, quantity, length, width, heigth]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> addLcd(String prjId,String userId,String ctrCode,List<Map<String,Object>> lcdList,String prjMark){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        String zcpbId = "";
        String quantity = "";
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = dateMapper.getSysDate();
            Date date = dateFormat.parse(d);
            for (Map<String, Object> map : lcdList) {
                zcpbId = map.get("zcpbId").toString();
                quantity = map.get("quantity").toString();
                xyGcbPrjPlanMapper.addLcd(prjId,zcpbId,quantity,ctrCode);
            }
            xyGcbPrjPlanMapper.toEnsure(date,prjId,userId);
            xyGcbPrjPlanMapper.addPrjMark(prjId,prjMark);

            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 获取量尺单列表
     * @author: GeWeiliang
     * @date: 2018\11\14 0014 16:57
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> showLcd(String ctrCode){
        String code = "";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            List<Map<String,Object>> lcd =  xyGcbPrjPlanMapper.showLcdByCtrCode(ctrCode);
            obj.put("lcdList",lcd);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("lcdData",obj);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 查看我的在建工地日程
     * @author: GeWeiliang
     * @date: 2018\11\16 0016 9:54
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getMyPlan(String userId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> everyDay = new LinkedHashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String dd = dateFormat.format(d);
            Date nowDate = dateFormat.parse(dd);
            List<Map<String,Object>> list = xyGcbPrjPlanMapper.showMyPlan(userId);
            for (int i=0;i<list.size();i++){
                Map<String,Object> map = list.get(i);
                Date date1 = dateFormat.parse(map.get("DAYS").toString());
                String date = dateFormat.format(map.get("DAYS"));
                if(map.containsKey("EDIT_DATE")){
                    String opDate = dateFormat.format(map.get("EDIT_DATE"));
                    map.put("opDate",opDate);
                }
                map.put("DAYS",date);
                int compareTo = date1.compareTo(nowDate);
                map.put("compareDate",compareTo);
                List<Map<String,Object>> dateList = new ArrayList<>();
                if(everyDay.containsKey(map.get("DAYS"))){
                    List<Map<String,Object>> existList =(List<Map<String,Object>>) everyDay.get(map.get("DAYS"));
                    existList.add(map);
                    everyDay.put(map.get("DAYS").toString(),existList);
                }else{
                    dateList.add(map);
                    everyDay.put(map.get("DAYS").toString(),dateList);
                }
            }

            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",everyDay);
        }
        return resultMap;
    }
}
