package cn.xyzs.api.controller;

import cn.xyzs.api.service.IntermediateAcceptanceSrevice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/IntermediateAcceptance")
public class IntermediateAcceptanceController {

    @Resource
    private IntermediateAcceptanceSrevice intermediateAcceptanceSrevice;

    /**
     * 根据ctrCode获取派工验收表里的信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/24 15:32
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getPgYsList")
    public Map<String ,Object> getPgYsList(String ctrCode){
        return intermediateAcceptanceSrevice.getPgYsList(ctrCode);
    }

    /**
     * 验收提交申请
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/28 14:36
     * @param: [ctrCode, ysGz, opUserId, zxyMark, custMark]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/inspectionSubmitApply")
    public Map<String ,Object> inspectionSubmitApply(String ctrCode, String ysGz ,String opUserId, String zxyMark){
        return intermediateAcceptanceSrevice.inspectionSubmitApply(ctrCode,ysGz,opUserId,zxyMark);
    }

    /**
     * 获取允许验收的工种
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/28 16:17
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @ResponseBody
    @RequestMapping("/getSllowYsGz")
    public Map<String ,Object> getSllowYsGz(String ctrCode){
        return intermediateAcceptanceSrevice.getSllowYsGz(ctrCode);
    }

    /**
     * 获取派工工种List
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/10 12:49
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getPgGzList")
    public Map<String ,Object> getPgGzList(String ctrCode){
        return intermediateAcceptanceSrevice.getPgGzList(ctrCode);
    }

    /**
     * 执行派工
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/11 9:18
     * @param: [ctrCode, pgStage, pgBeginDate, pgOpUser]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/pg")
    public Map<String, Object> pg(String ctrCode ,String pgStage ,String pgBeginDate ,String pgOpUser){
        return intermediateAcceptanceSrevice.pg(ctrCode,pgStage,pgBeginDate,pgOpUser);
    }

    /**
     * 获取工程费用结算详情信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/15 16:11
     * @param: [ctrCode, ckdFcType]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getEngineeringExpenseSettlementDetailInfo")
    public Map<String,Object> getEngineeringExpenseSettlementDetailInfo(String ctrCode,String ckdFcType){
        return intermediateAcceptanceSrevice.getEngineeringExpenseSettlementDetailInfo(ctrCode,ckdFcType);
    }

    /**
     * 获取工程费用结算List
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/17 9:52
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getEngineeringExpenseSettlementList")
    public Map<String,Object> getEngineeringExpenseSettlementList(String ctrCode){
        return intermediateAcceptanceSrevice.getEngineeringExpenseSettlementList(ctrCode);
    }

    /**
     * 客户验收
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/17 13:03
     * @param: [ctrCode, ckdFcType, custMark]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/customerInspection")
    public Map<String ,Object> customerInspection(String ctrCode,String ckdFcType,String custMark,String isAgree){
        return intermediateAcceptanceSrevice.customerInspection(ctrCode,ckdFcType,custMark,isAgree);
    }
}
