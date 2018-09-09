package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcDb;
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
    @Select("<script>" +
            "SELECT s.*,u.USER_NAME,ZC_QTY*ZC_PRICE_OUT AS TOTAL,SUM(ZC_QTY*ZC_PRICE_OUT) OVER(ORDER BY s.ROW_ID) AS ZJ" +
            " FROM XY_CLB_ZC_SHOPPING s,XY_USER u WHERE CTR_CODE=#{ctrCode} AND s.OP_USERID=u.USER_ID" +
            "</script>")
    List<Map<String,Object>> showZcShopping(String ctrCode);

    @Select("<script>SELECT ZC_AREA FROM XY_CLB_ZC_DB WHERE ZC_TYPE=#{zcType} AND ZC_CODE=#{zcCode}</script>")
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
    void addShoppingCart(@Param("ctrCode") String ctrCode,@Param("opUserid") String opUserid,@Param("zcCode") String zcCode,@Param("zcName") String zcName,@Param("zcType") String zcType,@Param("zcQty") String zcQty,
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
    @Select("<script>SELECT * FROM XY_CLB_ZC_DB WHERE ZC_CODE=#{zcCode}</script>")
    List<Map<String,Object>> queryZcDb(@Param("zcCode") String zcCode);

    /***
     *
     * @Description: 根据zcBrand或zcVersion查询商品并分页
     * @author: GeWeiliang
     * @date: 2018\8\27 0027 11:32
     * @param: [zcBrand, zcVersion]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM(\n" +
            "\tSELECT A.*, ROWNUM RN FROM(\n" +
            "\t\tSELECT\n" +
            "\t\t\t* \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CLB_ZC_DB xczd \n" +
            "\t\tWHERE\n" +
            "\t\t\txczd.ZC_BRAND LIKE '%'||#{condition,}||'%' \n" +
            "\t\t\tOR xczd.ZC_VERSION LIKE '%'||#{condition}||'%'\n" +
            "\t) A\n" +
            ") WHERE RN BETWEEN #{startNum} AND #{endNum}\n" +
            "</script>")
    @Results(id="getGoodByZcType",value={
            @Result(column = "ZC_CODE", property = "zcCode", javaType = String.class),
            @Result(column = "ZC_NAME", property = "zcName", javaType = String.class),
            @Result(column = "ZC_TYPE", property = "zcType", javaType = String.class),
            @Result(column = "ZC_PRICE_IN", property = "zcPriceIn", javaType = String.class),
            @Result(column = "ZC_PRICE_LOOK", property = "zcPirceLook", javaType = String.class),
            @Result(column = "ZC_PRICE_OUT", property = "zcPriceOut", javaType = String.class),
            @Result(column = "ZC_PRICE_HD", property = "zcPriceHd", javaType = String.class),
            @Result(column = "ZC_BRAND", property = "zcBrand", javaType = String.class),
            @Result(column = "ZC_SUP", property = "zcSup", javaType = String.class),
            @Result(column = "ZC_SPEC", property = "zcSpec", javaType = String.class),
            @Result(column = "ZC_MATERIAL", property = "zcMaterial", javaType = String.class),
            @Result(column = "ZC_COLOR", property = "zcColor", javaType = String.class),
            @Result(column = "ZC_STYLE", property = "zcStyle", javaType = String.class),
            @Result(column = "ZC_IS_NEW", property = "zcIsNew", javaType = String.class),
            @Result(column = "ZC_IS_HOT", property = "zcIshot", javaType = String.class),
            @Result(column = "ZC_UNIT", property = "zcUnit", javaType = String.class),
            @Result(column = "ZC_DES", property = "zcDes", javaType = String.class),
            @Result(column = "ZC_CYC", property = "zcCyc", javaType = String.class),
            @Result(column = "ZC_IS_USED", property = "zcIsUsed", javaType = String.class),
            @Result(column = "ZC_PRO_AREA", property = "zcProArea", javaType = String.class),
            @Result(column = "ZC_VERSION", property = "zcVersion", javaType = String.class),
            @Result(column = "ZC_AREA",property = "zcArea", javaType = String.class),
    })
    List<XyClbZcDb> queryGoods(@Param("condition") String condition,
                               @Param("startNum") String startNum, @Param("endNum") String endNum)throws SQLException;

    /**
     *
     * @Description: 根据流水号将商品移出购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 9:23
     * @param: [rowId]
     * @return: int
     */
    @Delete("DELETE FROM XY_CLB_ZC_SHOPPING WHERE ROW_ID=#{rowId}")
    void removeGoods(@Param("rowId") String rowId) throws SQLException;

    /**
     *
     * @Description: 修改购物车
     * @author: GeWeiliang
     * @date: 2018\8\24 0024 10:55
     * @param: [rowId, zcQty, zcArea, zcMark]
     * @return: int
     */
    @UpdateProvider(type = updateGoods.class,method = "updateGoods")
    void updateGoods(@Param("rowId") String rowId,@Param("zcQty") String zcQty,
                    @Param("zcArea") String zcArea,@Param("zcMark") String zcMark) throws SQLException;
    class updateGoods{
        public String updateGoods(@Param("rowId") String rowId,@Param("zcQty")String zcQty,
                                  @Param("zcArea") String zcArea,@Param("zcMark") String zcMark){
            return new SQL(){{
                UPDATE("XY_CLB_ZC_SHOPPING");
                if (zcQty!=null && zcQty!=""){
                    SET("ZC_QTY = #{zcQty}");
                }
                if (zcArea!=null && zcArea!=""){
                    SET("ZC_AREA = #{zcArea}");
                }
                if (zcMark!=null){
                    SET("ZC_MARK = #{zcMark}");
                }
                WHERE("ROW_ID = #{rowId}");
            }}.toString();
        }
    }

    /**
     * 根据流水号查询购物车里的供应商
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/26 11:02
     * @param: [list]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
                "SELECT\n" +
                    "\tDISTINCT xczs.ZC_SUP\n" +
                "FROM\n" +
                    "\tXY_CLB_ZC_SHOPPING xczs \n" +
                "WHERE\n" +
                    "\txczs.ROW_ID IN " +
                        "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> "+
                            "#{item} "+
                        "</foreach>"+
            "</script>")
    public List<Map<String, Object>> getZcSupToShoppingCar(@Param("list") List<String> list) throws SQLException;

    /**
     * 查询单个供应商的商品总额
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/26 11:07
     * @param: [list, zcSup]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT SUM(C.ItemsTotal) SINGLEZCSUPTOTAL from(\n" +
            "\tSELECT \n" +
            "\t\tA.ZC_QTY*B.ZC_PRICE_OUT ItemsTotal\n" +
            "\tFROM (\n" +
            "\t\tSELECT\n" +
            "\t\t\txczs.ZC_QTY,xczs.ROW_ID\n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CLB_ZC_SHOPPING xczs \n" +
            "\t\tWHERE\n" +
            "\t\t\txczs.ROW_ID IN \n" +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> "+
                "#{item} "+
            "</foreach>"+
            "\t\t\tAND xczs.ZC_SUP = #{zcSup}\n" +
            "\t) A,\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\txczs.ZC_PRICE_OUT,xczs.ROW_ID\n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CLB_ZC_SHOPPING xczs \n" +
            "\t\tWHERE\n" +
            "\t\t\txczs.ROW_ID IN \n" +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> "+
                "#{item} "+
            "</foreach>"+
            "\t\t\tAND xczs.ZC_SUP = #{zcSup}\n" +
            "\t) B\n" +
            "\tWHERE A.ROW_ID = B.ROW_ID\n" +
            ") C" +
            "</script>")
    public List<Map<String, Object>> getSingleZCSUPTotal(@Param("list") List<String> list,@Param("zcSup") String zcSup) throws SQLException;

    /**
     * 根据流水号与供应商查询购物车内的商品
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/26 11:14
     * @param: [list, zcSup]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t\t*\n" +
            "FROM\n" +
            "\t\tXY_CLB_ZC_SHOPPING xczs \n" +
            "WHERE\n" +
            "\t\txczs.ROW_ID IN \n" +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> "+
                "#{item} "+
            "</foreach>"+
            "AND xczs.ZC_SUP = #{zcSup}" +
            "</script>")
    public List<Map<String, Object>> getGoodByRowIdAndZcSup(@Param("list") List<String> list,@Param("zcSup") String zcSup) throws SQLException;

    /**
     * 根据流水号批量清空购物车
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/26 14:34
     * @param: [list]
     * @return: void
     */
    @Delete("<script>" +
            "DELETE FROM \n" +
            "\tXY_CLB_ZC_SHOPPING xczs \n" +
            "WHERE \n" +
            "\txczs.ROW_ID IN \n" +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> "+
                "#{item} "+
            "</foreach>"+
            "</script>")
    public void deleteGood(@Param("list") List<String> list) throws SQLException;
}
