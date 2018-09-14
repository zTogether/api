package cn.xyzs.api.controller;

import cn.xyzs.api.service.LoginService;
import cn.xyzs.api.service.MvSysSmsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @Resource
    private MvSysSmsService mvSysSmsService;

    /**
     * 登陆
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/9 17:15
     * @param: [ctrTel, password, verificationCode, roleFlag]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/login")
    public Map<String,Object> login(String userTel, String password, String verificationCode, String roleFlag){
        return loginService.login(userTel,password,verificationCode,roleFlag);
    }
    @ResponseBody
    @RequestMapping("/sendVerificationCode")
    public  Map<String,Object> sendVerificationCode(String userTel){
        return mvSysSmsService.sendVerificationCode(userTel);
    }

    /**
     * 根据用户角色id获取用户菜单信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/16 14:43
     * @param: [roleId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getMenuByRole")
    public Map<String ,Object> getMenuByRole(String roleId) {
        return loginService.getMenuByRole(roleId);
    }


}
