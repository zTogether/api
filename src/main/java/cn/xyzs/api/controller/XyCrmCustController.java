package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyCrmCustService;
import cn.xyzs.common.pojo.XyCrmCust;
import cn.xyzs.common.pojo.XyCrmRelation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/crmCus")
public class XyCrmCustController {

    @Resource
    private XyCrmCustService xyCrmCustService;

    /**
     * 添加意向信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 9:42
     * @param: [xyCrmCust]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/addCrmCust")
    public Map<String ,Object> addCrmCust(XyCrmCust xyCrmCust){
        return xyCrmCustService.addCrmCust(xyCrmCust);
    }

    /**
     * 修改信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 9:43
     * @param: [xyCrmCust]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/updateCrmCustByCustId")
    public Map<String ,Object> updateCrmCustByCustId(XyCrmCust xyCrmCust){
        return xyCrmCustService.updateCrmCustByCustId(xyCrmCust);
    }

    /**
     * 获取信息提供人
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 10:02
     * @param: [condition]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getCustProvider")
    public Map<String ,Object> getCustProvider(String condition){
        return xyCrmCustService.getCustProvider(condition);
    }

    /**
     * 获取提交的意向信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 14:10
     * @param: [xyCrmCust, condition, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getCrmCustByCondition")
    public Map<String ,Object> getCrmCustByCondition(XyCrmCust xyCrmCust , String condition , Integer startNum ,
                                                     Integer endNum ,String selectFlag ,String roleId ,String userId){
        return xyCrmCustService.getCrmCustByCondition(xyCrmCust,condition,startNum,endNum,selectFlag,roleId,userId);
    }

    /**
     * 获取下属人数
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/4 16:58
     * @param: [express, userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getSubordinateCount")
    public Map<String ,Object> getSubordinateCount(String express ,String userId){
        return xyCrmCustService.getSubordinateCount(express,userId);
    }

    /**
     * 根据userId和roleId获取下属
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/4 13:27
     * @param: [xyCrmRelation]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getSubordinateByUserIdAndRoleId")
    public Map<String ,Object> getSubordinateByUserIdAndRoleId(String express ,String userId  ,String condition){
        return xyCrmCustService.getSubordinateByUserIdAndRoleId(express,userId,condition);
    }

    /**
     * 根据custId获取提交信息的详情
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/2 9:16
     * @param: [custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getInfoDetailData")
    public Map<String ,Object> getInfoDetailData(String custId ,String roleId){
        return xyCrmCustService.getInfoDetailData(custId,roleId);
    }

    /**
     * 获取信息历史处理记录
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/3 17:15
     * @param: [custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getInfoHistoryFlow")
    public Map<String ,Object> getInfoHistoryFlow(String custId){
        return xyCrmCustService.getInfoHistoryFlow(custId);
    }

    /**
     * 获取分析图数据
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/4 10:35
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getAnalyzeImgData")
    public Map<String ,Object> getAnalyzeImgData(String express , String userId){
        return xyCrmCustService.getAnalyzeImgData(express,userId);
    }

    /**
     * 信息处理
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 9:35
     * @param: [custId, flag, afterNodeId, wadOperation, wadId, nodeName, wadRemark, xyUserId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/dispose")
    public Map<String ,Object> dispose(String custId ,String flag ,String afterNodeId ,String wadOperation ,
                                       String wadId ,String nodeName,String wadRemark ,String xyUserId ,String nodeFlag){
        return xyCrmCustService.dispose(custId,flag,afterNodeId,wadOperation,wadId,nodeName,wadRemark,xyUserId,nodeFlag);
    }

    /**
     * 获取设计成效人
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 9:48
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getSjcxrInfoList")
    public Map<String ,Object> getSjcxrInfoList(){
        return xyCrmCustService.getSjcxrInfoList();
    }

    /**
     * 选择设计成效人
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 10:42
     * @param: [custId, afterNodeId, wadOperation, wadId, wadRemark, xyUserId, sjcxr]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/selectSjcxr")
    public Map<String ,Object> selectSjcxr(String custId ,String afterNodeId ,String wadOperation ,
                                           String wadId ,String wadRemark ,String xyUserId ,String sjcxr){
        return xyCrmCustService.selectSjcxr(custId,afterNodeId,wadOperation,wadId,wadRemark,xyUserId,sjcxr);
    }

    /**
     *  获取设计师
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 12:45
     * @param: [custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getSjsList")
    public Map<String ,Object> getSjsList(String custId) {
        return xyCrmCustService.getSjsList(custId);
    }

    /**
     * 选择设计师执行派单
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 13:51
     * @param: [custId, afterNodeId, wadOperation, wadId, wadRemark, xyUserId, sjs]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/selectSjs")
    public Map<String ,Object> selectSjs(String custId ,String afterNodeId ,String wadOperation ,
                                         String wadId ,String wadRemark ,String xyUserId ,String sjs){
        return xyCrmCustService.selectSjs(custId,afterNodeId,wadOperation,wadId,wadRemark,xyUserId,sjs);
    }

    /**
     * 处理节点
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/10 10:25
     * @param: [jobSchedule, custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/disposeNode")
    public Map<String ,Object> disposeNode(String jobSchedule ,String custId) {
        return xyCrmCustService.disposeNode(jobSchedule,custId);
    }

    /**
     * 获取提交信息详情信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/10 11:29
     * @param: [custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getCustInfoData")
    public Map<String ,Object> getCustInfoData(String custId){
        return xyCrmCustService.getCustInfoData(custId);
    }

}
