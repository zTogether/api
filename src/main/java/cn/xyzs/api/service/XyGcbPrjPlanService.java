package cn.xyzs.api.service;

import cn.xyzs.api.mapper.DateMapper;
import cn.xyzs.api.mapper.XyGcbPrjPlanMapper;
import cn.xyzs.common.pojo.XyClbZcOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
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
    public Map<String,Object> getPrjPlan(String ctrCode,String roleName,String edit){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> everyDay = new LinkedHashMap<>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String dd = dateFormat.format(d);
            Date nowDate = dateFormat.parse(dd);
            List<Map<String,Object>> planList = xyGcbPrjPlanMapper.getGcbPrjPlan(ctrCode,roleName,edit);
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
    public Map<String,Object> isDG(String prjId,String content,String userId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = dateMapper.getSysDate();
            Date date = dateFormat.parse(d);
            xyGcbPrjPlanMapper.isDaiGou(date,prjId,content,userId);
            if(content.equals("客户自购")){
                String conIdList = xyGcbPrjPlanMapper.getConPrj(prjId);
                String[] arr = conIdList.split(",");
                for(int i=0;i<arr.length;i++){
                    String rowId = arr[i];
                    Map<String,Object> onePrj = xyGcbPrjPlanMapper.getOnePrj(rowId);
                    if(onePrj.get("ROEL_NAME").toString().equals("材料中心")){
                        xyGcbPrjPlanMapper.toEnsure(date,rowId,userId);
                    }
                    xyGcbPrjPlanMapper.addPrjMark(rowId,content);
                }
            }
            if (content.equals("无需要")){
                String conIdList = xyGcbPrjPlanMapper.getConPrj(prjId);
                String[] arr = conIdList.split(",");
                for(int i=0;i<arr.length;i++){
                    String rowId = arr[i];
                    xyGcbPrjPlanMapper.toEnsure(date,rowId,userId);
                    xyGcbPrjPlanMapper.addPrjMark(rowId,content);
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
    public Map<String,Object> getLcd(String rowId,String ctrCode){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try{
            List lcdList = xyGcbPrjPlanMapper.getLcd(rowId,ctrCode);
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
        String lcdMark = "";
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = dateMapper.getSysDate();
            Date date = dateFormat.parse(d);
            for (Map<String, Object> map : lcdList) {
                zcpbId = map.get("zcpbId").toString();
                if(map.get("quantity")!=null&&map.get("quantity")!=""){
                    quantity = map.get("quantity").toString();
                }
                if(map.get("lcdMark")!=null&&map.get("lcdMark")!=""){
                    lcdMark = map.get("lcdMark").toString();
                }
                //添加到量尺单
                xyGcbPrjPlanMapper.addLcd(prjId,zcpbId,quantity,ctrCode,lcdMark);
            }
            //修改plan状态
            xyGcbPrjPlanMapper.toEnsure(date,prjId,userId);
            xyGcbPrjPlanMapper.addPrjMark(prjId,prjMark);
            List<Map<String,Object>> lcdList1 = xyGcbPrjPlanMapper.getLcdList(ctrCode);
            for (Map<String, Object> map : lcdList1) {
                String orderJe = String.valueOf(map.get("JE"));
                String orderSup = String.valueOf(map.get("ZC_SUP"));
                String type = String.valueOf(map.get("ZCPB_DC"));
                XyClbZcOrder xyClbZcOrder = new XyClbZcOrder();
                xyClbZcOrder.setCtrCode(ctrCode);
                xyClbZcOrder.setOrderJe(orderJe);
                xyClbZcOrder.setOpUserid(userId);
                xyClbZcOrder.setOrderSup(orderSup);
                xyClbZcOrder.setOrderType(type);
                //添加订单主表
                xyGcbPrjPlanMapper.addOrder(xyClbZcOrder);
                //获取生成的主表ID
                String orderId = xyClbZcOrder.getOrderId();
                if("1".equals(type)){
                    //后付费list
                    List<Map<String,Object>> orderList = xyGcbPrjPlanMapper.getOrderList(ctrCode,orderSup,"11");
                    addList(orderList,orderId,orderSup);
                }else{
                    //代购list
                    List<Map<String,Object>> orderList = xyGcbPrjPlanMapper.getOrderList(ctrCode,orderSup,"");
                    addList(orderList,orderId,orderSup);
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

    public void addList(List<Map<String,Object>> orderList,String orderId,String orderSup){
        try{
            for (Map<String, Object> objectMap : orderList) {
                String zcCode = objectMap.get("ZCPB_ZC_CODE").toString();
                String zcName = objectMap.get("ZC_NAME").toString();
                String zcType = objectMap.get("ZC_TYPE").toString();
                String zcPriceIn = objectMap.get("ZC_PRICE_IN").toString();
                String zcPriceOut = objectMap.get("ZC_PRICE_OUT").toString();
                String zcQty = String.valueOf(objectMap.get("QUANTITY"));
                String zcBrand = objectMap.get("ZC_BRAND").toString();
                String zcSpec = objectMap.get("ZC_SPEC").toString();
                String zcMaterial = objectMap.get("ZC_MATERIAL").toString();
                String zcColor = objectMap.get("ZC_COLOR").toString();
                String zcUnit = objectMap.get("ZC_UNIT").toString();
                String zcMark = String.valueOf(objectMap.get("MARK"));
                String zcCyc = objectMap.get("ZC_CYC").toString();
                String zcArea = objectMap.get("ZC_AREA").toString();
                String zcVersion = objectMap.get("ZC_VERSION").toString();
                String lcdId = objectMap.get("LCDID").toString();
                //添加订单LIST表
                xyGcbPrjPlanMapper.addOrderList(orderId,zcCode,zcName,zcType,zcPriceIn,zcPriceOut,zcQty,zcBrand,orderSup,
                        zcSpec,zcMaterial,zcColor,zcUnit,zcMark,zcCyc,zcArea,zcVersion);
                xyGcbPrjPlanMapper.updateLcdOrderState(lcdId);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     *
     * @Description: 获取量尺单列表
     * @author: GeWeiliang
     * @date: 2018\11\14 0014 16:57
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> showLcd(String ctrCode,String prjId){
        String code = "";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            List<Map<String,Object>> lcd =  xyGcbPrjPlanMapper.showLcdByCtrCode(ctrCode,prjId);
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

    /**
     *
     * @Description: 根据uerId获取日程
     * @author: GeWeiliang
     * @date: 2018\12\14 0014 18:16
     * @param: [userId, roleName, addr]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> planTable(String userId,String roleName,String ctrCode,String date1,String date2,String ctrTel){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String dd = dateFormat.format(d);
            Date nowDate = dateFormat.parse(dd);
            List<Map<String,Object>> planList = xyGcbPrjPlanMapper.getMyPlan(userId,roleName,ctrCode,date1,date2,ctrTel);
            for (int i=0;i<planList.size();i++){
                Map<String,Object> map = planList.get(i);
                Date date0 = dateFormat.parse(map.get("DAYS").toString());
                String date = dateFormat.format(map.get("DAYS"));
                long days = (date0.getTime()-nowDate.getTime())/(1000*3600*24);
                if(days==0){
                    map.put("DAYS","今天");
                }else if(days==1){
                    map.put("DAYS","明天");
                }else if(days==2){
                    map.put("DAYS","后天");
                }else{
                    map.put("DAYS",date);
                }
                if(map.containsKey("EDIT_DATE")){
                    String opDate = dateFormat.format(map.get("EDIT_DATE"));
                    map.put("opDate",opDate);
                }
                int compareTo = date0.compareTo(nowDate);
                map.put("compareDate",compareTo);
            }
            obj.put("planList",planList);
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
     * @Description: 获取我的所有工地的地址
     * @author: GeWeiliang
     * @date: 2018\12\14 0014 16:52
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getMyPrjAddr(String userId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            List<Map<String,Object>> list = xyGcbPrjPlanMapper.getMyPrjAddr(userId);
            obj.put("addrList",list);
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
