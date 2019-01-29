package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyBjdFcTemp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyBjdFcTempMapper extends Mapper<XyBjdFcTemp>{

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
    public List<Map<String ,Object>> getNameAndVal(@Param("ctrCode") String ctrCode, @Param("rgStage") String rgStage) throws SQLException;

    /**
     * 一键报价添加操作
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/26 15:28
     * @param: [houseId, ctrCode]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_BJD_FC_TEMP(\n" +
            "\tCTR_CODE, \n" +
            "\tRG_VER_CODE, \n" +
            "\tRG_STAGE, \n" +
            "\tFC_TYPE, \n" +
            "\tS_NAME, \n" +
            "\tS_VAL, \n" +
            "\tS_PR, \n" +
            "\tIS_VISIBAL\n" +
            ") (\n" +
            "\tSELECT \n" +
            "\t\t#{ctrCode,jdbcType=VARCHAR},\n" +
            "\t\tB.RG_VER_CODE,\n" +
            "\t\t'10',\n" +
            "\t\t'10',\n" +
            "\t\tA.S_NAME,\n" +
            "\t\tA.S_VAL,\n" +
            "\t\t'0',\n" +
            "\t\t'Y'\n" +
            "\tFROM \n" +
            "\t\tXY_HOUSE_FC_BRAND A\n" +
            "\tLEFT JOIN XY_MAIN_HOUSER B\n" +
            "\tON A.HOUSE_ID = B.HOUSE_ID\n" +
            "\tWHERE\n" +
            "\t\tA.HOUSE_ID = #{houseId,jdbcType=VARCHAR}\n" +
            ")" +
            "</script>")
    public void addAutoBjBjdFcTemp(@Param("houseId") String houseId ,@Param("ctrCode") String ctrCode) throws SQLException;
}
