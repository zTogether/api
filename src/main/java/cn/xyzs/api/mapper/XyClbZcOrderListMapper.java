package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbZcOrderList;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcOrderListMapper extends Mapper<XyClbZcOrderList>{

    /**
     * 添加订单详情
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/1 13:12
     * @param: [xyClbZcOrderList]
     * @return: void
     */
    @Insert("INSERT INTO \n" +
            "\tXY_CLB_ZC_ORDER_LIST ( \n" +
            "\t\tORDER_ID,\n" +
            "\t\tROW_ID,\n" +
            "\t\tZC_CODE,\n" +
            "\t\tZC_NAME,\n" +
            "\t\tZC_TYPE,\n" +
            "\t\tZC_PRICE_IN,\n" +
            "\t\tZC_PRICE_OUT,\n" +
            "\t\tZC_QTY,\n" +
            "\t\tZC_BRAND,\n" +
            "\t\tZC_SUP,\n" +
            "\t\tZC_SPEC,\n" +
            "\t\tZC_MATERIAL,\n" +
            "\t\tZC_COLOR,\n" +
            "\t\tZC_UNIT,\n" +
            "\t\tZC_MARK,\n" +
            "\t\tZC_CYC,\n" +
            "\t\tZC_AREA,\n" +
            "\t\tZC_VERSION,\n" +
            "\t\tZC_SHOP_STATUS )\n" +
            "VALUES(\n" +
            "\t#{orderId,jdbcType=VARCHAR},\n" +
            "\tsys_guid(),\n" +
            "\t#{zcCode,jdbcType=VARCHAR},\n" +
            "\t#{zcName,jdbcType=VARCHAR},\n" +
            "\t#{zcType,jdbcType=VARCHAR},\n" +
            "\tto_number(#{zcPriceIn,jdbcType=VARCHAR}),\n" +
            "\tto_number(#{zcPriceOut,jdbcType=VARCHAR}),\n" +
            "\tto_number(#{zcQty,jdbcType=VARCHAR}),\n" +
            "\t#{zcBrand,jdbcType=VARCHAR},\n" +
            "\t#{zcSup,jdbcType=VARCHAR},\n" +
            "\t#{zcSpec,jdbcType=VARCHAR},\n" +
            "\t#{zcMaterial,jdbcType=VARCHAR},\n" +
            "\t#{zcColor,jdbcType=VARCHAR},\n" +
            "\t#{zcUnit,jdbcType=VARCHAR},\n" +
            "\t#{zcMark,jdbcType=VARCHAR},\n" +
            "\tto_number(#{zcCyc,jdbcType=VARCHAR}),\n" +
            "\t#{zcArea,jdbcType=VARCHAR},\n" +
            "\t#{zcVersion,jdbcType=VARCHAR},\n" +
            "\t#{zcShopStatus,jdbcType=VARCHAR}\n" +
            ")")
    public void addOrderList(XyClbZcOrderList xyClbZcOrderList) throws SQLException;

    /***
     *
     * @Description: 查询订单明细
     * @author: GeWeiliang
     * @date: 2018\8\29 0029 15:21
     * @param: [orderId]
     * @return: void
     */
    @Select("<script>" +
            "SELECT zol.*,sup.SUP_NAME,zf.ZCFL_NAME \n" +
            "FROM XY_CLB_ZC_ORDER_LIST zol,XY_SUPPLIER sup,XY_CLB_ZC_FL zf \n" +
            "WHERE ORDER_ID=#{orderId,jdbcType=VARCHAR} \n" +
            "AND zol.ZC_SUP=sup.SUP_CODE AND zf.ZCFL_CODE=zol.ZC_TYPE" +
            "</script>")
    public List<Map<String,Object>> showOrderList(String orderId)throws SQLException;

    /***
     *
     * @Description: 根据rowId修改orderList
     * @author: GeWeiliang
     * @date: 2018\8\30 0030 15:08
     * @param: [rowId, zcQty, zcArea, zcMark]
     * @return: void
     */
    @UpdateProvider(type = updateOrderList.class,method = "updateOrderList")
    public void updateOrderList(@Param("rowId") String rowId, @Param("zcQty") String zcQty,
                                @Param("zcArea") String zcArea, @Param("zcMark") String zcMark)throws SQLException;
    class updateOrderList{
        public String updateOrderList(@Param("rowId") String rowId, @Param("zcQty") String zcQty,
                                      @Param("zcArea") String zcArea,@Param("zcMark") String zcMark){
          return new SQL(){{
              UPDATE("XY_CLB_ZC_ORDER_LIST");
              SET("ROW_ID=#{rowId,jdbcType=VARCHAR}");
              if (zcQty!=null && zcQty!=""){
                  SET("ZC_QTY=#{zcQty,jdbcType=VARCHAR}");
              }
              if (zcArea!=null && zcArea!=""){
                  SET("ZC_AREA=#{zcArea,jdbcType=VARCHAR}");
              }
              if (zcMark!=null){
                  SET("ZC_MARK=#{zcMark,jdbcType=VARCHAR}");
              }
              WHERE("ROW_ID=#{rowId,jdbcType=VARCHAR}");
          }}.toString();
        }
    }

    /***
     *
     * @Description: 删除标准化商品
     * @author: GeWeiliang
     * @date: 2018\9\13 0013 14:36
     * @param: [orderId, rowId]
     * @return: void
     */
    @DeleteProvider(type = deleteFromOrderList.class,method = "deleteFromOrderGoods")
    public void deleteFromOrderList(@Param("orderId") String orderId, @Param("rowId") String rowId) throws SQLException;
    class deleteFromOrderList{
        public String deleteFromOrderGoods(@Param("orderId") String orderId,@Param("rowId") String rowId){
            return new SQL(){{
                DELETE_FROM("XY_CLB_ZC_ORDER_LIST");
                if (orderId!=null && orderId!=""){
                    WHERE("ORDER_ID=#{orderId,jdbcType=VARCHAR}");
                }
                if (rowId!=null && rowId!=""){
                    WHERE("ROW_ID=#{rowId,jdbcType=VARCHAR}");
                }
            }}.toString();
        }
    }

    /**
     * 根据供应商和档案号获取可退货的的商品
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/22 14:13
     * @param: [ctrCode, orderSup]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT DISTINCT \n" +
            "\tB.ORDER_ID, \n" +
            "\tB.ZC_CODE, \n" +
            "\tB.ZC_NAME, \n" +
            "\tB.ZC_TYPE, \n" +
            "\tB.ZC_PRICE_IN, \n" +
            "\tB.ZC_PRICE_OUT, \n" +
            "\tB.ZC_BRAND, \n" +
            "\tB.ZC_SUP, \n" +
            "\tB.ZC_SPEC, \n" +
            "\tB.ZC_MATERIAL, \n" +
            "\tB.ZC_COLOR, \n" +
            "\tB.ZC_UNIT, \n" +
            "\tB.ZC_MARK, \n" +
            "\tB.ZC_CYC, \n" +
            "\tB.ZC_AREA, \n" +
            "\tB.ZC_VERSION, \n" +
            "\tB.ZC_SHOP_STATUS\n" +
            "FROM \n" +
            "\tXY_CLB_ZC_ORDER_LIST B,\n" +
            "\t(\n" +
            "\t\tSELECT \n" +
            "\t\t\tA.ORDER_ID\n" +
            "\t\tFROM \n" +
            "\t\t\tXY_CLB_ZC_ORDER A\n" +
            "\t\tWHERE\n" +
            "\t\t\tA.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}\n" +
            "\t\tAND\n" +
            "\t\t\tA.ORDER_SUP = #{orderSup,jdbcType=VARCHAR}\n" +
            "\t) C\n" +
            "WHERE\n" +
            "\tB.ORDER_ID = C.ORDER_ID" +
            "</script>")
    public List<Map<String ,Object>> getZcByOrderSupAndCtrCode(@Param("ctrCode") String ctrCode, @Param("orderSup") String orderSup) throws SQLException;

    /**
     * 添加主材退库明细表
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/22 15:32
     * @param: [xyClbZcOrderList]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Insert("<script>" +
            "INSERT INTO XY_CLB_ZC_ORDER(\n" +
            "\tORDER_ID, \n" +
            "\tROW_ID, \n" +
            "\tZC_CODE, \n" +
            "\tZC_NAME, \n" +
            "\tZC_TYPE, \n" +
            "\tZC_PRICE_IN, \n" +
            "\tZC_PRICE_OUT, \n" +
            "\tZC_QTY, \n" +
            "\tZC_BRAND, \n" +
            "\tZC_SUP, \n" +
            "\tZC_SPEC, \n" +
            "\tZC_MATERIAL, \n" +
            "\tZC_COLOR, \n" +
            "\tZC_UNIT,\n" +
            "\tZC_CYC, \n" +
            "\tZC_AREA, \n" +
            "\tZC_VERSION, \n" +
            "\tZC_SHOP_STATUS\n" +
            ")\n" +
            "VALUES(\n" +
            "\t#{orderId,jdbcType=VARCHAR}, \n" +
            "\tsys_guid(), \n" +
            "\t#{zcCode,jdbcType=VARCHAR}, \n" +
            "\t#{zcName,jdbcType=VARCHAR}, \n" +
            "\t#{zcType,jdbcType=VARCHAR}, \n" +
            "\t#{zcPriceIn,jdbcType=VARCHAR}, \n" +
            "\t#{zcPriceOut,jdbcType=VARCHAR}, \n" +
            "\t#{zcQty,jdbcType=VARCHAR}, \n" +
            "\t#{zcBrand,jdbcType=VARCHAR}, \n" +
            "\t#{zcSup,jdbcType=VARCHAR}, \n" +
            "\t#{zcSpec,jdbcType=VARCHAR}, \n" +
            "\t#{zcMaterial,jdbcType=VARCHAR}, \n" +
            "\t#{zcColor,jdbcType=VARCHAR}, \n" +
            "\t#{zcUnit,jdbcType=VARCHAR}, \n" +
            "\t#{zcCyc,jdbcType=VARCHAR}, \n" +
            "\t#{zcArea,jdbcType=VARCHAR}, \n" +
            "\t#{zcVersion,jdbcType=VARCHAR}, \n" +
            "\t#{zcShopStatus,jdbcType=VARCHAR} \n" +
            ")" +
            "</script>")
    public List<Map<String ,Object>> addTHDLsit(XyClbZcOrderList xyClbZcOrderList) throws SQLException;

}
