package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcDb;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcDbMapper{

    @Select("<script>" +
            "SELECT * FROM(SELECT XY_CLB_ZC_DB.*,rownum rn FROM XY_CLB_ZC_DB WHERE ZC_TYPE IN " +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> "+
                "#{item} "+
            "</foreach> <![CDATA[AND ROWNUM <=#{endNum}) table_alias WHERE rn >= #{startNum}]]> " +
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
    public List<XyClbZcDb> getGoodByZcType(@Param("list") List<String> list,@Param("startNum")String startNum,@Param("endNum" )String endNum) throws SQLException;

    @Select("SELECT\n" +
            "\txczd.ZC_VERSION \n" +
            "FROM\n" +
            "\tXY_CLB_ZC_DB xczd \n" +
            "WHERE\n" +
            "\txczd.ZC_CODE = #{zcCode}")
    public String getZcVersion (@Param("zcCode") String zcCode) throws SQLException;
}
