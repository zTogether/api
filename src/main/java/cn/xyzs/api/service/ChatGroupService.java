package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyCustomerInfoMapper;
import cn.xyzs.api.mapper.XyPgMapper;
import cn.xyzs.api.pojo.MvChatMember;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGroupService {
    @Resource
    private XyCustomerInfoMapper xyCustomerInfoMapper;


    /**
     * 获取用户所属的所有聊天分组
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/18 17:56
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getChatGroupByUserTel(String userTel) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> userHaveChatGroup = xyCustomerInfoMapper.getChatGroupByUserTel(userTel);
            obj.put("userHaveChatGroup",userHaveChatGroup);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }


    /**
     * 根据条件和userId获取用户群组
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/19 15:20
     * @param: [userId, condition]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getChatGroupByConditionAndUserTel(String userTel , String condition) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> userHaveChatGroup = xyCustomerInfoMapper.getChatGroupByConditionAndUserTel(userTel,condition);
            obj.put("userHaveChatGroup",userHaveChatGroup);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }
}
