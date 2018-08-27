package cn.xyzs.api.controller;

import cn.xyzs.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    /***
     *
     * @Description: 修改密码
     * @author: GeWeiliang
     * @date: 2018\8\16 0016 14:52
     * @param: [user, password]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/resetPassword")
    public Map<String,Object> resetPassword(String userTel, String password){
        return  userService.resetPassword(userTel,password);
    }

    /**
     *
     * @Description: 修改个人资料
     * @author: GeWeiliang
     * @date: 2018\8\22 0022 11:32
     * @param: [userTel, userSex, userBthd, idCard, bankIdBc, bankIdIcbc, bankIdCmbc]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/changePersonalInfo")
    public Map<String,Object> changePersonalInfo(String userCode,String userTel,String userSex, String userBthd,String idCard,
                                                 String bankIdBc, String bankIdIcbc, String bankIdCmbc){
        return  userService.changePersonalInfo(userCode,userTel,userSex,userBthd,idCard,bankIdBc,bankIdIcbc,bankIdCmbc);
    }

    /***
     *
     * @Description: 根据手机号获取用户信息
     * @author: GeWeiliang
     * @date: 2018\8\27 0027 16:03
     * @param: [userTel]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getUserInfo")
    public Map<String,Object> getUserInfo(String userTel){
       return userService.getUserInfo(userTel);
    }
}
