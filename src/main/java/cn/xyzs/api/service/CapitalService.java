package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyCwbCapitalDetailMapper;
import cn.xyzs.api.mapper.XyCwbCapitalMapper;
import cn.xyzs.api.mapper.XyCwbCapitalYytxMapper;
import cn.xyzs.common.pojo.XyCwbCapital;
import cn.xyzs.common.pojo.XyCwbCapitalYytx;
import cn.xyzs.common.util.CalculateUtil;
import cn.xyzs.common.util.DEStool;
import cn.xyzs.common.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CapitalService {

    @Resource
    private XyCwbCapitalMapper xyCwbCapitalMapper;

    @Resource
    private XyCwbCapitalDetailMapper xyCwbCapitalDetailMapper;

    @Resource
    private XyCwbCapitalYytxMapper xyCwbCapitalYytxMapper;

    /**
     * 获取证书
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 13:55
     * @param: [userId, allowWithdrawDepositMenoy]
     * @return: java.lang.String
     */
    private String getTransactionCertificate(String userId ,String allowWithdrawDepositMenoy) throws Exception {
        DEStool deStool = new DEStool("xyzs1.0.0");
        return deStool.encrypt(userId + allowWithdrawDepositMenoy);
    }

    /**
     * 判断是否生成资金账户
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 13:36
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> existsCapitalInfo(String userId){
        Map<String, Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Integer existsCapitalFlag = xyCwbCapitalMapper.existsCapitalInfo(userId);
            if (existsCapitalFlag < 1){
                XyCwbCapital xyCwbCapital = new XyCwbCapital();
                xyCwbCapital.setUserId(userId);
                xyCwbCapital.setTransactionCertificate(getTransactionCertificate(userId,"0"));
                xyCwbCapitalMapper.addCapitalInfo(xyCwbCapital);
            }
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 获取资金页数据
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 16:07
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getCapitalFontPageInfo(String userId){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Map<String ,Object> capitalInfo = xyCwbCapitalMapper.getCapitalInfoByUserId(userId);
            String transactionPassword = String.valueOf(capitalInfo.get("TRANSACTION_PASSWORD"));
            Boolean existsTransactionPassword = true;
            if ("".equals(transactionPassword) || transactionPassword == null || "null".equals(transactionPassword)){
                existsTransactionPassword = false;
            }
            //删除证书
            capitalInfo.remove("TRANSACTION_CERTIFICATE");
            //删除交易密码
            capitalInfo.remove("TRANSACTION_PASSWORD");
            List<Map<String ,Object>> recentCapitalDetailLsit = xyCwbCapitalDetailMapper.getRecentCapitalDetail(String.valueOf(capitalInfo.get("CAPITAL_ID")));
            List<Map<String ,Object>> analyzeImgData = getAnalyzeImgData(recentCapitalDetailLsit,Double.valueOf(String.valueOf(capitalInfo.get("ALLOW_WITHDRAW_DEPOSIT_MONEY"))));
            Map<String ,Object> newCapitalDetailInfo = xyCwbCapitalDetailMapper.getNewCapitalDetail(String.valueOf(capitalInfo.get("CAPITAL_ID")));
            obj.put("capitalInfo",capitalInfo);
            obj.put("analyzeImgData",analyzeImgData);
            obj.put("newCapitalDetailInfo",newCapitalDetailInfo);
            obj.put("existsTransactionPassword",existsTransactionPassword);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    private List<Map<String ,Object>> getAnalyzeImgData(List<Map<String ,Object>> recentCapitalDetailLsit ,double nowMoney){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = format.format(date);
        String nowDate1 = format.format(new Date(date.getTime() - 1 * 24 * 60 * 60 * 1000));
        String nowDate2 = format.format(new Date(date.getTime() - 2 * 24 * 60 * 60 * 1000));
        String nowDate3 = format.format(new Date(date.getTime() - 3 * 24 * 60 * 60 * 1000));
        String nowDate4 = format.format(new Date(date.getTime() - 4 * 24 * 60 * 60 * 1000));
        double nowDateTempZCMoney = 0.0;
        double nowDateTempSRMoney = 0.0;
        double nowDateTemp1ZCMoney = 0.0;
        double nowDateTemp1SRMoney = 0.0;
        double nowDateTemp2ZCMoney = 0.0;
        double nowDateTemp2SRMoney = 0.0;
        double nowDateTemp3ZCMoney = 0.0;
        double nowDateTemp3SRMoney = 0.0;
        for (Map<String, Object> map : recentCapitalDetailLsit) {
            if (nowDate.equals(String.valueOf(map.get("OP_DATE")))){
                if ("0".equals(String.valueOf(map.get("CAPITAL_TYPE")))){
                    nowDateTempSRMoney = CalculateUtil.GetResult(nowDateTempSRMoney,Double.valueOf(String.valueOf(map.get("MONEY"))),"+");
                } else {
                    nowDateTempZCMoney = CalculateUtil.GetResult(nowDateTempZCMoney,Double.valueOf(String.valueOf(map.get("MONEY"))),"+");
                }
            } else if (nowDate1.equals(String.valueOf(map.get("OP_DATE")))){
                if ("0".equals(String.valueOf(map.get("CAPITAL_TYPE")))){
                    nowDateTemp1SRMoney = CalculateUtil.GetResult(nowDateTemp1SRMoney,Double.valueOf(String.valueOf(map.get("MONEY"))),"+");
                } else {
                    nowDateTemp1ZCMoney = CalculateUtil.GetResult(nowDateTemp1ZCMoney,Double.valueOf(String.valueOf(map.get("MONEY"))),"+");
                }
            } else if (nowDate2.equals(String.valueOf(map.get("OP_DATE")))){
                if ("0".equals(String.valueOf(map.get("CAPITAL_TYPE")))){
                    nowDateTemp2SRMoney = CalculateUtil.GetResult(nowDateTemp2SRMoney,Double.valueOf(String.valueOf(map.get("MONEY"))),"+");
                } else {
                    nowDateTemp2ZCMoney = CalculateUtil.GetResult(nowDateTemp2ZCMoney,Double.valueOf(String.valueOf(map.get("MONEY"))),"+");
                }
            } else if (nowDate3.equals(String.valueOf(map.get("OP_DATE")))){
                if ("0".equals(String.valueOf(map.get("CAPITAL_TYPE")))){
                    nowDateTemp3SRMoney = CalculateUtil.GetResult(nowDateTemp3SRMoney,Double.valueOf(String.valueOf(map.get("MONEY"))),"+");
                } else {
                    nowDateTemp3ZCMoney = CalculateUtil.GetResult(nowDateTemp3ZCMoney,Double.valueOf(String.valueOf(map.get("MONEY"))),"+");
                }
            }
        }

        double nowDateTemp1Money = CalculateUtil.GetResult(nowMoney,nowDateTempZCMoney,"+");
        nowDateTemp1Money = CalculateUtil.GetResult(nowDateTemp1Money,nowDateTempSRMoney,"-");

        double nowDateTemp2Money = CalculateUtil.GetResult(nowDateTemp1Money,nowDateTemp1ZCMoney,"+");
        nowDateTemp2Money = CalculateUtil.GetResult(nowDateTemp2Money,nowDateTemp1SRMoney,"-");

        double nowDateTemp3Money = CalculateUtil.GetResult(nowDateTemp2Money,nowDateTemp2ZCMoney,"+");
        nowDateTemp3Money = CalculateUtil.GetResult(nowDateTemp3Money,nowDateTemp2SRMoney,"-");

        double nowDateTemp4Money = CalculateUtil.GetResult(nowDateTemp3Money,nowDateTemp3ZCMoney,"+");
        nowDateTemp4Money = CalculateUtil.GetResult(nowDateTemp4Money,nowDateTemp3SRMoney,"-");

        List<Map<String ,Object>> analyzeImgData = new ArrayList<>();
        Map<String ,Object> nowDateMap = new HashMap<>();
        nowDateMap.put("title",nowDate.substring(5));
        nowDateMap.put("money",nowMoney);
        Map<String ,Object> nowDate1Map = new HashMap<>();
        nowDate1Map.put("title",nowDate1.substring(5));
        nowDate1Map.put("money",nowDateTemp1Money);
        Map<String ,Object> nowDate2Map = new HashMap<>();
        nowDate2Map.put("title",nowDate2.substring(5));
        nowDate2Map.put("money",nowDateTemp2Money);
        Map<String ,Object> nowDate3Map = new HashMap<>();
        nowDate3Map.put("title",nowDate3.substring(5));
        nowDate3Map.put("money",nowDateTemp3Money);
        Map<String ,Object> nowDate4Map = new HashMap<>();
        nowDate4Map.put("title",nowDate4.substring(5));
        nowDate4Map.put("money",nowDateTemp4Money);
        analyzeImgData.add(nowDate4Map);
        analyzeImgData.add(nowDate3Map);
        analyzeImgData.add(nowDate2Map);
        analyzeImgData.add(nowDate1Map);
        analyzeImgData.add(nowDateMap);
        return analyzeImgData;
    }

    /**
     * 获取资金详情
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/24 9:40
     * @param: [userId, capitalType, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getCapitalDetail(String userId, String capitalType, Integer startNum, Integer endNum){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> capitalDetailList = new ArrayList<>();
            if ("101".equals(capitalType)){
                capitalDetailList = xyCwbCapitalDetailMapper.getAllCapitalDetail(userId,startNum,endNum);
            } else {
                capitalDetailList = xyCwbCapitalDetailMapper.getSrOrZcCapitalDetail(userId,capitalType,startNum,endNum);
            }
            obj.put("capitalDetailList",capitalDetailList);
            code = "200";
            msg = "";
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
     * 设置交易密码
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/24 13:35
     * @param: [xyCwbCapital]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> settingTransactionPassword(XyCwbCapital xyCwbCapital){
        Map<String, Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            xyCwbCapital.setTransactionPassword(MD5Util.md5Password(xyCwbCapital.getTransactionPassword()));
            xyCwbCapitalMapper.updateTransactionPassword(xyCwbCapital);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 提现前进行校验
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/24 15:39
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> withdrawBeforeDetection(String userId){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Map<String ,Object> capitalInfo = xyCwbCapitalMapper.getCapitalInfoByUserId(userId);
            String transactionPassword = String.valueOf(capitalInfo.get("TRANSACTION_PASSWORD"));
            String allowWithdrawDepositMoney = String.valueOf(capitalInfo.get("ALLOW_WITHDRAW_DEPOSIT_MONEY"));
            String transactionCertificate = String.valueOf(capitalInfo.get("TRANSACTION_CERTIFICATE"));
            if ("".equals(transactionPassword) || transactionPassword == null || "null".equals(transactionPassword)){
                code = "404";
                msg = "未设置交易密码";
            } else if (transactionCertificate.equals(getTransactionCertificate(userId,allowWithdrawDepositMoney))){
                if(Integer.valueOf(allowWithdrawDepositMoney) <= 0){
                    code = "500";
                    msg = "余额为0";
                } else {
                    code = "200";
                    msg = "可以进行转账";
                }
            } else {
                code = "400";
                msg = "非法余额";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 添加预约提现记录（userId：用户id；  appointmentMoney：预约提现金额；  remark：备注；）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/26 16:10
     * @param: [xyCwbCapitalYytx]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> addCapitalYytx(XyCwbCapitalYytx xyCwbCapitalYytx){
        Map<String, Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            xyCwbCapitalYytxMapper.addCapitalYytx(xyCwbCapitalYytx);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 根据userId获取预约提现记录（分页）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/26 16:32
     * @param: [userId, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getYytxRecord(String userId, Integer startNum, Integer endNum){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> yytxRecordList = xyCwbCapitalYytxMapper.getYytxRecord(userId,startNum,endNum);
            obj.put("yytxRecordList",yytxRecordList);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取预约提现数据
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/26 17:14
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getYytxData(String userId){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Map<String ,Object> yytxData = xyCwbCapitalYytxMapper.getAwdmoneyAndAmoney(userId);
            String nowDayYytxMoney = String.valueOf(yytxData.get("APPOINTMENT_MONEY"));
            Boolean existsYy = true;
            if("".equals(nowDayYytxMoney) || "null".equals(nowDayYytxMoney) || nowDayYytxMoney == null){
                existsYy = false;
            }
            SimpleDateFormat format = new SimpleDateFormat("MM-dd");
            Date nowDate = new Date();
            nowDate = addAndSubtractDaysByGetTime(nowDate,1);
            Integer day = nowDate.getDay();
            String dayStr = "";
            switch(day){
                case 1:
                    dayStr = "一";
                    break;
                case 2:
                    dayStr = "二";
                    break;
                case 3:
                    dayStr = "三";
                    break;
                case 4:
                    dayStr = "四";
                    break;
                case 5:
                    dayStr = "五";
                    break;
                case 6:
                    dayStr = "六";
                    break;
                case 7:
                    dayStr = "日";
                    break;
            }
            String nowDateStr = format.format(nowDate);
            nowDateStr += "(星期"+ dayStr +")";
            yytxData.put("nowDateStr",nowDateStr);
            yytxData.put("existsYy",existsYy);
            obj.put("yytxData",yytxData);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    public static Date addAndSubtractDaysByGetTime(Date date,int n){
        //日期格式
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date(date.getTime() + n * 24 * 60 * 60 * 1000L)));
        //注意这里一定要转换成Long类型，要不n超过25时会出现范围溢出，从而得不到想要的日期值
        return new Date(date.getTime() + n * 24 * 60 * 60 * 1000L);
    }


}
