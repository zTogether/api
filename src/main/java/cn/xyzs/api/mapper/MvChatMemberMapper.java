package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.MvChatMember;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MvChatMemberMapper extends Mapper<MvChatMember> {

    /**
     * 添加聊天成员
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/18 14:50
     * @param: [mvChatMember]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO MV_CHAT_MEMBER\n" +
            "VALUES\n" +
            "\t(\n" +
            "\t#{groupId,jdbcType=VARCHAR},\n" +
            "\t#{userId,jdbcType=VARCHAR},\n" +
            "\t#{userRoleName,jdbcType=VARCHAR},\n" +
            "\tsysdate\n" +
            "\t)" +
            "</script>")
    public void addChatMember(MvChatMember mvChatMember) throws SQLException;


    /**
     * 根据条件查询群组人员
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/18 15:15
     * @param: [mvChatMember]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getChatMemberByCondition.class,method = "getChatMemberByCondition")
    public List<Map<String ,Object>> getChatMemberByCondition(MvChatMember mvChatMember)throws SQLException;
    class getChatMemberByCondition{
        public String getChatMemberByCondition(MvChatMember mvChatMember){
            return new SQL(){{
                SELECT("GROUP_ID," +
                        "USER_ID," +
                        "USER_ROLE_NAME," +
                        "TO_CHAR(ADD_TIME,'yyyy-MM-dd HH24:mi:ss') ADD_TIME," +
                        "");
                FROM("MV_CHAT_MEMBER");
                if (mvChatMember.getGroupId() != null && !"".equals(mvChatMember.getGroupId())){
                    WHERE("GROUP_ID=#{groupId,jdbcType=VARCHAR}");
                }
                if (mvChatMember.getUserId() != null && !"".equals(mvChatMember.getUserId())){
                    WHERE("USER_ID=#{userId,jdbcType=VARCHAR}");
                }
                if (mvChatMember.getUserRoleName() != null && !"".equals(mvChatMember.getUserRoleName())){
                    WHERE("USER_ROLE_NAME=#{userRoleName,jdbcType=VARCHAR}");
                }
                if (mvChatMember.getAddTime() != null && !"".equals(mvChatMember.getAddTime())){
                    WHERE("ADD_TIME=TO_DATE(#{addTime,jdbcType=VARCHAR},'yyyy-MM-dd HH24:mi:ss')");
                }
            }}.toString();
        }
    }

    /**
     * 根据GroupIdAndUserId可修改用户角色名称，加入时间
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/18 15:24
     * @param: [mvChatMember]
     * @return: void
     */
    @UpdateProvider(type = updateChatMemberByGroupIdAndUserId.class,method = "updateChatMemberByGroupIdAndUserId")
    public void updateChatMemberByGroupIdAndUserId(MvChatMember mvChatMember)throws SQLException;
    class updateChatMemberByGroupIdAndUserId{
        public String updateChatMemberByGroupIdAndUserId(MvChatMember mvChatMember){
            return new SQL(){{
                UPDATE("MV_CHAT_MEMBER");
                if (mvChatMember.getAddTime() != null && !"".equals(mvChatMember.getAddTime())){
                    SET("ADD_TIME = TO_DATE(#{addTime,jdbcType=VARCHAR},'yyyy-MM-dd HH24:mi:ss')");
                }
                if (mvChatMember.getUserRoleName() != null && !"".equals(mvChatMember.getUserRoleName())){
                    SET("USER_ROLE_NAME = #{userRoleName,jdbcType=VARCHAR}");
                }
            }}.toString();
        }
    }

    /**
     * 根据ctrCode获取聊天群组的信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/18 16:19
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tmcm.GROUP_ID,\n" +
            "  mcm.USER_ID,\n" +
            "  mcm.USER_ROLE_NAME,\n" +
            "  TO_CHAR(mcm.ADD_TIME,'yyyy-MM-dd HH24:mi:ss') ADD_TIME,\n" +
            "\t(\n" +
            "\t\tNVL(\n" +
            "\t\t\t(SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = mcm.USER_ID), \n" +
            "\t\t\tNVL(\n" +
            "\t\t\t\t(SELECT xgg.GR_NAME FROM XY_GCB_GRXX xgg WHERE xgg.GR_ID = mcm.USER_ID), \n" +
            "\t\t\t\t(SELECT xci.CTR_NAME FROM XY_CUSTOMER_INFO xci WHERE xci.CTR_CODE = #{ctrCode,jdbcType=VARCHAR})\n" +
            "\t\t\t)\t\n" +
            "\t\t)\n" +
            "\t) user_name\n" +
            "FROM\n" +
            "\tMV_CHAT_MEMBER mcm\n" +
            "WHERE mcm.GROUP_ID = (SELECT mcg.GROUP_ID FROM MV_CHAT_GROUP mcg WHERE mcg.CTR_CODE = #{ctrCode,jdbcType=VARCHAR})" +
            "</script>")
    public List<Map<String ,Object>> getChatMemberInfoLsitByCtrCode(@Param("ctrCode") String ctrCode) throws SQLException;



}
