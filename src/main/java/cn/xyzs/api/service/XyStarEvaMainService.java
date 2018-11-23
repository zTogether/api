package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyStarEvaMainMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class XyStarEvaMainService {
    @Resource
    private XyStarEvaMainMapper xyStarEvaMainMapper;
    /**
     *
     * @Description: 添加星级评论
     * @author: GeWeiliang
     * @date: 2018\11\2 0002 13:15
     * @param: [evaNo, evaType, level, evaluation, evaName]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> addStarEva(String evaNo,String evaType,String level,String evaluation,String evaName){
        Map<String,Object> resultMap = new HashMap<>();
        String msg = "系统异常";
        String code = "500";
        try{
            xyStarEvaMainMapper.addEvaMain(evaNo,evaType);
            xyStarEvaMainMapper.addEvaList(evaNo,level,evaluation,evaName);
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
