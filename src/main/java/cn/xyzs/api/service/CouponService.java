package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyCwbCouponMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponService {

    @Resource
    private XyCwbCouponMapper xyCwbCouponMapper;

    /**
     * 获取优惠券首页数据
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/27 17:23
     * @param: [couponUser]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getCouponFontpageData(String couponUser){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            //未使用的优惠券
            List<Map<String ,Object>> unusedCouponList = xyCwbCouponMapper.getUnusedCouponList(couponUser);
            //已使用的优惠券
            List<Map<String ,Object>> userdCouponList = xyCwbCouponMapper.getUserdCouponList(couponUser);
            //已过期的优惠券
            List<Map<String ,Object>> expiredCouponList = xyCwbCouponMapper.getExpiredCouponList(couponUser);
            obj.put("unusedCouponList",unusedCouponList);
            obj.put("userdCouponList",userdCouponList);
            obj.put("expiredCouponList",expiredCouponList);
            code = "200";
            msg = "";
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
     * 根据couponCode获取优惠券信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/28 10:07
     * @param: [couponCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getCouponInfoByCouponCode(String couponCode){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            Map<String ,Object> couponInfo = xyCwbCouponMapper.getCouponInfoByCouponCode(couponCode);
            obj.put("couponInfo",couponInfo);
            code = "200";
            msg = "";
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
