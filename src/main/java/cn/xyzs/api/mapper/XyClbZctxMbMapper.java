package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZctxMbVr;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZctxMbMapper extends Mapper<XyClbZctxMbVr> {
    /***
     *
     * @Description: 根据vrStyle查询套系VR
     * @author: GeWeiliang
     * @date: 2018\9\2 0002 17:35
     * @param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("SELECT * FROM XY_CLB_ZCTX_MB_VR WHERE VR_STYLE=#{vrStyle}")
    public List<Map<String,Object>> showZctxVr(@Param("vrStyle") String vrStyle)throws SQLException;
}
