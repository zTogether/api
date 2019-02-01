package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyBjdRgList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyBjdRgListMapper extends Mapper<XyBjdRgList> {

    @Select("<script>SELECT BRL.BJD_CODE,BRL.BJD_RG_ROWID,BRL.BJD_RG_STAGE,BRL.BJD_RG_NO,\n" +
            "\t\tBRL.RG_NAME,BRL.RG_UNIT," +
            "NVL(BRL.RG_QTY,'0') RG_QTY,BRL.RG_PRICE,BRL.RG_XJ,\n" +
            "\t\tNVL(BRL.RG_MARK, '-') RG_MARK,\n" +
            "\t\tNVL(BRL.RG_DES, '-') RG_DES,\n" +
            "\t\tNVL(BRL.RG_YN, '-') RG_YN,\n" +
            "\t\tNVL(BRL.RG_YZRK, '-') RG_YZRK\t" +
            "FROM XY_BJD_RG_LIST BRL \n" +
            "LEFT JOIN XY_BJD_MAIN BM ON BRL.BJD_CODE=BM.BJD_CODE\n" +
            "WHERE BM.CTR_CODE=#{ctrCode ,jdbcType=VARCHAR} AND BRL.BJD_CODE=#{bjdCode ,jdbcType=VARCHAR}\n" +
            "ORDER BY TO_NUMBER(BRL.BJD_RG_NO) </script>")
    public List<Map<String,Object>> getBjdRgList(@Param("ctrCode") String ctrCode,
                                                 @Param("bjdCode") String bjdCode)throws SQLException;

    @Select("<script>" +
            "SELECT NVL(SUM(BRL.RG_XJ),0) PRJ_ZJ\t\n" +
            "FROM XY_BJD_RG_LIST BRL \n" +
            "LEFT JOIN XY_BJD_MAIN BM ON BRL.BJD_CODE=BM.BJD_CODE\n" +
            "WHERE BM.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND BRL.BJD_CODE=#{bjdCode,jdbcType=VARCHAR} AND BRL.BJD_RG_STAGE=#{rgStage,jdbcType=VARCHAR}\n" +
            "ORDER BY TO_NUMBER(BRL.BJD_RG_NO)" +
            "</script>")
    public Map<String,Object> prjZongJi(@Param("ctrCode") String ctrCode, @Param("bjdCode") String bjdCode,
                                        @Param("rgStage") String rgStage) throws SQLException;


    /**
     * 一键报价添加人工
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/24 13:34
     * @param: [ctrCode, houseId, rgArray]
     * @return: void
     */
    @InsertProvider(type = addAutoBjRg.class,method = "addAutoBjRg")
    public void addAutoBjRg(String ctrCode ,String houseId ,String []rgArray) throws SQLException;
    public class addAutoBjRg{
        public String addAutoBjRg(String ctrCode ,String houseId ,String []rgArray){
            String tempSql = "";
            tempSql += "INSERT INTO XY_BJD_RG_LIST (\n" +
                    "\tBJD_CODE,\n" +
                    "\tBJD_RG_ROWID,\n" +
                    "\tBJD_RG_STAGE, \n" +
                    "\tBJD_RG_NO, \n" +
                    "\tRG_ID, \n" +
                    "\tRG_NAME, \n" +
                    "\tRG_UNIT, \n" +
                    "\tRG_QTY, \n" +
                    "\tRG_PRICE, \n" +
                    "\tRG_XJ, \n" +
                    "\tRG_DES\n" +
                    ") (\n" +
                    "\tSELECT\n" +
                    "\t\t'"+ctrCode+"01"+"',\n" +
                    "\t\tsys_guid(),\n" +
                    "\t\tRG_STAGE, \n" +
                    "\t\tTEMPLATE_NO, \n" +
                    "\t\tRG_ID, \n" +
                    "\t\tRG_NAME, \n" +
                    "\t\tRG_UNIT, \n" +
                    "\t\tRG_QTY,\n" +
                    "\t\tRG_PRICE, \n" +
                    "\t\tRG_QTY*RG_PRICE RG_XJ,\n" +
                    "\t\tRG_DES\n" +
                    "\tFROM \n" +
                    "\t\tXY_BJD_TEMPLATE_LIST\n" +
                    "\tWHERE\n" +
                    "\t\tTEMPLATE_ID = (\n" +
                    "\t\t\tSELECT HOUSE_TEMPLATE_RG_ID FROM XY_MAIN_HOUSER WHERE HOUSE_ID = '"+houseId+"'\n" +
                    "\t\t)\n";
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
            tempSql += ")";
            return tempSql;
        }
    }

    /**
     * 一键报价执行删除操作
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 8:59
     * @param: [bjdCode]
     * @return: void
     */
    @Delete("<script>" +
            "DELETE FROM XY_BJD_RG_LIST WHERE BJD_CODE = #{bjdCode,jdbcType=VARCHAR}" +
            "</script>")
    public void autoBjDelete(String bjdCode) throws SQLException;
}
