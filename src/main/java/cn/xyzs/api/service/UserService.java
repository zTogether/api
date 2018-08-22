package cn.xyzs.api.service;

import cn.xyzs.api.mapper.UserMapper;
import cn.xyzs.api.pojo.XyUser;
import cn.xyzs.api.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        int result = userMapper.changePassword(userTel, MD5Util.md5Password(password));
        Map<String,Object> map = new HashMap<String,Object>();
        if(result==1){
            map.put("code","1");
            map.put("msg","密码修改成功");
        }else{
            map.put("code","-1");
            map.put("msg","修改失败");
        }
        return map;
    }

    /**
     *
     * @Description:修改个人资料
     * @author: GeWeiliang
     * @date: 2018\8\22 0022 9:55
     * @param: [userTel, userSex, userBthd, idCard, bankIdBc, bankIdIcbc, bankIdCmbc]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> changePersonalInfo(String userTel,String userSex, String userBthd,String idCard,
                                                 String bankIdBc, String bankIdIcbc, String bankIdCmbc){
    int result =  userMapper.changePersonalInfo(userTel,userSex,userBthd,idCard,bankIdBc,bankIdIcbc,bankIdCmbc);
    Map<String,Object> map = new HashMap<String,Object>();
    if (result>0){
        map.put("code","1");
        map.put("msg","修改成功");
    }else{
        map.put("code","-1");
        map.put("msg","修改失败");
    }
    return map;
    }

    public Map<String,Object> getUserInfo(String userTel){
         Map<String,Object> map = userMapper.getUserInfo(userTel);
         return map;
    }
}
