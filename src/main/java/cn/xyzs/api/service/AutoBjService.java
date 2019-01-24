package cn.xyzs.api.service;

import cn.xyzs.api.mapper.*;
import cn.xyzs.common.pojo.XyClbZcpbTemplateList;
import cn.xyzs.common.pojo.XyMainArea;
import cn.xyzs.common.pojo.XyMainHouser;
import cn.xyzs.common.pojo.XySysDistrict;
import com.sun.corba.se.spi.ior.ObjectKey;
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

    @Resource
    private XyClbZcpbTemplateListMapper xyClbZcpbTemplateListMapper;

    @Resource
    private XyBjdTemplateListMapper xyBjdTemplateListMapper;

    @Resource
    private XyBjdFcListMapper xyBjdFcListMapper;

    /**
     * 获取一键报价首页数据
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/18 12:23
     * @param: [xyMainHouser, xyMainArea, xySysDistrict]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getFontPageData(XyMainHouser xyMainHouser, XyMainArea xyMainArea ,
                                               XySysDistrict xySysDistrict ,String flag ,Integer startNum, Integer endNum){
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
            if(!"552717F82EF442CA81FAAD9AAA3C055D".equals(xySysDistrict.getDistrictId())){
                //根据区/县获取小区(参数：districtId)
                List<Map<String ,Object>> communityInfoList = xyMainAreaMapper.getCommunityByDistrictId(xyMainArea);
                obj.put("communityInfoList",communityInfoList);
                if (!"".equals(xyMainHouser.getAreaId()) && xyMainHouser.getAreaId() != null && !"null".equals(xyMainHouser.getAreaId())){
                    //根据条件获取小区的附加查询信息(参数：areaId，houseStyle，houseLevel)
                    List<Map<String ,Object>> additionalInfoList = xyMainHouserMapper.getAdditionalInfo(xyMainHouser);
                    obj.put("additionalInfoList",additionalInfoList);
                }
            }
            //根据条件获取房屋信息(参数：districtId，areaId，houseSty，houseLevel)
            List<Map<String ,Object>> houserInfoList = xyMainHouserMapper.getHouseInfoByCondition(xyMainHouser,
                    xyMainArea,xySysDistrict,startNum,endNum);
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

    /**
     * 获取模板主材list
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 17:21
     * @param: [xyClbZcpbTemplateList, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getMbZcList(String houseId ,Integer startNum , Integer endNum){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> mbZcList = xyClbZcpbTemplateListMapper.getMbZcOrRzList(houseId,"0",startNum,endNum);
            Integer mbZcCount = xyClbZcpbTemplateListMapper.getMbZcOrRzCount(houseId,"0");
            obj.put("mbZcList",mbZcList);
            obj.put("mbZcCount",mbZcCount);
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

    /**
     * 获取模板软装list
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 17:22
     * @param: [xyClbZcpbTemplateList, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getMbRzList(String houseId ,Integer startNum , Integer endNum){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> mbRzList = xyClbZcpbTemplateListMapper.getMbZcOrRzList(houseId,"1",startNum,endNum);
            Integer mbRzCount = xyClbZcpbTemplateListMapper.getMbZcOrRzCount(houseId,"1");
            obj.put("mbRzList",mbRzList);
            obj.put("mbRzCount",mbRzCount);
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

    /**
     * 获取模板人工费项目
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 17:30
     * @param: [houseId, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getMbRgList( String houseId,Integer startNum , Integer endNum){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> mbRgList = xyBjdTemplateListMapper.getMbRgList(houseId,startNum,endNum);
            Integer mbRgCount = xyBjdTemplateListMapper.getMbRgCount(houseId);
            obj.put("mbRgList",mbRgList);
            obj.put("mbRgCount",mbRgCount);
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

    /**
     * 获取房屋信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 18:04
     * @param: [houseId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getHouseInfo( String houseId){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Map<String ,Object> houseInfo = xyMainHouserMapper.getHouseInfoByHouseId(houseId);
            obj.put("houseInfo",houseInfo);
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

    /**
     * 获取造价表数据
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/23 16:02
     * @param: [houseId, zcArray, rzArray, rgArray]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getTotalPriceTableData(String houseId ,String []zcArray ,String []rzArray ,String []rgArray){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            //主材总计价格
            Double zcTotalPrice = xyClbZcpbTemplateListMapper.getMbZcOrRzZj(houseId,"0",zcArray);
            obj.put("zcTotalPrice",zcTotalPrice);
            //人工总计价格
            Double rgTotalPrice = xyBjdTemplateListMapper.getMbRgZj(houseId,rgArray);
            obj.put("rgTotalPrice",rgTotalPrice);
            //辅材总计价格
            Double fcTotalPrice = xyBjdFcListMapper.getFcZj();
            obj.put("fcTotalPrice",fcTotalPrice);
            //硬装小计
            Double yzXj = zcTotalPrice + rgTotalPrice + fcTotalPrice;
            obj.put("yzXj",yzXj);
            //服务费
            Double fwf = (fcTotalPrice + rgTotalPrice) * 1.05;
            obj.put("fwf",fwf);
            //软装总计价格
            Double rzTotalPrice = xyClbZcpbTemplateListMapper.getMbZcOrRzZj(houseId,"0",rzArray);
            obj.put("rzTotalPrice",rzTotalPrice);
            //总计
            Double TotalPrice = yzXj + rzTotalPrice + fwf;
            obj.put("TotalPrice",TotalPrice);
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
