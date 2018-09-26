package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyPgYs;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyPgYsMapper extends Mapper<XyPgYs> {

    /**
     * 根据ctrCode获取派工验收表里的信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/24 15:26
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txpy.YS_ID, \n" +
            "\txpy.CTR_CODE, \n" +
            "\txpy.YS_GZ, \n" +
            "\tTO_CHAR(xpy.OP_DATE,'yyyy-MM-dd HH24:mi:ss') OP_DATE, \n" +
            "\txpy.OP_USERID, \n" +
            "\txpy.YS_STATU, \n" +
            "\txpy.ZXY_MARK, \n" +
            "\txpy.CUST_MARK, \n" +
            "\tTO_CHAR(xpy.YS_DATE,'yyyy-MM-dd') YS_DATE,\n" +
            "\t(SELECT xv.VAL_NAME FROM XY_VAL xv WHERE xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C' AND xv.VAL_ID = xpy.YS_GZ) YS_GZ_NAME,\n" +
            "\t(SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = xpy.OP_USERID) OP_USER_NAME\n" +
            "FROM\n" +
            "\tXY_PG_YS xpy\n" +
            "WHERE\n" +
            "\txpy.CTR_CODE = #{ctrCode}" +
            "</script>")
    public List<Map<String ,Object>> getXyPgYsListByCtrCode(String ctrCode) throws SQLException;
}
