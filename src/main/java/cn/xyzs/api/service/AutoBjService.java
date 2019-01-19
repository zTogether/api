package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyMainAreaMapper;
import cn.xyzs.api.mapper.XyMainHouserMapper;
import cn.xyzs.api.mapper.XySysDistrictMapper;
import cn.xyzs.common.pojo.XyMainArea;
import cn.xyzs.common.pojo.XyMainHouser;
import cn.xyzs.common.pojo.XySysDistrict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AutoBjService {

    @Resource
    private XySysDistrictMapper xySysDistrictMapper;

    @Resource
    private XyMainAreaMapper xyMainAreaMapper;

    @Resource
    private XyMainHouserMapper xyMainHouserMapper;

    /**
     * 获取一键报价首页数据
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/18 12:23
     * @param: [xyMainHouser, xyMainArea, xySysDistrict]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getFontPageData(XyMainHouser xyMainHouser, XyMainArea xyMainArea ,
                                               XySysDistrict xySysDistrict ,String flag){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            if ("0".equals(flag)){
                //获取行政区信息(参数：districtLevel，districtId)
                List<Map<String ,Object>> districtInfoList = xySysDistrictMapper.getSysDistrict(xySysDistrict);
                obj.put("districtInfoList",districtInfoList);
            }
            //根据区/县获取小区(参数：districtId)
            List<Map<String ,Object>> communityInfoList = xyMainAreaMapper.getCommunityByDistrictId(xyMainArea);
            //根据条件获取小区的附加查询信息(参数：areaId，houseStyle，houseLevel)
            List<Map<String ,Object>> additionalInfoList = xyMainHouserMapper.getAdditionalInfo(xyMainHouser);
            //根据条件获取房屋信息(参数：districtId，areaId，houseSty，houseLevel)
            List<Map<String ,Object>> houserInfoList = xyMainHouserMapper.getHouseInfoByCondition(xyMainHouser,xyMainArea,xySysDistrict);
            obj.put("communityInfoList",communityInfoList);
            obj.put("additionalInfoList",additionalInfoList);
            obj.put("houserInfoList",houserInfoList);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

}
