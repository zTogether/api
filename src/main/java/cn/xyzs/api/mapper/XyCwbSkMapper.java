package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyCwbSk;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyCwbSkMapper extends Mapper<XyCwbSk>{

    /**
     *
     * @Description: 收款情况
     * @author: GeWeiliang
     * @date: 2018\9\20 0020 10:56
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("SELECT SUM(CASE WHEN CWB_SK_IO='2' THEN -(CWB_SK_MONEY)\n" +
            "\t\tWHEN CWB_SK_IO='1' THEN CWB_SK_MONEY END) AS MONEY,\n" +
            "\t\tCWB_SK_CONTENT AS B\n" +
            "\t\tFROM XY_CWB_SK WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR} GROUP BY CWB_SK_CONTENT")
    public List<Map<String,Object>> skCondition(String ctrCode) throws SQLException;

    /**
     *
     * @Description: 收款明细
     * @author: GeWeiliang
     * @date: 2018\9\19 0019 9:19
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCTR_CODE, \n" +
            "\tCWB_SK_ROWID, \n" +
            "\tTO_CHAR(xcs.CWB_SK_DATE,'yyyy-MM-dd HH24:mi:ss') CWB_SK_DATE, \n" +
            "\tCWB_SK_TYPE, \n" +
            "\tCWB_SK_CONTENT, \n" +
            "\tCWB_SK_MONEY, \n" +
            "\tCWB_SK_CMONEY, \n" +
            "\tCWB_SK_IO, \n" +
            "\tCWB_SK_MARK, \n" +
            "\tCWB_SK_PRINT\n" +
            "FROM\n" +
            "\tXY_CWB_SK xcs\n" +
            "WHERE\n" +
            "\tCTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "\tORDER BY xcs.CWB_SK_DATE DESC" +
            "</script>")
    public List<Map<String,Object>> skList(String ctrCode) throws SQLException;
}
