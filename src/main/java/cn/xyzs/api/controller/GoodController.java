package cn.xyzs.api.controller;

import cn.xyzs.api.service.GoodService;
import cn.xyzs.api.service.XyCustomerInfoService;
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

    @Resource
    private XyCustomerInfoService xyCustomerInfoService;

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
    public Map<String, Object> test(String zcflCode,String startNum,String endNum){

        return goodService.sortFilter(zcflCode,startNum,endNum);
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
    public Map<String,Object> getCustomerInfoByUserId(String userId,String startNum,String endNum,String roleType){
        return goodService.getCustomerInfoByUserId(userId,startNum,endNum,roleType);
    }

    @ResponseBody
    @RequestMapping("/getCuntomerInfoByCondition")
    public Map<String ,Object> getCuntomerInfoByCondition(String userId, String condition ,String roleType){
        return goodService.getCuntomerInfoByCondition(userId,condition,roleType);
    }
}
