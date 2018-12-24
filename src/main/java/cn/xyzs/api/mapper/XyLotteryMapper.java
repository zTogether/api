package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyLottery;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyLotteryMapper extends Mapper<XyLottery> {
    @Select("<script>" +
            "SELECT * FROM XY_LOTTERY ORDER BY ROW_ID" +
            "</script>")
    List<Map<String,Object>> getLotNum() throws SQLException;
}
