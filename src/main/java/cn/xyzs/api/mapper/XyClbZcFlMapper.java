package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyClbZcFl;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcFlMapper extends Mapper<XyClbZcFl>{

    /**
     * 查询下级目录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/20 14:42
     * @param: [zcflCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>SELECT xczf.* FROM XY_CLB_ZC_FL xczf WHERE xczf.P_CODE = #{zcflCode}</script>")
    public List<Map<String, Object>> getSubdirectory(String zcflCode) throws SQLException;
}
