package cn.xyzs.api.controller;

import cn.xyzs.api.service.CapitalService;
import cn.xyzs.common.pojo.XyCwbCapital;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/capital")
public class CapitalController {

    @Resource
    private CapitalService capitalService;

    /**
     * 判断是否生成资金账户
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 13:36
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/existsCapitalInfo")
    public Map<String ,Object> existsCapitalInfo(String userId){
        return capitalService.existsCapitalInfo(userId);
    }

    /**
     * 获取资金页数据
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 16:07
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getCapitalFontPageInfo")
    public Map<String ,Object> getCapitalFontPageInfo(String userId){
        return capitalService.getCapitalFontPageInfo(userId);
    }

    /**
     * 获取资金详情
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/24 9:40
     * @param: [userId, capitalType, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getCapitalDetail")
    public Map<String ,Object> getCapitalDetail(String userId, String capitalType, Integer startNum, Integer endNum){
        return capitalService.getCapitalDetail(userId,capitalType,startNum,endNum);
    }

    /**
     * 设置交易密码
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/24 13:35
     * @param: [xyCwbCapital]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/settingTransactionPassword")
    public Map<String ,Object> settingTransactionPassword(XyCwbCapital xyCwbCapital){
        return capitalService.settingTransactionPassword(xyCwbCapital);
    }

    /**
     * 判断是否设置了交易密码
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/24 15:39
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/withdrawBeforeDetection")
    public Map<String ,Object> withdrawBeforeDetection(String userId){
        return capitalService.withdrawBeforeDetection(userId);
    }
}
