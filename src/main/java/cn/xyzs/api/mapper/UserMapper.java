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
    @Select("SELECT * FROM XY_USER_ROLE xyro,XY_ROLE xr WHERE xyro.ROLE_ID = xr.ROLE_ID AND xyro.USER_ID = #{userId}")
    public List<Map<String,Object>> getUserRole(String userId) throws SQLException;

    /**
     * 根据角色id查询对应的菜单信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/16 14:10
     * @param: [roleId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("SELECT \n" +
            "\txrf.* ,\n" +
            "\tXY_COMPO.*\n" +
            "FROM \n" +
            "\tXY_ROLE_FUC xrf,\n" +
            "\t(SELECT XY_ROLE.ROLE_ID FROM XY_ROLE WHERE XY_ROLE.ROLE_ID = #{roleId} ) xy,\n" +
            "\tXY_COMPO\n" +
            "WHERE \n" +
            "\txrf.ROLE_ID = xy.ROLE_ID\n" +
            "AND\n" +
            "\txrf.COMPO_ID = XY_COMPO.COMPO_ID")
    public List<Map<String, Object>> getMenuByRole(String roleId) throws SQLException;

    //修改密码
    @Select("UPDATE XY_USER SET PASSWORD=#{password} WHERE USER_TEL=#{userTel}")
    public int changePassword(@Param("userTel") String userTel,@Param("password") String password);

    /**
     *
     * @Description: 修改个人资料
     * @author: GeWeiliang
     * @date: 2018\8\22 0022 9:46
     * @param: [userTel, userSex, userBthd, idCard, bankIdBc, bankIdIcbc, bankIdCmbc]
     * @return: int
     */
    @Select("UPDATE XY_USER SET USER_TEL=#{userTel} AND  USER_SEX=#{userSex} AND \n"+
            "\tUSER_BTHD=#{userBthd} AND ID_CARD=#{idCard} AND BANK_ID_BC=#{bankIdBc} \n"+
            "\tBANK_ID_ICBC=#{bankIdIcbc} AND BANK_ID_CMBC=#{bankIdCmbc}")
    public int changePersonalInfo(@Param("userTel") String userTel,@Param("userSex") String userSex,
                                  @Param("userBthd") String userBthd,@Param("idCard") String idCard,
                                  @Param("bankIdBc") String bankIdBc,@Param("bankIdIcbc") String bankIdIcbc,
                                  @Param("bankIdCmbc") String bankIdCmbc);

    @Select("SELECT * FROM XY_USER WHERE USER_TEL=#{userTel}")
    public Map<String,Object> getUserInfo(@Param("userTel") String userTel);


}
