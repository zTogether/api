package cn.xyzs.api.controller;

import cn.xyzs.api.service.CouponService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/coupon")
public class CouponController {

    @Resource
    private CouponService couponService;

    /**
     * 获取优惠券首页数据
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/27 17:23
     * @param: [couponUser]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getCouponFontpageData")
    public Map<String ,Object> getCouponFontpageData(String couponUser){
        return couponService.getCouponFontpageData(couponUser);
    }

    /**
     * 根据couponCode获取优惠券信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/28 10:07
     * @param: [couponCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getCouponInfoByCouponCode")
    public Map<String ,Object> getCouponInfoByCouponCode(String couponCode){
        return couponService.getCouponInfoByCouponCode(couponCode);
    }
}
