package cn.xyzs.api.service;

import cn.xyzs.api.mapper.MvChatGroupMapper;
import cn.xyzs.api.pojo.MvChatGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MvChatGroupService {
    @Resource
    private MvChatGroupMapper mvChatGroupMapper;

    /**
     *
     * @Description: 动态查询聊天群组
     * @author: GeWeiliang
     * @date: 2018\10\18 0018 17:29
     * @param: [chatGroup]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> queryChatGroup(MvChatGroup chatGroup){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            List<Map<String,Object>> chatGroupList =  mvChatGroupMapper.queryChatGroup(chatGroup);
            code = "200";
            msg = "成功";
            obj.put("chatGroup",chatGroupList);
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
