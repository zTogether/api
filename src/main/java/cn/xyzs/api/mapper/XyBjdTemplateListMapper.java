package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyBjdTemplateList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyBjdTemplateListMapper extends Mapper<XyBjdTemplateList>{

    /**
     * 获取人工费项目
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 17:12
     * @param: [templateId, startNum, endNum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "\tSELECT \n" +
            "\t\tTEMPLATE_ID,\n" +
            "\t\tTEMPLATE_ROWID,\n" +
            "\t\tRG_STAGE,\n" +
            "\t\tTEMPLATE_NO,\n" +
            "\t\tRG_ID,\n" +
            "\t\tRG_NAME,\n" +
            "\t\tRG_UNIT,\n" +
            "\t\tRG_PRICE,\n" +
            "\t\tRG_DES,\n" +
            "\t\tRG_QTY,\n" +
            "\t\tcast(RG_QTY*RG_PRICE as   decimal(10, 2)) RG_XJ" +
            "\tFROM \n" +
            "\t\tXY_BJD_TEMPLATE_LIST\n" +
            "\tWHERE\n" +
            "\t\tTEMPLATE_ID = (" +
            "SELECT HOUSE_TEMPLATE_RG_ID FROM XY_MAIN_HOUSER WHERE HOUSE_ID = #{houseId,jdbcType=VARCHAR}" +
            ")\n" +
            "ORDER BY RG_STAGE" +
            "</script>")
    public List<Map<String ,Object>> getMbRgList(String houseId ) throws SQLException;

    /**
     * 获取人工费项目总记录数
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/23 9:46
     * @param: [houseId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tCOUNT(1)\n" +
            "FROM \n" +
            "\tXY_BJD_TEMPLATE_LIST\n" +
            "WHERE\n" +
            "\tTEMPLATE_ID = (" +
            "SELECT HOUSE_TEMPLATE_RG_ID FROM XY_MAIN_HOUSER WHERE HOUSE_ID = #{houseId,jdbcType=VARCHAR}" +
            ")\n" +
            "</script>")
    public Integer getMbRgCount(String houseId ) throws SQLException;

   /**
    * 获取人工费项目总计
    * @Description:
    * @author: zheng shuai
    * @date: 2019/1/23 15:42
    * @param: [houseId, zcArray]
    * @return: java.lang.Double
    */
    @SelectProvider(type = getMbRgZj.class,method = "getMbRgZj")
    public double getMbRgZj(String houseId ,String []rgArray) throws SQLException;
    public class getMbRgZj{
        public String getMbRgZj(String houseId ,String []rgArray){
            String tempSql = "";
            tempSql += "SELECT \n" +
                    "\tNVL(SUM(RG_PRICE * RG_QTY) , 0)\n" +
                    "FROM \n" +
                    "\tXY_BJD_TEMPLATE_LIST\n" +
                    "WHERE\n" +
                    "\tTEMPLATE_ID = (\n" +
                    "\t\tSELECT HOUSE_TEMPLATE_RG_ID FROM XY_MAIN_HOUSER WHERE HOUSE_ID = '"+houseId+"'\n" +
                    "\t)\n";
            if (rgArray != null && rgArray.length > 0){
                String tempVariable = "";
                for (int i = 0; i < rgArray.length ; i++) {
                    if (i == 0){
                        tempVariable += "'"+rgArray[i]+"'";
                    } else {
                        tempVariable += ",'"+rgArray[i]+"'";
                    }
                }
                tempSql += "AND TEMPLATE_ROWID NOT IN("+tempVariable+")";
            }
            return tempSql;
        }
    }

    /**
     * 获取辅材总计
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/26 11:03
     * @param: [houseId, rgArray]
     * @return: double
     */
   @SelectProvider(type = getFcZj.class,method = "getFcZj")
    public double getFcZj(String houseId ,String []rgArray) throws SQLException;
    public class getFcZj{
        public String getFcZj(String houseId ,String []rgArray){
            String tempSql = "";
            tempSql += "\tSELECT\n" +
                    "\t\tNVL(SUM(SUM( V.SL )*V.FC_PRICE_OUT) , 0)\n" +
                    "\tFROM\n" +
                    "\t\t(\n" +
                    "\t\tSELECT\n" +
                    "\t\t\tG.RG_SG_STAGE,\n" +
                    "\t\t\tC.FC_CODE,\n" +
                    "\t\t\tE.FC_PRICE_CODE,\n" +
                    "\t\t\tC.FC_NAME,\n" +
                    "\t\t\tC.FC_UNIT,\n" +
                    "\t\t\tD.BRAND_NAME,\n" +
                    "\t\t\tD.S_NAME,\n" +
                    "\t\t\tD.S_VAL,\n" +
                    "\t\t\tE.FC_PRICE_OUT,\n" +
                    "\t\tCEIL(( CASE B.CLC_TYPE WHEN '0' THEN B.SL ELSE B.SL * A.RG_QTY END ) / C.FC_N4 ) * C.FC_N4 SL \n" +
                    "FROM\n" +
                    "\tXYZS_PLAT2.XY_BJD_TEMPLATE_LIST A,\n" +
                    "\tXYZS_PLAT2.XY_BJD_FC_MB B,\n" +
                    "\tXYZS_PLAT2.XY_CLB_FC_DB C,\n" +
                    "\tXYZS_PLAT2.XY_CLB_FC_BRAND D,\n" +
                    "\tXYZS_PLAT2.XY_CLB_FC_DB_PRICE E,\n" +
                    "\tXYZS_PLAT2.XY_MAIN_HOUSER F,\n" +
                    "\tXYZS_PLAT2.XY_GCB_RG_VER_LIST G \n" +
                    "\tWHERE SUBSTR( A.RG_ID, 1, 10 ) = B.RG_CODE ";
            if (rgArray != null && rgArray.length > 0){
                String tempVariable = "";
                for (int i = 0; i < rgArray.length ; i++) {
                    if (i == 0){
                        tempVariable += "'"+rgArray[i]+"'";
                    } else {
                        tempVariable += ",'"+rgArray[i]+"'";
                    }
                }
                tempSql += "\tAND A.TEMPLATE_ROWID NOT IN("+tempVariable+")";
            }

            tempSql += "\tAND C.FC_CODE = D.FC_CODE \n" +
                    "\tAND D.BRAND_ID = E.BRAND_ID \n" +
                    "\tAND F.HOUSE_ID = A.TEMPLATE_ID \n" +
                    "\tAND F.HOUSE_ID = '"+houseId+"' \n" +
                    "\tAND C.FC_ISUSED = 1 \n" +
                    "\tAND D.BRAND_ISUSED = 1 \n" +
                    "\tAND E.FC_PRICE_ISUSED = 1 \n" +
                    "\tAND B.FC_CODE LIKE '%' || C.FC_CODE || '%' \n" +
                    "\tAND G.RG_VER_CODE = F.RG_VER_CODE \n" +
                    "\tAND G.RG_ID = A.RG_ID \n" +
                    "\tAND (\n" +
                    "\t\tD.S_NAME IS NULL \n" +
                    "\t\tOR (\n" +
                    "\t\t\tD.S_NAME IS NOT NULL \n" +
                    "\t\t\tAND EXISTS (\n" +
                    "\t\t\tSELECT\n" +
                    "\t\t\t\t1 \n" +
                    "\t\t\tFROM\n" +
                    "\t\t\t\tXYZS_TEST.XY_HOUSE_FC_BRAND H \n" +
                    "\t\t\tWHERE\n" +
                    "\t\t\t\th.house_id = a.template_id \n" +
                    "\t\t\t\tAND H.S_NAME = D.S_NAME \n" +
                    "\t\t\t\tAND H.S_VAL = D.S_VAL \n" +
                    "\t\t\t)))) V \n" +
                    "GROUP BY\n" +
                    "\tV.RG_SG_STAGE,\n" +
                    "\tV.FC_CODE,\n" +
                    "\tV.FC_NAME,\n" +
                    "\tV.FC_UNIT,\n" +
                    "\tV.BRAND_NAME,\n" +
                    "\tV.S_NAME,\n" +
                    "\tV.S_VAL,\n" +
                    "\tV.FC_PRICE_OUT,\n" +
                    "\tV.FC_PRICE_CODE \n" +
                    "HAVING\n" +
                    "\tSUM( V.SL ) <> 0";
            return tempSql;
        }
    }
}
