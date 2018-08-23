package cn.xyzs.api.controller;

import cn.xyzs.api.service.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/good")
public class GoodController {

    @Resource
    private GoodService goodService;

    /**
     * 获取下级目录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/20 14:53
     * @param: [zcflCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getSubdirectory")
    public Map<String, Object> getSubdirectory(String zcflCode){
        return goodService.getSubdirectory(zcflCode);
    }

    @ResponseBody
    @RequestMapping("/sortFilter")
    public Map<String, Object> test(String zcflCode,String startNum,String endNum){

        return goodService.sortFilter(zcflCode,startNum,endNum);
    }
}
