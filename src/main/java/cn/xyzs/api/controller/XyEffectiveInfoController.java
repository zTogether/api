package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyEffectiveInfoService;
import cn.xyzs.common.pojo.XyEffectiveInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/effectiveInfo")
public class XyEffectiveInfoController {

    @Resource
    private XyEffectiveInfoService xyEffectiveInfoService;

    /**
     * 添加有效信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 14:44
     * @param: [xyEffectiveInfo]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/addEffectiveInfo")
    public Map<String ,Object> addEffectiveInfo(XyEffectiveInfo xyEffectiveInfo){
        return xyEffectiveInfoService.addEffectiveInfo(xyEffectiveInfo);
    }

    /**
     * 修改有效信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 14:44
     * @param: [xyEffectiveInfo]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/updateEffectiveInfo")
    public Map<String ,Object> updateEffectiveInfo(XyEffectiveInfo xyEffectiveInfo){
        return xyEffectiveInfoService.updateEffectiveInfo(xyEffectiveInfo);
    }

    /**
     * 删除有效信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 14:45
     * @param: [xyEffectiveInfo]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/deleteEffectiveInfoByEffectiveInfoId")
    public Map<String ,Object> deleteEffectiveInfoByEffectiveInfoId(String effectiveInfoId){
        return xyEffectiveInfoService.deleteEffectiveInfoByEffectiveInfoId(effectiveInfoId);
    }

    /**
     * 获取有效信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/3 14:47
     * @param: [submitUserId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getEffectiveInfoBySubmitUserId")
    public Map<String ,Object> getEffectiveInfoBySubmitUserId(String submitUserId){
        return xyEffectiveInfoService.getEffectiveInfoBySubmitUserId(submitUserId);
    }
}
