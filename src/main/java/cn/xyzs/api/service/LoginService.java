package cn.xyzs.api.service;

import cn.xyzs.api.mapper.UserMapper;
import cn.xyzs.api.mapper.XyCustomerInfoMapper;
import cn.xyzs.api.mapper.XyGcbGrxxMapper;
import cn.xyzs.api.mapper.XyRoleMapper;
import cn.xyzs.api.pojo.TUser;
import cn.xyzs.api.pojo.XyCustomerInfo;
import cn.xyzs.api.pojo.XyGcbGrxx;
import cn.xyzs.api.pojo.XyRole;
import cn.xyzs.api.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    @Resource
    private XyCustomerInfoMapper customerInfoMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private XyGcbGrxxMapper xyGcbGrxxMapper;

    @Resource
    private XyRoleMapper xyRoleMapper;

    @Resource
    private MvSysSmsService mvSysSmsService;

    /**
     * 登陆
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/9 15:45
     * @param: [ctrTel, password, verificationCode, roleFlag]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> login(String ctrTel,String password,String verificationCode,String roleFlag){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        //判断用户是以什么角色登陆的（0：客户/1：员工/2：工人）
        if ("0".equals(roleFlag)){
            Integer isCustomer = null;
            try {
                isCustomer = customerInfoMapper.isCustomer(ctrTel);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //判断客户是否存在
            if (isCustomer > 0){
                //判断验证码输入是否正确
                Map<String ,Object> checkMap = mvSysSmsService.checkVerificationCode(verificationCode,ctrTel);
                String checkCode = String.valueOf(checkMap.get("code"));
                if ("200".equals(checkCode)){
                    code = "200";
                    msg = "登陆成功";
                } else if ("400".equals(checkCode)){
                    code = "401";
                    msg = "验证码输入错误";
                } else if ("300".equals(checkCode)){
                    code = "402";
                    msg = "验证码超时";
                }
            } else {
                code = "400";
                msg = "用户不存在";
            }
        } else if("1".equals(roleFlag)) {
            TUser tUser = new TUser();
            tUser.setUserTel(ctrTel);
            tUser.setIsUsed("1");
            tUser = userMapper.selectOne(tUser);
            //判断用户手否存在
            if (tUser == null){
                code = "400";
                msg = "用户不存在";
            } else {
                //判断密码是否正确
                if (MD5Util.md5Password(password).equals(tUser.getPassword())){
                    code = "200";
                    msg = "登陆成功";
                    List<Map<String,Object>> roleList = null;
                    try {
                        roleList = userMapper.getUserRole(tUser.getUserId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //用户信息
                    obj.put("tUser",tUser);
                    //用户角色信息集合（USER_ID：用户id；ROLE_ID：角色id；ROLE_NAME：角色名称；ROLE_TYPE）
                    obj.put("roleList",roleList);
                } else {
                    code = "401";
                    msg = "用户名或密码错误";
                }
            }
        } else if ("2".equals(roleFlag)){
            XyGcbGrxx xyGcbGrxx = new XyGcbGrxx();
            xyGcbGrxx.setGrTel(ctrTel);
            xyGcbGrxx.setGrStatu("1");
            xyGcbGrxx = xyGcbGrxxMapper.selectOne(xyGcbGrxx);
            //判断用户手否存在
            if (xyGcbGrxx == null){
                code = "400";
                msg = "用户不存在";
            } else {
                //判断密码是否正确
                if (MD5Util.md5Password(password).equals(xyGcbGrxx.getPassword())){
                    XyRole xyRole = new XyRole();
                    xyRole.setRoleId("-1");
                    xyRole = xyRoleMapper.selectOne(xyRole);
                    obj.put("xyGcbGrxx",xyGcbGrxx);
                    obj.put("xyRole",xyRole);
                    code = "200";
                    msg = "登陆成功";
                } else {
                    code = "401";
                    msg = "登陆失败";
                }
            }
        }
        resultMap.put("code",code);
        resultMap.put("msg",msg);
        resultMap.put("resultData",obj);
        return resultMap;
    }


    /**
     * 根据用户角色id获取用户菜单信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/16 14:25
     * @param: [roleId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getMenuByRole(String roleId) {
        String code = "500";
        String msg = "系统异常";
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        //菜单信息（ROLE_ID,COMPO_ID,OP_ID,COMPO_CODE,COMPO_NAME）
        List<Map<String, Object>> menuList = null;
        try {
            menuList = userMapper.getMenuByRole(roleId);
            code = "200";
            msg = "登陆成功";
            obj.put("menuList", menuList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resultMap.put("code", code);
        resultMap.put("msg", msg);
        resultMap.put("resultData", obj);
        return resultMap;
    }

    /**
     * 发送短信验证码
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/23 13:56
     * @param: [phone]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public  Map<String ,Object> sendVerificationCode(String phone){
        String code = "500";
        String msg = "系统异常";
        Map<String, Object> resultMap = new HashMap<>();
        XyCustomerInfo xyCustomerInfo = new XyCustomerInfo();
        xyCustomerInfo.setCtrTel(phone);
        List<XyCustomerInfo> xyCustomerInfoLsit = customerInfoMapper.select(xyCustomerInfo);
        if (xyCustomerInfoLsit != null && xyCustomerInfoLsit.size() > 0){
            resultMap = mvSysSmsService.sendVerificationCode("0" ,phone);
            code = "200";
            msg = "登陆成功";
        } else {
            code = "400";
            msg = "用户不存在";
        }
        resultMap.put("code",code);
        resultMap.put("msg",msg);
        return resultMap;
    }

}
