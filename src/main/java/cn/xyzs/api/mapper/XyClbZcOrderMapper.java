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
            "\t#{ctrCode},\n" +
            "\t#{opUserid},\n" +
            "\t#{orderJe},\n" +
            "\t#{orderMark},\n" +
            "\t#{orderStatus},\n" +
            "\t#{orderType},\n" +
            "\t#{orderSup},\n" +
            "\t#{editType},\n" +
            "\t#{orderDis},\n" +
            "\t#{orderDisMark},\n" +
            "\t#{orderIsreturn}\n" +
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
            "\tB.*,TO_CHAR(B.ORDER_DATE,'yyyy-MM-dd HH24:mm:ss') ORDERDATE\n" +
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
     * @Description: 删除订单
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 9:48
     * @param: [orderId]
     * @return: void
     */
    @Delete("DELETE FROM XY_CLB_ZC_ORDER WHERE ORDER_ID=#{orderId}")
    public void deleteFromOrder(@Param("orderId") String orderId) throws SQLException;
    @Delete("DELETE FROM XY_CLB_ZC_ORDER_LIST WHERE ORDER_ID=#{orderId}")
    public void deleteFromOrderList(@Param("orderId") String orderId) throws SQLException;

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
                if (orderJe!=null && orderJe!=""){
                    SET("ORDER_JE=#{orderJe,jdbcType=VARCHAR}");
                }
                if (orderMark!=null && orderMark!=""){
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
                if (orderDisMark!=null && orderDisMark!=""){
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
    @Select("<script>SELECT * FROM XY_CLB_ZC_ORDER WHERE ORDER_ID=#{orderId}</script>")
    public Map<String,Object> getOrderInfo(@Param("orderId") String orderId)throws SQLException;

}
