package cn.xyzs.api.service;

import cn.xyzs.api.mapper.*;
import cn.xyzs.api.pojo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Resource
    private XyCustomerInfoMapper xyCustomerInfoMapper;

    @Resource
    private XyBjdMainMapper xyBjdMainMapper;

    @Resource
    private XyPgMapper xyPgMapper;

    @Resource
    private XyPgLsitMapper xyPgLsitMapper;

    @Resource
    private VwXyJdjsMapper vwXyJdjsMapper;

    @Resource
    private XyClbFcCkdMainMapper xyClbFcCkdMainMapper;


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
                    System.out.println(sysDate);
                    xyPgYsMapper.addYanshou(ctrCode,ysGz,opUserId,"1",zxyMark,null,sysDate);
                    xyPgWaiterMapper.updateYsDate(ctrCode,sysDate,ysGz);
                } else   {
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

    /**
     * 获取派工工种List
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/10 12:49
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getPgGzList(String ctrCode){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Map<String ,Object> rgVer = xyCustomerInfoMapper.getRgVer(ctrCode);
            String rgVerBjSgml = String.valueOf(rgVer.get("RG_VER_BJ_SGML"));
            String []rgVerBjSgmlArray = rgVerBjSgml.split(",");
            List<String> rgVerBjSgmlList = new ArrayList<>();
            for (String s : rgVerBjSgmlArray) {
                rgVerBjSgmlList.add(s);
            }
            List<Map<String ,Object>> pgGzList = xyValMapper.getValist(rgVerBjSgmlList,"B3B32F221FF14256988E7C0A218EBF5C");
            obj.put("pgGzList",pgGzList);
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

    /**
     * 执行派工
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/11 9:17
     * @param: [ctrCode, pgStage, pgBeginDate, pgOpUser]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String, Object> pg(String ctrCode ,String pgStage ,String pgBeginDate ,String pgOpUser){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Integer isPgFlag = xyBjdMainMapper.getIsPg(ctrCode,pgStage);
            if (isPgFlag < 1){
                code = "300";
                msg = "没有派工信息";
            } else {
                Integer isRepetitionPgFlag = xyPgMapper.isRepetitionPg(ctrCode, pgStage);
                if (isRepetitionPgFlag > 0) {
                    code = "301";
                    msg = "不能重复派工";
                } else {
                    if ("31".equals(pgStage)) {
                        Integer isAllowPg = xyPgMapper.isRepetitionPg(ctrCode, "32");
                        if (isAllowPg > 0) {
                            code = "302";
                            msg = "砌筑工和镶贴工总共只能派一次";
                        } else {
                            addPgAndList(ctrCode ,pgStage , pgBeginDate , pgOpUser);
                            code = "200";
                            msg = "派工成功";
                        }
                    } else if ("32".equals(pgStage)) {
                        Integer isAllowPg = xyPgMapper.isRepetitionPg(ctrCode, "31");
                        if (isAllowPg > 0) {
                            code = "302";
                            msg = "砌筑工和镶贴工总共只能派一次";
                        } else {
                            addPgAndList(ctrCode ,pgStage , pgBeginDate , pgOpUser);
                            code = "200";
                            msg = "派工成功";
                        }
                    } else {
                        addPgAndList(ctrCode ,pgStage , pgBeginDate , pgOpUser);
                        code = "200";
                        msg = "派工成功";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    private void addPgAndList(String ctrCode ,String pgStage ,String pgBeginDate ,String pgOpUser) throws SQLException{
        XyPg xyPg = new XyPg();
        // #{ctrCode,jdbcType=VARCHAR}, #{pgStage,jdbcType=VARCHAR}, TO_DATE( #{pgBeginDate,jdbcType=VARCHAR}, 'yyyy-MM-dd HH24:mi:ss' ), #{pgOpUser,jdbcType=VARCHAR}
        xyPg.setCtrCode(ctrCode);
        xyPg.setPgStage(pgStage);
        xyPg.setPgBeginDate(pgBeginDate);
        xyPg.setPgOpUser(pgOpUser);
        xyPgMapper.addPg(xyPg);
        xyPgMapper.updateDays(xyPg.getPgId());
        xyPgLsitMapper.addPgList(xyPg.getPgId(),ctrCode,pgStage);
        List<Map<String ,Object>> maxGrMapLsit = xyPgMapper.getMaxGr(xyPg.getPgId());
        if(maxGrMapLsit == null || maxGrMapLsit.size()==0) {

        }else {
            xyPgMapper.updatePgGrByPgId(maxGrMapLsit.get(0).get("GR_ID").toString(),xyPg.getPgId());
        }
    }

    public Map<String,Object> getEngineeringExpenseSettlementList(String ctrCode){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            List<Map<String,Object>> engineeringExpenseSettlementList =  vwXyJdjsMapper.getJdjs(ctrCode);
            obj.put("engineeringExpenseSettlementList",engineeringExpenseSettlementList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    public Map<String,Object> getEngineeringExpenseSettlementDetailInfo(String ctrCode,String ckdFcType){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String ysGz = "";
        String pgYsStatu = "1";
        try {
            List<Map<String,Object>> engineeringExpenseSettlementDetail = null;
            if ("100".equals(ckdFcType)){
                engineeringExpenseSettlementDetail = xyClbFcCkdMainMapper.getAllEngineeringExpenseSettlementDetail(ctrCode);
            } else {
                engineeringExpenseSettlementDetail = xyClbFcCkdMainMapper.getEngineeringExpenseSettlementDetail(ctrCode,ckdFcType);
            }
            Map<String,Object> custInfo = xyCustomerInfoMapper.getCustInfoByCtrCode(ctrCode);
            if ("20".equals(ckdFcType) || "30".equals(ckdFcType) || "40".equals(ckdFcType) || "50".equals(ckdFcType) ){
                if("20".equals(ckdFcType)){
                    ysGz = "22";
                }else{
                    ysGz = ckdFcType;
                }
                pgYsStatu = xyPgYsMapper.gteYsStatu(ctrCode,ysGz);
            }
            if (pgYsStatu == null){
                pgYsStatu = "1";
            }
            obj.put("pgYsStatu",pgYsStatu);
            obj.put("custInfo",custInfo);
            obj.put("engineeringExpenseSettlementDetail",engineeringExpenseSettlementDetail);
            code = "200";
            msg = "成功";
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
     * 客户验收
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/17 13:03
     * @param: [ctrCode, ckdFcType, custMark, isAgree]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> customerInspection(String ctrCode,String ckdFcType,String custMark,String isAgree){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String sysDate = dateMapper.getSysDate();
            if ("20".equals(ckdFcType)){
                if ("0".equals(isAgree)){
                    xyPgYsMapper.updateYanshou(custMark,ctrCode,"22",sysDate);
                    xyPgWaiterMapper.updateYsDate(ctrCode,sysDate,"22");
                } else {
                    upadteYsStatu(custMark,ctrCode,"22");
                }
            } else if ("30".equals(ckdFcType)){
                if ("0".equals(isAgree)){
                    xyPgYsMapper.updateYanshou(custMark,ctrCode,"30",sysDate);
                    xyPgWaiterMapper.updateWgYsDate(ctrCode,sysDate);
                } else {
                    upadteYsStatu(custMark,ctrCode,"30");
                }
            } else {
                if ("0".equals(isAgree)){
                    xyPgYsMapper.updateYanshou(custMark,ctrCode,ckdFcType,sysDate);
                    xyPgWaiterMapper.updateYsDate(ctrCode,sysDate,ckdFcType);
                } else {
                    upadteYsStatu(custMark,ctrCode,ckdFcType);
                }
            }
            code = "200";
            msg = "验收成功";
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    private void upadteYsStatu(String custMark, String ctrCode, String ysGz) throws SQLException{
        xyPgYsMapper.updateYanShouYsStatuAndCustMark(custMark,ctrCode,ysGz,"2");
    }

}
