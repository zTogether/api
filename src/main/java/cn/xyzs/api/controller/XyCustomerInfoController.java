package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyCustomerInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/CustomerInfo")
public class XyCustomerInfoController {

    @Resource
    private XyCustomerInfoService xyCustomerInfoService;

    /**
     * 根据客户电话查询客户工程信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:20
     * @param: [ctrTel]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getCustomerEngineeringInfo")
    public Map<String,Object> getCustomerEngineeringInfo(String ctrTel){
        return xyCustomerInfoService.getCustomerEngineeringInfo(ctrTel);
    }

    /**
     *
     * @Description: 根据客户档案号获取客户信息
     * @author: GeWeiliang
     * @date: 2018\8\23 0023 11:12
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/getCustInfoByCtrCode")
    @ResponseBody
    public Map<String,Object> getCustInfoByCtrCode(String ctrCode){
        return xyCustomerInfoService.getCustInfoByCtrCode(ctrCode);
    }
}
