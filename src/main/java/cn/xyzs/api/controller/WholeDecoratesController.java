package cn.xyzs.api.controller;

import cn.xyzs.api.service.WholeDecoratesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
    @RequestMapping("/zctx")
public class WholeDecoratesController {
    @Resource
    private WholeDecoratesService wholeDecoratesService;

    /***
     *
     * @Description: 展示主材套系VR
     * @author: GeWeiliang
     * @date: 2018\9\2 0002 17:47
     * @param: [vrStyle]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/txVr")
    public Map<String,Object> showTxVr(String vrStyle){
        return wholeDecoratesService.showZctxVr(vrStyle);
    }

    /***
     *
     * @Description: 套系介绍
     * @author: GeWeiliang
     * @date: 2018\9\3 0003 17:30
     * @param: [vrId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/txIntroduce")
    public Map<String,Object> txIntroduce(String vrId){
        return wholeDecoratesService.txDetail(vrId);
    }

    /***
     *
     * @Description: 套系材料列表
     * @author: GeWeiliang
     * @date: 2018\9\3 0003 17:30
     * @param: [vrId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/clList")
    public Map<String,Object> txClList(String vrId,String flBh){
        return  wholeDecoratesService.txClList(vrId,flBh);
    }
}
