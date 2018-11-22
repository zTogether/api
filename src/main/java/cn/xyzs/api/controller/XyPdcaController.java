package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyPdcaService;
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
}
