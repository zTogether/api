package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyBjdFcList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
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
     * 一键报价添加辅材list
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/26 15:03
     * @param: [houseId, rgArray]
     * @return: void
     */
    @InsertProvider(type = addAutoBjFc.class,method = "addAutoBjFc")
    public void addAutoBjFc(String bjdCode ,String houseId ,String []rgArray) throws SQLException;
    public class addAutoBjFc{
        public String addAutoBjFc(String bjdCode ,String houseId ,String []rgArray){
            String tempSql = "";
            tempSql += "INSERT INTO XY_BJD_FC_LIST(\n" +
                    "\tBJD_CODE,\n" +
                    "\tBJD_FC_ROWID,\n" +
                    "\tBJD_FC_STAGE,\n" +
                    "\tBJD_FC_NO,\n" +
                    "\tFC_PRICE_CODE,\n" +
                    "\tFC_NAME,\n" +
                    "\tBRAND_NAME,\n" +
                    "\tFC_UNIT,\n" +
                    "\tFC_QTY,\n" +
                    "\tFC_PRICE,\n" +
                    "\tFC_XJ,\n" +
                    "\tFC_YN \n" +
                    ") (\n" +
                    "\tSELECT\n" +
                    "\t\t'"+bjdCode+"',\n" +
                    "\t\tsys_guid(),\n" +
                    "\t\tJ.RG_STAGE,\n" +
                    "\t\tROWNUM rn,\n" +
                    "\t\tJ.FC_PRICE_CODE,\n" +
                    "\t\tJ.FC_NAME,\n" +
                    "\t\tJ.BRAND_NAME,\n" +
                    "\t\tJ.FC_UNIT,\n" +
                    "\t\tJ.SL,\n" +
                    "\t\tJ.FC_PRICE_OUT,\n" +
                    "\t\t(J.SL*J.FC_PRICE_OUT) FC_XJ,\n" +
                    "\t\t'1'\n" +
                    "\tFROM (\n" +
                    "\t\tSELECT\n" +
                    "\t\t\tV.RG_STAGE,\n" +
                    "\t\t\tV.FC_CODE,\n" +
                    "\t\t\tV.FC_PRICE_CODE,\n" +
                    "\t\t\tV.FC_NAME,\n" +
                    "\t\t\tV.FC_UNIT,\n" +
                    "\t\t\tV.BRAND_NAME,\n" +
                    "\t\t\tV.S_NAME,\n" +
                    "\t\t\tV.S_VAL,\n" +
                    "\t\t\tV.FC_PRICE_OUT,\n" +
                    "\t\t\tSUM( V.SL ) SL\n" +
                    "\t\tFROM (\n" +
                    "\t\t\tSELECT\n" +
                    "\t\t\t\tG.RG_STAGE,\n" +
                    "\t\t\t\tC.FC_CODE,\n" +
                    "\t\t\t\tE.FC_PRICE_CODE,\n" +
                    "\t\t\t\tC.FC_NAME,\n" +
                    "\t\t\t\tC.FC_UNIT,\n" +
                    "\t\t\t\tD.BRAND_NAME,\n" +
                    "\t\t\t\tD.S_NAME,\n" +
                    "\t\t\t\tD.S_VAL,\n" +
                    "\t\t\t\tE.FC_PRICE_OUT,\n" +
                    "\t\t\t\tCEIL(( CASE B.CLC_TYPE WHEN '0' THEN B.SL ELSE B.SL * A.RG_QTY END ) / C.FC_N4 ) * C.FC_N4 SL\n" +
                    "\t\t\tFROM\n" +
                    "\t\t\t\tXYZS_PLAT2.XY_BJD_TEMPLATE_LIST A,\n" +
                    "\t\t\t\tXYZS_PLAT2.XY_BJD_FC_MB B,\n" +
                    "\t\t\t\tXYZS_PLAT2.XY_CLB_FC_DB C,\n" +
                    "\t\t\t\tXYZS_PLAT2.XY_CLB_FC_BRAND D,\n" +
                    "\t\t\t\tXYZS_PLAT2.XY_CLB_FC_DB_PRICE E,\n" +
                    "\t\t\t\tXYZS_TEST.XY_MAIN_HOUSER F,\n" +
                    "\t\t\t\tXYZS_PLAT2.XY_GCB_RG_VER_LIST G\n" +
                    "\t\t\tWHERE SUBSTR( A.RG_ID, 1, 10 ) = B.RG_CODE\n";
            if (rgArray != null && rgArray.length > 0){
                String tempVariable = "";
                for (int i = 0; i < rgArray.length ; i++) {
                    if (i == 0){
                        tempVariable += "'"+rgArray[i]+"'";
                    } else {
                        tempVariable += ",'"+rgArray[i]+"'";
                    }
                }
                tempSql += "\t\t\tAND A.TEMPLATE_ROWID NOT IN("+tempVariable+")\n";
            }

            tempSql += "\t\t\tAND C.FC_CODE = D.FC_CODE\n" +
                    "\t\t\tAND D.BRAND_ID = E.BRAND_ID\n" +
                    "\t\t\tAND F.HOUSE_ID = A.TEMPLATE_ID\n" +
                    "\t\t\tAND F.HOUSE_ID = '"+houseId+"'\n" +
                    "\t\t\tAND C.FC_ISUSED = 1\n" +
                    "\t\t\tAND D.BRAND_ISUSED = 1\n" +
                    "\t\t\tAND E.FC_PRICE_ISUSED = 1 \n" +
                    "\t\t\tAND B.FC_CODE LIKE '%' || C.FC_CODE || '%'\n" +
                    "\t\t\tAND G.RG_VER_CODE = F.RG_VER_CODE \n" +
                    "\t\t\tAND G.RG_ID = A.RG_ID\n" +
                    "\t\t\tAND (\n" +
                    "\t\t\t\tD.S_NAME IS NULL\n" +
                    "\t\t\t\tOR (\n" +
                    "\t\t\t\t\tD.S_NAME IS NOT NULL\n" +
                    "\t\t\t\t\tAND EXISTS (\n" +
                    "\t\t\t\t\t\tSELECT\n" +
                    "\t\t\t\t\t\t\t1\n" +
                    "\t\t\t\t\t\tFROM\n" +
                    "\t\t\t\t\t\t\tXYZS_TEST.XY_HOUSE_FC_BRAND H\n" +
                    "\t\t\t\t\t\tWHERE\n" +
                    "\t\t\t\t\t\t\th.house_id = a.template_id\n" +
                    "\t\t\t\t\t\tAND H.S_NAME = D.S_NAME\n" +
                    "\t\t\t\t\t\tAND H.S_VAL = D.S_VAL\n" +
                    "\t\t\t\t\t\t)))) V\n" +
                    "\t\t\tGROUP BY\n" +
                    "\t\t\t\tV.RG_STAGE,\n" +
                    "\t\t\t\tV.FC_CODE,\n" +
                    "\t\t\t\tV.FC_NAME,\n" +
                    "\t\t\t\tV.FC_UNIT,\n" +
                    "\t\t\t\tV.BRAND_NAME,\n" +
                    "\t\t\t\tV.S_NAME,\n" +
                    "\t\t\t\tV.S_VAL,\n" +
                    "\t\t\t\tV.FC_PRICE_OUT,\n" +
                    "\t\t\t\tV.FC_PRICE_CODE\n" +
                    "\t\t\tHAVING SUM( V.SL ) <> 0   \n" +
                    "\t\t\tORDER BY V.RG_STAGE,V.BRAND_NAME,V.FC_NAME  \n" +
                    "\t\t) J \n" +
                    "\t)";
            return tempSql;
        }
    }
}
