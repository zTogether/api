package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcShopping;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface XyClbZcShoppingMapper extends Mapper<XyClbZcShopping> {
    /**
     *
     * @Description: 根据客户号查询购物车
     * @author: GeWeiliang
     * @date: 2018\8\23 0023 15:04
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("SELECT s.*,u.USER_NAME,ZC_QTY*ZC_PRICE_OUT AS TOTAL,SUM(ZC_QTY*ZC_PRICE_OUT) OVER(ORDER BY s.ROW_ID) AS ZJ" +
            " FROM XY_CLB_ZC_SHOPPING s,XY_USER u WHERE CTR_CODE=#{ctrCode} AND s.OP_USERID=u.USER_ID")
    List<Map<String,Object>> showZcShopping(String ctrCode) throws SQLException;

    @Select("SELECT ZC_AREA FROM XY_CLB_ZC_DB WHERE ZC_TYPE=#{zcType} AND ZC_CODE=#{zcCode}")
    String getArea(@Param("zcType") String zcType, @Param("zcCode") String zcCode)throws SQLException;


    /**
     *
     * @Description: 加入购物车
     * @author: GeWeiliang
     * @date: 2018\8\23 0023 17:52
     * @param: [ctrCode, opUserid, zcCode, zcName, zcType, zcQty, zcPriceIn, zcPriceOut, zcBrand, zcSup, zcSpec, zcMaterial, zcColor, zcUnit, zcMark, zcCyc, zcArea]
     * @return: int
     */
    @Insert("INSERT INTO \n" +
            "XY_CLB_ZC_SHOPPING(ROW_ID,CTR_CODE,OP_USERID,ZC_CODE,ZC_NAME,ZC_TYPE,ZC_QTY,ZC_PRICE_IN,ZC_PRICE_OUT,ZC_BRAND," +
            "ZC_SUP,ZC_SPEC,ZC_MATERIAL,ZC_COLOR,ZC_UNIT,ZC_MARK,ZC_CYC,ZC_AREA)\n" +
            "VALUES(sys_guid(),#{ctrCode,jdbcType=VARCHAR},#{opUserid,jdbcType=VARCHAR},#{zcCode,jdbcType=VARCHAR},#{zcName,jdbcType=VARCHAR}," +
            "#{zcType,jdbcType=VARCHAR},#{zcQty,jdbcType=VARCHAR},#{zcPriceIn,jdbcType=VARCHAR},#{zcPriceOut,jdbcType=VARCHAR},#{zcBrand,jdbcType=VARCHAR},#{zcSup,jdbcType=VARCHAR},#{zcSpec,jdbcType=VARCHAR}," +
            "#{zcMaterial,jdbcType=VARCHAR},#{zcColor,jdbcType=VARCHAR},#{zcUnit,jdbcType=VARCHAR},#{zcMark,jdbcType=VARCHAR},#{zcCyc,jdbcType=VARCHAR},#{zcArea,jdbcType=VARCHAR})")
    int addShoppingCart(@Param("ctrCode") String ctrCode,@Param("opUserid") String opUserid,@Param("zcCode") String zcCode,@Param("zcName") String zcName,@Param("zcType") String zcType,@Param("zcQty") String zcQty,
                        @Param("zcPriceIn") String zcPriceIn,@Param("zcPriceOut") String zcPriceOut,@Param("zcBrand") String zcBrand, @Param("zcSup")String zcSup,@Param("zcSpec") String zcSpec,@Param("zcMaterial") String zcMaterial,
                        @Param("zcColor") String zcColor,@Param("zcUnit") String zcUnit,@Param("zcMark")String zcMark,@Param("zcCyc") String zcCyc,@Param("zcArea") String zcArea)throws SQLException;

    /**
     *
     * @Description: 根据zcCode查询商品信息
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 9:17
     * @param: [zcCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("SELECT * FROM XY_CLB_ZC_DB WHERE ZC_CODE=#{zcCode}")
    List<Map<String,Object>> queryZcDb(@Param("zcCode") String zcCode);

    /**
     *
     * @Description: 根据流水号将商品移出购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 9:23
     * @param: [rowId]
     * @return: int
     */
    @Delete("DELETE FROM XY_CLB_ZC_SHOPPING WHERE ROW_ID=#{rowId}")
    int removeGoods(@Param("rowId") String rowId) throws SQLException;

    /**
     *
     * @Description: 修改购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 10:55
     * @param: [rowId, zcQty, zcArea, zcMark]
     * @return: int
     */
    @UpdateProvider(type = updateGoods.class,method = "updateGoods")
    int updateGoods(@Param("rowId") String rowId,@Param("zcQty") String zcQty,
                    @Param("zcArea") String zcArea,@Param("zcMark") String zcMark) throws SQLException;
    class updateGoods{
        public String updateGoods(@Param("rowId") String rowId,@Param("zcQty")String zcQty,
                                  @Param("zcArea") String zcArea,@Param("zcMark") String zcMark){
            return new SQL(){{
                UPDATE("XY_CLB_ZC_SHOPPING");
                if (zcQty!=null && zcQty!=""){
                    SET("ZC_QTY = #{zcQty}");
                }
                if (zcArea!=null && zcQty!=""){
                    SET("ZC_AREA = #{zcArea}");
                }
                if (zcMark!=null && zcQty!=""){
                    SET("ZC_MARK = #{zcMark}");
                }
                WHERE("ROW_ID = #{rowId}");
            }}.toString();
        }
    }
}
