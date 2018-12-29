package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyCwbCapital;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.Map;

public interface XyCwbCapitalMapper extends Mapper<XyCwbCapital> {

    /**
     * 是否存在资金信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 13:09
     * @param: [userId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM XY_CWB_CAPITAL WHERE USER_ID = #{userId,jdbcType=VARCHAR}" +
            "</script>")
    public Integer existsCapitalInfo(String userId) throws SQLException;

    /**
     * 添加资金信息（userId：用户id   transactionCertificate：证书 ）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 13:11
     * @param: [xyCwbCapital]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_CWB_CAPITAL (USER_ID,TRANSACTION_CERTIFICATE) " +
            "VALUES(#{userId,jdbcType=VARCHAR},#{transactionCertificate,jdbcType=VARCHAR})" +
            "</script>")
    public void addCapitalInfo(XyCwbCapital xyCwbCapital) throws SQLException;

    /**
     * 根据资金主键id修改交易密码
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 13:14
     * @param: [xyCwbCapital]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_CWB_CAPITAL SET TRANSACTION_PASSWORD = #{transactionPassword,jdbcType=VARCHAR} " +
            "WHERE USER_ID = #{userId,jdbcType=VARCHAR}" +
            "</script>")
    public void updateTransactionPassword(XyCwbCapital xyCwbCapital) throws SQLException;

    /**
     * 根据资金主键id修改可提现金额，（capitalId：资金表主键id  transactionCertificate：证书   allowWithdrawDepositMenoy：可提现金额 ）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 13:17
     * @param: [xyCwbCapital]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_CWB_CAPITAL SET " +
            "ALLOW_WITHDRAW_DEPOSIT_MONEY = #{allowWithdrawDepositMoney,jdbcType=VARCHAR}," +
            "TRANSACTION_CERTIFICATE = #{transactionCertificate,jdbcType=VARCHAR} " +
            "WHERE CAPITAL_ID = #{capitalId,jdbcType=VARCHAR}" +
            "</script>")
    public void updateAllowWithdrawDepositMenoy(XyCwbCapital xyCwbCapital) throws SQLException;

    /**
     * 根据userId获取资金主表信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/23 13:51
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tCAPITAL_ID, \n" +
            "\tUSER_ID, \n" +
            "\tALLOW_WITHDRAW_DEPOSIT_MONEY, \n" +
            "\tNOTA_WITHDRAW_DEPOSIT_MONEY, \n" +
            "\tTRANSACTION_PASSWORD, \n" +
            "\tTRANSACTION_CERTIFICATE\n" +
            "FROM \n" +
            "\tXY_CWB_CAPITAL\n" +
            "WHERE\n" +
            "\tUSER_ID = #{userId,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String, Object> getCapitalInfoByUserId(String userId) throws SQLException;
}
