package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyStarEvaListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/starEva")
public class XyStarEvaListController {
    @Resource
    private XyStarEvaListService xyStarEvaListService;

    /**
     *
     * @Description: 添加星级评价
     * @author: GeWeiliang
     * @date: 2018\11\1 0001 13:16
     * @param: [evaNo, evaType, level, evaluation, evaName]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/addEva")
    public Map<String,Object> addStarEva(String evaNo,String evaType,String quality,String evaluation,
                                         String evaName,String service,String days,String hygiene){
        return xyStarEvaListService.addStarEva(evaNo,evaType,quality,evaluation,evaName,service,days,hygiene);
    }
}
