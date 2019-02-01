package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbZcpbList;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcpbListMapper extends Mapper<XyClbZcpbList> {
    /**
     *
     * @Description: 获取一级目录
     * @author: GeWeiliang
     * @date: 2018\12\23 0023 9:56
     * @param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT zl.ZCPB_ML FROM XY_CLB_ZCPB_LIST zl\n" +
            "LEFT JOIN XY_CLB_ZCPB_MAIN zm ON zm.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}\n" +
            "WHERE zl.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND zl.ZCPB_STAGE='A' AND zl.ZCPB_ZC_CODE IS NOT NULL\n" +
            "GROUP BY zl.ZCPB_XH,zl.ZCPB_ML\n" +
            "ORDER BY zl.ZCPB_XH" +
            "</script>")
    List<Map<String,Object>> getFirstMl(@Param("ctrCode") String ctrCode,@Param("zcType") String zcType)throws SQLException;

    /**
     *
     * @Description: 获取主材配比列表
     * @author: GeWeiliang
     * @date: 2018\12\23 0023 13:45
     * @param: [ctrCode, mlName, zcType]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getZcpbList.class,method = "getZcpbList")
    List<Map<String,Object>> getZcpbList(@Param("ctrCode") String ctrCode,@Param("mlName") String mlName,
                                         @Param("zcType") String zcType,@Param("condition") String condition)throws SQLException;
    class getZcpbList{
        public String getZcpbList(@Param("ctrCode") String ctrCode,@Param("mlName") String mlName,
                                  @Param("zcType") String zcType,@Param("condition") String condition){
            return new SQL(){{
                SELECT("zl.*,zd.ZC_VERSION");
                FROM("XY_CLB_ZCPB_LIST zl");
                LEFT_OUTER_JOIN("XY_CLB_ZC_DB zd ON zl.ZCPB_ZC_CODE=zd.ZC_CODE");
                LEFT_OUTER_JOIN("XY_CLB_ZCPB_MAIN zm ON zm.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}");
                WHERE("ZL.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND ZL.ZCPB_ZC_CODE IS NOT NULL");
                if(mlName!=null&&mlName!=""){
                    WHERE("zl.ZCPB_ML=#{mlName,jdbcType=VARCHAR}");
                }
                if(zcType!=null&&zcType!=""){
                    WHERE("zl.ZCPB_STAGE=#{zcType,jdbcType=VARCHAR}");
                    WHERE("ZM.ZCPB_STATU LIKE #{condition,jdbcType=VARCHAR}");
                }
                ORDER_BY("ZL.ZCPB_XH");
            }}.toString();
        }
    }

    @SelectProvider(type = getZj.class,method = "getZj")
    String selectedZj(@Param("ctrCode") String ctrCode,@Param("mlName") String mlName,
                                  @Param("zcType") String zcType,@Param("condition") String condition)throws SQLException;
    class getZj{
        public String getZj(@Param("ctrCode") String ctrCode,@Param("mlName") String mlName,
                            @Param("zcType") String zcType,@Param("condition") String condition){
            return new SQL(){{
                SELECT("SUM(ZCPB_XJ) ZJ");
                FROM("XY_CLB_ZCPB_LIST zl");
                LEFT_OUTER_JOIN("XY_CLB_ZCPB_MAIN zm ON zm.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}");
                WHERE("zl.CTR_CODE=#{ctrCode,jdbcType=VARCHAR} AND zl.ZCPB_ZC_CODE IS NOT NULL");
                if(mlName!=null&&mlName!=""){
                    WHERE("zl.ZCPB_ML=#{mlName,jdbcType=VARCHAR}");
                }
                if(zcType!=null&&zcType!=""){
                    WHERE("zl.ZCPB_STAGE=#{zcType,jdbcType=VARCHAR}");
                    WHERE("ZM.ZCPB_STATU like #{condition,jdbcType=VARCHAR}");
                }
            }}.toString();
        }
    }

    /**
     * 一键报价添加主材和软装
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/24 13:28
     * @param: [houseId, zcArray, rzArray]
     * @return: void
     */
    @InsertProvider(type = addAutoBjZcAndRz.class,method = "addAutoBjZcAndRz")
    public void addAutoBjZcAndRz(String ctrCode ,String houseId ,String []zcArray ,String []rzArray) throws SQLException;
    public class addAutoBjZcAndRz{
        public String addAutoBjZcAndRz(String ctrCode ,String houseId ,String []zcArray ,String []rzArray){
            String tempSql = "";
            tempSql += "INSERT INTO XY_CLB_ZCPB_LIST (\n" +
                    "\tSELECT \n" +
                    "\t\tZCPB_MBID,\n" +
                    "\t\t'"+ctrCode+"',\n" +
                    "\t\tZCPB_ML, \n" +
                    "\t\tZCPB_XH, \n" +
                    "\t\tZCPB_TYPE, \n" +
                    "\t\tZCPB_PP, \n" +
                    "\t\tZCPB_DC, \n" +
                    "\t\tZCPB_SPEC, \n" +
                    "\t\tZCPB_UNIT, \n" +
                    "\t\tZCPB_PRICE, \n" +
                    "\t\tZCPB_QTY, \n" +
                    "\t\tZCPB_XJ, \n" +
                    "\t\tZCPB_MARK, \n" +
                    "\t\tsys_guid(), \n" +
                    "\t\tZCPB_VERSION, \n" +
                    "\t\tZCPB_METE, \n" +
                    "\t\tZCPB_MX, \n" +
                    "\t\tZCPB_STAGE, \n" +
                    "\t\tZCPB_ZC_CODE, \n" +
                    "\t\tZCPB_ZC_TYPE\n" +
                    "\tFROM \n" +
                    "\t\tXY_CLB_ZCPB_TEMPLATE_LIST\n" +
                    "\tWHERE \n" +
                    "\t\tZCPB_MBID = (\n" +
                    "\t\t\tSELECT HOUSE_TEMPLATE_ZC_ID FROM XY_MAIN_HOUSER WHERE HOUSE_ID = '"+houseId+"'\n" +
                    "\t\t)\n" +
                    "\tAND\n" +
                    "\t\tZCPB_ZC_CODE <> '0'\n";
            if (zcArray != null && zcArray.length > 0){
                String tempVariable = "";
                //主材
                for (int i = 0; i < zcArray.length ; i++) {
                    if (i == 0){
                        tempVariable += "'"+zcArray[i]+"'";
                    } else {
                        tempVariable += ",'"+zcArray[i]+"'";
                    }
                }
                //软装
                for (int i = 0; i < rzArray.length ; i++) {
                    tempVariable += ",'"+rzArray[i]+"'";
                }
                tempSql += "AND ZCPB_ROWID NOT IN("+tempVariable+")";
            }
            tempSql +=  ")";
            return tempSql;
        }
    }

    /**
     * 一键报价删除操作
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 9:01
     * @param: [ctrCode]
     * @return: void
     */
    @Delete("<script>" +
            "DELETE FROM XY_CLB_ZCPB_LIST WHERE CTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public void autoBjDelete(String ctrCode) throws SQLException;

}
