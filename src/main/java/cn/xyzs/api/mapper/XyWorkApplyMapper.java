package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.sys.XyWorkApply;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyWorkApplyMapper extends Mapper<XyWorkApply> {

    /**
     * 获取信息历史处理记录
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/3 17:12
     * @param: [custId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t(SELECT USER_NAME FROM XY_USER WHERE USER_ID = B.XY_USER_ID) USER_NAME,\n" +
            "\tTO_CHAR( B.WAD_ADDTIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ), 'yyyy-MM-dd' ) WAD_ADDTIME,\n" +
            "\tB.WAD_REMARK,\n" +
            "\tC.NODE_NAME\n" +
            "FROM\n" +
            "\tXY_WORK_APPLY A\n" +
            "LEFT JOIN XY_WORK_ADETAIL B\n" +
            "ON A.APPLY_ID = B.APPLY_ID\n" +
            "LEFT JOIN XY_WORK_NODE C\n" +
            "ON B.NODE_ID = C.NODE_ID\n" +
            "WHERE A.APPLY_CONTENT = #{custId,jdbcType=VARCHAR}\n" +
            "ORDER BY B.WAD_ADDTIME DESC" +
            "</script>")
    public List<Map<String ,Object>> getInfoHistoryFlow(String custId) throws SQLException;
}
