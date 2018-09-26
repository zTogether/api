package cn.xyzs.api.service;

import cn.xyzs.api.mapper.MvSysSmsMapper;
import cn.xyzs.api.pojo.MvSysSms;
import cn.xyzs.api.util.SendMsgUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MvSysSmsService {

    @Resource
    private MvSysSmsMapper mvSysSmsMapper;

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
            String sendResultCode = SendMsgUtil.sendMsg(sendType,verificationCode ,phone);
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
}
