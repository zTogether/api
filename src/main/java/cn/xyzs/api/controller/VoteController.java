package cn.xyzs.api.controller;

import cn.xyzs.api.service.VoteService;
import cn.xyzs.common.pojo.XyVoteLog;
import cn.xyzs.common.pojo.XyVoteMain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/vote")
public class VoteController {

    @Resource
    private VoteService voteService;

    /**
     * 获取待投票人
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/27 17:08
     * @param: [voteId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getVoteObjList")
    public Map<String ,Object> getVoteObjList(XyVoteLog xyVoteLog,Integer startNum ,Integer endNum){
        return voteService.getVoteObjList(xyVoteLog,startNum,endNum);
    }

    /**
     * 执行投票
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/28 9:34
     * @param: [xyVoteLog, xyVoteMain]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/vote")
    public Map<String ,Object> vote(XyVoteLog xyVoteLog , XyVoteMain xyVoteMain){
        return voteService.vote(xyVoteLog,xyVoteMain);
    }
}
