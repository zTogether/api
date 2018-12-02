package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyUserGpsMapper;
import cn.xyzs.common.pojo.XyUserGps;
import cn.xyzs.common.util.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
            if ("wgs84".equals(xyUserGps.getCoordinateType().toLowerCase())){
                Map<String ,String> parameterMap = new HashMap<>();
                String locations = xyUserGps.getLongitude() + "," + xyUserGps.getLatitude();
                parameterMap.put("locations",locations);
                parameterMap.put("coordsys","gps");
                parameterMap.put("output","JSON");
                parameterMap.put("key","1372b5856d762072e845b4b95b54fec3");
                String result = HttpUtil.doPost("https://restapi.amap.com/v3/assistant/coordinate/convert",parameterMap);
                JSONObject resultJson = JSON.parseObject(result);
                locations = resultJson.getString("locations");
                String[] locationsArray = locations.split(",");
                xyUserGps.setLatitude(locationsArray[1]);
                xyUserGps.setLongitude(locationsArray[0]);
            }
           int isExistData = xyUserGpsMapper.isExistData(xyUserGps.getUserId());
            if (isExistData < 1){
                xyUserGpsMapper.addUserGps(xyUserGps);
            } else {
                xyUserGpsMapper.updateUserGpsByUserId(xyUserGps);
            }
            code = "200";
            msg = "成功";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }
}
