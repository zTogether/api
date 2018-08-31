package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.TUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<TUser> {
    @SelectProvider(type = UserMapperProvider.class,method = "selectByCondition")
    List<Map<String,Object>> selectByCondition(Map<String,Object> map);
    class UserMapperProvider{
        public String selectByCondition(Map<String,Object> map){
            return new SQL(){{
                //SELECT  FROM
                SELECT("user_Id,user_code,user_Name,user_Tel,password,user_Bthd,user_Sex,is_Used");
                FROM("XY_USER");
                if (map.get("userName")!=null){
                    WHERE("user_Name like '%'||#{userName}||'%'");
                }
                if(map.get("userCode")!=null&&!"".equals(map.get("userCode"))){
                    WHERE("user_code = #{userCode}");
                }
            }}.toString();
        }
    }

    /**
     * 根据userid获取用户身份
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/10 17:10
     * @param: [userId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>SELECT * FROM XY_USER_ROLE xyro,XY_ROLE xr WHERE xyro.ROLE_ID = xr.ROLE_ID AND xyro.USER_ID = #{userId}</script>")
     List<Map<String,Object>> getUserRole(String userId) throws SQLException;

    /**
     * 根据角色id查询对应的菜单信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/16 14:10
     * @param: [roleId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\txrf.* ,\n" +
            "\tXY_COMPO.*\n" +
            "FROM \n" +
            "\tXY_ROLE_FUC xrf,\n" +
            "\t(SELECT XY_ROLE.ROLE_ID FROM XY_ROLE WHERE XY_ROLE.ROLE_ID = #{roleId} ) xy,\n" +
            "\tXY_COMPO\n" +
            "WHERE \n" +
            "\txrf.ROLE_ID = xy.ROLE_ID\n" +
            "AND\n" +
            "\txrf.COMPO_ID = XY_COMPO.COMPO_ID" +
            "</script>")
    List<Map<String, Object>> getMenuByRole(String roleId) throws SQLException;

    //修改密码
    @Select("<script>UPDATE XY_USER SET PASSWORD=#{password} WHERE USER_TEL=#{userTel}</script>")
    void changePassword(@Param("userTel") String userTel,@Param("password") String password) throws SQLException;

    /**
     *
     * @Description: 修改个人资料
     * @author: GeWeiliang
     * @date: 2018\8\22 0022 9:46
     * @param: [userTel, userSex, userBthd, idCard, bankIdBc, bankIdIcbc, bankIdCmbc]
     * @return: int
     */
    @SelectProvider(type = updatePersonalInfo.class,method = "updatePersonalInfo")
     void changePersonalInfo(@Param("userCode") String userCode,@Param("userTel") String userTel,@Param("userSex") String userSex,
                                  @Param("userBthd") String userBthd,@Param("idCard") String idCard,
                                  @Param("bankIdBc") String bankIdBc,@Param("bankIdIcbc") String bankIdIcbc,
                                  @Param("bankIdCmbc") String bankIdCmbc)throws SQLException;
    class updatePersonalInfo{
        public String updatePersonalInfo(@Param("userCode") String userCode,@Param("userTel") String userTel,@Param("userSex") String userSex,
                                         @Param("userBthd") String userBthd,@Param("idCard") String idCard,
                                         @Param("bankIdBc") String bankIdBc,@Param("bankIdIcbc") String bankIdIcbc,
                                         @Param("bankIdCmbc") String bankIdCmbc){
            return new SQL(){{
                UPDATE("XY_USER");
                if(userTel!=null){
                    SET("USER_TEL=#{userTel}");
                }
                if (userSex!=null){
                    SET("USER_SEX=#{userSex}");
                }
                if (userBthd!=null){
                    SET("USER_BTHD=#{userBthd}");
                }
                if (idCard!=null){
                    SET("ID_CARD=#{idCard}");
                }
                if (bankIdBc!=null){
                    SET("BANK_ID_BC=#{bankIdBc}");
                }
                if (bankIdIcbc!=null){
                    SET("BANK_ID_ICBC=#{bankIdIcbc}");
                }
                if (bankIdCmbc!=null){
                    SET("BANK_ID_CMBC=#{bankIdCmbc}");
                }
                WHERE("USER_CODE=#{userCode}");
            }}.toString();
        }
    }

    @Select("<script>SELECT * FROM XY_USER WHERE USER_TEL=#{userTel}</script>")
    Map<String,Object> getUserInfo(@Param("userTel") String userTel);

}
