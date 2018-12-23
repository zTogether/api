package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyClbZcpbListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/zcpbList")
public class XyClbZcpbListController {

    @Resource
    private XyClbZcpbListService xyClbZcpbListService;

    /**
     *
     * @Description: 获取目录
     * @author: GeWeiliang
     * @date: 2018\12\23 0023 10:08
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getFirstMl")
    public Map<String,Object> getFirstMl(String ctrCode,String zcType){
        return xyClbZcpbListService.getFirstMl(ctrCode,zcType);
    }

    /**
     *
     * @Description: 获取主材配比列表
     * @author: GeWeiliang
     * @date: 2018\12\23 0023 13:53
     * @param: [ctrCode, mlName, zcType]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getZcpbList")
    public Map<String,Object> getZcpbList(String ctrCode,String mlName,String zcType){
        return xyClbZcpbListService.getZcpbList(ctrCode,mlName,zcType);
    }
}
