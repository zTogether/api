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
     * 根据户型获取风格
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/25 12:28
     * @param: [houseTypeId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tHOUSE_STYLE\n" +
            "FROM \n" +
            "\tXY_MAIN_HOUSER \n" +
            "WHERE \n" +
            "\tHOUSE_TYPE_ID = #{houseTypeId,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getHouseStyleByHouseTypeId(String houseTypeId) throws SQLException;

    /**
     * 根据条件查询房屋列表
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/18 12:11
     * @param: [xyMainHouser, xyMainArea, xySysDistrict]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getHouseInfoByCondition.class,method = "getHouseInfoByCondition")
    public List<Map<String ,Object>> getHouseInfoByCondition(String districtId ,String areaId ,String houseTypeId ,String houseStyle ,
                                                             Integer startNum, Integer endNum) throws SQLException;
    public class getHouseInfoByCondition{
        public String getHouseInfoByCondition(String districtId ,String areaId ,String houseTypeId ,String houseStyle ,
                                              Integer startNum, Integer endNum){
            String tempSql = "SELECT J.*  FROM ( SELECT H.*, ROWNUM RN  FROM (";
            tempSql += new SQL(){{
                SELECT("( SELECT Z.IMG_URL FROM XY_MAIN_HOUSE_IMG Z WHERE Z.HOUSE_ID = A.HOUSE_ID AND ROWNUM = 1 ) HOUSE_IMG," +
                        "\tA.HOUSE_ID,\n" +
                        "\tA.HOUSE_TYPE_ID,\n" +
                        "\tB.HOUSE_TYPE_NAME HOUSE_TYPE_NAME,\n" +
                        "\tA.CAD_COMMON_URL,\n" +
                        "\tA.HOUSE_STYLE,\n" +
                        "\tA.EFFECTS_URL,\n" +
                        "\tA.VR_URL,\n" +
                        "\tA.HOUSE_AUTHOR,\n" +
                        "\tC.USER_NAME HOUSE_AUTHOR_NAME,\n" +
                        "\tA.CREATE_TIME,\n" +
                        "\tROUND(TO_NUMBER(SYSDATE - A.CREATE_TIME)) DAYS,\n" +
                        "\tA.HOUSE_DESC,\n" +
                        "\tA.LIKE_NUM,\n" +
                        "\tA.CAD_DECORATION_URL,\n" +
                        "\tA.FLOOR_HEIGHT,\n" +
                        "\tA.FLOOR_FACT_HEIGHT,\n" +
                        "\tA.HOUSE_LEVEL,\n" +
                        "\tA.HOUSE_TEMPLATE_RG_ID,\n" +
                        "\tA.HOUSE_TEMPLATE_ZC_ID");
                FROM("XY_MAIN_HOUSER A");
                LEFT_OUTER_JOIN("XY_HOUSER_TYPE B ON A.HOUSE_TYPE_ID = B.HOUSE_TYPE_ID");
                LEFT_OUTER_JOIN("XY_MAIN_AREA D ON B.AREA_ID = D.AREA_ID");
                LEFT_OUTER_JOIN("XY_USER C ON A.HOUSE_AUTHOR = C.USER_ID");
                //根据小区查询
                if (!"".equals(areaId) && !"null".equals(areaId) && areaId != null){
                    WHERE("B.AREA_ID = '"+areaId+"'");
                }
                //根据户型查询
                if (!"".equals(houseTypeId) && !"null".equals(houseTypeId) && houseTypeId != null){
                    WHERE("B.HOUSE_TYPE_ID = '"+ houseTypeId +"'");
                }
                //根据风格查询
                if (!"".equals(houseStyle) && !"null".equals(houseStyle) && houseStyle != null){
                    WHERE("A.HOUSE_STYLE = '"+ houseStyle +"'");
                }

                if (!"".equals(districtId) && !"null".equals(districtId) && districtId != null){
                    WHERE("D.DISTRICT_ID = '"+ districtId +"'");
                }
            }}.toString();
            tempSql += ") H) J WHERE RN BETWEEN "+ startNum +" AND "+ endNum;
            tempSql += " ORDER BY LIKE_NUM DESC";
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
            "\tA.HOUSE_TYPE_ID,\n" +
            "\tB.HOUSE_TYPE_NAME HOUSE_TYPE_NAME,\n" +
            "\tA.CAD_COMMON_URL,\n" +
            "\tA.HOUSE_STYLE,\n" +
            "\tA.EFFECTS_URL,\n" +
            "\tA.VR_URL,\n" +
            "\tA.HOUSE_AUTHOR,\n" +
            "\tC.USER_NAME HOUSE_AUTHOR_NAME,\n" +
            "\tA.CREATE_TIME,\n" +
            "\tROUND(TO_NUMBER(SYSDATE - A.CREATE_TIME)) DAYS,\n" +
            "\tA.HOUSE_DESC,\n" +
            "\tA.LIKE_NUM,\n" +
            "\tA.CAD_DECORATION_URL,\n" +
            "\tA.FLOOR_HEIGHT,\n" +
            "\tA.FLOOR_FACT_HEIGHT,\n" +
            "\tA.HOUSE_LEVEL,\n" +
            "\tA.HOUSE_TEMPLATE_RG_ID,\n" +
            "\tA.HOUSE_TEMPLATE_ZC_ID,\n" +
            "\tD.AREA_NAME\n" +
            "FROM \n" +
            "\tXY_MAIN_HOUSER A\n" +
            "LEFT JOIN XY_HOUSER_TYPE B ON A.HOUSE_TYPE_ID = B.HOUSE_TYPE_ID\n" +
            "LEFT JOIN XY_MAIN_AREA D ON B.AREA_ID = D.AREA_ID\n" +
            "LEFT JOIN XY_USER C ON A.HOUSE_AUTHOR = C.USER_ID\n" +
            "WHERE HOUSE_ID = #{houseId,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,Object> getHouseInfoByHouseId(String houseId) throws SQLException;
}
