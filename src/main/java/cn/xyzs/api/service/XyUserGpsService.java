package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyUserGpsMapper;
import cn.xyzs.api.pojo.XyUserGps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class XyUserGpsService {

    @Resource
    private XyUserGpsMapper xyUserGpsMapper;

    /**
     * 获取实时定位
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/28 10:46
     * @param: [xyUserGps]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> getRealTimePositioning(XyUserGps xyUserGps){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String msg = "系统异常";
        String code = "500";
        try{
            int isExistData = xyUserGpsMapper.isExistData(xyUserGps.getUserId());
            if (isExistData < 1){
                xyUserGpsMapper.addUserGps(xyUserGps);
            } else {
                xyUserGpsMapper.updateUserGpsByUserId(xyUserGps);
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
