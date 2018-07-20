package cn.xyzs.api.controller;

import cn.xyzs.api.pojo.TRole;
import cn.xyzs.api.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Resource
    private RoleService roleService;
    @RequestMapping("index.do")
    public PageInfo<TRole> index(){
        return roleService.getRole();
    }
    @RequestMapping("roles.do")
    public PageInfo<Map<String,Object>> getRoles(){
        return  roleService.getMap();
    }
}
