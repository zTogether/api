package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyVoteListMapper;
import cn.xyzs.api.mapper.XyVoteLogMapper;
import cn.xyzs.api.mapper.XyVoteMainMapper;
import cn.xyzs.common.pojo.XyVoteLog;
import cn.xyzs.common.pojo.XyVoteMain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteService {

    @Resource
    private XyVoteMainMapper xyVoteMainMapper;

    @Resource
    private XyVoteListMapper XyVoteListMapper;

    @Resource
    private XyVoteLogMapper XyVoteLogMapper;

    /**
     * 获取待投票人
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/27 17:08
     * @param: [voteId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getVoteObjList(XyVoteLog xyVoteLog,Integer startNum ,Integer endNum){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> voteList = XyVoteListMapper.getVoteListByVoteId(xyVoteLog.getVoteId(),startNum,endNum);
            //获取今日当前项目以投票的记录(参数：voteId，opUser)
            List<Map<String ,Object>> todayVoteLog = XyVoteLogMapper.getTodayVoteLog(xyVoteLog);
            for (Map<String, Object> map : voteList) {
                for (Map<String, Object> todayVoteLogMap : todayVoteLog) {
                    if (map.get("SELECT_ID").equals(todayVoteLogMap.get("SELECT_ID"))){
                        map.put("isHaveVoted","true");
                    }
                }
            }
            code = "200";
            msg = "成功";
            obj.put("voteList",voteList);
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
     * 执行投票
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/28 9:34
     * @param: [xyVoteLog, xyVoteMain]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> vote(XyVoteLog xyVoteLog , XyVoteMain xyVoteMain){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Map<String ,Object> voteMainInfo = xyVoteMainMapper.getVoteInfo(xyVoteMain);
            Integer voteNum = Integer.valueOf(String.valueOf(voteMainInfo.get("VOTE_NUM") == null?"0":voteMainInfo.get("VOTE_NUM")));
            //获取今日当前项目以投票的记录(参数：voteId，opUser)
            List<Map<String ,Object>> todayVoteLog = XyVoteLogMapper.getTodayVoteLog(xyVoteLog);
            if (todayVoteLog.size() < voteNum){
                //获取今日当前项目当前被投票人以投票的记录数(参数：voteId，opUser，selectId)
                int todayVoteLogCount = XyVoteLogMapper.getTodayVoteLogCount(xyVoteLog);
                if (todayVoteLogCount < 1){
                    //添加投票纪录(参数：voteId，opUser，selectId，voteScore)
                    XyVoteLogMapper.addVoteLog(xyVoteLog);
                    int voteLogCount = XyVoteLogMapper.getVoteLogCount(xyVoteLog);
                    obj.put("voteLogCount",voteLogCount);
                    code = "200";
                    msg = "投票成功";
                } else {
                    code = "400";
                    msg = "每人每天对投票对象只能投票一次哦";
                }
            } else {
                code = "401";
                msg = "今日投票次数已达到上限";
            }
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
