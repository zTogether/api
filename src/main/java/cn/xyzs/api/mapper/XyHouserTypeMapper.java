package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyHouserType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyHouserTypeMapper extends Mapper<XyHouserType> {

    /**
     * 根据小区获取户型
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/25 12:34
     * @param: [areaId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tHOUSE_TYPE_ID,\n" +
            "\tHOUSE_TYPE_NAME,\n" +
            "\tAREA_ID,\n" +
            "\tHOUSE_DESC\n" +
            "FROM \n" +
            "\tXY_HOUSER_TYPE \n" +
            "WHERE \n" +
            "\tAREA_ID = #{areaId,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getHouserTypeByAreaId(String areaId) throws SQLException;
}
