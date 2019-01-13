package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyLog;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

public interface XyLogMapper extends Mapper<XyLog> {

    /**
     * 添加日志
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/12 9:28
     * @param: [xyLog]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_LOG (\n" +
            "\tUSER_ID,\n" +
            "\tCOMPO_ID,\n" +
            "\tOP_ID,\n" +
            "\tLOG_IP,\n" +
            "\tLOG_RESULT,\n" +
            "\tLOG_DATAID\n" +
            ") VALUES (\n" +
            "\t#{userId,jdbcType=VARCHAR},\n" +
            "\t#{compoId,jdbcType=VARCHAR},\n" +
            "\t#{opId,jdbcType=VARCHAR},\n" +
            "\t#{logIp,jdbcType=VARCHAR},\n" +
            "\t#{logResult,jdbcType=VARCHAR},\n" +
            "\t#{logDataid,jdbcType=VARCHAR}\n" +
            ")" +
            "</script>")
    public void addXyLog(XyLog xyLog) throws SQLException;
}
