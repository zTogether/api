package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyCustomerInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface XyCustomerInfoMapper extends Mapper<XyCustomerInfo> {

    /**
     * 根据手机号码判断是否存在此客户
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/9 15:16
     * @param: [ctrTel]
     * @return: java.lang.Integer
     */
    @Select("<script>SELECT COUNT(1) FROM XY_CUSTOMER_INFO xci WHERE xci.CTR_TEL = #{ctrTel,jdbcType=VARCHAR}</script>")
    public Integer isCustomer(String ctrTel) throws SQLException;

    /**
     * 根据客户电话查询客户工程信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/11 15:49
     * @param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script> " +
            "SELECT " +
                "xci.CTR_CODE ctrCode," +
                "xci.CTR_CRT_DATE ctrCrtDate," +
                "xci.CTR_KG_DATE ctrKgDate," +
                "xci.CTR_ADDR ctrAddr," +
                "xci.CTR_AREA ctrArea," +
                "xci.CTR_STRUCTURE ctrStructure," +
                "xci.CTR_FLOOR ctrFloor," +
                "xci.CTR_LIFT ctrLift," +
                "xci.CTR_PRJ_TYPE ctrPrjType," +
                "xci.CTR_QT_RANGE ctrQtQange," +
                "(SELECT USER_NAME FROM XY_USER WHERE USER_ID = " +
                    "(SELECT CTR_SJS FROM XY_CUSTOMER_INFO WHERE CTR_CODE = xci.CTR_CODE)" +
                ") ctrSjs," +
                "(SELECT USER_NAME FROM XY_USER WHERE USER_ID = " +
                    "(SELECT CTR_GCJL FROM XY_CUSTOMER_INFO WHERE CTR_CODE = xci.CTR_CODE)" +
                ") ctrGcjl," +
                "xo.ORG_NAME orgName " +
            " FROM " +
                "XY_CUSTOMER_INFO xci," +
                "XY_ORG xo" +
            " WHERE " +
                "xci.CTR_ORG = xo.ORG_CODE" +
            " AND " +
                "xci.CTR_TEL = #{ctrTel,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String, Object>> getCustomerEngineeringInfo(String ctrTel) throws SQLException;

    /**
     * 用户角色为E类型是查询所拥有的客户
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/24 16:06
     * @param: [userId, startNum, endNum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tA.*,\n" +
            "\t\tROWNUM RN \n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tT.CTR_CODE,\n" +
            "\t\t\tT.CTR_NAME,\n" +
            "\t\t\tT.CTR_TEL,\n" +
            "\t\t\tT.CTR_ADDR,\n" +
            "\t\t\tT.CTR_CRT_DATE,\n" +
            "\t\t\tT.CTR_CARDID,\n" +
            "\t\t\tT.CTR_AREA,\n" +
            "\t\t\tU1.USER_NAME JDRY_NAME,\n" +
            "\t\t\tU2.USER_NAME SJS_NAME,\n" +
            "\t\t\tU3.USER_NAME GCJL_NAME,\n" +
            "\t\t\tU4.USER_NAME CLDD_NAME,\n" +
            "\t\t\to1.org_name FWJG,\n" +
            "\t\t\to2.org_name SGJG \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CUSTOMER_INFO T\n" +
            "\t\t\tLEFT JOIN XY_USER U1 ON T.CTR_WAITER = U1.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U2 ON T.Ctr_Sjs = U2.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U3 ON T.Ctr_Gcjl = U3.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U4 ON T.Ctr_Cldd = U4.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_ORG O1 ON T.CTR_ORG = O1.ORG_CODE\n" +
            "\t\t\tLEFT JOIN XY_ORG O2 ON T.Ctr_Pro_Org = O2.ORG_CODE \n" +
            "\t\tWHERE\n" +
            "\t\t\tT.CTR_WAITER = #{userId,jdbcType=VARCHAR}\n" +
            "\t\t\tOR T.CTR_SJS = #{userId,jdbcType=VARCHAR}\n" +
            "\t\t\tOR T.CTR_GCJL = #{userId,jdbcType=VARCHAR}\n" +
            "\t\t\tOR T.CTR_CLDD = #{userId,jdbcType=VARCHAR}\n" +
            "\t\t) A \n" +
            "\t) \n" +
            "WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> getCustomerInfoByRoleTypeE(@Param("userId") String userId, @Param("startNum") String startNum, @Param("endNum") String endNum) throws SQLException;

    /**
     * 用户角色为R类型是查询所拥有的客户
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/25 16:41
     * @param: [userId, startNum, endNum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tA.*,\n" +
            "\t\tROWNUM RN \n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tT.CTR_CODE,\n" +
            "\t\t\tT.CTR_NAME,\n" +
            "\t\t\tT.CTR_TEL,\n" +
            "\t\t\tT.CTR_ADDR,\n" +
            "\t\t\tT.CTR_CRT_DATE,\n" +
            "\t\t\tT.CTR_CARDID,\n" +
            "\t\t\tT.CTR_AREA,\n" +
            "\t\t\tU1.USER_NAME JDRY_NAME,\n" +
            "\t\t\tU2.USER_NAME SJS_NAME,\n" +
            "\t\t\tU3.USER_NAME GCJL_NAME,\n" +
            "\t\t\tU4.USER_NAME CLDD_NAME,\n" +
            "\t\t\to1.org_name FWJG,\n" +
            "\t\t\to2.org_name SGJG \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CUSTOMER_INFO T\n" +
            "\t\t\tLEFT JOIN XY_USER U1 ON T.CTR_WAITER = U1.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U2 ON T.Ctr_Sjs = U2.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U3 ON T.Ctr_Gcjl = U3.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U4 ON T.Ctr_Cldd = U4.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_ORG O1 ON T.CTR_ORG = O1.ORG_CODE\n" +
            "\t\t\tLEFT JOIN XY_ORG O2 ON T.Ctr_Pro_Org = O2.ORG_CODE \n" +
            "\t\tWHERE\n" +
            "\t\t\t(\n" +
            "\t\t\t\tNOT EXISTS ( SELECT 1 FROM XY_USER_RELATION C WHERE C.LEADER_ID = #{userId,jdbcType=VARCHAR} ) \n" +
            "\t\t\t\tAND (\n" +
            "\t\t\t\t\tEXISTS (\n" +
            "\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\t1 \n" +
            "\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\tXY_USER_ROLE_ORG A,\n" +
            "\t\t\t\t\t\tXY_USER_ROLE B \n" +
            "\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\tA.UR_ID = B.UR_ID \n" +
            "\t\t\t\t\t\tAND B.USER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\t\tAND B.ROLE_ID = #{roleId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\t\tAND T.CTR_ORG LIKE A.ORG_CODE || '%' \n" +
            "\t\t\t\t\t) \n" +
            "\t\t\t\t\tOR EXISTS (\n" +
            "\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\t1 \n" +
            "\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\tXY_USER_ROLE_ORG A,\n" +
            "\t\t\t\t\t\tXY_USER_ROLE B \n" +
            "\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\tA.UR_ID = B.UR_ID \n" +
            "\t\t\t\t\t\tAND B.USER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\t\tAND B.ROLE_ID = #{roleId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\t\tAND T.CTR_PRO_ORG LIKE A.ORG_CODE || '%' \n" +
            "\t\t\t\t\t) \n" +
            "\t\t\t\t) \n" +
            "\t\t\t) \n" +
            "\t\t\tOR EXISTS (\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\t1 \n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tXY_USER_RELATION D \n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\tD.LEADER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\t\tAND (\n" +
            "\t\t\t\t\tD.FOLLOWER_ID = T.CTR_WAITER \n" +
            "\t\t\t\t\tOR D.FOLLOWER_ID = T.CTR_SJS \n" +
            "\t\t\t\t\tOR D.FOLLOWER_ID = T.CTR_GCJL \n" +
            "\t\t\t\t\tOR D.FOLLOWER_ID = T.CTR_CLDD \n" +
            "\t\t\t\t) \n" +
            "\t\t\t) \n" +
            "\t\t) A \n" +
            "\t) \n" +
            "WHERE\n" +
            "\tRN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> getCustomerInfoByRoleTypeR(@Param("userId") String userId, @Param("roleId") String roleId, @Param("startNum") String startNum, @Param("endNum") String endNum) throws SQLException;

     /**
     * @Description: 根据客户号获取客户信息
     * @author: GeWeiliang
     * @date: 2018\8\23 0023 11:13
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT " +
            "xci.CTR_CODE ctrCode,xci.CTR_CRT_DATE ctrCrtDate,\"NVL\"(xci.CTR_NAME,'-') ctrName," +
            "xci.CTR_TEL ctrTel,\"NVL\"(xci.CTR_CARDID,'-') ctr_cardid,\"NVL\"(xci.CTR_ADDR,'-') ctrAddr," +
            "\"NVL\"(xci.CTR_AREA,-1) ctrArea,\"NVL\"(xci.CTR_STRUCTURE,'-') ctrStructure,\"NVL\"(xci.CTR_FLOOR,-1) ctrFloor," +
            "\"NVL\"(xci.CTR_LIFT,'-') ctrLift,\"NVL\"(xci.CTR_PRJ_TYPE,'-') ctrPrjType,\"NVL\"(xci.CTR_QT_RANGE,'-') ctrQtRange," +
            "\"NVL\"(xci.CTR_QT_TYPE,'-') ctrQtType," +
            "\"NVL\"((SELECT xgrv.RG_VER_NAME FROM XY_GCB_RG_VER xgrv WHERE xgrv.RG_VER_CODE = xci.RG_VER_CODE),'-') rgVerCode," +
            "\"NVL\"((SELECT xu.USER_NAME FROM XY_CUSTOMER_INFO xci,XY_USER xu WHERE xci.CTR_WAITER=xu.USER_ID AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}), '-') ctrWaiter," +
            "\"NVL\"((SELECT xu.USER_TEL FROM XY_CUSTOMER_INFO xci,XY_USER xu WHERE xci.CTR_WAITER=xu.USER_ID AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}), '-') WaiterTel," +
            "\"NVL\"((SELECT xo.ORG_NAME FROM XY_ORG xo,XY_CUSTOMER_INFO xci WHERE xci.CTR_ORG=xo.ORG_CODE AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}),'-') ctrOrg," +
            "\"NVL\"((SELECT xu.USER_NAME FROM XY_CUSTOMER_INFO xci,XY_USER xu WHERE xci.CTR_SJS=xu.USER_ID AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}),'-') ctrSjs," +
            "\"NVL\"((SELECT xu.USER_TEL FROM XY_CUSTOMER_INFO xci,XY_USER xu WHERE xci.CTR_SJS=xu.USER_ID AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}),'-') SjsTel," +
            "\"NVL\"((SELECT xu.USER_NAME FROM XY_CUSTOMER_INFO xci,XY_USER xu WHERE xci.CTR_GCJL=xu.USER_ID AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}),'-') CtrGcjl," +
            "\"NVL\"((SELECT xu.USER_TEL FROM XY_CUSTOMER_INFO xci,XY_USER xu WHERE xci.CTR_GCJL=xu.USER_ID AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}),'-') GcjlTel," +
            "\"NVL\"((SELECT xu.USER_NAME FROM XY_CUSTOMER_INFO xci,XY_USER xu WHERE xci.CTR_CLDD=xu.USER_ID AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}),'-') CtrCldd," +
            "\"NVL\"((SELECT xu.USER_TEL FROM XY_CUSTOMER_INFO xci,XY_USER xu WHERE xci.CTR_CLDD=xu.USER_ID AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}),'-') ClddTel," +
            "\"NVL\"(xci.CTR_ROWID,'-') ctrRowid," +
            "\"NVL\"((SELECT xo.ORG_NAME FROM XY_ORG xo,XY_CUSTOMER_INFO xci WHERE xci.CTR_PRO_ORG=xo.ORG_CODE AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}),'-') ctrProOrg," +
            "xci.CTR_KG_DATE ctrKgDate," +
            "\"NVL\"(xci.CTR_STATU,'-') ctrStatu,\"NVL\"(xci.CTR_DRAW,'-') ctrDraw,\"NVL\"(xci.DRAW_STATU,'-') drawStatu," +
            "\"NVL\"(xci.CTR_X,'-') ctrX," +
            "\"NVL\"(xci.CTR_Y,'-') ctrY," +
            "\"NVL\"(xci.CTR_MAP_VERSION,'-') ctrMapVersion," +
            "\"NVL\"((SELECT xu.USER_TEL FROM XY_USER xu,XY_CUSTOMER_INFO xci WHERE xci.CTR_AREA_MA=xu.USER_ID AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}),'-') CTRAREAMATEL," +
            "\"NVL\"((SELECT xu.USER_NAME FROM XY_USER xu,XY_CUSTOMER_INFO xci WHERE xci.CTR_AREA_MA=xu.USER_ID AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}),'-') CTRAREAMA," +
            "\"NVL\"((SELECT xu.USER_NAME FROM XY_USER xu,XY_CUSTOMER_INFO xci WHERE xci.CTR_OWENER=xu.USER_ID AND xci.CTR_CODE=#{ctrCode,jdbcType=VARCHAR}),'-') ctrOwener" +
            " FROM XY_CUSTOMER_INFO xci WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
//    @Select("SELECT * FROM XY_CUSTOMER_INFO WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR}")
    public Map<String,Object> getCustInfoByCtrCode(@Param("ctrCode") String ctrCode) throws SQLException;

    /**
     * E根据客户档案号/手机号/姓名查询E类型员工所拥有得到客户
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/25 16:46
     * @param: [userId, condition]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM (\n" +
            "\tSELECT\n" +
            "\t\tT.CTR_CODE,\n" +
            "\t\tT.CTR_NAME,\n" +
            "\t\tT.CTR_TEL,\n" +
            "\t\tT.CTR_ADDR,\n" +
            "\t\tT.CTR_CRT_DATE,\n" +
            "\t\tT.CTR_CARDID,\n" +
            "\t\tT.CTR_AREA,\n" +
            "\t\tU1.USER_NAME JDRY_NAME,\n" +
            "\t\tU2.USER_NAME SJS_NAME,\n" +
            "\t\tU3.USER_NAME GCJL_NAME,\n" +
            "\t\tU4.USER_NAME CLDD_NAME,\n" +
            "\t\to1.org_name FWJG,\n" +
            "\t\to2.org_name SGJG \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO T\n" +
            "\t\tLEFT JOIN XY_USER U1 ON T.CTR_WAITER = U1.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U2 ON T.Ctr_Sjs = U2.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U3 ON T.Ctr_Gcjl = U3.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U4 ON T.Ctr_Cldd = U4.USER_ID\n" +
            "\t\tLEFT JOIN XY_ORG O1 ON T.CTR_ORG = O1.ORG_CODE\n" +
            "\t\tLEFT JOIN XY_ORG O2 ON T.Ctr_Pro_Org = O2.ORG_CODE \n" +
            "\tWHERE\n" +
            "\t\tT.CTR_WAITER = #{userId,jdbcType=VARCHAR} \n" +
            "\t\tOR T.CTR_SJS = #{userId,jdbcType=VARCHAR} \n" +
            "\t\tOR T.CTR_GCJL = #{userId,jdbcType=VARCHAR} \n" +
            "\t\tOR T.CTR_CLDD = #{userId,jdbcType=VARCHAR} \n" +
            ") A\n" +
            "WHERE A.CTR_CODE = #{condition,jdbcType=VARCHAR}\n" +
            "OR A.CTR_NAME = #{condition,jdbcType=VARCHAR}\n" +
            "OR A.CTR_TEL = #{condition,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> getECuntomerInfoByCondition(@Param("userId") String userId, @Param("condition") String condition) throws SQLException;

    /**
     * 根据条件获取R类型下拥有的客户
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/1 13:13
     * @param: [userId, condition, roleId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tT.CTR_CODE,\n" +
            "\t\tT.CTR_NAME,\n" +
            "\t\tT.CTR_TEL,\n" +
            "\t\tT.CTR_ADDR,\n" +
            "\t\tT.CTR_CRT_DATE,\n" +
            "\t\tT.CTR_CARDID,\n" +
            "\t\tT.CTR_AREA,\n" +
            "\t\tU1.USER_NAME JDRY_NAME,\n" +
            "\t\tU2.USER_NAME SJS_NAME,\n" +
            "\t\tU3.USER_NAME GCJL_NAME,\n" +
            "\t\tU4.USER_NAME CLDD_NAME,\n" +
            "\t\to1.org_name FWJG,\n" +
            "\t\to2.org_name SGJG \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO T\n" +
            "\t\tLEFT JOIN XY_USER U1 ON T.CTR_WAITER = U1.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U2 ON T.Ctr_Sjs = U2.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U3 ON T.Ctr_Gcjl = U3.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U4 ON T.Ctr_Cldd = U4.USER_ID\n" +
            "\t\tLEFT JOIN XY_ORG O1 ON T.CTR_ORG = O1.ORG_CODE\n" +
            "\t\tLEFT JOIN XY_ORG O2 ON T.Ctr_Pro_Org = O2.ORG_CODE \n" +
            "\tWHERE\n" +
            "\t\t(\n" +
            "\t\t\tNOT EXISTS ( SELECT 1 FROM XY_USER_RELATION C WHERE C.LEADER_ID = #{userId,jdbcType=VARCHAR} ) \n" +
            "\t\t\tAND (\n" +
            "\t\t\t\tEXISTS (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\t1 \n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tXY_USER_ROLE_ORG A,\n" +
            "\t\t\t\t\tXY_USER_ROLE B \n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tA.UR_ID = B.UR_ID \n" +
            "\t\t\t\t\tAND B.USER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND B.ROLE_ID = #{roleId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND T.CTR_ORG LIKE A.ORG_CODE || '%' \n" +
            "\t\t\t\t) \n" +
            "\t\t\t\tOR EXISTS (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\t1 \n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tXY_USER_ROLE_ORG A,\n" +
            "\t\t\t\t\tXY_USER_ROLE B \n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tA.UR_ID = B.UR_ID \n" +
            "\t\t\t\t\tAND B.USER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND B.ROLE_ID = #{roleId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND T.CTR_PRO_ORG LIKE A.ORG_CODE || '%' \n" +
            "\t\t\t\t) \n" +
            "\t\t\t) \n" +
            "\t\t) \n" +
            "\t\tOR EXISTS (\n" +
            "\t\tSELECT\n" +
            "\t\t\t1 \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_USER_RELATION D \n" +
            "\t\tWHERE\n" +
            "\t\t\tD.LEADER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\tAND (\n" +
            "\t\t\t\tD.FOLLOWER_ID = T.CTR_WAITER \n" +
            "\t\t\t\tOR D.FOLLOWER_ID = T.CTR_SJS \n" +
            "\t\t\t\tOR D.FOLLOWER_ID = T.CTR_GCJL \n" +
            "\t\t\t\tOR D.FOLLOWER_ID = T.CTR_CLDD \n" +
            "\t\t\t)) \n" +
            "\t) temptable \n" +
            "WHERE\n" +
            "\ttemptable.CTR_CODE = #{condition,jdbcType=VARCHAR} \n" +
            "\tOR temptable.CTR_NAME = #{condition,jdbcType=VARCHAR} \n" +
            "\tOR temptable.CTR_TEL = #{condition,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> getRCuntomerInfoByCondition(@Param("userId") String userId, @Param("condition") String condition, @Param("roleId") String roleId) throws SQLException;

    /**
     *
     * @Description: 根据档案号查询RG_VER
     * @author: GeWeiliang
     * @date: 2018\10\6 0006 17:14
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tRV.RG_VER_CODE,\n" +
            "\trv.RG_VER_NAME,\n" +
            "\trv.RG_VER_MARK,\n" +
            "\trv.RG_VER_ISUSED,\n" +
            "\trv.RG_VER_BJ_RGML,\n" +
            "\trv.RG_VER_BJ_FCML,\n" +
            "\trv.RG_VER_BJ_SGML,\n" +
            "\tNVL( rv.RG_VER_C1, '-' ) RG_VER_C1,\n" +
            "\tNVL( rv.RG_VER_C2, '-' ) RG_VER_C2,\n" +
            "\tNVL( rv.RG_VER_C3, '-' ) RG_VER_C3 \n" +
            "FROM\n" +
            "\tXY_GCB_RG_VER rv,\n" +
            "\tXY_CUSTOMER_INFO xc \n" +
            "WHERE\n" +
            "\trv.RG_VER_CODE = xc.RG_VER_CODE \n" +
            "\tAND xc.CTR_CODE = #{ctrCode,jdbcType=VARCHAR,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String,Object> getRgVer(@Param("ctrCode") String ctrCode) throws SQLException;


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
            "\tDISTINCT * \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\txui.CTR_CODE USER_ID,\n" +
            "\t\t'客户' USER_ROLE_NAME,\n" +
            "\t\txui.CTR_NAME USER_NAME \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO xui \n" +
            "\tWHERE\n" +
            "\t\txui.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} UNION ALL\n" +
            "\tSELECT\n" +
            "\t\txui.CTR_WAITER USER_ID,\n" +
            "\t\t'家装顾问' USER_ROLE_NAME,\n" +
            "\t\txu.USER_NAME \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO xui\n" +
            "\t\tLEFT JOIN XY_USER xu ON xui.CTR_WAITER = xu.USER_ID \n" +
            "\tWHERE\n" +
            "\t\txui.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} UNION ALL\n" +
            "\tSELECT\n" +
            "\t\txui.CTR_SJS USER_ID,\n" +
            "\t\t'设计师' USER_ROLE_NAME,\n" +
            "\t\txu.USER_NAME \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO xui\n" +
            "\t\tLEFT JOIN XY_USER xu ON xui.CTR_SJS = xu.USER_ID \n" +
            "\tWHERE\n" +
            "\t\txui.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} UNION ALL\n" +
            "\tSELECT\n" +
            "\t\txui.CTR_GCJL USER_ID,\n" +
            "\t\t'执行员' USER_ROLE_NAME,\n" +
            "\t\txu.USER_NAME \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO xui\n" +
            "\t\tLEFT JOIN XY_USER xu ON xui.CTR_GCJL = xu.USER_ID \n" +
            "\tWHERE\n" +
            "\t\txui.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} UNION ALL\n" +
            "\tSELECT\n" +
            "\t\txui.CTR_CLDD USER_ID,\n" +
            "\t\t'材料督导' USER_ROLE_NAME,\n" +
            "\t\txu.USER_NAME \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO xui\n" +
            "\t\tLEFT JOIN XY_USER xu ON xui.CTR_CLDD = xu.USER_ID \n" +
            "\tWHERE\n" +
            "\t\txui.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} UNION ALL\n" +
            "\tSELECT\n" +
            "\t\txui.CTR_AREA_MA USER_ID,\n" +
            "\t\t'区域老总' USER_ROLE_NAME,\n" +
            "\t\txu.USER_NAME \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO xui\n" +
            "\t\tLEFT JOIN XY_USER xu ON xui.CTR_AREA_MA = xu.USER_ID \n" +
            "\tWHERE\n" +
            "\t\txui.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} UNION ALL\n" +
            "\tSELECT\n" +
            "\t\txui.CTR_OWENER USER_ID,\n" +
            "\t\t'合约成效人' USER_ROLE_NAME,\n" +
            "\t\txu.USER_NAME \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO xui\n" +
            "\t\tLEFT JOIN XY_USER xu ON xui.CTR_OWENER = xu.USER_ID \n" +
            "\tWHERE\n" +
            "\t\txui.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} UNION ALL\n" +
            "\tSELECT\n" +
            "\t\txgg.GR_ID USER_ID,\n" +
            "\t\tCONCAT( ( SELECT xv.VAL_NAME FROM XY_VAL xv WHERE xv.VAL_ID = xp.PG_STAGE AND xv.VALSET_ID = 'B3B32F221FF14256988E7C0A218EBF5C' ), '工长' ) USER_ROLE_NAME,\n" +
            "\t\txgg.GR_NAME USER_NAME \n" +
            "\tFROM\n" +
            "\t\tXY_PG xp\n" +
            "\t\tRIGHT JOIN XY_GCB_GRXX xgg ON xp.PG_GR = xgg.GR_ID \n" +
            "\tWHERE\n" +
            "\t\txp.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} \n" +
            "\t) A \n" +
            "WHERE\n" +
            "\tA.USER_ID IS NOT NULL" +
            "</script>")
    public List<Map<String ,Object>> getChatMemberInfoLsitByCtrCode(@Param("ctrCode") String ctrCode) throws SQLException;

    /**
     * 根据手机号获取所有群组
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/31 12:02
     * @param: [userTel]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT DISTINCT\n" +
            "\tA.GROUP_ID,\n" +
            "\tA.CTR_CODE,\n" +
            "\tA.CTR_ADDR,\n" +
            "\tA.CTR_NAME\n" +
            "FROM (\n" +
            "\tSELECT \n" +
            "\t\txci.CTR_CODE GROUP_ID,\n" +
            "\t\txci.CTR_CODE,\n" +
            "\t\txci.CTR_ADDR,\n" +
            "\t\txci.CTR_NAME\n" +
            "\tFROM \n" +
            "\t\tXY_CUSTOMER_INFO xci\n" +
            "\tLEFT JOIN XY_USER xu\n" +
            "\tON xu.USER_TEL = #{userTel,jdbcType=VARCHAR}\n" +
            "\tWHERE xci.CTR_WAITER = xu.USER_ID\n" +
            "\tOR xci.CTR_SJS = xu.USER_ID\n" +
            "\tOR xci.CTR_GCJL = xu.USER_ID\n" +
            "\tOR xci.CTR_CLDD = xu.USER_ID\n" +
            "\tOR xci.CTR_AREA_MA = xu.USER_ID\n" +
            "\tOR xci.CTR_OWENER = xu.USER_ID\n" +
            "\tUNION ALL\n" +
            "\tSELECT\n" +
            "\t\txci.CTR_CODE GROUP_ID,\n" +
            "\t\txci.CTR_CODE,\n" +
            "\t\txci.CTR_ADDR,\n" +
            "\t\txci.CTR_NAME\n" +
            "\tFROM \n" +
            "\t\tXY_CUSTOMER_INFO xci\n" +
            "\tWHERE xci.CTR_TEL = #{userTel,jdbcType=VARCHAR}\n" +
            "\tUNION ALL\n" +
            "\tSELECT\n" +
            "\t\txci.CTR_CODE GROUP_ID,\n" +
            "\t\txci.CTR_CODE,\n" +
            "\t\txci.CTR_ADDR,\n" +
            "\t\txci.CTR_NAME\n" +
            "\tFROM \n" +
            "\t\tXY_CUSTOMER_INFO xci\n" +
            "\tLEFT JOIN XY_PG xp\n" +
            "\tON xci.CTR_CODE = xp.CTR_CODE\n" +
            "\tLEFT JOIN XY_GCB_GRXX xgg\n" +
            "\tON xp.PG_GR = xgg.GR_ID\n" +
            "\tWHERE xgg.GR_TEL = #{userTel,jdbcType=VARCHAR}\n" +
            ") A" +
            "</script>")
    public List<Map<String ,Object>> getChatGroupByUserTel(String userTel) throws SQLException;

    /**
     * 根据条件获取分组
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/31 12:47
     * @param: [userTel, condition]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT DISTINCT\n" +
            "\tA.GROUP_ID,\n" +
            "\tA.CTR_CODE,\n" +
            "\tA.CTR_ADDR,\n" +
            "\tA.CTR_NAME\n" +
            "FROM (\n" +
            "\tSELECT \n" +
            "\t\txci.CTR_CODE GROUP_ID,\n" +
            "\t\txci.CTR_CODE,\n" +
            "\t\txci.CTR_ADDR,\n" +
            "\t\txci.CTR_NAME\n" +
            "\tFROM \n" +
            "\t\tXY_CUSTOMER_INFO xci\n" +
            "\tLEFT JOIN XY_USER xu\n" +
            "\tON xu.USER_TEL =  #{userTel,jdbcType=VARCHAR}\n" +
            "\tWHERE xci.CTR_WAITER = xu.USER_ID\n" +
            "\tOR xci.CTR_SJS = xu.USER_ID\n" +
            "\tOR xci.CTR_GCJL = xu.USER_ID\n" +
            "\tOR xci.CTR_CLDD = xu.USER_ID\n" +
            "\tOR xci.CTR_AREA_MA = xu.USER_ID\n" +
            "\tOR xci.CTR_OWENER = xu.USER_ID\n" +
            "\tUNION ALL\n" +
            "\tSELECT\n" +
            "\t\txci.CTR_CODE GROUP_ID,\n" +
            "\t\txci.CTR_CODE,\n" +
            "\t\txci.CTR_ADDR,\n" +
            "\t\txci.CTR_NAME\n" +
            "\tFROM \n" +
            "\t\tXY_CUSTOMER_INFO xci\n" +
            "\tWHERE xci.CTR_TEL =  #{userTel,jdbcType=VARCHAR}\n" +
            "\tUNION ALL\n" +
            "\tSELECT\n" +
            "\t\txci.CTR_CODE GROUP_ID,\n" +
            "\t\txci.CTR_CODE,\n" +
            "\t\txci.CTR_ADDR,\n" +
            "\t\txci.CTR_NAME\n" +
            "\tFROM \n" +
            "\t\tXY_CUSTOMER_INFO xci\n" +
            "\tLEFT JOIN XY_PG xp\n" +
            "\tON xci.CTR_CODE = xp.CTR_CODE\n" +
            "\tLEFT JOIN XY_GCB_GRXX xgg\n" +
            "\tON xp.PG_GR = xgg.GR_ID\n" +
            "\tWHERE xgg.GR_TEL = #{userTel,jdbcType=VARCHAR}\n" +
            ") A\n" +
            "WHERE A.CTR_CODE =  #{condition,jdbcType=VARCHAR}\n" +
            "OR A.CTR_NAME =  #{condition,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getChatGroupByConditionAndUserTel(@Param("userTel") String userTel, @Param("condition") String condition) throws SQLException;

    /**
     * 获取用户开工时间
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/15 9:43
     * @param: [ctrCode]
     * @return: java.util.Date
     */
    @Select("<script>" +
            "SELECT xci.CTR_KG_DATE FROM XY_CUSTOMER_INFO xci WHERE xci.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public Date getCtrKgDate(String ctrCode) throws SQLException;

    /**
     * 获取用户姓名
     * @Description:
     * @author: zheng shuai
     * @date: 2018/12/28 17:01
     * @param: [ctrTel]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT B.CTR_NAME  FROM ( SELECT A.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\t\tSELECT CTR_NAME FROM XY_CUSTOMER_INFO WHERE CTR_TEL = #{ctrTel,jdbcType=VARCHAR} ORDER BY CTR_CRT_DATE DESC\n" +
            "\t) A  \n" +
            ")B\n" +
            "WHERE RN BETWEEN 1 AND 1" +
            "</script>")
    public String getCtrName(String ctrTel) throws SQLException;

    /**
     * 获取最新的档案号
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 13:13
     * @param: [nowYear]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tB.CTR_CODE + 1 CTR_CODE\n" +
            "FROM ( \n" +
            "\tSELECT A.*, ROWNUM RN \n" +
            "\tFROM ( \n" +
            "\t\tSELECT CTR_CODE FROM XY_CUSTOMER_INFO WHERE CTR_CODE LIKE #{nowYear,jdbcType=VARCHAR}||'%'\n" +
            "\t\tORDER BY CTR_CRT_DATE DESC\n" +
            "\t) A  \n" +
            ")B\n" +
            "WHERE RN BETWEEN 1 AND 1" +
            "</script>")
    public String getNewestCtrCode(String nowYear) throws SQLException;

    /**
     * 派单执行添加操作
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 13:49
     * @param: [ctrCode, sjs, custId]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_CUSTOMER_INFO (\n" +
            "\tCTR_CODE,\n" +
            "\tCTR_NAME,\n" +
            "\tCTR_TEL,\n" +
            "\tCTR_ADDR,\n" +
            "\tCTR_SJS,\n" +
            "\tCTR_WAITER\n" +
            ") SELECT \n" +
            "\t\t#{ctrCode,jdbcType=VARCHAR} CTR_CODE,\n" +
            "\t\tTO_CHAR(CUST_NAME) CTR_NAME,\n" +
            "\t\tTO_CHAR(CUST_MOBILE) CTR_TEL,\n" +
            "\t\tTO_CHAR(CONCAT(CUST_ADDRESS,CUST_ADDRESS_DETAIL)) CTR_ADDR,\n" +
            "\t\t#{sjs,jdbcType=VARCHAR} CTR_SJS,\n" +
            "\t\tTO_CHAR(CUST_PROVIDER) CUST_PROVIDER\n" +
            "\tFROM \n" +
            "\t\tXY_CRM_CUST\n" +
            "\tWHERE\n" +
            "\t\tCUST_ID = #{custId,jdbcType=VARCHAR}" +
            "</script>")
    public void pdAddCustInfo(@Param("ctrCode") String ctrCode ,@Param("sjs") String sjs ,@Param("custId") String custId) throws SQLException;

    /**
     * 判断是否可显示（0：是    1：否）
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/9 10:05
     * @param: [ctrCode]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCASE \n" +
            "\tWHEN TO_CHAR(CTR_CRT_DATE + 9,'yyyy-MM-dd') <![CDATA[<=]]> TO_CHAR(SYSDATE,'yyyy-MM-dd') THEN\n" +
            "\t\t'0'\n" +
            "\tELSE\n" +
            "\t\t'1'\n" +
            "END CASE\n" +
            "FROM\n" +
            "\tXY_CUSTOMER_INFO \n" +
            "WHERE\n" +
            "\tCTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public String isShow(String ctrCode) throws SQLException;

    /**
     * 根据地址判断是否有重复的客户
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/10 15:51
     * @param: [ctrAddr]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM XY_CUSTOMER_INFO WHERE CTR_ADDR = #{ctrAddr,jdbcType=VARCHAR}" +
            "</script>")
    public Integer existsByCtrAddr(String ctrAddr) throws SQLException;

    /**
     * 一键报价查询客户（E类型）
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 16:07
     * @param: [userId, startNum, endNum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT J.*  FROM ( SELECT H.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\tSELECT DISTINCT\n" +
            "\t\tA.*\n" +
            "\tFROM (\n" +
            "\t\tSELECT\n" +
            "\t\t\tT.CTR_CODE,\n" +
            "\t\t\tT.CTR_NAME,\n" +
            "\t\t\tT.CTR_TEL,\n" +
            "\t\t\tT.CTR_ADDR,\n" +
            "\t\t\tT.CTR_CRT_DATE,\n" +
            "\t\t\tT.CTR_CARDID,\n" +
            "\t\t\tT.CTR_AREA,\n" +
            "\t\t\tU1.USER_NAME JDRY_NAME,\n" +
            "\t\t\tU2.USER_NAME SJS_NAME,\n" +
            "\t\t\tU3.USER_NAME GCJL_NAME,\n" +
            "\t\t\tU4.USER_NAME CLDD_NAME,\n" +
            "\t\t\to1.org_name FWJG,\n" +
            "\t\t\to2.org_name SGJG \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CUSTOMER_INFO T\n" +
            "\t\t\tLEFT JOIN XY_USER U1 ON T.CTR_WAITER = U1.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U2 ON T.Ctr_Sjs = U2.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U3 ON T.Ctr_Gcjl = U3.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U4 ON T.Ctr_Cldd = U4.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_ORG O1 ON T.CTR_ORG = O1.ORG_CODE\n" +
            "\t\t\tLEFT JOIN XY_ORG O2 ON T.Ctr_Pro_Org = O2.ORG_CODE \n" +
            "\t\tWHERE\n" +
            "\t\t\tT.CTR_WAITER = #{userId,jdbcType=VARCHAR}\n" +
            "\t\t\tOR T.CTR_SJS = #{userId,jdbcType=VARCHAR}\n" +
            "\t\t\tOR T.CTR_GCJL = #{userId,jdbcType=VARCHAR}\n" +
            "\t\t\tOR T.CTR_CLDD = #{userId,jdbcType=VARCHAR}\n" +
            "\t) A\n" +
            "\tLEFT JOIN XY_BJD_MAIN B\n" +
            "\tON A.CTR_CODE = B.CTR_CODE\n" +
            "\tWHERE B.BJD_STAGE <![CDATA[<>]]> '3'\n" +
            "\t) H  \n" +
            ")J\n" +
            "WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> autoBjGetCustomerInfoByRoleTypeE(@Param("userId") String userId,
                                                                      @Param("startNum") String startNum,
                                                                      @Param("endNum") String endNum) throws SQLException;

    /**
     * 一键报价查询客户（R类型）
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 16:08
     * @param: []
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT J.*  FROM ( SELECT H.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\tSELECT\n" +
            "\t\tT.CTR_CODE,\n" +
            "\t\tT.CTR_NAME,\n" +
            "\t\tT.CTR_TEL,\n" +
            "\t\tT.CTR_ADDR,\n" +
            "\t\tT.CTR_CRT_DATE,\n" +
            "\t\tT.CTR_CARDID,\n" +
            "\t\tT.CTR_AREA,\n" +
            "\t\tU1.USER_NAME JDRY_NAME,\n" +
            "\t\tU2.USER_NAME SJS_NAME,\n" +
            "\t\tU3.USER_NAME GCJL_NAME,\n" +
            "\t\tU4.USER_NAME CLDD_NAME,\n" +
            "\t\to1.org_name FWJG,\n" +
            "\t\to2.org_name SGJG \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO T\n" +
            "\t\tLEFT JOIN XY_USER U1 ON T.CTR_WAITER = U1.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U2 ON T.Ctr_Sjs = U2.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U3 ON T.Ctr_Gcjl = U3.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U4 ON T.Ctr_Cldd = U4.USER_ID\n" +
            "\t\tLEFT JOIN XY_ORG O1 ON T.CTR_ORG = O1.ORG_CODE\n" +
            "\t\tLEFT JOIN XY_ORG O2 ON T.Ctr_Pro_Org = O2.ORG_CODE\n" +
            "\t\tLEFT JOIN XY_BJD_MAIN A ON T.CTR_CODE||'01' = A.BJD_CODE\n" +
            "\tWHERE\n" +
            "\t\tA.BJD_STAGE <![CDATA[<>]]> '3'\n" +
            "\tAND (\n" +
            "\t\t\tNOT EXISTS ( SELECT 1 FROM XY_USER_RELATION C WHERE C.LEADER_ID = #{userId,jdbcType=VARCHAR} ) \n" +
            "\t\t\tAND (\n" +
            "\t\t\t\tEXISTS (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\t1 \n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tXY_USER_ROLE_ORG A,\n" +
            "\t\t\t\t\tXY_USER_ROLE B \n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tA.UR_ID = B.UR_ID \n" +
            "\t\t\t\t\tAND B.USER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND B.ROLE_ID = #{roleId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND T.CTR_ORG LIKE A.ORG_CODE || '%' \n" +
            "\t\t\t\t) \n" +
            "\t\t\t\tOR EXISTS (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\t1 \n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tXY_USER_ROLE_ORG A,\n" +
            "\t\t\t\t\tXY_USER_ROLE B \n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tA.UR_ID = B.UR_ID \n" +
            "\t\t\t\t\tAND B.USER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND B.ROLE_ID = #{roleId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND T.CTR_PRO_ORG LIKE A.ORG_CODE || '%' \n" +
            "\t\t\t\t) \n" +
            "\t\t\t) \n" +
            "\t\t) \n" +
            "\t\tOR EXISTS (\n" +
            "\t\tSELECT\n" +
            "\t\t\t1 \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_USER_RELATION D \n" +
            "\t\tWHERE\n" +
            "\t\t\tD.LEADER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\tAND (\n" +
            "\t\t\t\tD.FOLLOWER_ID = T.CTR_WAITER \n" +
            "\t\t\t\tOR D.FOLLOWER_ID = T.CTR_SJS \n" +
            "\t\t\t\tOR D.FOLLOWER_ID = T.CTR_GCJL \n" +
            "\t\t\t\tOR D.FOLLOWER_ID = T.CTR_CLDD \n" +
            "\t\t\t) \n" +
            "\t\t)\n" +
            "\t) H  \n" +
            ")J\n" +
            "WHERE RN BETWEEN #{startNum,jdbcType=VARCHAR} AND #{endNum,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> autoBjGetCustomerInfoByRoleTypeR(@Param("userId") String userId, @Param("roleId") String roleId,
                                            @Param("startNum") String startNum,
                                            @Param("endNum") String endNum) throws SQLException;

    /**
     * 一键报价根据条件获取用户(E类型)
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 16:29
     * @param: [userId, condition]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tA.*\n" +
            "\tFROM (\n" +
            "\t\tSELECT\n" +
            "\t\t\tT.CTR_CODE,\n" +
            "\t\t\tT.CTR_NAME,\n" +
            "\t\t\tT.CTR_TEL,\n" +
            "\t\t\tT.CTR_ADDR,\n" +
            "\t\t\tT.CTR_CRT_DATE,\n" +
            "\t\t\tT.CTR_CARDID,\n" +
            "\t\t\tT.CTR_AREA,\n" +
            "\t\t\tU1.USER_NAME JDRY_NAME,\n" +
            "\t\t\tU2.USER_NAME SJS_NAME,\n" +
            "\t\t\tU3.USER_NAME GCJL_NAME,\n" +
            "\t\t\tU4.USER_NAME CLDD_NAME,\n" +
            "\t\t\to1.org_name FWJG,\n" +
            "\t\t\to2.org_name SGJG \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_CUSTOMER_INFO T\n" +
            "\t\t\tLEFT JOIN XY_USER U1 ON T.CTR_WAITER = U1.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U2 ON T.Ctr_Sjs = U2.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U3 ON T.Ctr_Gcjl = U3.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_USER U4 ON T.Ctr_Cldd = U4.USER_ID\n" +
            "\t\t\tLEFT JOIN XY_ORG O1 ON T.CTR_ORG = O1.ORG_CODE\n" +
            "\t\t\tLEFT JOIN XY_ORG O2 ON T.Ctr_Pro_Org = O2.ORG_CODE \n" +
            "\t\tWHERE\n" +
            "\t\t\tT.CTR_WAITER = #{userId,jdbcType=VARCHAR}\n" +
            "\t\t\tOR T.CTR_SJS = #{userId,jdbcType=VARCHAR}\n" +
            "\t\t\tOR T.CTR_GCJL = #{userId,jdbcType=VARCHAR}\n" +
            "\t\t\tOR T.CTR_CLDD = #{userId,jdbcType=VARCHAR}\n" +
            "\t) A\n" +
            "\tLEFT JOIN XY_BJD_MAIN B\n" +
            "\tON A.CTR_CODE||'01' = B.BJD_CODE\n" +
            "\tWHERE B.BJD_STAGE <![CDATA[<>]]> '3'\n" +
            "\t) A \n" +
            "WHERE\n" +
            "\tA.CTR_CODE = #{condition,jdbcType=VARCHAR}\n" +
            "\tOR A.CTR_NAME = #{condition,jdbcType=VARCHAR}\n" +
            "\tOR A.CTR_TEL = #{condition,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> autoBjGetECuntomerInfoByCondition(@Param("userId") String userId,
                                                                       @Param("condition") String condition) throws SQLException;

    /**
     * 一键报价根据条件获取用户(R类型)
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/30 16:34
     * @param: [userId, condition, roleId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tT.CTR_CODE,\n" +
            "\t\tT.CTR_NAME,\n" +
            "\t\tT.CTR_TEL,\n" +
            "\t\tT.CTR_ADDR,\n" +
            "\t\tT.CTR_CRT_DATE,\n" +
            "\t\tT.CTR_CARDID,\n" +
            "\t\tT.CTR_AREA,\n" +
            "\t\tU1.USER_NAME JDRY_NAME,\n" +
            "\t\tU2.USER_NAME SJS_NAME,\n" +
            "\t\tU3.USER_NAME GCJL_NAME,\n" +
            "\t\tU4.USER_NAME CLDD_NAME,\n" +
            "\t\to1.org_name FWJG,\n" +
            "\t\to2.org_name SGJG \n" +
            "\tFROM\n" +
            "\t\tXY_CUSTOMER_INFO T\n" +
            "\t\tLEFT JOIN XY_USER U1 ON T.CTR_WAITER = U1.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U2 ON T.Ctr_Sjs = U2.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U3 ON T.Ctr_Gcjl = U3.USER_ID\n" +
            "\t\tLEFT JOIN XY_USER U4 ON T.Ctr_Cldd = U4.USER_ID\n" +
            "\t\tLEFT JOIN XY_ORG O1 ON T.CTR_ORG = O1.ORG_CODE\n" +
            "\t\tLEFT JOIN XY_ORG O2 ON T.Ctr_Pro_Org = O2.ORG_CODE\n" +
            "\t\tLEFT JOIN XY_BJD_MAIN A ON T.CTR_CODE||'01' = A.BJD_CODE\n" +
            "\tWHERE\n" +
            "\t\tA.BJD_STAGE <![CDATA[<>]]> '3'\n" +
            "\tAND (\n" +
            "\t\t\tNOT EXISTS ( SELECT 1 FROM XY_USER_RELATION C WHERE C.LEADER_ID = #{userId,jdbcType=VARCHAR} ) \n" +
            "\t\t\tAND (\n" +
            "\t\t\t\tEXISTS (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\t1 \n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tXY_USER_ROLE_ORG A,\n" +
            "\t\t\t\t\tXY_USER_ROLE B \n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tA.UR_ID = B.UR_ID \n" +
            "\t\t\t\t\tAND B.USER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND B.ROLE_ID = #{roleId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND T.CTR_ORG LIKE A.ORG_CODE || '%' \n" +
            "\t\t\t\t) \n" +
            "\t\t\t\tOR EXISTS (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\t1 \n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tXY_USER_ROLE_ORG A,\n" +
            "\t\t\t\t\tXY_USER_ROLE B \n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tA.UR_ID = B.UR_ID \n" +
            "\t\t\t\t\tAND B.USER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND B.ROLE_ID = #{roleId,jdbcType=VARCHAR} \n" +
            "\t\t\t\t\tAND T.CTR_PRO_ORG LIKE A.ORG_CODE || '%' \n" +
            "\t\t\t\t) \n" +
            "\t\t\t) \n" +
            "\t\t) \n" +
            "\t\tOR EXISTS (\n" +
            "\t\tSELECT\n" +
            "\t\t\t1 \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_USER_RELATION D \n" +
            "\t\tWHERE\n" +
            "\t\t\tD.LEADER_ID = #{userId,jdbcType=VARCHAR} \n" +
            "\t\t\tAND (\n" +
            "\t\t\t\tD.FOLLOWER_ID = T.CTR_WAITER \n" +
            "\t\t\t\tOR D.FOLLOWER_ID = T.CTR_SJS \n" +
            "\t\t\t\tOR D.FOLLOWER_ID = T.CTR_GCJL \n" +
            "\t\t\t\tOR D.FOLLOWER_ID = T.CTR_CLDD \n" +
            "\t\t\t) \n" +
            "\t\t)\n" +
            "\t) temptable \n" +
            "WHERE\n" +
            "\ttemptable.CTR_CODE = #{condition,jdbcType=VARCHAR}\n" +
            "\tOR temptable.CTR_NAME = #{condition,jdbcType=VARCHAR}\n" +
            "\tOR temptable.CTR_TEL = #{condition,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> autoBjGetRCuntomerInfoByCondition(@Param("userId") String userId,
                                                                       @Param("condition") String condition,
                                                                       @Param("roleId") String roleId) throws SQLException;
}
