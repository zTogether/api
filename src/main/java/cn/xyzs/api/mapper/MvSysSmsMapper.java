package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.MvSysSms;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;

public interface MvSysSmsMapper extends Mapper<MvSysSms> {

    /**
     * 添加发送信息记录表信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/14 15:13
     * @param: [mvSysSms]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO MV_SYS_SMS(\n" +
            "\tSMS_ID,\n" +
            "\tTEL,\n" +
            "\tVERIFICATION_CODE,\n" +
            "\tADD_TIME,\n" +
            "\tSMS_CONTENT,\n" +
            "\tSMS_SENDER,\n" +
            "\tSEND_STATUS\n" +
            ") VALUES(\n" +
            "\tsys_guid(),\n" +
            "\t#{tel,jdbcType=VARCHAR},\n" +
            "\t#{verificationCode,jdbcType=VARCHAR},\n" +
            "\tSYSDATE,\n" +
            "\t#{smsContent,jdbcType=VARCHAR},\n" +
            "\t#{smsSender,jdbcType=VARCHAR},\n" +
            "\t#{sendStatus,jdbcType=VARCHAR}\n" +
            ")" +
            "</script>")
    public void addMvSysSmsInfo(MvSysSms mvSysSms) throws SQLException;

    /**
     * 校验验证码
     * @Description: 参数：tel,   verificationCode
     * @author: zheng shuai
     * @date: 2018/9/14 15:21
     * @param: [mvSysSms]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCOUNT( 1 ) \n" +
            "FROM\n" +
            "\tMV_SYS_SMS mss \n" +
            "WHERE\n" +
            "\tmss.TEL = #{tel,jdbcType=VARCHAR} \n" +
            "AND \n" +
            "\tmss.VERIFICATION_CODE = #{verificationCode,jdbcType=VARCHAR} \n" +
            "AND\n" +
            "\tmss.SEND_STATUS = '200' \n" +
            "</script>")
    public Integer checkVerificationCode(MvSysSms mvSysSms) throws SQLException;

    /**
     * 校验是否超时（时效两分钟）
     * @Description: 参数：tel,   verificationCode
     * @author: zheng shuai
     * @date: 2018/9/14 15:21
     * @param: [mvSysSms]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCOUNT( 1 ) \n" +
            "FROM\n" +
            "\tMV_SYS_SMS mss \n" +
            "WHERE\n" +
            "\tmss.TEL = #{tel,jdbcType=VARCHAR} \n" +
            "AND \n" +
            "\tmss.VERIFICATION_CODE = #{verificationCode,jdbcType=VARCHAR} \n" +
            "AND\n" +
            "\tmss.SEND_STATUS = '200' \n" +
            "AND\n" +
            "\tmss.ADD_TIME+2/(24*60) >= SYSDATE " +
            "</script>")
    public Integer checkTimeout(MvSysSms mvSysSms) throws SQLException;
}
