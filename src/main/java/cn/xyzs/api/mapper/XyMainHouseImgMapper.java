package cn.xyzs.api.mapper;

import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyMainHouseImgMapper {

    /**
     * 根据houseId获取图片list
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 17:23
     * @param: [houseId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_MAIN_HOUSE_IMG WHERE HOUSE_ID = #{houseId,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getHouseImgListByHouseId(String houseId) throws SQLException;

}
