package cn.xyzs.api.controller;

import cn.xyzs.api.pojo.XyUserGps;
import cn.xyzs.api.service.XyUserGpsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/xyUserGps")
public class XyUserGpsController {

    @Resource
    private XyUserGpsService xyUserGpsService;

    /**
     * 获取实时定位
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/28 10:46
     * @param: [xyUserGps]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getRealTimePositioning")
    public Map<String ,Object> getRealTimePositioning(XyUserGps xyUserGps){
        return xyUserGpsService.getRealTimePositioning(xyUserGps);
    }
}
