package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyBjdFcList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyBjdFcListMapper extends Mapper<XyBjdFcList> {
    @Select("<script>" +
            "SELECT *  FROM ( SELECT A.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\tSELECT BJD_CODE,BJD_FC_NO,FC_NAME,NVL(FC_PRICE_CODE,'-') FC_PRICE_CODE,\n" +
            "\t\t\tNVL(FC_SPEC,'-') FC_SPEC,\n" +
            "\t\t\tNVL(BRAND_NAME, '-') BRAND_NAME,NVL(FC_UNIT,'-') FC_UNIT,\n" +
            "\t\t\tNVL(FC_QTY,0) FC_QTY,NVL(FC_PRICE,0) FC_PRICE,NVL(FC_XJ,0) FC_XJ,\n" +
            "\t\t\tNVL(FC_MARK,'-') FC_MARK,FC_YN\n" +
            "\tFROM XY_BJD_FC_LIST\n" +
            "\tWHERE BJD_CODE = #{bjdCode} AND BJD_FC_STAGE = #{fcStage}\n" +
            "\tORDER BY BJD_FC_NO\n" +
            "\t) A)\n" +
            "WHERE RN BETWEEN #{startNum} AND #{endNum}" +
            "</script>")
    public List<Map<String,Object>> bjdFcList(@Param("bjdCode") String bjdCode, @Param("fcStage") String fcStage,
                                              @Param("startNum") String startNum,@Param("endNum") String endNum) throws SQLException;

    @Select("<script>" +
            "SELECT\n" +
            "\txbm.BJD_FC_ZJ\n" +
            "FROM\n" +
            "\tXY_BJD_MAIN xbm\n" +
            "WHERE\n" +
            "\txbm.BJD_CODE = #{bjdCode}" +
            "</script>")
    public String getZj(@Param("bjdCode") String ctrCode) throws SQLException;
}
