package cn.xyzs.api.service;

import cn.xyzs.api.mapper.MvChatMemberMapper;
import cn.xyzs.api.mapper.MvChattingRecordsMapper;
import cn.xyzs.api.mapper.XyCustomerInfoMapper;
import cn.xyzs.api.mapper.XyPgMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatRoomService {

    @Resource
    private MvChattingRecordsMapper mvChattingRecordsMapper;
    @Resource
    private MvChatMemberMapper mvChatMemberMapper;

    /**
     *
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 9:21
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getServicePersonalInfoByCtrCode(String ctrCode){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> servicePersonalInfoList = mvChatMemberMapper.getChatMemberInfoLsitByCtrCode(ctrCode);
            code = "200";
            msg = "成功";
            obj.put("servicePersonalInfoList",servicePersonalInfoList);
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
     * 添加聊天记录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 16:49
     * @param: [ctrCode, userId, chatingContent, contentType]
     * @return: void
     */
    @Transactional
    public Map<String ,Object> addChattingRecords (String ctrCode , String userId ,String sendDate , String chatingContent , String contentType){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            mvChattingRecordsMapper.addChattingRecords(ctrCode,userId,sendDate,chatingContent,contentType);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 获取离线消息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 17:07
     * @param: [lastSendDate, ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getOfflineMessage (String userId ,String ctrCode , String [] sendDates){
        List<String> sendDateList = new ArrayList<>();
        if (sendDates == null){
            sendDateList.add ("9999-12-12 12:12:12");
        } else {
            for (int i = 0; i <sendDates.length ; i++) {
                sendDateList.add(sendDates[i]);
            }
        }
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            //离线消息
            List<Map<String ,Object>> offlineMessageList = mvChattingRecordsMapper.getOfflineMessage(userId,ctrCode,sendDateList);
            obj.put("offlineMessageList",offlineMessageList);
            code = "200";
            msg = "成功";
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
     *  获取离线消息(2)
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/5 17:49
     * @param: [ctrCode, dateNde, selectFlag]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getOfflineMessageByDateNode (String ctrCode ,String dateNode ,String selectFlag){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> offlineMessageList = new ArrayList<>();
            if ("0".equals(selectFlag)){
                offlineMessageList = mvChattingRecordsMapper.getOfflineMessageByDateNode(ctrCode,dateNode);
            } else if ("1".equals(selectFlag)){
                offlineMessageList = mvChattingRecordsMapper.getOfflineMessageNotByDateNode(ctrCode);
            }
            obj.put("offlineMessageList",offlineMessageList);
            code = "200";
            msg = "成功";
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
