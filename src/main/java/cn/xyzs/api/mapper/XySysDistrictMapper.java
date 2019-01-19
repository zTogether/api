package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XySysDistrict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XySysDistrictMapper extends Mapper<XySysDistrict> {

    /**
     * 根据条件获取省市区
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/18 10:39
     * @param: [xySysDistrict]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getSysDistrict.class,method = "getSysDistrict")
    public List<Map<String ,Object>> getSysDistrict(XySysDistrict xySysDistrict) throws SQLException;
    public class getSysDistrict{
        public String getSysDistrict(XySysDistrict xySysDistrict){
            return new SQL(){{
                SELECT("DISTRICT_ID,\n" +
                        "\tDISTRICT_NAME, \n" +
                        "\tPREV_DISTRICT_ID,\n" +
                        "\tDISTRICT_LEVEL");
                FROM("XY_SYS_DISTRICT");
                if (!"".equals(xySysDistrict.getDistrictLevel()) && !"null".equals(xySysDistrict.getDistrictLevel())
                        && xySysDistrict.getDistrictLevel() != null){
                    //查询省
                    WHERE("DISTRICT_LEVEL = #{districtLevel,jdbcType=VARCHAR}");
                }
                if (!"".equals(xySysDistrict.getDistrictId()) && !"null".equals(xySysDistrict.getDistrictId())
                        && xySysDistrict.getDistrictId() != null){
                    //查询市或区
                    WHERE("PREV_DISTRICT_ID = #{districtId,jdbcType=VARCHAR}");
                }
            }}.toString();
        }
    }
}
