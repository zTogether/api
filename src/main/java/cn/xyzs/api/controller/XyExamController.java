package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyExamManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/exam")
public class XyExamController {
    @Resource
    private XyExamManagerService xyExamManagerService;

    @ResponseBody
    @RequestMapping("/exam")
    public Map<String,Object> getExamPaer(String examCode){
        return xyExamManagerService.getExamPaper(examCode);
    }

    @ResponseBody
    @RequestMapping("/addAnswer")
    public Map<String,Object> updateAnswer(String examCode,String empNo,String questionNo,String answer,String spare){
        return xyExamManagerService.updateAnswer(examCode,empNo,questionNo,answer,spare);
    }
}
