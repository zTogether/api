package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyMainArea;
import cn.xyzs.common.pojo.XyMainHouser;
import cn.xyzs.common.pojo.XySysDistrict;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyMainHouserMapper extends Mapper<XyMainHouser> {

    /**
     * 根据条件获取小区的附加查询信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/18 11:21
     * @param: [xyMainHouser]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getAdditionalInfo.class,method = "getAdditionalInfo")
    public List<Map<String ,Object>> getAdditionalInfo(XyMainHouser xyMainHouser) throws SQLException;
    public class getAdditionalInfo{
        public String getAdditionalInfo(XyMainHouser xyMainHouser){
            return new SQL(){{
                SELECT("HOUSE_ID,\n" +
                        "\tAREA_ID,\n" +
                        "\tCAD_COMMON_URL,\n" +
                        "\tHOUSE_STYLE,\n" +
                        "\tEFFECTS_URL,\n" +
                        "\tVR_URL,\n" +
                        "\tHOUSE_AUTHOR,\n" +
                        "\tCREATE_TIME,\n" +
                        "\tHOUSE_DESC,\n" +
                        "\tLIKE_NUM, \n" +
                        "\tCAD_DECORATION_URL,\n" +
                        "\tFLOOR_HEIGHT, \n" +
                        "\tFLOOR_FACT_HEIGHT,\n" +
                        "\tHOUSE_LEVEL");
                FROM("XY_MAIN_HOUSER");
                if (!"".equals(xyMainHouser.getAreaId()) && !"null".equals(xyMainHouser.getAreaId())
                        && xyMainHouser.getAreaId() != null){
                    WHERE("AREA_ID = #{areaId,jdbcType=VARCHAR}");
                }

                if (!"".equals(xyMainHouser.getHouseStyle()) && !"null".equals(xyMainHouser.getHouseStyle())
                        && xyMainHouser.getHouseStyle() != null){
                    WHERE("HOUSE_STYLE = #{houseStyle,jdbcType=VARCHAR}");
                }

                if (!"".equals(xyMainHouser.getHouseLevel()) && !"null".equals(xyMainHouser.getHouseLevel())
                        && xyMainHouser.getHouseLevel() != null){
                    WHERE("HOUSE_LEVEL = #{houseLevel,jdbcType=VARCHAR}");
                }

            }}.toString();
        }
    }

    /**
     * 根据条件查询房屋列表
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/18 12:11
     * @param: [xyMainHouser, xyMainArea, xySysDistrict]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getHouseInfoByCondition.class,method = "getHouseInfoByCondition")
    public List<Map<String ,Object>> getHouseInfoByCondition(XyMainHouser xyMainHouser, XyMainArea xyMainArea ,
                                                             XySysDistrict xySysDistrict ,Integer startNum,
                                                             Integer endNum) throws SQLException;
    public class getHouseInfoByCondition{
        public String getHouseInfoByCondition(XyMainHouser xyMainHouser, XyMainArea xyMainArea ,
                                              XySysDistrict xySysDistrict ,Integer startNum, Integer endNum){
            String tempSql = "SELECT J.*  FROM ( SELECT H.*, ROWNUM RN  FROM (";
            tempSql += new SQL(){{
                SELECT("A.HOUSE_ID,\n" +
                        "\tA.AREA_ID,\n" +
                        "\tA.CAD_COMMON_URL,\n" +
                        "\tA.HOUSE_STYLE,\n" +
                        "\tA.EFFECTS_URL,\n" +
                        "\tA.VR_URL,\n" +
                        "\tU.USER_NAME,\n" +
                        "\tA.HOUSE_AUTHOR,\n" +
                        "\tA.CREATE_TIME,\n" +
                        "\tA.HOUSE_DESC,\n" +
                        "\tA.LIKE_NUM, \n" +
                        "\tA.CAD_DECORATION_URL,\n" +
                        "\tA.FLOOR_HEIGHT, \n" +
                        "\tA.FLOOR_FACT_HEIGHT,\n" +
                        "\tA.HOUSE_LEVEL,\n" +
                        "\tROUND(TO_NUMBER(SYSDATE - A.CREATE_TIME)) days\n");
                FROM("XY_MAIN_HOUSER A");
                LEFT_OUTER_JOIN("XY_MAIN_AREA B ON A.AREA_ID = B.AREA_ID");
                LEFT_OUTER_JOIN("XY_SYS_DISTRICT C ON B.DISTRICT_ID = C.DISTRICT_ID");
                LEFT_OUTER_JOIN("XY_USER U ON U.USER_ID=A.HOUSE_AUTHOR");
                //根据市/区/县查询
                if (!"".equals(xySysDistrict.getDistrictId()) && !"null".equals(xySysDistrict.getDistrictId())
                        && xySysDistrict.getDistrictId() != null){
                    WHERE("C.DISTRICT_ID IN (\n" +
                            "\t\tSELECT '"+ xySysDistrict.getDistrictId() +"' FROM dual\n" +
                            "\t\tUNION ALL\n" +
                            "\t\tSELECT DISTRICT_ID FROM XY_SYS_DISTRICT WHERE PREV_DISTRICT_ID = '"+ xySysDistrict.getDistrictId() +"'\n" +
                            "\t)");
                }
                //根据小区查询
                if (!"".equals(xyMainArea.getAreaId()) && !"null".equals(xyMainArea.getAreaId())
                        && xyMainArea.getAreaId() != null){
                    WHERE("B.AREA_ID = '"+ xyMainArea.getAreaId() +"'");
                }
                //根据户型查询
                if (!"".equals(xyMainHouser.getHouseStyle()) && !"null".equals(xyMainHouser.getHouseStyle())
                        && xyMainHouser.getHouseStyle() != null){
                    WHERE("A.HOUSE_STYLE = '"+ xyMainHouser.getHouseStyle() +"'");
                }
                //根据装修等级查询
                if (!"".equals(xyMainHouser.getHouseLevel()) && !"null".equals(xyMainHouser.getHouseLevel())
                        && xyMainHouser.getHouseLevel() != null){
                    WHERE("A.HOUSE_LEVEL = '"+ xyMainHouser.getHouseLevel() +"'");
                }

            }}.toString();
            tempSql += ") H) J WHERE RN BETWEEN "+ startNum +" AND "+ endNum;
            return tempSql;
        }
    }

    /**
     * 根据houseId获取房屋信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 18:02
     * @param: [houseId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tA.HOUSE_ID,\n" +
            "\tA.AREA_ID,\n" +
            "\tA.CAD_COMMON_URL,\n" +
            "\tA.HOUSE_STYLE,\n" +
            "\tA.EFFECTS_URL,\n" +
            "\tA.VR_URL,\n" +
            "\tA.HOUSE_AUTHOR,\n" +
            "\tB.USER_NAME HOUSE_AUTHOR_NAME,\n" +
            "\tTO_CHAR(A.CREATE_TIME,'yyyy-MM-dd HH24:mi:ss') CREATE_TIME,\n" +
            "\tA.HOUSE_DESC,\n" +
            "\tA.LIKE_NUM, \n" +
            "\tA.CAD_DECORATION_URL,\n" +
            "\tA.FLOOR_HEIGHT, \n" +
            "\tA.FLOOR_FACT_HEIGHT,\n" +
            "\tA.HOUSE_LEVEL,\n" +
            "\tA.HOUSE_TEMPLATE_RG_ID, \n" +
            "\tA.HOUSE_TEMPLATE_ZC_ID\n" +
            "FROM \n" +
            "\tXY_MAIN_HOUSER A\n" +
            "LEFT JOIN XY_USER B\n" +
            "ON B.USER_ID = A.HOUSE_AUTHOR\n" +
            "WHERE\n" +
            "\tA.HOUSE_ID = #{houseId,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,Object> getHouseInfoByHouseId(String houseId) throws SQLException;
}
