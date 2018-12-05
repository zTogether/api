package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyGcbPrjPlanService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/App/gcbPrj")
public class XyGcbPrjPlanController {
    @Resource
    private XyGcbPrjPlanService xyGcbPrjPlanService;

    /**
     *
     * @Description: 查询日程项
     * @author: GeWeiliang
     * @date: 2018\11\10 0010 9:43
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/prjPlan")
    @ResponseBody
    public Map<String,Object> getPrjPlan(String ctrCode,String roleName,String edit){
        return xyGcbPrjPlanService.getPrjPlan(ctrCode,roleName,edit);
    }

    /**
     *
     * @Description: 确认工程完成
     * @author: GeWeiliang
     * @date: 2018\11\10 0010 9:43
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/ensurePrj")
    @ResponseBody
    public Map<String,Object> ensurePrj(String rowId,String userId){
        return xyGcbPrjPlanService.toEnsure(rowId,userId);
    }

    /**
     *
     * @Description: 添加日程备注
     * @author: GeWeiliang
     * @date: 2018\11\10 0010 14:48
     * @param: [rowId, mark]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/addPrjMark")
    public Map<String,Object> addPrjMark(String rowId,String mark){
        return xyGcbPrjPlanService.addMark(rowId,mark);
    }

    /**
     *
     * @Description: 获取一个日程项
     * @author: GeWeiliang
     * @date: 2018\11\12 0012 17:45
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getOnePlan")
    public Map<String,Object> getOnePlan(String rowId){
        return xyGcbPrjPlanService.getOnePlan(rowId);
    }

    /**
     *
     * @Description: 是否代购
     * @author: GeWeiliang
     * @date: 2018\11\12 0012 17:45
     * @param: [rowId, content]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/isDg")
    public Map<String,Object> isDaiGou(String prjId,String content,String userId){
        return xyGcbPrjPlanService.isDG(prjId,content,userId);
    }

    /**
     *
     * @Description: 获取量尺单
     * @author: GeWeiliang
     * @date: 2018\11\12 0012 17:44
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/getLcd")
    @ResponseBody
    public Map<String,Object> getLcd(String rowId){
        return xyGcbPrjPlanService.getLcd(rowId);
    }

    /**
     *
     * @Description: 添加量尺单
     * @author: GeWeiliang
     * @date: 2018\11\12 0012 17:44
     * @param: [mbId, prjId, quantity, length, width, heigth]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/addLcd")
    public Map<String,Object> addLcd(String prjId, String userId, String ctrCode,String lcd,String prjMark){
        JSONArray arr = JSONArray.parseArray(lcd);
        List lcdList = JSONObject.parseArray(arr.toJSONString());
        return xyGcbPrjPlanService.addLcd(prjId,userId,ctrCode,lcdList,prjMark);
    }


    /**
     *
     * @Description: 根据客户号获取量尺单
     * @author: GeWeiliang
     * @date: 2018\11\14 0014 16:33
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/lcdList")
    @ResponseBody
    public Map<String,Object> lcdList(String ctrCode,String prjId){
        return xyGcbPrjPlanService.showLcd(ctrCode,prjId);
    }

    /**
     *
     * @Description: 获取我的工地日程
     * @author: GeWeiliang
     * @date: 2018\11\26 0026 9:20
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/myPlan")
    public Map<String,Object> getMyPlan(String userId){
        return xyGcbPrjPlanService.getMyPlan(userId);
    }
}
