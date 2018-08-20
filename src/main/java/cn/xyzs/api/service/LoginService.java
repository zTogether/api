package cn.xyzs.api.service;

import cn.xyzs.api.mapper.UserMapper;
import cn.xyzs.api.mapper.XyCustomerInfoMapper;
import cn.xyzs.api.mapper.XyGcbGrxxMapper;
import cn.xyzs.api.pojo.TUser;
import cn.xyzs.api.pojo.XyGcbGrxx;
import cn.xyzs.api.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
                if ("1234".equals(verificationCode)){
                    code = "200";
                    msg = "登陆成功";
                } else {
                    code = "401";
                    msg = "验证码输入错误";
                }
            } else {
                code = "400";
                msg = "用户不存在";
            }
        } else if("1".equals(roleFlag)) {
            TUser tUser = new TUser();
            tUser.setUserTel(ctrTel);
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
                    msg = "登陆失败";
                }
            }
        } else if ("2".equals(roleFlag)){
            XyGcbGrxx xyGcbGrxx = new XyGcbGrxx();
            xyGcbGrxx.setGrTel(ctrTel);
            xyGcbGrxx = xyGcbGrxxMapper.selectOne(xyGcbGrxx);
            //判断用户手否存在
            if (xyGcbGrxx == null){
                code = "400";
                msg = "用户不存在";
            } else {
                //判断密码是否正确
                if (MD5Util.md5Password(password).equals(xyGcbGrxx.getPassword())){
                    obj.put("xyGcbGrxx",xyGcbGrxx);
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
    /***
     *
     * @Description:修改密码
     * @author: GeWeiliang
     * @date: 2018\8\16 0016 14:37
     * @param:
     * @return:
     */
    public Map<String,Object> resetPassword(String userTel,String password){
        int result = userMapper.changePassword(userTel, MD5Util.md5Password(password));
        Map<String,Object> map = new HashMap<String,Object>();
        if(result==1){
            map.put("code","200");
            map.put("msg","密码修改成功");
        }else{
            map.put("code","400");
            map.put("msg","修改失败");
        }
        return map;
    }
}
