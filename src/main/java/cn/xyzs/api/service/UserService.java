package cn.xyzs.api.service;

import cn.xyzs.api.mapper.UserMapper;
import cn.xyzs.common.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    public PageInfo<Map<String,Object>> getUsers(int index,int size,Map<String,Object> map){
        PageHelper.startPage(index,size);
        List<Map<String,Object>> users = userMapper.selectByCondition(map);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(users);
        return pageInfo;
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
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String code = "500";
        String msg = "系统异常";
        try {
            userMapper.changePassword(userTel, MD5Util.md5Password(password));
            code = "200";
            msg = "修改成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     *
     * @Description:修改个人资料
     * @author: GeWeiliang
     * @date: 2018\8\22 0022 9:55
     * @param: [userTel, userSex, userBthd, idCard, bankIdBc, bankIdIcbc, bankIdCmbc]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> changePersonalInfo(String userCode,String userTel,String userSex, String userBthd,String idCard,
                                                 String bankIdBc, String bankIdIcbc, String bankIdCmbc){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String code = "500";
        String msg = "修改失败";
        try{
            userMapper.changePersonalInfo(userCode,userTel,userSex,userBthd,idCard,bankIdBc,bankIdIcbc,bankIdCmbc);
            code = "200";
            msg = "修改成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }

        return resultMap;
    }

    public Map<String,Object> getUserInfo(String userTel){
         Map<String,Object> map = userMapper.getUserInfo(userTel);
         return map;
    }

    /**
     *
     * @Description: 员工通讯录
     * @author: GeWeiliang
     * @date: 2018\11\21 0021 17:04
     * @param: [condition]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> phoneBook(String name,String role,String orgName){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> phone = new LinkedHashMap<>();
        try{
            if (name!=null&&name!=""){
                name = "%"+name+"%";
            }
            List<Map<String,Object>> list = userMapper.phoneBook(name,role,orgName);
            for (Map<String, Object> map : list) {
                List phoneList = new ArrayList();
                if (map.get("ORG_NAME")!=null&&map.get("ORG_NAME")!=""){
                    if(phone.containsKey(map.get("ORG_NAME").toString())){
                        List<Map<String,Object>> existList =(List<Map<String,Object>>) phone.get(map.get("ORG_NAME").toString());
                        existList.add(map);
                        phone.put(map.get("ORG_NAME").toString(),existList);
                    }else{
                        phoneList.add(map);
                        phone.put(map.get("ORG_NAME").toString(),phoneList);
                    }
                }else {
                    if(phone.containsKey("其他")){
                        List<Map<String,Object>> existList =(List<Map<String,Object>>) phone.get("其他");
                        existList.add(map);
                        phone.put("其他",existList);
                    }else{
                        phoneList.add(map);
                        phone.put("其他",phoneList);
                    }
                }
            }
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",phone);
        }
        return resultMap;
    }

    /**
     * 判断用户是否可用
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/28 13:52
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> userWhetherEnabled(String userId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new LinkedHashMap<>();
        try{
            String isUsed = userMapper.userWhetherEnabled(userId);
            Boolean userWhetherEnabled = false;
            if ("1".equals(isUsed)){
                userWhetherEnabled = true;
            }
            obj.put("userWhetherEnabled",userWhetherEnabled);
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
}
