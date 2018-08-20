package cn.xyzs.api.service;

import cn.xyzs.api.mapper.MvCommoMenuMapper;
import cn.xyzs.api.pojo.MvCommoMenu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    @Resource
    private MvCommoMenuMapper mvCommoMenuMapper;

    /**
     * 保存常用菜单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/18 14:48
     * @param: [userId, roleId, compoIds]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> editCommoMenu(String userId,String roleId,String[] compoIds){
        String code = "500";
        String msg = "系统异常";
        Map<String ,Object> resultMap = new HashMap<>();
        try {
            MvCommoMenu mvCommoMenu = new MvCommoMenu();
            mvCommoMenu.setUserId(userId);
            mvCommoMenu.setRoleId(roleId);
            mvCommoMenuMapper.wipeCommoMenu(mvCommoMenu);
            for (int i = 0; i <compoIds.length ; i++) {
                mvCommoMenu.setCompoId(compoIds[i]);
                mvCommoMenuMapper.addCommoMenu(mvCommoMenu);
            }
            code = "200";
            msg = "修改成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 获取常用菜单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/18 14:55
     * @param: [userId, roleId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getCommoMenu(String userId,String roleId){
        String code = "500";
        String msg = "系统异常";
        Map<String ,Object> resultMap = new HashMap<>();
        Map<String ,Object> obj = new HashMap<>();
        try {
            MvCommoMenu mvCommoMenu = new MvCommoMenu();
            mvCommoMenu.setUserId(userId);
            mvCommoMenu.setRoleId(roleId);
            List<Map<String, Object>> commoMenuList = mvCommoMenuMapper.getCommoMenu(mvCommoMenu);
            obj.put("commoMenuList",commoMenuList);
            resultMap.put("resultData",obj);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 添加常用菜单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/18 14:57
     * @param: [userId, roleId, compoIds]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public  Map<String ,Object> addCommoMenu(String userId,String roleId,String[] compoIds){
        String code = "500";
        String msg = "系统异常";
        Map<String ,Object> resultMap = new HashMap<>();
        try {
            MvCommoMenu mvCommoMenu = new MvCommoMenu();
            mvCommoMenu.setUserId(userId);
            mvCommoMenu.setRoleId(roleId);
            for (int i = 0; i <compoIds.length ; i++) {
                mvCommoMenu.setCompoId(compoIds[i]);
                mvCommoMenuMapper.addCommoMenu(mvCommoMenu);
            }
            code = "200";
            msg = "添加成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }
}
