package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyGcbGrxx;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyGcbGrxxMapper extends Mapper<XyGcbGrxx>{
    @Select("<script>SELECT * FROM XY_GCB_GRXX WHERE GR_TEL=#{grTel}</script>")
    public Map<String,Object> getGrInfo(String grTel)throws SQLException;
    /***
     *
     * @Description: 修改工人密码
     * @author: GeWeiliang
     * @date: 2018\9\8 0008 14:44
     * @param: [grTel, password]
     * @return: void
     */
    @Update("<script>UPDATE XY_GCB_GRXX SET PASSWORD=#{password} WHERE GR_TEL=#{grTel}</script>")
    public void changeGrPassword(@Param("grTel") String grTel, @Param("password") String password)throws SQLException;

    /***
     *
     * @Description: 修改工人信息
     * @author: GeWeiliang
     * @date: 2018\9\8 0008 15:44
     * @param: [name, idCard, grTel, grBankId, grAdd, grId]
     * @return: void
     */
    @UpdateProvider(type = updateGrInfo.class,method = "updateGrInfo")
    public void changeGrInfo(@Param("name") String name, @Param("idCard") String idCard, @Param("grTel") String grTel,
                             @Param("grBankId") String grBankId, @Param("grAdd") String grAdd,
                             @Param("grId") String grId)throws SQLException;
    class updateGrInfo{
        public String updateGrInfo(@Param("name") String name,@Param("idCard") String idCard,@Param("grTel") String grTel,
                                   @Param("grBankId") String grBankId,@Param("grAdd") String grAdd,@Param("grId") String grId){
            return new SQL(){{
                UPDATE("XY_GCB_GRXX");
                if(name!=null&&name!=""){
                    SET("GR_NAME=#{name,jdbcType=VARCHAR}");
                }
                if (idCard!=null&&idCard!=""){
                    SET("GR_IDCARD=#{idCard,jdbcType=VARCHAR}");
                }
                if (grTel!=null&&grTel!=""){
                    SET("GR_TEL=#{grTel,jdbcType=VARCHAR}");
                }
                if (grBankId!=null&&grBankId!=""){
                    SET("GR_BANKID=#{grBankId,jdbcType=VARCHAR}");
                }
                if(grAdd!=null&&grAdd!=""){
                    SET("GR_ADDR=#{grAdd,jdbcType=VARCHAR}");
                }
                WHERE("GR_ID=#{grId,jdbcType=VARCHAR}");
            }}.toString();
        }
    }

    /**
     * 根据grId修改工人等级
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:24
     * @param: [grId]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_GCB_GRXX\n" +
            "SET\n" +
            "\tGR_LEVEL = 0\n" +
            "WHERE\n" +
            "\tGR_ID = #{grId,jdbcType=VARCHAR}\t" +
            "</script>")
    public void updateGrabSingleLevel(String grId) throws SQLException;

    @Update("<script>" +
            "UPDATE XY_GCB_GRXX SET GR_PRIV = (CASE WHEN GR_PRIV =0 THEN 0 ELSE GR_PRIV-1 END)\n" +
            "\t\tWHERE GR_ID=#{grId,jdbcType=VARCHAR}" +
            "</script>")
    void updatePriv(@Param("grId") String grId)throws SQLException;

    @Select("<script>" +
            "SELECT GR_PRIV,GR_LEVEL,GR_LEVEL_VM FROM XY_GCB_GRXX WHERE GR_ID=#{grId,jdbcType=VARCHAR}" +
            "</script>")
    Map<String,Object> getMyPriv(@Param("grId") String grId)throws SQLException;

    /**
     *
     * @Description: 查询此工人的限制抢单时间
     * @author: GeWeiliang
     * @date: 2018\12\24 0024 14:18
     * @param: [grId]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT A.* FROM\n" +
            "(SELECT gg.LIMIT_DATE,gi.MARK,gi.ADD_DATE\n" +
            "FROM XY_GCB_GRXX gg\n" +
            "LEFT JOIN XY_GCB_GRXX_IMPOSE gi ON gi.GR_ID=gg.GR_ID\n" +
            "WHERE gg.GR_ID=#{grId,jdbcType=VARCHAR}\n" +
            "ORDER BY gi.ADD_DATE DESC) A\n" +
            "WHERE ROWNUM &lt; 2" +
            "</script>")
    Map<String,Object> getLimitDate(@Param("grId") String grId)throws SQLException;
}
