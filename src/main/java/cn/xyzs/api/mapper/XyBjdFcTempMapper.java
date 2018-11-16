package cn.xyzs.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyBjdFcTempMapper {

    /**
     * 获取本材料大类所选择的品牌
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/15 16:27
     * @param:
     * @return:
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tDISTINCT xbft.S_NAME,xbft.S_VAL\n" +
            "FROM \n" +
            "\tXY_BJD_FC_TEMP xbft \n" +
            "WHERE\n" +
            "\txbft.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} \n" +
            "AND \n" +
            "\txbft.RG_STAGE = #{rgStage,jdbcType=VARCHAR}\t\n" +
            "AND \n" +
            "\txbft.S_NAME IS NOT NULL\n" +
            "AND\n" +
            "\txbft.S_VAL IS NOT NULL" +
            "</script>")
    public List<Map<String ,Object>> getNameAndVal(@Param("ctrCode") String ctrCode , @Param("rgStage") String rgStage) throws SQLException;
}
