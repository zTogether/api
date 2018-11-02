package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyExamManagerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XyExamManagerService {
    @Resource
    private XyExamManagerMapper xyExamManagerMapper;

    /**
     *
     * @Description: 加载试卷
     * @author: GeWeiliang
     * @date: 2018\10\28 0028 15:38
     * @param: [examCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getExamPaper(String examCode,String empNo){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String msg = "系统异常";
        String code = "500";
        try {
            List fill = new ArrayList();
            List mind = new ArrayList();
            List yn = new ArrayList();
            List multi = new ArrayList();
            int fillCount = 0;
            int mindCount = 0;
            int ynCount = 0;
            int multiCount = 0;
            List<Map<String,Object>> examPaper = xyExamManagerMapper.getExamPaper(examCode);
            for (Map<String, Object> map : examPaper) {
                String questionType = map.get("QUESTIONTYPE").toString();
                String questionNo = map.get("QUESTIONNO").toString();
                if ("2".equals(questionType)){
                    Map<String,Object> question = xyExamManagerMapper.getMindQuestion(questionNo,examCode,empNo);
                    mindCount++;
                    mind.add(question);
                }else if("1".equals(questionType)){
                    Map<String,Object> question = xyExamManagerMapper.getMultiQuestion(questionNo,examCode,empNo);
                    multiCount++;
                    multi.add(question);
                }else if("0".equals(questionType)){
                    Map<String,Object> question = xyExamManagerMapper.getYnQuestion(questionNo,examCode,empNo);
                    ynCount++;
                    yn.add(question);
                }else {
                    Map<String,Object> question = xyExamManagerMapper.getFillQuestion(questionNo,examCode,empNo);
                    fillCount++;
                    fill.add(question);
                }
            }
            code = "200";
            msg = "成功";
            obj.put("mindQuestion",mind);
            obj.put("fillQuestion",fill);
            obj.put("multiQuestion",multi);
            obj.put("ynQuestion",yn);
            obj.put("fillCount",fillCount);
            obj.put("mindCount",mindCount);
            obj.put("ynCount",ynCount);
            obj.put("multiCount",multiCount);
            obj.put("totalCount",fillCount+mindCount+ynCount+multiCount);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    public Map<String,Object> updateAnswer(String examCode,String empNo,String questionNo,String answer,String spare){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            int isExist =(int) xyExamManagerMapper.isExist(examCode,empNo,questionNo);
            if (isExist>0){
                xyExamManagerMapper.updateAnswer(examCode,empNo,questionNo,answer);
            }else{
                xyExamManagerMapper.addAnswer(examCode,empNo,questionNo,answer,spare);
            }
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }
}
