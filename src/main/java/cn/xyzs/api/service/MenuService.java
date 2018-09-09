package cn.xyzs.api.service;

import cn.xyzs.api.mapper.MvCommoMenuMapper;
import cn.xyzs.api.mapper.XyGcbGrxxMapper;
import cn.xyzs.api.pojo.MvCommoMenu;
import cn.xyzs.api.util.MD5Util;
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
    @Resource
    private XyGcbGrxxMapper xyGcbGrxxMapper;

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
            if (compoIds != null){
                for (int i = 0; i <compoIds.length ; i++) {
                    mvCommoMenu.setCompoId(compoIds[i]);
                    mvCommoMenuMapper.addCommoMenu(mvCommoMenu);
                }
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

    /***
     *
     * @Description: 查询工人信息
     * @author: GeWeiliang
     * @date: 2018\9\8 0008 14:56
     * @param: [grTel]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getGrInfo(String grTel){
        String code = "500";
        String msg = "系统异常";
        Map<String ,Object> resultMap = new HashMap<>();
        Map<String ,Object> obj = new HashMap<>();
        try{
            Map<String,Object> grInfo = xyGcbGrxxMapper.getGrInfo(grTel);
            obj.put("grInfo",grInfo);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /***
     *
     * @Description: 修改工人密码
     * @author: GeWeiliang
     * @date: 2018\9\8 0008 14:31
     * @param: [grTel, password]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> changeGrPassword(String grTel,String password){
        String code = "500";
        String msg = "系统异常";
        Map<String ,Object> resultMap = new HashMap<>();
        try{
            xyGcbGrxxMapper.changeGrPassword(grTel,MD5Util.md5Password(password));
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /***
     *
     * @Description: 修改工人信息
     * @author: GeWeiliang
     * @date: 2018\9\8 0008 15:49
     * @param: [name, idCard, grTel, grBankId, grAdd, grId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> updateGrInfo(String name,String idCard, String grTel, String grBankId,
                                           String grAdd,String grId){
        String code = "500";
        String msg = "系统异常";
        Map<String ,Object> resultMap = new HashMap<>();
        try{
            xyGcbGrxxMapper.changeGrInfo(name,idCard,grTel,grBankId,grAdd,grId);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }
}
