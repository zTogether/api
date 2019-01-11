package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.TUser;
import cn.xyzs.common.pojo.XyUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<TUser> {
    @SelectProvider(type = UserMapperProvider.class,method = "selectByCondition")
    List<Map<String,Object>> selectByCondition(Map<String, Object> map);
    class UserMapperProvider{
        public String selectByCondition(Map<String,Object> map){
            return new SQL(){{
                //SELECT  FROM
                SELECT("user_Id,user_code,user_Name,user_Tel,password,user_Bthd,user_Sex,is_Used");
                FROM("XY_USER");
                if (map.get("userName")!=null){
                    WHERE("user_Name like '%'||#{userName,jdbcType=VARCHAR}||'%'");
                }
                if(map.get("userCode")!=null&&!"".equals(map.get("userCode"))){
                    WHERE("user_code = #{userCode,jdbcType=VARCHAR}");
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
    @Select("<script>SELECT * FROM XY_USER_ROLE xyro,XY_ROLE xr WHERE xyro.ROLE_ID = xr.ROLE_ID AND xyro.USER_ID = #{userId,jdbcType=VARCHAR}</script>")
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
            "\t(SELECT XY_ROLE.ROLE_ID FROM XY_ROLE WHERE XY_ROLE.ROLE_ID = #{roleId,jdbcType=VARCHAR} ) xy,\n" +
            "\tXY_COMPO\n" +
            "WHERE \n" +
            "\txrf.ROLE_ID = xy.ROLE_ID\n" +
            "AND\n" +
            "\txrf.COMPO_ID = XY_COMPO.COMPO_ID" +
            "</script>")
    List<Map<String, Object>> getMenuByRole(String roleId) throws SQLException;

    //修改密码
    @Select("<script>UPDATE XY_USER SET PASSWORD=#{password,jdbcType=VARCHAR} WHERE USER_TEL=#{userTel,jdbcType=VARCHAR}</script>")
    void changePassword(@Param("userTel") String userTel, @Param("password") String password) throws SQLException;

    /**
     *
     * @Description: 修改个人资料
     * @author: GeWeiliang
     * @date: 2018\8\22 0022 9:46
     * @param: [userTel, userSex, userBthd, idCard, bankIdBc, bankIdIcbc, bankIdCmbc]
     * @return: int
     */
    @SelectProvider(type = updatePersonalInfo.class,method = "updatePersonalInfo")
     void changePersonalInfo(@Param("userCode") String userCode, @Param("userTel") String userTel, @Param("userSex") String userSex,
                             @Param("userBthd") String userBthd, @Param("idCard") String idCard,
                             @Param("bankIdBc") String bankIdBc, @Param("bankIdIcbc") String bankIdIcbc,
                             @Param("bankIdCmbc") String bankIdCmbc)throws SQLException;
    class updatePersonalInfo{
        public String updatePersonalInfo(@Param("userCode") String userCode,@Param("userTel") String userTel,@Param("userSex") String userSex,
                                         @Param("userBthd") String userBthd,@Param("idCard") String idCard,
                                         @Param("bankIdBc") String bankIdBc,@Param("bankIdIcbc") String bankIdIcbc,
                                         @Param("bankIdCmbc") String bankIdCmbc){
            return new SQL(){{
                UPDATE("XY_USER");
                if(userTel!=null&&userTel!=""){
                    SET("USER_TEL=#{userTel,jdbcType=VARCHAR}");
                }
                if (userSex!=null&&userSex!=""){
                    SET("USER_SEX=#{userSex,jdbcType=VARCHAR}");
                }
                if (userBthd!=null&&userBthd!=""){
                    SET("USER_BTHD=#{userBthd,jdbcType=VARCHAR}");
                }
                if (idCard!=null&&idCard!=""){
                    SET("ID_CARD=#{idCard,jdbcType=VARCHAR}");
                }
                if (bankIdBc!=null&&bankIdBc!=""){
                    SET("BANK_ID_BC=#{bankIdBc,jdbcType=VARCHAR}");
                }
                if (bankIdIcbc!=null&&bankIdIcbc!=""){
                    SET("BANK_ID_ICBC=#{bankIdIcbc,jdbcType=VARCHAR}");
                }
                if (bankIdCmbc!=null&&bankIdCmbc!=""){
                    SET("BANK_ID_CMBC=#{bankIdCmbc,jdbcType=VARCHAR}");
                }
                WHERE("USER_CODE=#{userCode,jdbcType=VARCHAR}");
            }}.toString();
        }
    }

    /**
     * 根据手机号获取员工信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 14:22
     * @param: [userTel]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>SELECT * FROM XY_USER WHERE USER_TEL=#{userTel,jdbcType=VARCHAR}</script>")
    Map<String,Object> getUserInfo(@Param("userTel") String userTel);


    /**
     *
     * @Description: 员工通讯录
     * @author: GeWeiliang
     * @date: 2018\11\21 0021 11:24
     * @param: [condition]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getPhoneBook.class,method = "getPhoneBook")
    List<Map<String,Object>> phoneBook(@Param("name") String name, @Param("role") String role, @Param("orgName") String orgName) throws SQLException;
    class getPhoneBook{
     public String getPhoneBook(@Param("name") String name,@Param("role") String role,@Param("orgName") String orgName){
            return new SQL(){{
                SELECT("DISTINCT A.USER_ID,A.USER_NAME,A.USER_CODE,A.USER_TEL,C.ROLE_NAME,D.ORG_CODE,E.ORG_NAME ");
                FROM("XY_USER A");
                LEFT_OUTER_JOIN("XY_USER_ROLE B ON A.USER_ID=B.USER_ID");
                LEFT_OUTER_JOIN("XY_ROLE C ON B.ROLE_ID=C.ROLE_ID");
                LEFT_OUTER_JOIN("XY_USER_ROLE_ORG D ON B.UR_ID=D.UR_ID ");
                LEFT_OUTER_JOIN("XY_ORG E ON D.ORG_CODE=E.ORG_CODE");
                WHERE("A.USER_CODE <> '000001' AND A.USER_CODE <> '000002' AND A.IS_USED='1'");
                if (name!=null&&name!=""){
                    WHERE("A.USER_NAME LIKE #{name}");
                }
                if(role!=null&&role!=""){
                    WHERE("C.ROLE_NAME = #{role}");
                }
                if (orgName!=null&&orgName!=""){
                    WHERE("E.ORG_NAME = #{orgName}");
                }
                ORDER_BY("D.ORG_CODE,A.USER_NAME");
            }}.toString();
        }
    }

    /**
     * 根据成员id获取成员电话
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/23 14:50
     * @param: [menberId]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tA.USER_TEL MENEBERTEL\n" +
            "FROM\n" +
            "\tXY_USER A\n" +
            "WHERE\n" +
            "\tA.USER_ID = #{menberId,jdbcType=VARCHAR}\n" +
            "UNION ALL\t\n" +
            "SELECT\n" +
            "\tB.GR_TEL MENEBERTEL\n" +
            "FROM\n" +
            "\tXY_GCB_GRXX B\n" +
            "WHERE\n" +
            "\tB.GR_ID = #{menberId,jdbcType=VARCHAR}" +
            "UNION ALL\t\n" +
            "SELECT\n" +
            "\tC.CTR_TEL MENEBERTEL\n" +
            "FROM\n" +
            "\tXY_CUSTOMER_INFO C\n" +
            "WHERE\n" +
            "\tC.CTR_CODE = #{menberId,jdbcType=VARCHAR}" +
            "</script>")
    public String getMemberTel(String menberId) throws SQLException;


    /**
     * 获取临时活动提醒成员表信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/24 11:06
     * @param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tNAME,\n" +
            "\tTEL,\n" +
            "\tSEND_STATUS,\n" +
            "\tTO_CHAR(SEND_DATE,'yyyy-MM-dd HH24:mi:ss') SEND_DATE\n" +
            "FROM\n" +
            "\tTEMP_SEND_MEMBER" +
            "</script>")
    public List<Map<String ,Object>> getTempMember() throws SQLException;

    @Update("<script>" +
            "UPDATE TEMP_SEND_MEMBER SET SEND_STATUS = #{sendStatu,jdbcType=VARCHAR},SEND_DATE = SYSDATE WHERE TEL = #{tel,jdbcType=VARCHAR}" +
            "</script>")
    public void  updateTemp(@Param("sendStatu") String sendStatu, @Param("tel") String tel) throws SQLException;

    /**
     * 判断员工是否可用
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/28 13:48
     * @param: [userId]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tIS_USED IS_USED\n" +
            "FROM\n" +
            "\tXY_USER \n" +
            "WHERE\n" +
            "\tUSER_ID = #{userId,jdbcType=VARCHAR}\n" +
            "UNION ALL\n" +
            "SELECT\n" +
            "\tGR_STATU IS_USED\n" +
            "FROM\n" +
            "\tXY_GCB_GRXX \n" +
            "WHERE\n" +
            "\tGR_ID = #{userId,jdbcType=VARCHAR} " +
            "</script>")
    public String userWhetherEnabled(String userId) throws SQLException;

    /**
     * 获取信息提供人
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 9:58
     * @param: [condition]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tA.USER_ID,\n" +
            "\tA.USER_TEL,\n" +
            "\tA.USER_SEX,\n" +
            "\tA.USER_NAME,\n" +
            "\tD.ORG_NAME\n" +
            "FROM\n" +
            "\tXY_USER A\n" +
            "\tLEFT JOIN XY_USER_ROLE B ON A.USER_ID = B.USER_ID\n" +
            "\tLEFT JOIN XY_USER_ROLE_ORG C ON B.UR_ID = C.UR_ID\n" +
            "\tLEFT JOIN XY_ORG D ON C.ORG_CODE = D.ORG_CODE \n" +
            "WHERE\n" +
            "\tUSER_TEL = #{condition,jdbcType=VARCHAR} \n" +
            "OR USER_NAME = #{condition,jdbcType=VARCHAR}\n" +
            "AND IS_USED = '1'" +
            "</script>")
    public List<Map<String ,Object>> getCustProvider(String condition) throws SQLException;

    /**
     * 根据角色id获取用户信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 9:34
     * @param: [roleId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tA.USER_ID,\n" +
            "\tA.USER_TEL,\n" +
            "\tA.USER_SEX,\n" +
            "\tA.USER_NAME,\n" +
            "\tD.ORG_NAME \n" +
            "FROM\n" +
            "\tXY_USER A\n" +
            "\tLEFT JOIN XY_USER_ROLE B ON A.USER_ID = B.USER_ID\n" +
            "\tLEFT JOIN XY_USER_ROLE_ORG C ON B.UR_ID = C.UR_ID\n" +
            "\tLEFT JOIN XY_ORG D ON C.ORG_CODE = D.ORG_CODE \n" +
            "WHERE\n" +
            "\tB.ROLE_ID = #{roleId,jdbcType=VARCHAR}" +
            "\tAND A.IS_USED = '1'" +
            "</script>")
    public List<Map<String ,Object>> getUserInfoByRoleId(String roleId) throws SQLException;

    /**
     * 根据custId获取某个旗舰店的设计师
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 12:42
     * @param: [custId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT DISTINCT\n" +
            "\tA.USER_ID,\n" +
            "\tA.USER_TEL,\n" +
            "\tA.USER_SEX,\n" +
            "\tA.USER_NAME,\n" +
            "\tD.ORG_NAME\n" +
            "FROM\n" +
            "\tXY_USER A\n" +
            "\tLEFT JOIN XY_USER_ROLE B ON A.USER_ID = B.USER_ID\n" +
            "\tLEFT JOIN XY_USER_ROLE_ORG C ON B.UR_ID = C.UR_ID\n" +
            "\tLEFT JOIN XY_ORG D ON C.ORG_CODE = D.ORG_CODE \n" +
            "WHERE\n" +
            "\tB.ROLE_ID = '90FFCAB4999A4A4E87BC1CC1125E952F'\n" +
            "AND\n" +
            "\tD.ORG_CODE = (\n" +
            "\t\tSELECT F.ORG_CODE \n" +
            "\t\tFROM XY_USER_ROLE E\n" +
            "\t\tLEFT JOIN XY_USER_ROLE_ORG F\n" +
            "\t\tON E.UR_ID = F.UR_ID\n" +
            "\t\tWHERE E.USER_ID = (SELECT JOIN_USERID FROM XY_CRM_CUST WHERE CUST_ID = #{custId,jdbcType=VARCHAR})\n" +
            "\t\tAND E.ROLE_ID = '0081BD4E4C9B46BCBD0BD0F23FC481EA'\n" +
            "\t)\n" +
            "AND IS_USED = '1'\n" +
            "ORDER BY A.USER_NAME" +
            "</script>")
    public List<Map<String ,Object>> getShopSjsByCustId(String custId) throws SQLException;

}
