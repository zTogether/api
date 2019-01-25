package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbZcpbTemplateList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcpbTemplateListMapper extends Mapper<XyClbZcpbTemplateList> {

    /**
     * 获取主材或软装（flag：  0：主材；8：软装）
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 16:17
     * @param: [xyClbZcpbTemplateList, flag, startNum, endNum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "\tSELECT \n" +
            "\t\tA.* ,\n" +
            "\t\tB.ZC_NAME\n" +
            "\tFROM \n" +
            "\t\tXY_CLB_ZCPB_TEMPLATE_LIST A\n" +
            "\tLEFT JOIN XY_CLB_ZC_DB B\n" +
            "\tON A.ZCPB_ZC_CODE = B.ZC_CODE\n" +
            "\tWHERE \n" +
            "\t\tZCPB_MBID = (" +
            "SELECT HOUSE_TEMPLATE_ZC_ID FROM XY_MAIN_HOUSER WHERE HOUSE_ID = #{houseId,jdbcType=VARCHAR}" +
            ")\n" +
            "\tAND\n" +
            "\t\tZCPB_ZC_CODE <![CDATA[<>]]> '0'\n" +
            "\tAND\n" +
            "\t\tSUBSTR(ZCPB_ZC_CODE, 1, 1) = #{acOrRzFlag,jdbcType=VARCHAR}\t\n" +
            "</script>")
    public List<Map<String ,Object>> getMbZcOrRzList(@Param("houseId") String houseId ,@Param("acOrRzFlag") String acOrRzFlag ) throws SQLException;

    /**
     * 获取主材或软装总记录数（flag：  0：主材；8：软装）
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/23 9:41
     * @param: [houseId, acOrRzFlag]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tCOUNT(1)\n" +
            "FROM \n" +
            "\tXY_CLB_ZCPB_TEMPLATE_LIST\n" +
            "WHERE \n" +
            "\tZCPB_MBID = (" +
            "SELECT HOUSE_TEMPLATE_ZC_ID FROM XY_MAIN_HOUSER WHERE HOUSE_ID = #{houseId,jdbcType=VARCHAR}" +
            ")\n" +
            "AND\n" +
            "\tZCPB_ZC_CODE <![CDATA[<>]]> '0'\n" +
            "AND\n" +
            "\tSUBSTR(ZCPB_ZC_CODE, 1, 1) = #{acOrRzFlag,jdbcType=VARCHAR}\t" +
            "</script>")
    public Integer getMbZcOrRzCount(@Param("houseId") String houseId ,@Param("acOrRzFlag") String acOrRzFlag ) throws SQLException;

    /**
     * 获取主材或软装的总计（flag：  0：主材；8：软装）
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/23 15:36
     * @param: [houseId, acOrRzFlag, zcArray]
     * @return: java.lang.Double
     */
    @SelectProvider(type = getMbZcOrRzZj.class,method = "getMbZcOrRzZj")
    public double getMbZcOrRzZj(String houseId ,String acOrRzFlag ,String []zcOrRzArray) throws SQLException;
    public class getMbZcOrRzZj{
        public String getMbZcOrRzZj(String houseId ,String acOrRzFlag ,String []zcOrRzArray){
            String tempSql = "";
            tempSql += "SELECT \n" +
                    "\tNVL(SUM( ZCPB_XJ ) , 0)\n" +
                    "FROM \n" +
                    "\tXY_CLB_ZCPB_TEMPLATE_LIST\n" +
                    "WHERE \n" +
                    "\tZCPB_MBID = (\n" +
                    "\t\tSELECT HOUSE_TEMPLATE_ZC_ID FROM XY_MAIN_HOUSER WHERE HOUSE_ID = '"+houseId+"'\n" +
                    "\t)\n" +
                    "AND\n" +
                    "\tZCPB_ZC_CODE <> '0'\n";
            if ("0".equals(acOrRzFlag)){
                tempSql += "AND\n" +
                        "\tSUBSTR(ZCPB_ZC_CODE, 1, 1) = '0'";
            } else if ("1".equals(acOrRzFlag)){
                tempSql += "AND\n" +
                        "\tSUBSTR(ZCPB_ZC_CODE, 1, 1) = '8'";
            }
            if (zcOrRzArray != null && zcOrRzArray.length > 0){
                String tempVariable = "";
                for (int i = 0; i < zcOrRzArray.length ; i++) {
                    if (i == 0){
                        tempVariable += "'"+zcOrRzArray[i]+"'";
                    } else {
                        tempVariable += ",'"+zcOrRzArray[i]+"'";
                    }
                }
                tempSql += "AND ZCPB_ROWID NOT IN("+tempVariable+")";
            }
            return tempSql;
        }
    }
}
