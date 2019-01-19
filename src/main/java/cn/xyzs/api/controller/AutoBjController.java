package cn.xyzs.api.controller;

import cn.xyzs.api.service.AutoBjService;
import cn.xyzs.common.pojo.XyMainArea;
import cn.xyzs.common.pojo.XyMainHouser;
import cn.xyzs.common.pojo.XySysDistrict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/autoBj")
public class AutoBjController {

    @Resource
    private AutoBjService autoBjService;

    /**
     * 获取一键报价首页数据
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/18 12:23
     * @param: [xyMainHouser, xyMainArea, xySysDistrict]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getFontPageData")
    public Map<String ,Object> getFontPageData(XyMainHouser xyMainHouser, XyMainArea xyMainArea ,
                                               XySysDistrict xySysDistrict ,String flag){
        return autoBjService.getFontPageData(xyMainHouser,xyMainArea,xySysDistrict,flag);
    }

}
