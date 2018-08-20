package cn.xyzs.api.controller;

import cn.xyzs.api.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 保存常用菜单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/18 15:05
     * @param: [userId, roleId, compoIds]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/editCommoMenu")
    public Map<String, Object> editCommoMenu(String userId,String roleId,String[] compoIds){
        return menuService.editCommoMenu(userId,roleId,compoIds);
    }

    /**
     * 获取常用菜单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/18 15:05
     * @param: [userId, roleId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getCommoMenu")
    public Map<String, Object> getCommoMenu(String userId,String roleId){
        return menuService.getCommoMenu(userId,roleId);
    }

    /**
     * 添加常用菜单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/18 15:06
     * @param: [userId, roleId, compoIds]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/addCommoMenu")
    public Map<String, Object> addCommoMenu(String userId,String roleId,String[] compoIds){
        return menuService.addCommoMenu(userId,roleId,compoIds);
    }
}
