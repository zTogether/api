package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyShopPositionInfo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyShopPositionInfoMapper extends Mapper<XyShopPositionInfo> {

    /**
     * 获取店铺信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/13 13:09
     * @param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_SHOP_POSITION_INFO WHERE IS_USED = 0" +
            "</script>")
    public List<Map<String ,Object>> getShopInfoList() throws SQLException;
}
