package cn.xyzs.api.service;

import cn.xyzs.api.mapper.*;
import cn.xyzs.common.pojo.XyClbFcCkdList;
import cn.xyzs.common.pojo.XyClbFcCkdMain;
import cn.xyzs.common.pojo.XyCustomerInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.action.PutAllAction;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

@Service
public class FcCkdService {

    @Resource
    private XyClbFcDbMapper xyClbFcDbMapper;

    @Resource
    private XyClbFcCkdMainMapper xyClbFcCkdMainMapper;

    @Resource
    private XyClbFcCkdListMapper xyClbFcCkdListMapper;

    @Resource
    private XyBjdMainMapper xyBjdMainMapper;

    @Resource
    private XyBjdFcTempMapper xyBjdFcTempMapper;

    @Resource
    private XyCustomerInfoMapper xyCustomerInfoMapper;

    /**
     * 根据材料分类获取辅材商品
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/12 10:14
     * @param: [ctrCode, fcType]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getFcGoodByFcType(String ctrCode ,String fcType){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> fcGoodList = xyClbFcDbMapper.getFcGoodByFcType(ctrCode,fcType);
            code = "200";
            msg = "成功";
            obj.put("fcGoodList",fcGoodList);
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
     * 添加出库单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/12 15:31
     * @param: [xyClbFcCkdMain, fcPriceCodeArray, fcQtyArray, fcPriceArray, fcMarkArray]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> addFcCkd(XyClbFcCkdMain xyClbFcCkdMain  ,String[] fcPriceCodeArray ,String[] fcQtyArray ,String[] fcPriceArray ,String[] fcMarkArray){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            String ckdCode = xyClbFcCkdMainMapper.getNewFcCkdCode(xyClbFcCkdMain.getCtrCode());
            if(ckdCode == null || "".equals(ckdCode) || "null".equals(ckdCode)){
                ckdCode = xyClbFcCkdMain.getCtrCode() + "01";
            }
            xyClbFcCkdMain.setCkdCode(ckdCode);
            xyClbFcCkdMain.setCkdStatu("0");
            xyClbFcCkdMain.setCkdCk("金盛仓库");
            xyClbFcCkdMainMapper.insertSelective(xyClbFcCkdMain);
            List<String> fcPriceCodeList = new ArrayList<>();
            for (int i = 0; i <fcPriceCodeArray.length ; i++) {
                fcPriceCodeList.add(fcPriceCodeArray[i]);
            }
            String fcMark = UUID.randomUUID().toString();
            xyClbFcCkdListMapper.addFcCkdList(xyClbFcCkdMain.getCkdCode(), fcMark,fcPriceCodeList);

            double ckdZj = 0;

            for (int i = 0; i < fcPriceCodeList.size(); i++) {
                XyClbFcCkdList xyClbFcCkdList = new XyClbFcCkdList();
                //数量
                xyClbFcCkdList.setFcQty(fcQtyArray[i]);
                //金额
                double fcJe = GetResult(Double.valueOf(fcQtyArray[i]) ,Double.valueOf(fcPriceArray[i]) ,"*");
                xyClbFcCkdList.setFcJe(String.valueOf(fcJe));
                //y运费
                double fcYf = GetResult(fcJe ,0.06 ,"*");
                xyClbFcCkdList.setFcYf(String.valueOf(fcYf));
                //小计
                String fcXj = String.valueOf(GetResult(fcJe,fcYf,"+"));
                xyClbFcCkdList.setFcXj(fcXj);
                //备注
                xyClbFcCkdList.setFcMark("**".equals(fcMarkArray[i])?"":fcMarkArray[i]);
                Example example = new Example(XyClbFcCkdList.class);
                example.createCriteria().andEqualTo("fcPriceId",fcPriceCodeList.get(i)).andEqualTo("fcMark",fcMark).andIsNotNull("fcMark");
                xyClbFcCkdListMapper.updateByExampleSelective(xyClbFcCkdList,example);

                //出库单总计
                ckdZj += GetResult(fcJe,fcYf,"+");
            }
            Example example = new Example(XyClbFcCkdMain.class);
            example.createCriteria().andEqualTo("ckdCode",ckdCode);
            XyClbFcCkdMain fcCkdMain = new XyClbFcCkdMain();
            fcCkdMain.setCkdZj(getRound(ckdZj,100));
            xyClbFcCkdMainMapper.updateByExampleSelective(fcCkdMain,example);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    private double GetResult(double numA, double numB, String operate){
        double res = 0;
        BigDecimal bigA = new BigDecimal(Double.toString(numA));
        BigDecimal bigB = new BigDecimal(Double.toString(numB));
        switch (operate) {

            case "+":
                res = bigA.add(bigB).doubleValue();
                break;
            case "-":
                res = bigA.subtract(bigB).doubleValue();
                break;
            case "*":
                res = bigA.multiply(bigB).doubleValue();
                break;
            case "/":
                res = bigA.divide(bigB).doubleValue();
                break;
            default :
                System.out.println("运算符不合法~");
                break;
        }
        return res;
    }

    private String getRound(double a ,int b){
        a = a * b;
        int c = (int)a;
        c = (c-(int) a)>0.5? ((int) a)+1:(int) a;
        return String.valueOf(Double.valueOf(c)  / b);
    }

    /**
     * 获取退库商品
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/12 18:14
     * @param: [ctrCode, ckdFcType]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getTKFcGood(String ctrCode ,String ckdFcType){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> TKFcGoodList = xyClbFcCkdListMapper.getTKFcGood(ctrCode,ckdFcType);
            code = "200";
            msg = "成功";
            obj.put("TKFcGoodList",TKFcGoodList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }


    /** 添加出库单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/12 15:31
     * @param: [xyClbFcCkdMain, fcPriceCodeArray, fcQtyArray, fcPriceArray, fcMarkArray]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> addFcTkd(XyClbFcCkdMain xyClbFcCkdMain  ,String[] fcPriceCodeArray ,String[] fcQtyArray ,String[] fcPriceArray ){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            String ckdCode = xyClbFcCkdMainMapper.getNewFcCkdCode(xyClbFcCkdMain.getCtrCode());
            xyClbFcCkdMain.setCkdCode(ckdCode);
            //退库单状态（待审）
            xyClbFcCkdMain.setCkdStatu("0");
            xyClbFcCkdMain.setCkdCk("金盛仓库");
            xyClbFcCkdMainMapper.insertSelective(xyClbFcCkdMain);
            List<String> fcPriceCodeList = new ArrayList<>();
            for (int i = 0; i <fcPriceCodeArray.length ; i++) {
                fcPriceCodeList.add(fcPriceCodeArray[i]);
            }
            String fcMark = UUID.randomUUID().toString();
            xyClbFcCkdListMapper.addFcCkdList(xyClbFcCkdMain.getCkdCode(), fcMark,fcPriceCodeList);

            double ckdZj = 0;

            for (int i = 0; i < fcPriceCodeList.size(); i++) {
                XyClbFcCkdList xyClbFcCkdList = new XyClbFcCkdList();
                //数量
                xyClbFcCkdList.setFcQty(fcQtyArray[i]);
                //金额
                double fcJe = GetResult(Double.valueOf(fcQtyArray[i]) ,Double.valueOf(fcPriceArray[i]) ,"*");
                xyClbFcCkdList.setFcJe(String.valueOf(fcJe));
                //y运费
                double fcYf = GetResult(fcJe ,0.06 ,"*");
                xyClbFcCkdList.setFcYf(String.valueOf(fcYf));
                //小计
                String fcXj = String.valueOf(GetResult(fcJe,fcYf,"+"));
                xyClbFcCkdList.setFcXj(fcXj);
                //备注
                xyClbFcCkdList.setFcMark("");
                Example example = new Example(XyClbFcCkdList.class);
                example.createCriteria().andEqualTo("fcPriceId",fcPriceCodeList.get(i)).andEqualTo("fcMark",fcMark).andIsNotNull("fcMark");
                xyClbFcCkdListMapper.updateByExampleSelective(xyClbFcCkdList,example);
                //出库单总计
                ckdZj += GetResult(fcJe,fcYf,"+");
                code = "200";
                msg = "成功";
            }
            Example example = new Example(XyClbFcCkdMain.class);
            example.createCriteria().andEqualTo("ckdCode",ckdCode);
            XyClbFcCkdMain fcCkdMain = new XyClbFcCkdMain();
            fcCkdMain.setCkdZj(getRound(ckdZj,100));
            xyClbFcCkdMainMapper.updateByExampleSelective(fcCkdMain,example);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 根据ctrCode获取当前的报价单金额与出库单金额
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/14 12:03
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getNowBjdjeAndCldjeByCtrCode(String ctrCode){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Map<String ,Object> nowBjdjeAndCldje = xyBjdMainMapper.getNowBjdjeAndCldjeByCtrCode(ctrCode);
            code = "200";
            msg = "成功";
            obj.put("nowBjdjeAndCldje",nowBjdjeAndCldje);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData" ,obj);
        }
        return resultMap;
    }

    /**
     * 根据ckdCode删除出库单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/14 14:06
     * @param: [ckdCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> deleteCkdByCkdCode(String ckdCode){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            xyClbFcCkdMainMapper.deleteByCkdCode(ckdCode);
            xyClbFcCkdListMapper.deleteByCkdCode(ckdCode);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 判断是否为首次开单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/15 15:17
     * @param: [ctrCode, ckdFcType]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> isFristKd(String ctrCode ,String ckdFcType){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Integer isFristKd = xyClbFcCkdMainMapper.isFristKd(ctrCode,ckdFcType);
            XyCustomerInfo xyCustomerInfo = new XyCustomerInfo();
            xyCustomerInfo.setCtrCode(ctrCode);
            xyCustomerInfo = xyCustomerInfoMapper.selectOne(xyCustomerInfo);
            String ctrPrjType = xyCustomerInfo.getCtrPrjType();
            String isFristKdFlag = "否";
            if (isFristKd < 1 && "0".equals(ctrPrjType)){
                isFristKdFlag = "是";
            }
            code = "200";
            msg = "成功";
            obj.put("isFristKdFlag",isFristKdFlag);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultDate",obj);
        }
        return resultMap;
    }

    /**
     * 获取本材料大类所选择的品牌
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/15 16:34
     * @param: [ctrCode, rgStage]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getNameAndVal(String ctrCode ,String rgStage){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> nameAndVaList = xyBjdFcTempMapper.getNameAndVal(ctrCode,rgStage);
            List<Map<String ,Object>> pidAndPval = xyClbFcCkdListMapper.getPidAndPval(ctrCode,rgStage);
            List<Map<String ,Object>> pidAndPvalList = new ArrayList<>();
            Set<String > tempPidSet = new HashSet<>();
            for (Map<String, Object> map : pidAndPval) {
                tempPidSet.add(String.valueOf(map.get("P_ID")));
            }
            for (String s : tempPidSet) {
                Map<String ,Object> tempMap = new HashMap<>();
                List<Object> tempList = new ArrayList<>();
                for (Map<String, Object> map : pidAndPval) {
                    if (s.equals(String.valueOf(map.get("P_ID")))){
                        tempList.add(map.get("P_VAL"));
                    }
                }
                tempMap.put("pid",s);
                tempMap.put("pvalArray",tempList);
                pidAndPvalList.add(tempMap);
            }
            code = "200";
            msg = "成功";
            obj.put("pidAndPvalList",pidAndPvalList);
            obj.put("nameAndVaList",nameAndVaList);
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
     * 一键开单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/16 16:51
     * @param: [xyClbFcCkdMain]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> autoOpenOrder(XyClbFcCkdMain xyClbFcCkdMain){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            String ckdCode = xyClbFcCkdMainMapper.getCkdCode(xyClbFcCkdMain.getCtrCode());
            if (ckdCode == null || "".equals(ckdCode) || "null".equals(ckdCode)){
                ckdCode = xyClbFcCkdMain.getCtrCode() + "01";
            }
            xyClbFcCkdMain.setCkdCode(ckdCode);
            xyClbFcCkdMainMapper.autoOpenOrderAddCkdMain(xyClbFcCkdMain);
            xyClbFcCkdListMapper.autoOpenOrderAddCkdLsit(xyClbFcCkdMain.getCkdCode(),xyClbFcCkdMain.getCtrCode(),xyClbFcCkdMain.getCkdFcType());
            Map<String ,Object> ckdInfo = xyClbFcCkdMainMapper.getCkdInfo(xyClbFcCkdMain.getCkdCode());
            obj.put("ckdInfo",ckdInfo);
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


}
