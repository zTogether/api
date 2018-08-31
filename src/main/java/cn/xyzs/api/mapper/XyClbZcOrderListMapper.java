package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcOrderList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcOrderListMapper extends Mapper<XyClbZcOrderList>{

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
            "\t#{orderId},\n" +
            "\tsys_guid(),\n" +
            "\t#{zcCode},\n" +
            "\t#{zcName},\n" +
            "\t#{zcType},\n" +
            "\tto_number(#{zcPriceIn}),\n" +
            "\tto_number(#{zcPriceOut}),\n" +
            "\tto_number(#{zcQty}),\n" +
            "\t#{zcBrand},\n" +
            "\t#{zcSup},\n" +
            "\t#{zcSpec},\n" +
            "\t#{zcMaterial},\n" +
            "\t#{zcColor},\n" +
            "\t#{zcUnit},\n" +
            "\t#{zcMark},\n" +
            "\tto_number(#{zcCyc}),\n" +
            "\t#{zcArea},\n" +
            "\t#{zcVersion},\n" +
            "\t#{zcShopStatus}\n" +
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
    @Select("SELECT zol.*,sup.SUP_NAME,zf.ZCFL_NAME \n" +
            "FROM XY_CLB_ZC_ORDER_LIST zol,XY_SUPPLIER sup,XY_CLB_ZC_FL zf \n" +
            "WHERE ORDER_ID=#{orderId} \n" +
            "AND zol.ZC_SUP=sup.SUP_CODE AND zf.ZCFL_CODE=zol.ZC_TYPE")
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
    public void updateOrderList(@Param("rowId") String rowId,@Param("zcQty") String zcQty,
                                @Param("zcArea") String zcArea,@Param("zcMark") String zcMark)throws SQLException;
    class updateOrderList{
        public String updateOrderList(@Param("rowId") String rowId, @Param("zcQty") String zcQty,
                                      @Param("zcArea") String zcArea,@Param("zcMark") String zcMark){
          return new SQL(){{
              UPDATE("XY_CLB_ZC_ORDER_LIST");
              if (zcQty!=null && zcQty!=""){
                  SET("ZC_QTY=#{zcQty,jdbcType=VARCHAR}");
              }
              if (zcArea!=null && zcArea!=""){
                  SET("ZC_AREA=#{zcArea,jdbcType=VARCHAR}");
              }
              if (zcMark!=null && zcMark!=""){
                  SET("ZC_MARK=#{zcMark,jdbcType=VARCHAR}");
              }
              WHERE("ROW_ID=#{rowId,jdbcType=VARCHAR}");
          }}.toString();
        }
    }
}
