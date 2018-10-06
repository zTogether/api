package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.MvChattingRecords;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MvChattingRecordsMapper extends Mapper<MvChattingRecords>{

    /**
     * 添加聊天记录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 16:49
     * @param: [ctrCode, userId, chatingContent, contentType]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO MV_CHATING_RECORDS ( CTR_CODE, USER_ID, SEND_DATE, CHATING_CONTENT, CONTENT_TYPE )\n" +
            "VALUES (\n" +
            "\t\t#{ctrCode},\n" +
            "\t\t#{userId},\n" +
            "\t\tTO_DATE(#{sendDate}, 'yyyy-MM-dd HH24:mi:ss') ,\n" +
            "\t\t#{chatingContent},\n" +
            "\t\t#{contentType}\n" +
            ")" +
            "</script>")
    public void addChattingRecords (@Param("ctrCode") String ctrCode , @Param("userId") String userId , @Param("sendDate") String sendDate , @Param("chatingContent") String chatingContent , @Param("contentType") String contentType) throws SQLException;

    /**
     * 获取离线消息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 17:00
     * @param: [lastSendDate]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tmcr.CTR_CODE, \n" +
            "\tmcr.USER_ID, \n" +
            "\tTO_CHAR(mcr.SEND_DATE,'yyyy-MM-dd HH24:mi:ss') SEND_DATE, \n" +
            "\tmcr.CHATING_CONTENT, \n" +
            "\tmcr.CONTENT_TYPE \n" +
            "FROM\n" +
            "\tMV_CHATING_RECORDS mcr \n" +
            "WHERE\n" +
            "\tmcr.SEND_DATE > (\n" +
            "\t\tSELECT B.SEND_DATE  FROM ( SELECT A.*, ROWNUM RN \n" +
            "\t\tFROM ( \n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\tmcr.SEND_DATE\n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tMV_CHATING_RECORDS mcr \n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\tmcr.USER_ID = #{userId ,jdbcType=VARCHAR}\n" +
            "\t\t\tAND\n" +
            "\t\t\t\tmcr.CTR_CODE = #{ctrCode ,jdbcType=VARCHAR}\n" +
            "\t\t\tORDER BY mcr.SEND_DATE DESC\n" +
            "\t\t\t) A  \n" +
            "\t\t)B\n" +
            "\t\tWHERE RN BETWEEN 1 AND 1\n" +
            "\t)\n" +
            "AND\n" +
            "\tmcr.CTR_CODE = #{ctrCode ,jdbcType=VARCHAR} " +
            "AND " +
            "mcr.SEND_DATE NOT IN " +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> " +
            "TO_DATE(#{item}, 'yyyy-MM-dd HH24:mi:ss') " +
            "</foreach>" +
            "ORDER BY mcr.SEND_DATE " +

            "</script>")
    public List<Map<String ,Object>> getOfflineMessage (@Param("userId") String userId ,@Param("ctrCode") String ctrCode, @Param("list") List<String> sendDateList) throws SQLException;

    /**
     * 根据时间节点查询离线消息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/5 17:41
     * @param: [ctrCode, nodeDate]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tmcr.CTR_CODE, \n" +
            "\tmcr.USER_ID, \n" +
            "\tTO_CHAR(mcr.SEND_DATE,'yyyy-MM-dd HH24:mi:ss') SEND_DATE, \n" +
            "\tmcr.CHATING_CONTENT, \n" +
            "\tmcr.CONTENT_TYPE \n" +
            "FROM\n" +
            "\tMV_CHATING_RECORDS mcr \n" +
            "WHERE\n" +
            "\tmcr.SEND_DATE > TO_DATE(#{nodeDate,jdbcType=VARCHAR},'yyyy-MM-dd HH24:mi:ss')\n" +
            "AND\n" +
            "\tmcr.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getOfflineMessageByDateNode (@Param("ctrCode") String ctrCode , @Param("nodeDate") String nodeDate ) throws SQLException;

    /**
     * 不根据时间节点查询离线消息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/5 17:42
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tmcr.CTR_CODE, \n" +
            "\tmcr.USER_ID, \n" +
            "\tTO_CHAR(mcr.SEND_DATE,'yyyy-MM-dd HH24:mi:ss') SEND_DATE, \n" +
            "\tmcr.CHATING_CONTENT, \n" +
            "\tmcr.CONTENT_TYPE \n" +
            "FROM\n" +
            "\tMV_CHATING_RECORDS mcr \n" +
            "WHERE\n" +
            "\tmcr.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getOfflineMessageNotByDateNode (@Param("ctrCode") String ctrCode) throws SQLException;

}
