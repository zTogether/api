package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyInfoManageConstrMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class XyInfoManageConstrService {
    @Resource
    private XyInfoManageConstrMapper xyInfoManageConstrMapper;

    /**
     *
     * @Description: 根据档案号获取记录
     * @author: GeWeiliang
     * @date: 2018\12\27 0027 10:38
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getConstrRecord(String ctrCode){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try{
            //执行总监记录
           List<Map<String,Object>> edList = xyInfoManageConstrMapper.getEdRecord(ctrCode);
           obj.put("edList",recordFl(edList));
           //工程总监记录
           List<Map<String,Object>> rbList = xyInfoManageConstrMapper.getRbRecord(ctrCode);
           obj.put("rbList",recordFl(rbList));
           List<Map<String,Object>> compList = xyInfoManageConstrMapper.getCompContent(ctrCode);
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           for (Map<String, Object> map : compList) {
               if(map.get("SOVLE_DATE")!=null&&map.get("SOVLE_DATE")!=""){
                   String solveDate = dateFormat.format(map.get("SOVLE_DATE"));
                   map.put("SOVLE_DATE",solveDate);
               }
               String comType = String.valueOf(map.get("COM_TYPE"));
               if("0".equals(comType)){
                   map.put("COM_TYPE","回访事项");
               }else if("1".equals(comType)){
                   map.put("COM_TYPE","电话投诉");
               }else if("2".equals(comType)){
                   map.put("COM_TYPE","上门投诉");
               }else if("3".equals(comType)){
                   map.put("COM_TYPE","总经办投诉");
               }else if("4".equals(comType)){
                   map.put("COM_TYPE","限期整改");
               }
           }
           obj.put("compList",compList);
           List<Map<String,Object>> getRevisit = xyInfoManageConstrMapper.getRevisit(ctrCode);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            for (Map<String, Object> map : getRevisit) {
                if(map.get("OP_DATE")!=null&&map.get("OP_DATE")!=""){
                    String reDate = dateFormat2.format(map.get("OP_DATE"));
                    map.put("OP_DATE",reDate);
                }
            }
           obj.put("revisit",getRevisit);
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

    public Map<String,Object> recordFl(List<Map<String,Object>> list){
        List<Map<String,Object>> shuiDian = new ArrayList<>();//水电
        List<Map<String,Object>> waGong = new ArrayList<>();//瓦工
        List<Map<String,Object>> muGong = new ArrayList<>();//木工
        List<Map<String,Object>> youQi = new ArrayList<>();//油漆
        List<Map<String,Object>> anZhuang = new ArrayList<>();//安装
        Map<String,Object> recordMap = new LinkedHashMap<>();
        for (Map<String, Object> map : list) {
            String constrType = String.valueOf(map.get("CONSTRUCTION_TYPE"));
            if("1".equals(constrType)){
                shuiDian.add(map);
            }else if("2".equals(constrType)){
                waGong.add(map);
            }else if("3".equals(constrType)){
                muGong.add(map);
            }else if("4".equals(constrType)){
                youQi.add(map);
            }else if("5".equals(constrType)){
                anZhuang.add(map);
            }
        }
        recordMap.put("水电工程",shuiDian);
        recordMap.put("瓦工工程",waGong);
        recordMap.put("木工工程",muGong);
        recordMap.put("油漆工程",youQi);
        recordMap.put("安装工程",anZhuang);

        return recordMap;
    }

    /**
     *
     * @Description: 添加记录
     * @author: GeWeiliang
     * @date: 2018\12\27 0027 17:31
     * @param: [ctrCode, consType, belongUser, edRecode, rbRecode, userType, xh]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> addConstr(String ctrCode,String consType,String belongUser,String edRecode,
                                        String rbRecode,String userType,String xh){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            xyInfoManageConstrMapper.addConstr(ctrCode,consType,belongUser,edRecode,rbRecode,userType,xh);
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

    public Map<String,Object> deleteConstr(String ctrCode,String consType,String userType,String xh){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            xyInfoManageConstrMapper.deleteConstr(ctrCode,consType,userType,xh);
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

    public Map<String,Object> updateConstr(String ctrCode,String consType,String belongUser,String edRecode,
                                           String rbRecode,String userType,String xh){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            xyInfoManageConstrMapper.updateConstr(edRecode,rbRecode,ctrCode,consType,userType,xh);
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
     * @Description: 获取责任人
     * @author: GeWeiliang
     * @date: 2018\12\29 0029 13:36
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getLiableUser(String ctrCode){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try{
            List<Map<String,Object>> lsit =  xyInfoManageConstrMapper.getLiableUser(ctrCode);
            obj.put("liableUser",lsit);
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
     * @Description: 添加投诉记录
     * @author: GeWeiliang
     * @date: 2018\12\29 0029 9:56
     * @param: [ctrCode, compContent, compType, solveDate, liableUser, opUser]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> addComp(String ctrCode,String compContent,String compType,String solveDate,
                                      String liableUser,String opUser){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new LinkedHashMap<>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date limitDate = dateFormat.parse(solveDate);
            String x = xyInfoManageConstrMapper.getCompNum(ctrCode);
            int xh = Integer.parseInt(x)+1;
            xyInfoManageConstrMapper.addComp(ctrCode,compContent,compType,limitDate,liableUser,opUser,xh);
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
        return  resultMap;
    }

    /**
     *
     * @Description: 删除投诉记录
     * @author: GeWeiliang
     * @date: 2018\12\29 0029 14:59
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> deleteComp(String rowId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
             xyInfoManageConstrMapper.deleteComp(rowId);
             code = "200";
             msg = "500";
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
     * @Description: 责任人回复
     * @author: GeWeiliang
     * @date: 2019\1\3 0003 16:52
     * @param: [rowId, laibleRes]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> liableRes(String rowId,String laibleRes){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try{
            xyInfoManageConstrMapper.resComp(rowId,laibleRes);
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
     * @Description: 添加回访记录
     * @author: GeWeiliang
     * @date: 2019\1\3 0003 16:49
     * @param: [ctrCode, content, opUser]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> addRevisit(String ctrCode,String content,String opUser ){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String l = xyInfoManageConstrMapper.getRevisitNum(ctrCode);
            int xh = Integer.parseInt(l)+1;
            xyInfoManageConstrMapper.addRevisit(ctrCode,content,opUser,xh);
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
