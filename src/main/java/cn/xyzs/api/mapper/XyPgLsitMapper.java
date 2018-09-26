package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyPgLsit;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyPgLsitMapper extends Mapper<XyPgLsit>{

    /**
     * 获取派单明细
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/23 9:25
     * @param: [pgId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txpl.PG_ID,\n" +
            "\txpl.PG_ROW,\n" +
            "\txpl.RG_ID,\n" +
            "\txpl.RG_NAME, \n" +
            "\txpl.RG_UNIT, \n" +
            "\txpl.RG_QTY, \n" +
            "\txpl.RG_HJ, \n" +
            "\txpl.RG_DESC, \n" +
            "\txpl.BJD_CODE \n" +
            "FROM\n" +
            "\tXY_PG_LIST xpl \n" +
            "WHERE\n" +
            "\txpl.PG_ID = #{pgId}" +
            "</script>")
    public List<Map<String ,Object>> getPgLsit(String pgId) throws SQLException;
}
