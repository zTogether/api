package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbZcpbMain;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * 一键报价删除操作
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 9:01
     * @param: [ctrCode]
     * @return: void
     */
    @Delete("<script>" +
            "DELETE FROM XY_CLB_ZCPB_MAIN WHERE CTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public void autoBjDelete(String ctrCode) throws SQLException;
}
