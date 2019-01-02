package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyPdcaService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/xyPdca")
public class XyPdcaController {

    @Resource
    private XyPdcaService xyPdcaService;

    /**
     * 根据userId获取所有的报表
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/10 9:56
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getPdcaByUserId")
    public Map<String ,Object> getPdcaByUserId(String userId,String beginDate ,String endDate) {
        return xyPdcaService.getPdcaByUserId(userId,beginDate,endDate);
    }

    /**
     * 获取下级报表
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/10 17:15
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getSubordinatePdca")
    public Map<String ,Object> getSubordinatePdca(String userId){
        return xyPdcaService.getSubordinatePdca(userId);
    }


    /**
     *
     * @Description: 获取每周的工作问题需求资源以及总经理意见
     * @author: GeWeiliang
     * @date: 2018\11\25 0025 10:15
     * @param: [userId, pdcaId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getWeekContent")
    public Map<String,Object> getWeekContent(String userId,String pdcaId){
        return xyPdcaService.getWeekContent(userId,pdcaId);
    }


    @ResponseBody
    @RequestMapping("/getWeekSummary")
    public Map<String,Object> getWeekSummary(String pdcaId){
        return xyPdcaService.getWeekSummary(pdcaId);
    }
    @ResponseBody
    @RequestMapping("/getWeekPlan")
    public Map<String,Object> getWeekPlan(String pdcaId){
        return xyPdcaService.getWeekPlan(pdcaId);
    }

    /**
     *
     * @Description: 获取每天的内容
     * @author: GeWeiliang
     * @date: 2018\11\25 0025 10:14
     * @param: [pdcaId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getDayContent")
    public Map<String,Object> getDatContent(String pdcaId){
        return xyPdcaService.getDayContent(pdcaId);
    }

    /**
     *
     * @Description: 增加日工作内容行
     * @author: GeWeiliang
     * @date: 2018\11\26 0026 9:19
     * @param: [pdcaId, week, res]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/addDayContent")
    public Map<String,Object> addPdcaList(String pdcaId,String week,String theDate,String PSummary,String res){
        return xyPdcaService.addPdcaList(pdcaId,week,theDate,PSummary,res);
    }

    /**
     *
     * @Description: 删除日工作内容行
     * @author: GeWeiliang
     * @date: 2018\11\26 0026 9:18
     * @param: [pdcaId, week, res]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/deleteDayContent")
    public Map<String,Object> deletePdcaList(String pdcaId,String week,String res){
        return xyPdcaService.deletePdcaList(pdcaId,week,res);
    }

    /**
     *
     * @Description: 日工作内容
     * @author: GeWeiliang
     * @date: 2018\11\26 0026 9:18
     * @param: [pdcaId, week, res, content]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/updateDayPcontent")
    public Map<String,Object> updatePdcaPcontent(String pdcaId,String week,String res,String content){
        return xyPdcaService.updatePdcaPcontent(pdcaId,week,res,content);
    }

    /**
     *
     * @Description: 日总结
     * @author: GeWeiliang
     * @date: 2018\11\26 0026 9:18
     * @param: [pdcaId, week, res, content]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/updateDayPsummary")
    public Map<String,Object> updatePsummary(String pdcaId,String week,String content){
        return xyPdcaService.updatePsummary(pdcaId,week,content);
    }

    //更新本周总结
    @ResponseBody
    @RequestMapping("/updateWeekSummary")
    public Map<String,Object> updateWeekSummary(String pdcaId,String content,String res){
        return xyPdcaService.updateWeekSummary(pdcaId,content,res);
    }
    /**
     *
     * @Description: 添加下周计划
     * @author: GeWeiliang
     * @date: 2018\11\26 0026 9:19
     * @param: [pdcaId, classify, content]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/updateWeekPlan")
    public Map<String,Object> updateWeekPlan(String pdcaId,String content,String res){
        return xyPdcaService.updateWeekPlan(pdcaId,content,res);
    }

    //添加总结行
    @ResponseBody
    @RequestMapping("/addSumCol")
    public Map<String,Object> addSumCol(String pdcaId,String res){
        return xyPdcaService.addSumCol(pdcaId,res);
    }
    //添加计划行
    @ResponseBody
    @RequestMapping("/addPlanCol")
    public Map<String,Object> addPlanCol(String pdcaId,String res){
        return xyPdcaService.addPlanCol(pdcaId,res);
    }

    @ResponseBody
    @RequestMapping("/updatePdca")
    public Map<String,Object> updatePdca(String pdcaId,String position,String opinion,String PResources,String issue){
        return xyPdcaService.updatePdca(pdcaId,position,opinion,PResources,issue);
    }

    @ResponseBody
    @RequestMapping("/deleteWeekSum")
    public Map<String,Object> deleteWeekSum(String pdcaId,String res){
        return xyPdcaService.delelteWeekSum(pdcaId,res);
    }

    @ResponseBody
    @RequestMapping("/deleteWeekPlan")
    public Map<String,Object> deleteWeekPlan(String pdcaId,String res){
        return xyPdcaService.deleteWeekPlan(pdcaId,res);
    }
}
