package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyClbZcDbMapper;
import cn.xyzs.api.mapper.XyClbZcFlMapper;
import cn.xyzs.api.mapper.XyCustomerInfoMapper;
import cn.xyzs.api.mapper.XyValMapper;

import cn.xyzs.api.mapper.*;
import cn.xyzs.api.pojo.XyClbZcDb;
import cn.xyzs.api.pojo.XyVal;
import cn.xyzs.api.pojo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodService {

    @Resource
    private XyClbZcFlMapper xyClbZcFlMapper;

    @Resource
    private XyClbZcDbMapper xyClbZcDbMapper;

    @Resource
    private XyValMapper xyValMapper;

    @Resource
    private XyCustomerInfoMapper xyCustomerInfoMapper;

    @Resource
    private XyClbZcShoppingMapper xyClbZcShoppingMapper;

    @Resource
    private XyClbZcOrderMapper xyClbZcOrderMapper;

    @Resource
    private XyClbZcOrderListMapper xyClbZcOrderListMapper;

    @Resource
    private XySupplierMapper xySupplierMapper;

    @Resource
    private XyClbZcOrderListFreeMapper xyClbZcOrderListFreeMapper;


    /**
     * 获取下级目录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/20 14:53
     * @param: [zcflCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public  Map<String, Object> getSubdirectory(String zcflCode){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String, Object>> Subdirectory = xyClbZcFlMapper.getSubdirectory(zcflCode);
            obj.put("Subdirectory",Subdirectory);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    public  Map<String, Object> sortFilter(String zcflCode,String startNum,String endNum,String minimum,String maximum){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        List<String> zcflCodeList = new ArrayList<>();
        String code = "500";
        String msg = "系统异常";
        try {
            if(minimum == null || minimum == ""){
                minimum = "0";
                //判断最低价是否为空的同时判断最高价是否为空
                if(maximum == null || maximum == ""){
                    //若最低价与最高价同时为空的话，则最高价为最大值，最低价为最小值0
                    maximum = "999999999";
                }
            } else {
                //若最低价不为空最高价为空的话则最高价为最大值
                if(maximum == null || maximum == ""){
                    maximum = "999999999";
                }
            }

            if (startNum == null || startNum == ""){
                startNum = "1";
                endNum = "10";
            }
            List<Map<String, Object>> YSubdirectory = xyClbZcFlMapper.getSubdirectory(zcflCode);
            if (YSubdirectory.size() < 1){
                System.out.println(1);
                zcflCodeList.add(zcflCode);
            } else {
                test(YSubdirectory,zcflCodeList);
            }
            List<XyClbZcDb> goodList = xyClbZcDbMapper.getGoodByZcType(zcflCodeList,startNum,endNum,minimum,maximum);
            for (XyClbZcDb xyClbZcDb : goodList) {
                List<XyVal> xyZcAcerList = xyValMapper.getZcAreaList(conversionList(xyClbZcDb.getZcArea()));
                xyClbZcDb.setXyZcAreas(xyZcAcerList);
            }
            obj.put("goodList",goodList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    public void test(List<Map<String, Object>> YSubdirectory,List<String> zcflCodeList){
        try {
            for (Map<String, Object> map : YSubdirectory) {
                YSubdirectory = xyClbZcFlMapper.getSubdirectory((String) map.get("ZCFL_CODE"));
                if (YSubdirectory.size()<1){
                    zcflCodeList.add((String) map.get("ZCFL_CODE"));
                    continue;
                } else {
                    test(YSubdirectory,zcflCodeList);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<String> conversionList(String a){
        String []b = a.split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i <b.length ; i++) {
            list.add(b[i]);
        }
        return list;
    }

    /**
     * 查询用户所拥有的客户
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/25 16:52
     * @param: [userId, startNum, endNum, roleType]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getCustomerInfoByUserId(String userId,String roleId,String startNum,String endNum,String roleType){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String,Object>> CustomerInfoList = null;
            if ("E".equals(roleType)){
                CustomerInfoList = xyCustomerInfoMapper.getCustomerInfoByRoleTypeE(userId,startNum,endNum);
            } else if("R".equals(roleType)){
                CustomerInfoList = xyCustomerInfoMapper.getCustomerInfoByRoleTypeR(userId,roleId,startNum,endNum);
            }
            obj.put("CustomerInfoList",CustomerInfoList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /***
     *
     * @Description: 根据zcBrand和zcVersion 查询商品并分页
     * @author: GeWeiliang
     * @date: 2018\8\27 0027 13:40
     * @param: [zcBrand, zcVersion, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> queryGoods(String condition,String startNum,String endNum){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        if (startNum == null || startNum == ""){
            startNum = "1";
            endNum = "10";
        }
        try{
            List<XyClbZcDb> goodsList = xyClbZcShoppingMapper.queryGoods(condition,startNum,endNum);
            for (XyClbZcDb xyClbZcDb : goodsList) {
                List<XyVal> xyZcAcerList = xyValMapper.getZcAreaList(conversionList(xyClbZcDb.getZcArea()));
                xyClbZcDb.setXyZcAreas(xyZcAcerList);
            }
            code = "200";
            msg = "成功";
            obj.put("goodsList",goodsList);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /***
     *
     * @Description: 根据zcCode查询商品信息
     * @author: GeWeiliang
     * @date: 2018\8\27 0027 11:04
     * @param: [zcCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public  Map<String,Object> queryGoodsByZcCode(String zcCode){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        List<XyClbZcDb> goods = null;
        try {
            List<XyClbZcDb> goodList = xyClbZcDbMapper.queryZcDb(zcCode);
            for (XyClbZcDb xyClbZcDb : goodList) {
                List<XyVal> xyZcAcerList = xyValMapper.getZcAreaList(conversionList(xyClbZcDb.getZcArea()));
                xyClbZcDb.setXyZcAreas(xyZcAcerList);
            }
            obj.put("goodList",goodList);
            code = "200";
            msg = "成功";
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
     *
     * @Description: 根据客户号查询购物车
     * @author: GeWeiliang
     * @date: 2018\8\23 0023 15:05
     * @param: [ctrCOde]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getZcShoppingByCtrCode(String ctrCode){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        double sum;
        try{
            List<Map<String,Object>> shoppingList = xyClbZcShoppingMapper.showZcShopping(ctrCode);
            for (Map<String, Object> map : shoppingList) {
                String area = xyClbZcShoppingMapper.getArea((String)map.get("ZC_TYPE"),(String)map.get("ZC_CODE"));
                List<XyVal> list = xyValMapper.getZcAreaList(conversionList(area));
                map.put("areaList",list);
            }
            if (shoppingList.size()<1){
                obj.put("ZJ",0);
            } else {
                obj.put("ZJ", shoppingList.get(shoppingList.size()-1).get("ZJ"));
            }
            code = "200";
            msg = "成功";
            obj.put("shoppingList",shoppingList);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 添加购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 9:30
     * @param: [ctrCode, opUserid, zcCode, zcQty, zcArea, zcMark]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> addShoppingCart(String ctrCode,String opUserid,String zcCode,String zcQty,String zcArea,String zcMark){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            List<Map<String,Object>> zcList = xyClbZcShoppingMapper.queryZcDb(zcCode);
            XyClbZcDb xyClbZcDb = new XyClbZcDb();
            for (Map<String, Object> map : zcList) {
                xyClbZcDb.setZcType((String) map.get("ZC_TYPE"));
                xyClbZcDb.setZcName((String) map.get("ZC_NAME"));
                xyClbZcDb.setZcPriceIn(String.valueOf(map.get("ZC_PRICE_IN")));
                xyClbZcDb.setZcPirceLook(String.valueOf(map.get("ZC_PRICE_LOOK")));
                xyClbZcDb.setZcPriceOut(String.valueOf(map.get("ZC_PRICE_OUT")));
                xyClbZcDb.setZcPriceHd(String.valueOf(map.get("ZC_PRICE_HD")));
                xyClbZcDb.setZcBrand((String)map.get("ZC_BRAND"));
                xyClbZcDb.setZcSup((String)map.get("ZC_SUP"));
                xyClbZcDb.setZcSpec((String) map.get("ZC_SPEC"));
                xyClbZcDb.setZcMaterial((String)map.get("ZC_MATERIAL"));
                xyClbZcDb.setZcColor((String)map.get("ZC_COLOR"));
                xyClbZcDb.setZcStyle((String)map.get("ZC_STYLE"));
                xyClbZcDb.setZcArea(zcArea);
                xyClbZcDb.setZcUnit((String) map.get("ZC_UNIT"));
                xyClbZcDb.setZcCyc((String) map.get("ZC_CYC"));
            }
            xyClbZcShoppingMapper.addShoppingCart(ctrCode,opUserid,zcCode,xyClbZcDb.getZcName(),xyClbZcDb.getZcType(),
                    zcQty,xyClbZcDb.getZcPriceIn(),xyClbZcDb.getZcPriceOut(),xyClbZcDb.getZcBrand(),xyClbZcDb.getZcSup(),
                    xyClbZcDb.getZcSpec(),xyClbZcDb.getZcMaterial(),xyClbZcDb.getZcColor(),xyClbZcDb.getZcUnit(),zcMark,
                    xyClbZcDb.getZcCyc(),zcArea);

            code = "200";
            msg = "已加入购物车";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 将商品移出购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 9:35
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> removeGoods(String rowId){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            xyClbZcShoppingMapper.removeGoods(rowId);
            code = "200";
            msg = "移除成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 修改购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 10:06
     * @param: [rowId, zcQty, zcArea, zcMark]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> updateGoods(String rowId,String zcQty,String zcArea,String zcMark){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            xyClbZcShoppingMapper.updateGoods(rowId,zcQty,zcArea,zcMark);
            code = "200";
            msg = "更改成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    public Map<String,Object> getCuntomerInfoByCondition(String userId, String condition ,String roleType ,String roleId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String,Object>> CustomerInfoList = null;
            if ("E".equals(roleType)){
                CustomerInfoList = xyCustomerInfoMapper.getECuntomerInfoByCondition(userId,condition);
            } else {
                CustomerInfoList = xyCustomerInfoMapper.getRCuntomerInfoByCondition(userId,condition,roleId);
            }
            code = "200";
            msg = "成功";
            obj.put("CustomerInfoList",CustomerInfoList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    @Transactional
    public Map<String,Object> addOeder(String[] rowIds,String ctrCode,String opUserid){
        List<String> rowIdList = new ArrayList<>();
        for (int i = 0; i <rowIds.length ; i++) {
            rowIdList.add(rowIds[i]);
        }
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String ,Object>> zcSupList = xyClbZcShoppingMapper.getZcSupToShoppingCar(rowIdList);
            for (Map<String, Object> map : zcSupList) {
                String singleZcsupZotal = String.valueOf(xyClbZcShoppingMapper.getSingleZCSUPTotal(rowIdList,(String)map.get("ZC_SUP")).get(0).get("SINGLEZCSUPTOTAL"));
                XyClbZcOrder xyClbZcOrder = new XyClbZcOrder();
                xyClbZcOrder.setCtrCode(ctrCode);
                xyClbZcOrder.setOpUserid(opUserid);
                xyClbZcOrder.setOrderJe(singleZcsupZotal);
                xyClbZcOrder.setOrderMark("");
                xyClbZcOrder.setOrderStatus("1");
                xyClbZcOrder.setOrderType("0");
                xyClbZcOrder.setOrderSup((String)map.get("ZC_SUP"));
                xyClbZcOrder.setEditType("1");
                xyClbZcOrder.setOrderDis("0");
                xyClbZcOrder.setOrderDisMark("");
                xyClbZcOrder.setOrderIsreturn("0");
                xyClbZcOrderMapper.addZcOrder(xyClbZcOrder);
                List<Map<String ,Object>> goodList = xyClbZcShoppingMapper.getGoodByRowIdAndZcSup(rowIdList,(String)map.get("ZC_SUP"));
                for (Map<String, Object> goodMap : goodList) {
                    XyClbZcOrderList xyClbZcOrderList = new XyClbZcOrderList();
                    xyClbZcOrderList.setOrderId(xyClbZcOrder.getOrderId());
                    xyClbZcOrderList.setZcCode(String.valueOf(goodMap.get("ZC_CODE")));
                    xyClbZcOrderList.setZcName(String.valueOf(goodMap.get("ZC_NAME")));
                    xyClbZcOrderList.setZcType(String.valueOf(goodMap.get("ZC_TYPE")));
                    xyClbZcOrderList.setZcPriceIn(String.valueOf(goodMap.get("ZC_PRICE_IN")));
                    xyClbZcOrderList.setZcPriceOut(String.valueOf(goodMap.get("ZC_PRICE_OUT")));
                    xyClbZcOrderList.setZcQty(String.valueOf(goodMap.get("ZC_QTY")));
                    xyClbZcOrderList.setZcBrand(String.valueOf(goodMap.get("ZC_BRAND")));
                    xyClbZcOrderList.setZcSup(String.valueOf(goodMap.get("ZC_SUP")));
                    xyClbZcOrderList.setZcSpec(String.valueOf(goodMap.get("ZC_SPEC")));
                    xyClbZcOrderList.setZcMaterial(String.valueOf(goodMap.get("ZC_MATERIAL")));
                    xyClbZcOrderList.setZcColor(String.valueOf(goodMap.get("ZC_COLOR")));
                    xyClbZcOrderList.setZcUnit(String.valueOf(goodMap.get("ZC_UNIT")));
                    xyClbZcOrderList.setZcMark(String.valueOf(goodMap.get("ZC_MARK")));
                    xyClbZcOrderList.setZcCyc((goodMap.get("ZC_CYC")==null)?"":String.valueOf(goodMap.get("ZC_CYC")));
                    xyClbZcOrderList.setZcArea(String.valueOf(goodMap.get("ZC_AREA")));
                    String zcVersion = xyClbZcDbMapper.getZcVersion(String.valueOf(goodMap.get("ZC_CODE")));
                    xyClbZcOrderList.setZcVersion(zcVersion);
                    xyClbZcOrderList.setZcShopStatus("0");
                    xyClbZcOrderListMapper.addOrderList(xyClbZcOrderList);
                }
            }
            xyClbZcShoppingMapper.deleteGood(rowIdList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /***
     *
     * @Description: 根据客户查询订单
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 10:09
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> showOrder(String ctrCode,String startNum,String endNum){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            List<Map<String,Object>> list = xyClbZcOrderMapper.queryOrderByctrCode(ctrCode,startNum,endNum);
            Map<String,Object> custInfo = xyCustomerInfoMapper.getCustInfoByCtrCode(ctrCode);
            code = "200";
            msg = "成功";
            obj.put("orderInfo",list);
            obj.put("custInfo",custInfo);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /***
     *
     * @Description: 查询订单明细
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 15:41
     * @param: [orderId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> showOrderList(String orderId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            List<Map<String,Object>> orderList = xyClbZcOrderListMapper.showOrderList(orderId);
            String supCode = "";
            for (Map<String, Object> map : orderList) {
                String area = xyClbZcShoppingMapper.getArea((String)map.get("ZC_TYPE"),(String)map.get("ZC_CODE"));
                List<XyVal> list = xyValMapper.getZcAreaList(conversionList(area));
                supCode = (String)map.get("ZC_SUP");
                map.put("areaList",list);
            }
            List<Map<String,Object>> supInfo = xySupplierMapper.getSupInfo(supCode);
            obj.put("supInfo",supInfo);
            obj.put("orderList",orderList);
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

    /***
     *
     * @Description: 删除订单
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 16:15
     * @param: [orderId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> deleteOrder(String orderId){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            xyClbZcOrderMapper.deleteFromOrder(orderId);
            xyClbZcOrderMapper.deleteFromOrderList(orderId);
            code = "200";
            msg = "删除成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /***
     *
     * @Description: 修改订单明细以及订单主表
     * @author: GeWeiliang
     * @date: 2018\8\30 0030 15:35
     * @param: [rowId, zcQty, zcArea, zcMark, orderId, orderJe, orderStatus, orderType, editType, orderDis, orderDisMark, orderIsreturn]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> updateOrderList(String rowId,String zcQty,String zcArea, String zcMark,
                                              String orderId,String orderJe,String orderMark,String orderStatus,String orderType,
                                              String editType,String orderDis, String orderDisMark, String orderIsreturn){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            xyClbZcOrderListMapper.updateOrderList(rowId,zcQty,zcArea,zcMark);
            if (orderId != null && orderId != ""){
                xyClbZcOrderMapper.updateOrder(orderId,orderJe,orderMark,orderStatus,orderType,editType,orderDis,orderDisMark,orderIsreturn);
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

    /**
     * 根据orderId查询非标商品
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/31 10:56
     * @param: [orderId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getNonStandard(String orderId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<XyClbZcOrderListFree> nonStandardList = xyClbZcOrderListFreeMapper.getNonStandard(orderId);
            List<XyVal> areaList = xyValMapper.getZcAreaListByValsetId("A3B32F221FF17256988E7C0A218EBF5C");
            obj.put("nonStandardList",nonStandardList);
            obj.put("areaList",areaList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("resultData",obj);
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /***
     *
     * @Description: 获取订单信息
     * @author: GeWeiliang
     * @date: 2018\8\31 0031 16:36
     * @param: [orderId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getOrderInfo(String orderId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        Map<String,Object> map;
        String code = "500";
        String msg = "系统异常";
        try {
            map = xyClbZcOrderMapper.getOrderInfo(orderId);
            obj.put("orderInfo",map);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("resultData",obj);
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    @Transactional
    public Map<String,Object> updateOrderInfo( String orderId, String orderJe,  String orderMark, String orderStatus,
                                               String orderType,  String editType, String orderDis, String orderDisMark,
                                               String orderIsreturn){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            xyClbZcOrderMapper.updateOrder(orderId,orderJe,orderMark,orderStatus,orderType,editType,orderDis,orderDisMark,orderIsreturn);
           if ("1".equals(editType)){
               xyClbZcOrderListFreeMapper.deleteOrderListFree(orderId,null);
           }
            code = "200";
            msg = "修改成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /***
     *
     * @Description: 根据rowId修改orderListFree
     * @author: GeWeiliang
     * @date: 2018\8\31 0031 18:22
     * @param: [orderId, zcQty, zcMark, zcArea]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> updateOrderListFree(String rowId,String zcQty, String zcMark,String zcArea,String orderId,
                                                  String orderJe,String orderMark,String orderStatus,String orderType,
                                                  String orderDis,String orderDisMark,String editType,String orderIsreturn){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            xyClbZcOrderListFreeMapper.updateOrderListFree(rowId,zcQty,zcMark,zcArea);
            if (orderId != null && orderId != ""){
                xyClbZcOrderMapper.updateOrder(orderId,orderJe,orderMark,orderStatus,orderType,editType,orderDis,orderDisMark,orderIsreturn);
            }
            code = "200";
            msg = "更改成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /***
     *
     * @Description: 删除非标化商品
     * @author: GeWeiliang
     * @date: 2018\9\1 0001 10:39
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> deleteOrderListFree(String rowId){
        Map<String,Object> resultMap = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            xyClbZcOrderListFreeMapper.deleteOrderListFree(null,rowId);
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
