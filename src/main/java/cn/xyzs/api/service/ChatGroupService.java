package cn.xyzs.api.service;

import cn.xyzs.api.mapper.MvChatMemberMapper;
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
    private XyPgMapper xyPgMapper;

    @Resource
    private MvChatMemberMapper mvChatMemberMapper;

    /**
     * 工人聊天群，获取工人工地
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/30 11:15
     * @param: [grId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public Map<String ,Object> getGrConstructionSite(String grId ,String startNum ,String endNum){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> grConstructionSiteList = xyPgMapper.getGrConstructionSite(grId,startNum,endNum);
            obj.put("grConstructionSiteList",grConstructionSiteList);
            code = "200";
            msg = "验收成功";
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
     * 工人聊天群，根据条件获取工人工地
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/30 13:46
     * @param: [grId, ctrTel, ctrName, ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public Map<String ,Object> getGrConstructionSiteByCondition(String grId , String condition){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> grConstructionSiteList = xyPgMapper.getGrConstructionSiteByCondition(grId,condition);
            obj.put("grConstructionSiteList",grConstructionSiteList);
            code = "200";
            msg = "验收成功";
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
     * 获取用户所属的所有聊天分组
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/18 17:56
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getChatGroupByUserId(String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            MvChatMember mvChatMember = new MvChatMember();
            mvChatMember.setUserId(userId);
            List<Map<String ,Object>> userHaveChatGroup = mvChatMemberMapper.getChatMemberByCondition(mvChatMember);
            obj.put("userHaveChatGroup",userHaveChatGroup);
            code = "200";
            msg = "验收成功";
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
     * 根据userId获取聊天群(分页)
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/19 14:56
     * @param: [userId, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getChatGroupListByUserIdLimit(String userId , String startNum , String endNum) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> userHaveChatGroup = mvChatMemberMapper.getChatGroupListByUserIdLimit(userId,startNum,endNum);
            obj.put("userHaveChatGroup",userHaveChatGroup);
            code = "200";
            msg = "验收成功";
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
    public Map<String ,Object> getChatGroupByConditionAndUserId(String userId , String condition) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> userHaveChatGroup = mvChatMemberMapper.getChatGroupByConditionAndUserId(userId,condition);
            obj.put("userHaveChatGroup",userHaveChatGroup);
            code = "200";
            msg = "验收成功";
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
