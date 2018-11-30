package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbZcOrderFh;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

public interface XyClbZcOrderFhMapper extends Mapper<XyClbZcOrderFh>{

    /**
     * 根据orderId获取数量
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/19 17:13
     * @param: [orderId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM XY_CLB_ZC_ORDER_FH WHERE ORDER_ID = #{orderId,jdbcType=VARCHAR}" +
            "</script>")
    public Integer getCountByOderId(String orderId) throws SQLException;

    /**
     * 添加主材订单发货表
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/19 17:17
     * @param: [orderId]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_CLB_ZC_ORDER_FH(ORDER_ID,ORDER_SUP,KH_STATU,KH_DATE) \n" +
            "VALUES(\n" +
            "\t#{orderId,jdbcType=VARCHAR},\n" +
            "\t(SELECT ORDER_SUP FROM XY_CLB_ZC_ORDER WHERE ORDER_ID = #{orderId,jdbcType=VARCHAR}),\n" +
            "\t1,\n" +
            "\tSYSDATE\n" +
            ")" +
            "</script>")
    public void addZcOrderFh(String orderId) throws SQLException;

    /**
     * 修改客户验收状态与时间
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/19 17:19
     * @param: [orderId]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_CLB_ZC_ORDER_FH \n" +
            "SET \n" +
            "\tKH_STATU = 1,\n" +
            "\tKH_DATE = SYSDATE\n" +
            "WHERE ORDER_ID = #{orderId,jdbcType=VARCHAR}" +
            "</script>")
    public void updateKhYs(String orderId) throws SQLException;

    /**
     * 判断客户是否可以验收
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/19 17:36
     * @param: [orderId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM XY_CLB_ZC_ORDER_FH WHERE ORDER_ID = #{orderId,jdbcType=VARCHAR} AND KH_STATU = 1" +
            "</script>")
    public Integer isKhYs(String orderId) throws SQLException;
}
