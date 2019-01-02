package cn.xyzs.api.service;

import cn.xyzs.api.mapper.UserMapper;
import cn.xyzs.api.mapper.XyCrmCustMapper;
import cn.xyzs.common.pojo.XyCrmCust;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XyCrmCustService {

    @Resource
    private XyCrmCustMapper xyCrmCustMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 添加意向信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 9:42
     * @param: [xyCrmCust]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> addCrmCust(XyCrmCust xyCrmCust){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try {
            Date date = new Date();
            Long nowTime = date.getTime();
            xyCrmCust.setCreateTime(String.valueOf(nowTime));
            xyCrmCustMapper.addCrmCust(xyCrmCust);
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
     * 修改信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 9:43
     * @param: [xyCrmCust]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> updateCrmCustByCustId(XyCrmCust xyCrmCust){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            Map<String ,Object> custCust = xyCrmCustMapper.getCrmCustInfoByCustId(xyCrmCust.getCustId());
            if ("2".equals(custCust.get("CUST_STATE"))){
                obj.put("custCust",custCust);
                code = "300";
                msg = "此信息已为有效信息无法进行修改";
            } else {
                xyCrmCustMapper.updateCrmCustByCustId(xyCrmCust);
                custCust = xyCrmCustMapper.getCrmCustInfoByCustId(xyCrmCust.getCustId());
                obj.put("custCust",custCust);
                code = "200";
                msg = "修改成功";
            }
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
     * 获取信息提供人
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 10:02
     * @param: [condition]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getCustProvider(String condition){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            List<Map<String ,Object>> custProviderList = userMapper.getCustProvider(condition);
            obj.put("custProviderList",custProviderList);
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
     * 获取提交的意向信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 14:10
     * @param: [xyCrmCust, condition, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getCrmCustByCondition(XyCrmCust xyCrmCust , String condition , Integer startNum , Integer endNum){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            List<Map<String ,Object>> custCustList = xyCrmCustMapper.getCrmCustByCondition(xyCrmCust,condition,startNum,endNum);
            obj.put("custCustList",custCustList);
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
     * 根据custId获取提交信息的详情
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/2 9:16
     * @param: [custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getCrmCustInfoByCustId(String custId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            Map<String ,Object> custCust = xyCrmCustMapper.getCrmCustInfoByCustId(custId);
            obj.put("custCust",custCust);
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
