package cn.xyzs.api.service;

import cn.xyzs.api.mapper.*;
import cn.xyzs.common.pojo.*;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
    @Resource
    private XyClbZcpbMainMapper xyClbZcpbMainMapper;
    @Resource
    private XyBjdFcTempMapper xyBjdFcTempMapper;
    @Resource
    private XyHtInfoMapper xyHtInfoMapper;
    @Resource
    private XyLogMapper xyLogMapper;
    @Resource
    private XyBjdStageMapper xyBjdStageMapper;
    @Resource
    private XyHouseFcBrandMapper xyHouseFcBrandMapper;

    @Resource
    private XyCustomerInfoMapper xyCustomerInfoMapper;

    @Resource
    private XyMainHouseImgMapper xyMainHouseImgMapper;

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
                //根据条件获取房屋信息(参数：areaId，houseTypeId，houseStyle)
                List<Map<String ,Object>> houserInfoList = xyMainHouserMapper.getHouseInfoByCondition(null,null,
                        null,null,startNum,endNum);
                obj.put("houserInfoList",houserInfoList);
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
                //根据条件获取房屋信息(参数：areaId，houseTypeId，houseStyle)
                List<Map<String ,Object>> houserInfoList = xyMainHouserMapper.getHouseInfoByCondition(districtId,areaId,
                        houseTypeId,houseStyle,startNum,endNum);
                obj.put("houserInfoList",houserInfoList);
            }

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
            List<Map<String ,Object>> mbRzList = xyClbZcpbTemplateListMapper.getMbZcOrRzList(houseId,"8");
            Integer mbRzCount = xyClbZcpbTemplateListMapper.getMbZcOrRzCount(houseId,"8");
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
            obj.put("zcTotalPrice",getRound(zcTotalPrice,100));
            //人工总计价格
            double rgTotalPrice = xyBjdTemplateListMapper.getMbRgZj(houseId,rgArray);
            obj.put("rgTotalPrice",getRound(rgTotalPrice,100));
            //辅材总计价格
            double fcTotalPrice = xyBjdTemplateListMapper.getFcZj(houseId,rgArray);
            obj.put("fcTotalPrice",getRound(fcTotalPrice*1.06,100));
            //硬装小计
            double yzXj = Double.valueOf(getRound(zcTotalPrice,100)) +
                    Double.valueOf(getRound(rgTotalPrice,100)) +
                    Double.valueOf(getRound(fcTotalPrice*1.06,100));
            obj.put("yzXj",getRound(yzXj,100));
            //服务费
            double fwf = (Double.valueOf(getRound(fcTotalPrice,100)) + Double.valueOf(getRound(rgTotalPrice,100))) * 0.15;
            obj.put("fwf",getRound(fwf,100));
            //软装总计价格
            double rzTotalPrice = xyClbZcpbTemplateListMapper.getMbZcOrRzZj(houseId,"1",rzArray);
            obj.put("rzTotalPrice",getRound(rzTotalPrice,100));
            //总计
            double TotalPrice = Double.valueOf(yzXj) + Double.valueOf(getRound(rzTotalPrice,100)) + Double.valueOf(getRound(fwf,100));
            obj.put("TotalPrice",getRound(TotalPrice,100));
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
     * 根据houseid获取辅材品牌
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/27 10:17
     * @param: [xyHouseFcBrand]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getFcBrandByHouseId(XyHouseFcBrand xyHouseFcBrand){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> fcBrandList = xyHouseFcBrandMapper.getFcBrandByCondition(xyHouseFcBrand);
            obj.put("fcBrandList",fcBrandList);
            code = "200";
            msg = "";
        } catch (SQLException e) {
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
    public Map<String ,Object> addAutoBj(HttpServletRequest request ,String ctrCode ,String houseId ,String []rgArray ,String []zcArray ,
                                      String []rzArray ,XyBjdMain xyBjdMain ,XyClbZcpbMain xyClbZcpbMain){
        Map<String, Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            String bjdCode = ctrCode + "01";

            //一键报价执行删除
            if(!"".equals(ctrCode) && !"null".equals(ctrCode) && ctrCode != null && !"undefined".equals(ctrCode)){
                xyBjdFcListMapper.autoBjDelete(bjdCode);
                xyBjdFcTempMapper.autoBjDelete(ctrCode);
                xyBjdRgListMapper.autoBjDelete(bjdCode);
                xyClbZcpbListMapper.autoBjDelete(ctrCode);
                xyClbZcpbMainMapper.autoBjDelete(ctrCode);
                xyBjdStageMapper.autoBjDelete(bjdCode);
                xyBjdMainMapper.autoBjDelete(bjdCode);
                xyHtInfoMapper.autoBjDelete(ctrCode);
            }

            //一键报价添加辅材
            xyBjdFcListMapper.addAutoBjFc(bjdCode,houseId,rgArray);
            xyBjdFcTempMapper.addAutoBjBjdFcTemp(houseId,ctrCode);

            //一键报价添加人工
            xyBjdRgListMapper.addAutoBjRg(ctrCode,houseId,rgArray);

            //一键报价添加主材和软转
            xyClbZcpbListMapper.addAutoBjZcAndRz(ctrCode,houseId,zcArray,rzArray);

            //一键报价添加主材配比主表(参数：ctrCode，opUserid，zcpbHj)
            xyClbZcpbMain.setZcpbLx("2");
            xyClbZcpbMain.setZcpbStatu("55");
            xyClbZcpbMainMapper.addAutoBjZcpbMain(xyClbZcpbMain);

            //一键报价添加bjdStage辅材
            xyBjdStageMapper.addAutoBjBjdStageFc(bjdCode,houseId,rgArray);
            //一键报价添加bjdStage人工
            xyBjdStageMapper.addAutoBjBjdStageRg(bjdCode,houseId,rgArray);

            //一键报价添加报价单主表
            xyBjdMain.setBjdCode(bjdCode);
            xyBjdMain.setCtrCode(ctrCode);
            xyBjdMainMapper.addAutoBjdMain(xyBjdMain);

            //一键报价添加合同表
            xyHtInfoMapper.addHtInfo(ctrCode);

            //一键报价添加日志表
            XyLog xyLog = new XyLog();
            xyLog.setLogIp(getIpAddr(request));
            xyLog.setUserId(xyClbZcpbMain.getOpUserid());
            xyLog.setOpId("S");
            xyLog.setLogResult("初次报价");
            xyLogMapper.addXyLog(xyLog);
            xyLog.setOpId("S");
            xyLog.setUserId("B8FC8BC5D80B4D6199A9E7752711B3EB");
            xyLog.setLogResult("报价审批通过");
            xyLogMapper.addXyLog(xyLog);
            xyLog.setOpId("S");
            xyLog.setUserId(xyClbZcpbMain.getOpUserid());
            xyLog.setLogResult("生成草稿合同");
            xyLogMapper.addXyLog(xyLog);
            xyLog.setUserId("B8FC8BC5D80B4D6199A9E7752711B3EB");
            xyLog.setOpId("S");
            xyLog.setLogResult("审核合同");
            xyLogMapper.addXyLog(xyLog);

            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    private String getRound(double a ,int b){
        a = a * b;
        int c = (int)a;
        c = (c-(int) a)>0.5? ((int) a)+1:(int) a;
        return String.valueOf(Double.valueOf(c)  / b);
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/12 9:50
     * @param: [request]
     * @return: java.lang.String
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            System.out.println("Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            System.out.println("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            System.out.println("HTTP_CLIENT_IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            System.out.println("X-Real-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            System.out.println("getRemoteAddr ip: " + ip);
        }
        System.out.println("获取客户端ip: " + ip);
        return ip;
    }

    /**
     * 一键报价根据userId获取客户
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 16:37
     * @param: [userId, roleId, startNum, endNum, roleType]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> getCustomerInfoByUserId(String userId, String roleId, String startNum, String endNum, String roleType) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String, Object>> CustomerInfoList = null;
            if ("E".equals(roleType)) {
                CustomerInfoList = xyCustomerInfoMapper.autoBjGetCustomerInfoByRoleTypeE(userId, startNum, endNum);
            } else if ("R".equals(roleType)) {
                CustomerInfoList = xyCustomerInfoMapper.autoBjGetCustomerInfoByRoleTypeR(userId, roleId, startNum, endNum);
            }
            obj.put("CustomerInfoList", CustomerInfoList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code", code);
            resultMap.put("msg", msg);
            resultMap.put("resultData", obj);
        }
        return resultMap;
    }

    /**
     * 一键报价根据userId和条件获取客户
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 16:41
     * @param: [userId, condition, roleType, roleId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> getCuntomerInfoByCondition(String userId, String condition, String roleType, String roleId) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String, Object>> CustomerInfoList = null;
            if ("E".equals(roleType)) {
                CustomerInfoList = xyCustomerInfoMapper.autoBjGetECuntomerInfoByCondition(userId, condition);
            } else {
                CustomerInfoList = xyCustomerInfoMapper.autoBjGetRCuntomerInfoByCondition(userId, condition, roleId);
            }
            code = "200";
            msg = "成功";
            obj.put("CustomerInfoList", CustomerInfoList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code", code);
            resultMap.put("msg", msg);
            resultMap.put("resultData", obj);
        }
        return resultMap;
    }

    /**
     * 根据houseId获取图片集合
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 17:26
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> getHouseImgListByHouseId(String houseId) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> houseImgList = xyMainHouseImgMapper.getHouseImgListByHouseId(houseId);
            code = "200";
            msg = "成功";
            obj.put("houseImgList", houseImgList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code", code);
            resultMap.put("msg", msg);
            resultMap.put("resultData", obj);
        }
        return resultMap;
    }



}
