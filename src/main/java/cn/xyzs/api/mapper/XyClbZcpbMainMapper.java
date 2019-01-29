package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbZcpbMain;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

public interface XyClbZcpbMainMapper extends Mapper<XyClbZcpbMain> {

    @Insert("<script>" +
            "INSERT INTO XY_CLB_ZCPB_MAIN(\n" +
            "\tCTR_CODE, \n" +
            "\tOP_USERID, \n" +
            "\tZCPB_STATU, \n" +
            "\tZCPB_HJ, \n" +
            "\tZCPB_LX, \n" +
            "\tZCPB_BL\n" +
            ") VALUES(\n" +
            "\t#{ctrCode,jdbcType=VARCHAR},\n" +
            "\t#{opUserid,jdbcType=VARCHAR},\n" +
            "\t#{zcpbStatu,jdbcType=VARCHAR},\n" +
            "\t#{zcpbHj,jdbcType=VARCHAR},\n" +
            "\t#{zcpbLx,jdbcType=VARCHAR},\n" +
            "\t#{zcpbBl,jdbcType=VARCHAR}\n" +
            ")" +
            "</script>")
    public void addAutoBjZcpbMain(XyClbZcpbMain xyClbZcpbMain) throws SQLException;
}
