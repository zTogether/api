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

    /**
     *
     * @Description: 获取试卷
     * @author: GeWeiliang
     * @date: 2018\10\31 0031 9:04
     * @param: [examCode, empNo]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/exam")
    public Map<String,Object> getExamPaer(String examCode,String empNo){
        return xyExamManagerService.getExamPaper(examCode,empNo);
    }

    /**
     *
     * @Description: 提交答案
     * @author: GeWeiliang
     * @date: 2018\10\31 0031 9:04
     * @param: [examCode, empNo, questionNo, answer, spare]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/addAnswer")
    public Map<String,Object> updateAnswer(String examCode,String empNo,String questionNo,String answer,String spare){
        return xyExamManagerService.updateAnswer(examCode,empNo,questionNo,answer,spare);
    }
}
