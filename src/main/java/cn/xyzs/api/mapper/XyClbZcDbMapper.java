package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbZcDb;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcDbMapper{

    /**
     *
     * @Description:根据ZC_TYPE获取商品
     * @author: zheng shuai
     * @date: 2018/9/1 13:10
     * @param: [list, startNum, endNum, minimum, maximum]
     * @return: java.util.List<cn.xyzs.common.pojo.XyClbZcDb>
     */
    @Select("<script>" +
            "SELECT * FROM(\n" +
            "\tSELECT A.*, ROWNUM RN FROM(\n" +
            "\t\tSELECT \n" +
            "\t\t\t* \n" +
            "\t\tFROM \n" +
            "\t\t\tXY_CLB_ZC_DB xczd \n" +
            "\t\tWHERE \n" +
            "\t\t\txczd.ZC_TYPE IN " +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> "+
            "#{item,jdbcType=VARCHAR} "+
            "</foreach>"+
            "\t\tAND\n" +
            "\t\t\t<![CDATA[xczd.ZC_PRICE_OUT >= #{minimum,jdbcType=VARCHAR}]]>\n" +
            "\t\tAND\n" +
            "\t\t\t<![CDATA[xczd.ZC_PRICE_OUT <= #{maximum,jdbcType=VARCHAR}]]>\n" +
            "\t AND ZC_IS_USED = '0') A\n" +
            ") WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
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
    public List<XyClbZcDb> getGoodByZcType(@Param("list") List<String> list, @Param("startNum") String startNum, @Param("endNum") String endNum,
                                           @Param("minimum") String minimum, @Param("maximum") String maximum) throws SQLException;

    /**
     *
     * @Description:根据zcCode获取商品信息
     * @author: zheng shuai
     * @date: 2018/9/1 13:10
     * @param: [zcCode]
     * @return: java.util.List<cn.xyzs.common.pojo.XyClbZcDb>
     */
    @Select("<script>SELECT * FROM XY_CLB_ZC_DB WHERE ZC_CODE=#{zcCode,jdbcType=VARCHAR} AND ZC_IS_USED = '0'</script>")
    @Results(id="queryZcDb",value={
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
    public List<XyClbZcDb> queryZcDb(@Param("zcCode") String zcCode) throws SQLException;

    /**
     * 根据zcCode获取商品型号
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/1 13:11
     * @param: [zcCode]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txczd.ZC_VERSION \n" +
            "FROM\n" +
            "\tXY_CLB_ZC_DB xczd \n" +
            "WHERE\n" +
            "\txczd.ZC_CODE = #{zcCode,jdbcType=VARCHAR} AND ZC_IS_USED = '0'" +
            "</script>")
    public String getZcVersion(@Param("zcCode") String zcCode) throws SQLException;


    @Select("<script>" +
            "SELECT ZC_CODE,ZC_NAME,ZC_PRICE_OUT,ZC_BRAND FROM XY_CLB_ZC_DB SAMPLE(5)\n" +
            "WHERE ROWNUM &lt; 70 AND ZC_IS_USED = '0'" +
            "</script>")
    public List<Map<String,Object>> getRandGoods() throws SQLException;
}
