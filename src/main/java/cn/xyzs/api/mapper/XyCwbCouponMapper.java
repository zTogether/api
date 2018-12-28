package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyCwbCoupon;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyCwbCouponMapper extends Mapper<XyCwbCoupon> {

    /**
     * 获取未使用的优惠券
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/27 17:07
     * @param: [couponUser]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCOUPON_CODE,\n" +
            "\tCOUPON_STATUS,\n" +
            "\tCOUPON_TYPE,\n" +
            "\tCOUPON_ISSUE_PERSONS,\n" +
            "\tTO_CHAR(COUPON_ISSUE_DATE,'yyyy-MM-dd') COUPON_ISSUE_DATE,\n" +
            "\tCOUPON_NAME,\n" +
            "\tCOUPON_NOTES,\n" +
            "\tCOUPON_MONEY,\n" +
            "\tTO_CHAR(COUPON_START_DATE,'yyyy-MM-dd') COUPON_START_DATE,\n" +
            "\tTO_CHAR(COUPON_END_DATE,'yyyy-MM-dd') COUPON_END_DATE,\n" +
            "\tCOUPON_USER,\n" +
            "\tCOUPON_USE_OP,\n" +
            "\tTO_CHAR(COUPON_USE_DATE,'yyyy-MM-dd') COUPON_USE_DATE,\n" +
            "\tCOUPON_USE_REMARK,\n" +
            "\tCOUPON_SERIAL\n" +
            "FROM\n" +
            "\tXY_CWB_COUPON\n" +
            "WHERE\n" +
            "\tCOUPON_STATUS = 0\n" +
            "AND\n" +
            "\tTO_DATE(TO_CHAR(SYSDATE, 'yyyy-MM-dd'), 'yyyy-MM-dd') <![CDATA[<=]]> TO_DATE(TO_CHAR(COUPON_END_DATE, 'yyyy-MM-dd'), 'yyyy-MM-dd')\n" +
            "AND\n" +
            "\tCOUPON_USER = #{couponUser,jdbcType=VARCHAR}" +
            "\tORDER BY COUPON_ISSUE_DATE DESC" +
            "</script>")
    public List<Map<String ,Object>> getUnusedCouponList(String couponUser) throws SQLException;

    /**
     * 获取已使用的优惠券
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/27 17:10
     * @param: [couponUser]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCOUPON_CODE,\n" +
            "\tCOUPON_STATUS,\n" +
            "\tCOUPON_TYPE,\n" +
            "\tCOUPON_ISSUE_PERSONS,\n" +
            "\tTO_CHAR(COUPON_ISSUE_DATE,'yyyy-MM-dd') COUPON_ISSUE_DATE,\n" +
            "\tCOUPON_NAME,\n" +
            "\tCOUPON_NOTES,\n" +
            "\tCOUPON_MONEY,\n" +
            "\tTO_CHAR(COUPON_START_DATE,'yyyy-MM-dd') COUPON_START_DATE,\n" +
            "\tTO_CHAR(COUPON_END_DATE,'yyyy-MM-dd') COUPON_END_DATE,\n" +
            "\tCOUPON_USER,\n" +
            "\tCOUPON_USE_OP,\n" +
            "\tTO_CHAR(COUPON_USE_DATE,'yyyy-MM-dd') COUPON_USE_DATE,\n" +
            "\tCOUPON_USE_REMARK,\n" +
            "\tCOUPON_SERIAL\n" +
            "FROM\n" +
            "\tXY_CWB_COUPON\n" +
            "WHERE\n" +
            "\tCOUPON_STATUS = 1\n" +
            "AND\n" +
            "\tCOUPON_USER = #{couponUser,jdbcType=VARCHAR}\n" +
            "ORDER BY COUPON_USE_DATE DESC" +
            "</script>")
    public List<Map<String ,Object>> getUserdCouponList(String couponUser) throws SQLException;

    /**
     * 获取已过期的优惠券
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/27 17:17
     * @param: [couponUser]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCOUPON_CODE,\n" +
            "\tCOUPON_STATUS,\n" +
            "\tCOUPON_TYPE,\n" +
            "\tCOUPON_ISSUE_PERSONS,\n" +
            "\tTO_CHAR(COUPON_ISSUE_DATE,'yyyy-MM-dd') COUPON_ISSUE_DATE,\n" +
            "\tCOUPON_NAME,\n" +
            "\tCOUPON_NOTES,\n" +
            "\tCOUPON_MONEY,\n" +
            "\tTO_CHAR(COUPON_START_DATE,'yyyy-MM-dd') COUPON_START_DATE,\n" +
            "\tTO_CHAR(COUPON_END_DATE,'yyyy-MM-dd') COUPON_END_DATE,\n" +
            "\tCOUPON_USER,\n" +
            "\tCOUPON_USE_OP,\n" +
            "\tTO_CHAR(COUPON_USE_DATE,'yyyy-MM-dd') COUPON_USE_DATE,\n" +
            "\tCOUPON_USE_REMARK,\n" +
            "\tCOUPON_SERIAL\n" +
            "FROM\n" +
            "\tXY_CWB_COUPON\n" +
            "WHERE\n" +
            "\tCOUPON_STATUS = 0\n" +
            "AND\n" +
            "\tTO_DATE(TO_CHAR(SYSDATE, 'yyyy-MM-dd'), 'yyyy-MM-dd') <![CDATA[>]]> TO_DATE(TO_CHAR(COUPON_END_DATE, 'yyyy-MM-dd'), 'yyyy-MM-dd')\n" +
            "AND\n" +
            "\tCOUPON_USER = #{couponUser,jdbcType=VARCHAR}\n" +
            "ORDER BY COUPON_END_DATE DESC" +
            "</script>")
    public List<Map<String ,Object>> getExpiredCouponList(String couponUser) throws SQLException;

    /**
     * 根据couponCode获取优惠券信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/28 10:06
     * @param: [couponCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCOUPON_CODE,\n" +
            "\tCOUPON_STATUS,\n" +
            "\tCOUPON_TYPE,\n" +
            "\tCOUPON_ISSUE_PERSONS,\n" +
            "\tTO_CHAR( COUPON_ISSUE_DATE, 'yyyy-MM-dd' ) COUPON_ISSUE_DATE,\n" +
            "\tCOUPON_NAME,\n" +
            "\tCOUPON_NOTES,\n" +
            "\tCOUPON_MONEY,\n" +
            "\tTO_CHAR( COUPON_START_DATE, 'yyyy-MM-dd' ) COUPON_START_DATE,\n" +
            "\tTO_CHAR( COUPON_END_DATE, 'yyyy-MM-dd' ) COUPON_END_DATE,\n" +
            "\tCOUPON_USER,\n" +
            "\tCOUPON_USE_OP,\n" +
            "\tTO_CHAR( COUPON_USE_DATE, 'yyyy-MM-dd' ) COUPON_USE_DATE,\n" +
            "\tCOUPON_USE_REMARK,\n" +
            "\tCOUPON_SERIAL \n" +
            "FROM\n" +
            "\tXY_CWB_COUPON \n" +
            "WHERE\n" +
            "\tCOUPON_CODE = #{couponCode,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,Object> getCouponInfoByCouponCode(String couponCode) throws SQLException;

}
