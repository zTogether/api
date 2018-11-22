package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcOrder;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcOrderMapper extends Mapper<XyClbZcOrder> {
    /**
     *
     * @Description: 添加订单主表
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 18:39
     * @param: [orderDate, ctrCode, opUserid, orderJe, orderMark]
     * @return: int
     */
    @Insert("INSERT INTO \n" +
            "\tXY_CLB_ZC_ORDER ( \n" +
            "\t\tORDER_ID,\n" +
            "\t\tORDER_DATE,\n" +
            "\t\tCTR_CODE,\n" +
            "\t\tOP_USERID,\n" +
            "\t\tORDER_JE,\n" +
            "\t\tORDER_MARK,\n" +
            "\t\tORDER_STATUS,\n" +
            "\t\tORDER_TYPE,\n" +
            "\t\tORDER_SUP,\n" +
            "\t\tEDIT_TYPE,\n" +
            "\t\tORDER_DIS,\n" +
            "\t\tORDER_DIS_MARK,\n" +
            "\t\tORDER_ISRETURN\n" +
            "\n" +
            "\t) \n" +
            "VALUES(\n" +
            "\tsys_guid(),\n" +
            "\tSYSDATE,\n" +
            "\t#{ctrCode,jdbcType=VARCHAR},\n" +
            "\t#{opUserid,jdbcType=VARCHAR},\n" +
            "\t#{orderJe,jdbcType=VARCHAR},\n" +
            "\t#{orderMark,jdbcType=VARCHAR},\n" +
            "\t#{orderStatus,jdbcType=VARCHAR},\n" +
            "\t#{orderType,jdbcType=VARCHAR},\n" +
            "\t#{orderSup,jdbcType=VARCHAR},\n" +
            "\t#{editType,jdbcType=VARCHAR},\n" +
            "\t#{orderDis,jdbcType=VARCHAR},\n" +
            "\t#{orderDisMark,jdbcType=VARCHAR},\n" +
            "\t#{orderIsreturn,jdbcType=VARCHAR}\n" +
            ")")
    @Options(useGeneratedKeys=true, keyProperty="orderId", keyColumn="ORDER_ID")
    public void addZcOrder(XyClbZcOrder xyClbZcOrder) throws SQLException;

    /***
     *
     * @Description: 根据ctrCode查询订单
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 9:40
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tB.*,TO_CHAR(B.ORDER_DATE,'yyyy-MM-dd hh24:mi:ss') ORDERDATE\n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tA.*,\n" +
            "\t\tROWNUM RN \n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tzo.*,\n" +
            "\t\t\tu.USER_NAME,\n" +
            "\t\t\tsup.SUP_NAME \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CLB_ZC_ORDER zo,\n" +
            "\t\t\tXY_USER u,\n" +
            "\t\t\tXY_SUPPLIER sup \n" +
            "\t\tWHERE\n" +
            "\t\t\tzo.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} \n" +
            "\t\t\tAND zo.OP_USERID = u.USER_ID \n" +
            "\t\t\tAND sup.SUP_CODE = zo.ORDER_SUP\n" +
            "\t\t\tORDER BY zo.ORDER_DATE DESC\n" +
            "\t\t) A\n" +
            "\t) B WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> queryOrderByctrCode(@Param("ctrCode") String ctrCode,@Param("startNum")String startNum,@Param("endNum" )String endNum) throws SQLException;

    /***
     *
     * @Description: 删除订单主表
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 9:48
     * @param: [orderId]
     * @return: void
     */
    @Delete("DELETE FROM XY_CLB_ZC_ORDER WHERE ORDER_ID=#{orderId,jdbcType=VARCHAR}")
    public void deleteFromOrder(@Param("orderId") String orderId) throws SQLException;


    /***
     *
     * @Description: 根据orderId修改订单表
     * @author: GeWeiliang
     * @date: 2018\8\30 0030 15:26
     * @param: [orderId, orderJe, orderMark, orderStatus, orderType, editType, orderDis, orderDisMark, orderIsreturn]
     * @return: void
     */
    @UpdateProvider(type = updateOrder.class,method = "updateOrder")
    public void updateOrder(@Param("orderId") String orderId,@Param("orderJe") String orderJe,
                            @Param("orderMark") String orderMark,@Param("orderStatus") String orderStatus,
                            @Param("orderType") String orderType, @Param("editType") String editType,
                            @Param("orderDis") String orderDis,@Param("orderDisMark") String orderDisMark,
                            @Param("orderIsreturn") String orderIsreturn)throws SQLException;
    class updateOrder{
        public String updateOrder(@Param("orderId") String orderId,@Param("orderJe") String orderJe,
                                  @Param("orderMark") String orderMark,@Param("orderStatus") String orderStatus,
                                  @Param("orderType") String orderType,@Param("editType") String editType,
                                  @Param("orderDis") String orderDis,@Param("orderDisMark") String orderDisMark,
                                  @Param("orderIsreturn") String orderIsreturn){
            return new SQL(){{
                UPDATE("XY_CLB_ZC_ORDER");
                SET("ORDER_ID=#{orderId,jdbcType=VARCHAR}");
                if (orderJe!=null && orderJe!=""){
                    SET("ORDER_JE=#{orderJe,jdbcType=VARCHAR}");
                }
                if (orderMark!=null){
                    SET("ORDER_MARK=#{orderMark,jdbcType=VARCHAR}");
                }
                if (orderStatus!=null && orderStatus!=""){
                    SET("ORDER_STATUS=#{orderStatus,jdbcType=VARCHAR}");
                }
                if (orderType!=null && orderType!=""){
                    SET("ORDER_TYPE=#{orderType,jdbcType=VARCHAR}");
                }
                if (editType!=null && editType!=""){
                    SET("EDIT_TYPE=#{editType,jdbcType=VARCHAR}");
                }
                if (orderDis!=null && orderDis!=""){
                    SET("ORDER_DIS=#{orderDis,jdbcType=VARCHAR}");
                }
                if (orderDisMark!=null){
                    SET("ORDER_DIS_MARK=#{orderDisMark,jdbcType=VARCHAR}");
                }
                if (orderIsreturn!=null && orderIsreturn!=""){
                    SET("ORDER_ISRETURN=#{orderIsreturn,jdbcType=VARCHAR}");
                }
                WHERE("ORDER_ID=#{orderId,jdbcType=VARCHAR}");
            }}.toString();
        }
    }

    /***
     *
     * @Description: 根据orderId获取订单信息
     * @author: GeWeiliang
     * @date: 2018\8\31 0031 16:37
     * @param: [orderId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>SELECT * FROM XY_CLB_ZC_ORDER WHERE ORDER_ID=#{orderId,jdbcType=VARCHAR}</script>")
    public Map<String,Object> getOrderInfo(@Param("orderId") String orderId)throws SQLException;

    /**
     * 获取允许执行员发送主材订单验收的主材订单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/19 13:22
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tXCZO.ORDER_ID, \n" +
            "\tTO_CHAR(XCZO.ORDER_DATE,'yyyy-MM-dd HH24:mi:ss') ORDER_DATE, \n" +
            "\tXCZO.CTR_CODE, \n" +
            "\tXCZO.OP_USERID, \n" +
            "\tXCZO.ORDER_JE, \n" +
            "\tXCZO.ORDER_MARK, \n" +
            "\tXCZO.ORDER_STATUS, \n" +
            "\tXCZO.ORDER_TYPE, \n" +
            "\tXCZO.ORDER_SUP, \n" +
            "\tXCZO.EDIT_TYPE, \n" +
            "\tXCZO.ORDER_DIS, \n" +
            "\tXCZO.ORDER_DIS_MARK, \n" +
            "\tXCZO.ORDER_ISRETURN,\n" +
            "\tTO_CHAR(XCZO.PRINT_DATE,'yyyy-MM-dd HH24:mi:ss') PRINT_DATE,\n" +
            "\txs.SUP_NAME\n" +
            "FROM\n" +
            "\tXY_CLB_ZC_ORDER XCZO,\n" +
            "\tXY_SUPPLIER xs\n" +
            "WHERE\n" +
            "\tXCZO.ORDER_ISRETURN = 0 \n" +
            "AND XCZO.PRINT_DATE IS NOT NULL \n" +
            "AND XCZO.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}\n" +
            "AND XCZO.ORDER_SUP = xs.SUP_CODE\n" +
            "AND XCZO.ORDER_ID NOT IN (\n" +
            "\tSELECT A.ORDER_ID FROM XY_CLB_ZC_ORDER_FH A WHERE A.ORDER_ID IN (\n" +
            "\t\tSELECT B.ORDER_ID FROM XY_CLB_ZC_ORDER B WHERE B.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} AND B.PRINT_DATE IS NOT NULL \n" +
            "\t) AND A.KH_STATU = 1\n" +
            ")" +
            "</script>")
    public List<Map<String ,Object>> getZxySendZcYsOrder(String ctrCode) throws SQLException;

}
