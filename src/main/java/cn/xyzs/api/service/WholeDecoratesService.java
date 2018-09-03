package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyClbZctxMb;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WholeDecoratesService {
    @Resource
    private XyClbZctxMb xyClbZctxMb;

    /***
     *
     * @Description: 展示主材套系vr
     * @author: GeWeiliang
     * @date: 2018\9\2 0002 17:47
     * @param:
     * @return:
     */
    public Map<String,Object> showZctxVr(String vrStyle){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            List<Map<String,Object>> vrList = xyClbZctxMb.showZctxVr(vrStyle);
            obj.put("zctxVr",vrList);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }
}
