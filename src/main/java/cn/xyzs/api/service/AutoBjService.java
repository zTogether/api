package cn.xyzs.api.service;

import cn.xyzs.api.mapper.*;
import cn.xyzs.common.pojo.*;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private XyHouserTypeMapper xyHouserTypeMapper;

    //执行一键报价的添加操作
    @Resource
    private XyBjdFcListMapper xyBjdFcListMapper;
    @Resource
    private XyBjdRgListMapper xyBjdRgListMapper;
    @Resource
    private XyClbZcpbListMapper xyClbZcpbListMapper;
    @Resource
    private XyBjdMainMapper xyBjdMainMapper;

    /**
     * 获取一键报价首页数据
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/18 12:23
     * @param: [xyMainHouser, xyMainArea, xySysDistrict]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getFontPageData(String flag ,String districtId,
                                               String areaId ,String houseTypeId ,String houseStyle ,
                                               Integer startNum, Integer endNum){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            if ("0".equals(flag)){
                //获取行政区信息(参数：districtLevel，districtId)
                XySysDistrict xySysDistrict = new XySysDistrict();
                xySysDistrict.setDistrictId("552717F82EF442CA81FAAD9AAA3C055D");
                List<Map<String ,Object>> districtInfoList = xySysDistrictMapper.getSysDistrict(xySysDistrict);
                obj.put("districtInfoList",districtInfoList);
            } else {
                //根据区/县获取小区(参数：districtId)
                XyMainArea xyMainArea = new XyMainArea();
                xyMainArea.setDistrictId(districtId);
                List<Map<String ,Object>> communityInfoList = xyMainAreaMapper.getCommunityByDistrictId(xyMainArea);
                obj.put("communityInfoList",communityInfoList);

                if (!"".equals(areaId) && !"null".equals(areaId) && areaId != null){
                    //根据小区查户型（参数：areaId）
                    List<Map<String ,Object>> houserTypeInfoList = xyHouserTypeMapper.getHouserTypeByAreaId(areaId);
                    obj.put("houserTypeInfoList",houserTypeInfoList);
                }

                if (!"".equals(houseTypeId) && !"null".equals(houseTypeId) && houseTypeId != null){
                    //根据查户型查风格（参数：houseTypeId）
                    List<Map<String ,Object>> houseStyleInfoList = xyMainHouserMapper.getHouseStyleByHouseTypeId(houseTypeId);
                    obj.put("houseStyleInfoList",houseStyleInfoList);
                }
            }
            //根据条件获取房屋信息(参数：areaId，houseTypeId，houseStyle)
            List<Map<String ,Object>> houserInfoList = xyMainHouserMapper.getHouseInfoByCondition(areaId,
                    houseTypeId,houseStyle,startNum,endNum);
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
    public Map<String ,Object> getMbZcList(String houseId ){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> mbZcList = xyClbZcpbTemplateListMapper.getMbZcOrRzList(houseId,"0");
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
    public Map<String ,Object> getMbRzList(String houseId ){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> mbRzList = xyClbZcpbTemplateListMapper.getMbZcOrRzList(houseId,"1");
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
    public Map<String ,Object> getMbRgList( String houseId){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> mbRgList = xyBjdTemplateListMapper.getMbRgList(houseId);
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
            double zcTotalPrice = xyClbZcpbTemplateListMapper.getMbZcOrRzZj(houseId,"0",zcArray);
            obj.put("zcTotalPrice",zcTotalPrice);
            //人工总计价格
            double rgTotalPrice = xyBjdTemplateListMapper.getMbRgZj(houseId,rgArray);
            obj.put("rgTotalPrice",rgTotalPrice);
            //辅材总计价格
            double fcTotalPrice = xyBjdFcListMapper.getFcZj();
            obj.put("fcTotalPrice",fcTotalPrice);
            //硬装小计
            double yzXj = zcTotalPrice + rgTotalPrice + fcTotalPrice;
            obj.put("yzXj",yzXj);
            //服务费
            double fwf = (fcTotalPrice + rgTotalPrice) * 1.05;
            obj.put("fwf",fwf);
            //软装总计价格
            double rzTotalPrice = xyClbZcpbTemplateListMapper.getMbZcOrRzZj(houseId,"0",rzArray);
            obj.put("rzTotalPrice",rzTotalPrice);
            //总计
            double TotalPrice = yzXj + rzTotalPrice + fwf;
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

    /**
     * 执行一键报价添加操作
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/24 14:05
     * @param: [ctrCode, houseId, rgArray, zcArray, rzArray, xyBjdMain]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> addAutoBj(String ctrCode ,String houseId ,String []rgArray ,String []zcArray ,
                                      String []rzArray ,XyBjdMain xyBjdMain){
        Map<String, Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            String bjdCode = ctrCode + "01";
            //一键报价添加辅材
            xyBjdFcListMapper.addAutoBjFc(bjdCode);
            //一键报价添加人工
            xyBjdRgListMapper.addAutoBjRg(ctrCode,houseId,rgArray);
            //一键报价添加主材和软转
            xyClbZcpbListMapper.addAutoBjZcAndRz(ctrCode,houseId,zcArray,rzArray);
            //一键报价添加主表
            xyBjdMain.setBjdCode(bjdCode);
            xyBjdMain.setCtrCode(ctrCode);
            xyBjdMainMapper.addAutoBjdMain(xyBjdMain);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

}
