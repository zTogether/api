package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyHtInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

public interface XyHtInfoMapper extends Mapper<XyHtInfo> {

    /**
     * 添加合同信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/26 14:54
     * @param: [ctrCode]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_HT_INFO(\n" +
            "\tCTR_CODE, \n" +
            "\tADDR_LIVE,\n" +
            "\tWT_YN,\n" +
            "\tHT_KGRQ,\n" +
            "\tHT_STATU\n" +
            ") VALUES (\n" +
            "\t#{ctrCode,jdbcType=VARCHAR},\n" +
            "\t(SELECT CTR_ADDR FROM XY_CUSTOMER_INFO WHERE CTR_CODE = #{ctrCode,jdbcType=VARCHAR}),\n" +
            "\t'0',\n" +
            "\tSYSDATE,\n" +
            "\t'1'\n" +
            ")" +
            "</script>")
    public void addHtInfo(String ctrCode) throws SQLException;

    /**
     * 一键报价执行删除
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 9:04
     * @param: [ctrCode]
     * @return: void
     */
    @Delete("<script>" +
            "DELETE FROM XY_HT_INFO WHERE CTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public void autoBjDelete(String ctrCode) throws SQLException;
}
