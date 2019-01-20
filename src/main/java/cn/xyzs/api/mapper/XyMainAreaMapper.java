package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyMainArea;
import cn.xyzs.common.pojo.XySysDistrict;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyMainAreaMapper extends Mapper<XyMainArea> {

    /**
     * 根据区县id获取小区
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/18 11:02
     * @param: [xyMainArea]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getCommunityByDistrictId.class,method = "getCommunityByDistrictId")
    public List<Map<String ,Object>> getCommunityByDistrictId(XyMainArea xyMainArea) throws SQLException;
    public class getCommunityByDistrictId{
        public String getCommunityByDistrictId(XyMainArea xyMainArea){
            return new SQL(){{
                SELECT("AREA_ID,\n" +
                        "\tAREA_NAME,\n" +
                        "\tAREA_ADDRESS,\n" +
                        "\tAREA_ADDRESS_DETAIL,\n" +
                        "\tAREA_NUMBER,\n" +
                        "\tAREA_AVG_PRICE,\n" +
                        "\tTRAFFIC_REMARK,\n" +
                        "\tHOSPITAL_REMARK,\n" +
                        "\tBUSINESS_REMARK, \n" +
                        "\tEDUCATION_REMARK,\n" +
                        "\tOTHER_REMARK,\n" +
                        "\tAREA_RUBBISH,\n" +
                        "\tDISTRICT_ID ");
                FROM("XY_MAIN_AREA");
                if (!"".equals(xyMainArea.getDistrictId()) && !"null".equals(xyMainArea.getDistrictId())
                        && xyMainArea.getDistrictId() != null){
                    WHERE("DISTRICT_ID IN (" +
                            "SELECT '"+ xyMainArea.getDistrictId() +"' FROM dual\n" +
                            "\t\tUNION ALL\n" +
                            "\t\tSELECT DISTRICT_ID FROM XY_SYS_DISTRICT WHERE PREV_DISTRICT_ID = '"+ xyMainArea.getDistrictId() +"'" +
                            ")");
                }

            }}.toString();
        }
    }

    @Select("script>" +
            "" +
            "</script>")
    List<Map<String,Object>> getHouseStyle();
}
