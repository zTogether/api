package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcOrderListFree;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;

public interface XyClbZcOrderListFreeMapper extends Mapper<XyClbZcOrderListFree>{

    /**
     * 根据orderId查询非标商品
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/31 10:51
     * @param: [orderId]
     * @return: java.util.List<cn.xyzs.api.pojo.XyClbZcOrderListFree>
     */
    @Select("<script>SELECT * FROM XY_CLB_ZC_ORDER_LIST_FREE xczolf WHERE xczolf.ORDER_ID = #{orderId}</script>")
    @Results(id="getNonStandard",value={
            @Result(column = "ORDER_ID", property = "orderId", javaType = String.class),
            @Result(column = "ROW_ID", property = "rowId", javaType = String.class),
            @Result(column = "ZC_CODE", property = "zcCode", javaType = String.class),
            @Result(column = "ZC_NAME", property = "zcName", javaType = String.class),
            @Result(column = "ZC_TYPE", property = "zcType", javaType = String.class),
            @Result(column = "ZC_PRICE_IN", property = "zcPriceIn", javaType = String.class),
            @Result(column = "ZC_PRICE_OUT", property = "zcPriceOut", javaType = String.class),
            @Result(column = "ZC_QTY", property = "zcQty", javaType = String.class),
            @Result(column = "ZC_BRAND", property = "zcBrand", javaType = String.class),
            @Result(column = "ZC_SUP", property = "zcSup", javaType = String.class),
            @Result(column = "ZC_SPEC", property = "zcSpec", javaType = String.class),
            @Result(column = "ZC_MATERIAL", property = "zcMaterial", javaType = String.class),
            @Result(column = "ZC_COLOR", property = "zcColor", javaType = String.class),
            @Result(column = "ZC_UNIT", property = "zcUnit", javaType = String.class),
            @Result(column = "ZC_MARK", property = "zcMark", javaType = String.class),
            @Result(column = "ZC_CYC", property = "zcCyc", javaType = String.class),
            @Result(column = "ZC_AREA",property = "zcArea", javaType = String.class),
            @Result(column = "ZC_VERSION", property = "zcVersion", javaType = String.class),
            @Result(column = "ZC_SHOP_STATUS", property = "zcShopStatus", javaType = String.class),
            @Result(column="ZC_AREA",property="xyVal",one=@One(select="cn.xyzs.api.mapper.XyValMapper.getZcArea",fetchType= FetchType.EAGER))
    })
    public List<XyClbZcOrderListFree> getNonStandard(@Param("orderId") String orderId) throws SQLException;

    /***
     *
     * @Description: 根据rowId修改orderListFree
     * @author: GeWeiliang
     * @date: 2018\8\31 0031 18:23
     * @param: [orderId, zcQty, zcMark, zcArea]
     * @return: void
     */
    @UpdateProvider(type = updateOrderListFree.class,method = "updateOrderListFree")
    public void updateOrderListFree(@Param("rowId") String rowId,@Param("zcQty") String zcQty,
                                    @Param("zcMark") String zcMark,@Param("zcArea") String zcArea)throws SQLException;
    class updateOrderListFree{
        public String updateOrderListFree(@Param("rowId") String rowId,@Param("zcQty") String zcQty,
                                          @Param("zcMark") String zcMark,@Param("zcArea") String zcArea){
            return new SQL(){{
                UPDATE("XY_CLB_ZC_ORDER_LIST_FREE");
                SET("ROW_ID=#{rowId}");
                if (zcQty!=null && zcQty!=""){
                    SET("ZC_QTY=#{zcQty}");
                }
                if (zcMark!=null){
                    SET("ZC_MARK=#{zcMark}");
                }
                if (zcArea!=null && zcArea!=""){
                    SET("ZC_AREA=#{zcArea}");
                }
                WHERE("ROW_ID=#{rowId}");
            }}.toString();
        }
    }

    /***
     *
     * @Description: 删除非标化商品
     * @author: GeWeiliang
     * @date: 2018\9\1 0001 10:11
     * @param: [orderId, rowId]
     * @return: void
     */
    @DeleteProvider(type = deleteOrderListFree.class,method = "deleteOrderListFree")
    public void deleteOrderListFree(@Param("orderId") String orderId,@Param("rowId") String rowId)throws SQLException;
    class deleteOrderListFree{
        public String deleteOrderListFree(@Param("orderId") String orderId,@Param("rowId") String rowId){
            return new SQL(){{
                DELETE_FROM("XY_CLB_ZC_ORDER_LIST_FREE");
                if (orderId!=null && orderId!=""){
                    WHERE("ORDER_ID=#{orderId}");
                }
                if(rowId!=null && rowId!=""){
                    WHERE("ROW_ID=#{rowId}");
                }
            }}.toString();
        }
    }
}
