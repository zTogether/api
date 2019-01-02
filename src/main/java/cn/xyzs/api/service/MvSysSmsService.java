package cn.xyzs.api.service;

import cn.xyzs.api.mapper.MvSysSmsMapper;
import cn.xyzs.api.mapper.UserMapper;
import cn.xyzs.common.pojo.MvSysSms;
import cn.xyzs.common.util.SendMsgUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MvSysSmsService {

    @Resource
    private MvSysSmsMapper mvSysSmsMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 发送短信验证码（单发）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/14 15:29
     * @param: [phone]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> sendVerificationCode(String sendType ,String phone){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            //发送短信（sendResultCode = 200 为发送成功）
            String verificationCode = SendMsgUtil.getVerificationCode();
            String []params = {verificationCode};
            String sendResultCode = SendMsgUtil.sendMsg(sendType,params ,phone);
            //如果发送成功
            if ("200".equals(sendResultCode)){
                MvSysSms mvSysSms = new MvSysSms();
                mvSysSms.setTel(phone);
                mvSysSms.setVerificationCode(verificationCode);
                mvSysSms.setSendStatus(sendResultCode);
                mvSysSmsMapper.addMvSysSmsInfo(mvSysSms);
                code = "200";
                msg = "成功";
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
     * 校验验证码（时效为两分钟）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/14 15:41
     * @param: [verificationCode, phone]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> checkVerificationCode(String verificationCode ,String phone){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            MvSysSms mvSysSms = new MvSysSms();
            mvSysSms.setTel(phone);
            mvSysSms.setVerificationCode(verificationCode);
            //校验验证码是否正确
            Integer checkCode = mvSysSmsMapper.checkVerificationCode(mvSysSms);
            if (checkCode > 0){
                //校验验证码是否超时
                Integer checkTimeoutCode = mvSysSmsMapper.checkTimeout(mvSysSms);
                if (checkTimeoutCode > 0){
                    code = "200";
                    msg = "校验成功";
                } else {
                    code = "300";
                    msg = "验证码超时";
                }
            } else {
                code = "400";
                msg = "验证码不正确";
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
     * 发送活动提醒
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/24 11:27
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> sendActivityReminder(){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> memberList = userMapper.getTempMember();

            String sendResultCode = "";
            String phone = "";

            for (Map<String, Object> map : memberList) {
                synchronized (this) {
                    String[] params = {};
                    phone = String.valueOf(map.get("TEL"));
                    if (!"".equals(phone) && phone != null) {
                        sendResultCode = SendMsgUtil.sendMsg("7", params, phone);
                        userMapper.updateTemp(sendResultCode, phone);
                    }
                }
            }
            memberList = userMapper.getTempMember();
            obj.put("memberList",memberList);
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
