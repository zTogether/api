package cn.xyzs.api.controller;

import cn.xyzs.api.pojo.TRole;
import cn.xyzs.api.service.RoleService;
import cn.xyzs.api.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Resource
    private RoleService roleService;
    @Resource
    private UserService userService;
    @RequestMapping("index.do")
    public PageInfo<TRole> index(){
        return roleService.getRole();
    }
    @RequestMapping("roles.do")
    public PageInfo<Map<String,Object>> getRoles(){
        return  roleService.getMap();
    }

    @RequestMapping("users.do")
    public PageInfo<Map<String,Object>> getUsers(@RequestParam Map<String,Object> map){
        return userService.getUsers(1,10,map);
    }
}
