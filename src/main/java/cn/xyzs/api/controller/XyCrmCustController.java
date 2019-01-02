package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyCrmCustService;
import cn.xyzs.common.pojo.XyCrmCust;
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
    public Map<String ,Object> getCrmCustByCondition(XyCrmCust xyCrmCust , String condition , Integer startNum , Integer endNum){
        return xyCrmCustService.getCrmCustByCondition(xyCrmCust,condition,startNum,endNum);
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
    @RequestMapping("/getCrmCustInfoByCustId")
    public Map<String ,Object> getCrmCustInfoByCustId(String custId){
        return xyCrmCustService.getCrmCustInfoByCustId(custId);
    }

}
