package cn.xyzs.api.controller;

import cn.xyzs.api.service.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/good")
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

    @ResponseBody
    @RequestMapping("/sortFilter")
    public Map<String, Object> test(String zcflCode,String startNum,String endNum,String minimum,String maximum){

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

    @ResponseBody
    @RequestMapping("/getCustomerInfoByUserId")
    public Map<String,Object> getCustomerInfoByUserId(String userId,String roleId,String startNum,String endNum,String roleType){
        return goodService.getCustomerInfoByUserId(userId,roleId,startNum,endNum,roleType);
    }

    @ResponseBody
    @RequestMapping("/getCuntomerInfoByCondition")
    public Map<String ,Object> getCuntomerInfoByCondition(String userId,String condition ,String roleType,String roleId){
        return goodService.getCuntomerInfoByCondition(userId,condition,roleType,roleId);
    }

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
    public Map<String,Object> deleteOrder(String orderId){
        return goodService.deleteOrder(orderId);
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

    @ResponseBody
    @RequestMapping("/getOrderInfo")
    public Map<String,Object> getOrderInfo(String orderId){
        return goodService.getOrderInfo(orderId);
    }

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
}
