package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyBjdFcList;
import org.apache.ibatis.annotations.Insert;
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
            "\tWHERE BJD_CODE = #{bjdCode,jdbcType=VARCHAR} AND BJD_FC_STAGE = #{fcStage,jdbcType=VARCHAR}\n" +
            "\tORDER BY BJD_FC_NO\n" +
            "\t) A)\n" +
            "WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> bjdFcList(@Param("bjdCode") String bjdCode, @Param("fcStage") String fcStage,
                                              @Param("startNum") String startNum, @Param("endNum") String endNum) throws SQLException;

    @Select("<script>" +
            "SELECT\n" +
            "\txbm.BJD_FC_ZJ\n" +
            "FROM\n" +
            "\tXY_BJD_MAIN xbm\n" +
            "WHERE\n" +
            "\txbm.BJD_CODE = #{bjdCode,jdbcType=VARCHAR}" +
            "</script>")
    public String getZj(@Param("bjdCode") String ctrCode) throws SQLException;

    /**
     * 获取辅材总计
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/23 15:12
     * @param: []
     * @return: java.lang.Double
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tSUM(FC_PRICE * FC_QTY)\n" +
            "FROM \n" +
            "\tXY_BJD_FC_LIST \n" +
            "WHERE BJD_CODE = '201800053201'" +
            "</script>")
    public Double getFcZj() throws SQLException;

    /**
     * 一键报价添加辅材（测试版）
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/24 13:47
     * @param: [bjd_code]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_BJD_FC_LIST (\n" +
            "\tSELECT \n" +
            "\t\t#{bjdCode,jdbcType=VARCHAR}, \n" +
            "\t\tsys_guid(), \n" +
            "\t\tBJD_FC_STAGE, \n" +
            "\t\tBJD_FC_NO, \n" +
            "\t\tFC_PRICE_CODE, \n" +
            "\t\tFC_NAME, \n" +
            "\t\tBRAND_NAME, \n" +
            "\t\tFC_SPEC, \n" +
            "\t\tFC_UNIT, \n" +
            "\t\tFC_QTY, \n" +
            "\t\tFC_PRICE, \n" +
            "\t\tFC_XJ, \n" +
            "\t\tFC_MARK, \n" +
            "\t\tFC_YN\n" +
            "\tFROM \n" +
            "\t\tXY_BJD_FC_LIST \n" +
            "\tWHERE \n" +
            "\t\tBJD_CODE = '201800053201'\n" +
            ")" +
            "</script>")
    public void addAutoBjFc(String bjdCode) throws SQLException;
}
