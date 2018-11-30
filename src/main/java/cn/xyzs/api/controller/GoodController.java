package cn.xyzs.api.controller;

import cn.xyzs.api.pojo.XyClbZcOrder;
import cn.xyzs.api.service.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/good")
public class GoodController {

    @Resource
    private GoodService goodService;


    /**
     * 获取下级目录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/20 14:53
     * @param: [zcflCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getSubdirectory")
    public Map<String, Object> getSubdirectory(String zcflCode){
        return goodService.getSubdirectory(zcflCode);
    }

    /**
     * 分类筛选获取商品信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/1 13:20
     * @param: [zcflCode, startNum, endNum, minimum, maximum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/sortFilter")
    public Map<String, Object> sortFilter(String zcflCode,String startNum,String endNum,String minimum,String maximum){
        return goodService.sortFilter(zcflCode,startNum,endNum,minimum,maximum);
    }

    /***
     *
     * @Description: 查询商品信息
     * @author: GeWeiliang
     * @date: 2018\8\27 0027 11:06
     * @param: [zcCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/showGoodsInfo")
    public Map<String,Object> showGoodsInfo(String zcCode){
        return goodService.queryGoodsByZcCode(zcCode);
    }

    /***
     *
     * @Description: 根据品牌和zcVersion查询并分页展示
     * @author: GeWeiliang
     * @date: 2018\8\27 0027 13:43
     * @param: [zcBrand, zcVersion, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/queryGoods")
    public Map<String,Object> queryGoods(String condition,String startNum,String endNum){
        return goodService.queryGoods(condition,startNum,endNum);
    }
    /**
     *
     * @Description: 根据客户号查询购物车
     * @author: GeWeiliang
     * @date: 2018\8\23 0023 15:06
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/showZcShopping")
    @ResponseBody
    public Map<String,Object> showZcShopping(String ctrCode){
        return goodService.getZcShoppingByCtrCode(ctrCode);
    }

    /**
     *
     * @Description: 添加购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 9:13
     * @param: [ctrCode, opUserid, zcCode, zcQty, zcArea, zcMark]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/addShoppingCart")
    @ResponseBody
    public Map<String,Object> addShoppingCart(String ctrCode,String opUserid,String zcCode,String zcQty,String zcArea,String zcMark){
        return goodService.addShoppingCart(ctrCode,opUserid,zcCode,zcQty,zcArea,zcMark);
    }

    /**
     *
     * @Description: 移出购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 9:37
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/removeGoods")
    @ResponseBody
    public Map<String,Object> removeGoods(String rowId){
        return goodService.removeGoods(rowId);
    }

    /**
     *
     * @Description: 修改购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 10:07
     * @param: [rowId, zcQty, zcArea, zcMark]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/updateGoods")
    @ResponseBody
    public Map<String,Object> updateGoods(String rowId,String zcQty,String zcArea,String zcMark){
        return goodService.updateGoods(rowId,zcQty,zcArea,zcMark);
    }

    /**
     * 获取用户所拥有的客户
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/1 13:20
     * @param: [userId, roleId, startNum, endNum, roleType]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getCustomerInfoByUserId")
    public Map<String,Object> getCustomerInfoByUserId(String userId,String roleId,String startNum,String endNum,String roleType){
        return goodService.getCustomerInfoByUserId(userId,roleId,startNum,endNum,roleType);
    }

    /**
     * 根据条件获取用户所拥有的客户
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/1 13:20
     * @param: [userId, condition, roleType, roleId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getCuntomerInfoByCondition")
        public Map<String ,Object> getCuntomerInfoByCondition(String userId,String condition ,String roleType,String roleId){
        return goodService.getCuntomerInfoByCondition(userId,condition,roleType,roleId);
    }

    /**
     * 添加订单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/1 13:21
     * @param: [rowIds, ctrCode, opUserid]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/addOeder")
    public Map<String ,Object> addOrder(String[] rowIds,String ctrCode,String opUserid){
        return goodService.addOeder(rowIds,ctrCode,opUserid);
    }

    /***
     *
     * @Description: 查看订单
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 15:41
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/showOrder")
    public Map<String,Object> showOrderByCtrCode(String ctrCode,String startNum,String endNum){
        return goodService.showOrder(ctrCode,startNum,endNum);
    }

    /***
     *
     * @Description: 订单详情
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 15:45
     * @param: [orderId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/showOrderList")
    @ResponseBody
    public Map<String,Object> showOrderList(String orderId){
        return goodService.showOrderList(orderId);
    }

    /***
     *
     * @Description: 删除订单
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 16:16
     * @param: [orderId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/deleteOrder")
    public Map<String,Object> deleteOrder(String orderId,String rowId){
        return goodService.deleteOrder(orderId,rowId);
    }

    /***
     *
     * @Description: 删除订单中商品
     * @author: GeWeiliang
     * @date: 2018\9\13 0013 15:23
     * @param: [orderId, rowId, flag]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/deleteOrderGoods")
    public Map<String,Object> deleteOrderGoods(String orderId,String rowId,String flag){
        return goodService.deleteOrderGoods(orderId,rowId,flag);
    }

    /***
     *
     * @Description: 修改订单
     * @author: GeWeiliang
     * @date: 2018\8\30 0030 15:39
     * @param: [rowId, zcQty, zcArea, zcMark, orderId, orderJe, orderMark, orderStatus, orderType, editType, orderDis, orderDisMark, orderIsreturn]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/updateOrderList")
    public Map<String,Object> updateOrder(String rowId,String zcQty,String zcArea, String zcMark,
                                          String orderId,String orderJe,String orderMark,String orderStatus,String orderType,
                                          String editType, String orderDis, String orderDisMark, String orderIsreturn){
        return goodService.updateOrderList(rowId,zcQty,zcArea,zcMark,orderId,orderJe,orderMark,orderStatus,orderType,editType,
                orderDis,orderDisMark,orderIsreturn);
    }

    /**
     * 根据orderId查询非标商品
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/31 10:59
     * @param: [orderId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getNonStandard")
    public Map<String ,Object> getNonStandard(String orderId){
        return goodService.getNonStandard(orderId);
    }

    /**
     * 获取订单信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/1 13:21
     * @param: [orderId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getOrderInfo")
    public Map<String,Object> getOrderInfo(String orderId){
        return goodService.getOrderInfo(orderId);
    }

    /**
     * 修改订单信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/1 13:21
     * @param: [orderId, orderJe, orderMark, orderStatus, orderType, editType, orderDis, orderDisMark, orderIsreturn]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/updateOrderInfo")
    public Map<String,Object> updateOrderInfo(String orderId, String orderJe,  String orderMark, String orderStatus,
                                              String orderType,  String editType, String orderDis, String orderDisMark,
                                              String orderIsreturn){
        return goodService.updateOrderInfo(orderId,orderJe,orderMark,orderStatus,orderType,editType,orderDis,orderDisMark,orderIsreturn);
    }

    /***
     *
     * @Description: 根据rowId修改OrderListFree
     * @author: GeWeiliang
     * @date: 2018\8\31 0031 18:32
     * @param: [rowId, zcQty, zcMark, zcArea]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/updateOrderListFree")
    @ResponseBody
    public Map<String,Object> updateOrderListFree(String rowId,String zcQty, String zcMark,String zcArea,String orderId,
                                                  String orderJe,String orderMark,String orderStatus,String orderType,
                                                  String orderDis,String orderDisMark,String editType,String orderIsreturn){
        return goodService.updateOrderListFree(rowId,zcQty,zcMark,zcArea,orderId,orderJe,orderMark,orderStatus,orderType,orderDis,
                orderDisMark,editType,orderIsreturn);
    }

    /**
     *
     * @Description: 根据rowId删除非标化商品
     * @author: GeWeiliang
     * @date: 2018\9\1 0001 10:42
     * @param: [rowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/deleteOrderListFree")
    public Map<String,Object> deleteOrderListFree(String rowId){
        return goodService.deleteOrderListFree(rowId);
    }

    @ResponseBody
    @RequestMapping("/getRandGoods")
    public Map<String,Object> getRandGoods(){
        return goodService.getRandGoods();
    }

    /**
     * 根据档案号获取所包含的供应商
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/22 11:57
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getSupByCtrCode")
    public Map<String ,Object> getSupByCtrCode(String ctrCode){
        return goodService.getSupByCtrCode(ctrCode);
    }

    /**
     * 根据供应商和档案号获取可退货的的商品
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/22 14:15
     * @param: [ctrCode, orderSup]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getZcByOrderSupAndCtrCode")
    public Map<String ,Object> getZcByOrderSupAndCtrCode(String ctrCode ,String orderSup){
        return goodService.getZcByOrderSupAndCtrCode(ctrCode,orderSup);
    }

    /**
     * 添加主材退货单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/22 16:24
     * @param: [xyClbZcOrder, zcCodeArray, zcNameArray, zcTypeArray, zcPriceInArray, zcPriceOutArray, zcQtyArray, zcBrandArray, zcSupArray, zcSpecArray, zcMaterialArray, zcColorArray, zcUnitArray, zcCycArray, zcAreaArray, zcVersionArray, zcShopStatusArray]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/addZcTHD")
    public Map<String ,Object> addZcTHD(XyClbZcOrder xyClbZcOrder,
                                        String[] zcCodeArray,  String[] zcNameArray, String[] zcTypeArray,
                                        String[] zcPriceInArray, String[] zcPriceOutArray, String[] zcQtyArray,
                                        String[] zcBrandArray,  String[] zcSupArray, String[] zcSpecArray,
                                        String[] zcMaterialArray,  String[] zcColorArray,  String[] zcUnitArray,
                                        String[] zcCycArray, String[] zcAreaArray , String[] zcVersionArray,
                                        String[] zcShopStatusArray
    ){
        return goodService.addZcTHD(xyClbZcOrder,zcCodeArray,zcNameArray,zcTypeArray,zcPriceInArray,zcPriceOutArray,
                zcQtyArray,zcBrandArray,zcSupArray,zcSpecArray,zcMaterialArray,zcColorArray,zcUnitArray,zcCycArray,
                zcAreaArray,zcVersionArray,zcShopStatusArray);
    }

    /**
     * 获取订单金额与优惠金额
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/29 13:07
     * @param: [orderId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getOrderJeAndYhJe")
    public Map<String ,Object> getOrderJeAndYhJe(String orderId){
        return goodService.getOrderJeAndYhJe(orderId);
    }
}
