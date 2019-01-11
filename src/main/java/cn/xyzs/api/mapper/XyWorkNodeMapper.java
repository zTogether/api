package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.sys.XyWorkNode;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.Map;

public interface XyWorkNodeMapper extends Mapper<XyWorkNode> {

    /**
     * 获取第一个节点
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/5 13:33
     * @param: [flowId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT * FROM XY_WORK_NODE WHERE FLOW_ID = #{flowId,jdbcType=VARCHAR} AND NODE_TYPE = 1" +
            "</script>")
    public Map<String ,Object> getFristNodeInfoByFlowId(String flowId) throws SQLException;
}
