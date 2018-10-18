package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.MvChatGroup;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MvChatGroupMapper extends Mapper<MvChatGroup> {
    /***
     *
     * @Description: 根据档案号创建群组
     * @author: GeWeiliang
     * @date: 2018\10\18 0018 14:57
     * @param: [ctrCode]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO MV_CHAT_GROUP VALUES(sys_guid(),#{ctrCode},SYSDATE,0)" +
            "</script>")
    public void addChatGroup(String ctrCode) throws SQLException;


    /***
     *
     * @Description: 根据群组ID动态修改
     * @author: GeWeiliang
     * @date: 2018\10\18 0018 15:58
     * @param: [ctrCode, groupId, creationTimr, isOccupied]
     * @return: void
     */
    @UpdateProvider(type = updateChatGroup.class,method = "updateChatGroup")
    public void updateChatGroup(@Param("ctrCode") String ctrCode, @Param("groupId") String groupId,
                                @Param("ctrCode") String creationTimr, @Param("isOccupied") String isOccupied) throws SQLException;
    class updateChatGroup{
        public String updateChatGroup(@Param("ctrCode") String ctrCode, @Param("groupId") String groupId,
                                      @Param("ctrCode") String creationTime, @Param("isOccupied") String isOccupied){
            return new SQL(){{
                UPDATE("MV_CHAT_GROUP");
                SET("GROUP_ID=#{groupId,jdbctype=VARCHAR}");
                if (ctrCode!=null && !"".equals(ctrCode)){
                    SET("CTR_CODE=#{ctrCode,jdbcType=VARCHAR}");
                }
                if (creationTime!=null && !"".equals(creationTime)){
                    SET("CREATION_TIME=#{creationTime,jdbcType=VARCHAR}");
                }
                if (isOccupied!=null && !"".equals(isOccupied)){
                    SET("IS_OCCUPIED=#{isOccupied,jdbcType=VARCHAR}");
                }
            }}.toString();
        }
    }

    /**
     *
     * @Description: 对群组进行动态查询
     * @author: GeWeiliang
     * @date: 2018\10\18 0018 15:58
     * @param: [chatGroup]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = queryChatGroup.class,method = "queryChatGroup")
    public List<Map<String,Object>> queryChatGroup(MvChatGroup chatGroup) throws SQLException;
    class queryChatGroup{
        public String queryChatGroup(MvChatGroup chatGroup){
            return new SQL(){{
                SELECT("GROUP_ID,CTR_CODE,TO_CHAR(CREATION_TIME,'yyyy-MM-dd HH24:mi:ss') CREATION_TIME,IS_OCCUPIED");
                FROM("MV_CHAT_GROUP");
                if (chatGroup.getGroupId()!=null && !"".equals(chatGroup.getGroupId())){
                    WHERE("GROUP_ID=#{groupId,jdbcType=VARCHAR}");
                }
                if (chatGroup.getCtrCode()!=null && !"".equals(chatGroup.getCtrCode())){
                    WHERE("CTR_CODE=#{ctrCode,jdbcType=VARCHAR}");
                }
                if (chatGroup.getCreationTime()!=null && !"".equals(chatGroup.getCreationTime())){
                    WHERE("CREATION_TIME=TO_DATE(#{creationTime,jdbcType=VARCHAR},'yyyy-MM-dd HH24:mi:ss')");
                }
                if (chatGroup.getIsOccupied()!=null && !"".equals(chatGroup.getIsOccupied())){
                    WHERE("IS_OCCUPIED=#{isOccupied,jdbcType=VARCHAR}");
                }
            }}.toString();
        }
    }


}
