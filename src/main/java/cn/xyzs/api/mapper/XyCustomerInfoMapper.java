package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyCustomerInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
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
    public List<Map<String,Object>> getECuntomerInfoByCondition(@Param("userId") String userId,@Param("condition") String condition) throws SQLException;

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
    public List<Map<String,Object>> getRCuntomerInfoByCondition(@Param("userId") String userId,@Param("condition") String condition,@Param("roleId") String roleId) throws SQLException;

    /**
     * 根据ctrCode获取所有服务人员
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/3 9:22
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txui.CTR_WAITER,\n" +
            "\txui.CTR_SJS,\n" +
            "\txui.CTR_GCJL,\n" +
            "\txui.CTR_CLDD,\n" +
            "\txui.CTR_AREA_MA,\n" +
            "\txui.CTR_OWENER\n" +
            "FROM\n" +
            "\tXY_CUSTOMER_INFO xui \n" +
            "WHERE\n" +
            "\txui.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,String> getServicePersonalByCtrCode(String ctrCode) throws SQLException;

    /**
     * 根据ctrCode获取所有服务人员姓名及ID
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/4 8:21
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txui.CTR_WAITER,\n" +
            "\t(SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = xui.CTR_WAITER) CTR_WAITER_NAME,\n" +
            "\txui.CTR_SJS,\n" +
            "\t(SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = xui.CTR_SJS) CTR_SJS_NAME,\n" +
            "\txui.CTR_GCJL,\n" +
            "\t(SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = xui.CTR_GCJL) CTR_GCJL_NAME,\n" +
            "\txui.CTR_CLDD,\n" +
            "\t(SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = xui.CTR_CLDD) CTR_CLDD_NAME,\n" +
            "\txui.CTR_AREA_MA,\n" +
            "\t(SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = xui.CTR_AREA_MA) CTR_AREA_MA_NAME,\n" +
            "\txui.CTR_OWENER,\n" +
            "\t(SELECT xu.USER_NAME FROM XY_USER xu WHERE xu.USER_ID = xui.CTR_OWENER) CTR_OWENER_NAME\n" +
            "FROM\n" +
            "\tXY_CUSTOMER_INFO xui \n" +
            "WHERE\n" +
            "\txui.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,Object> getServicePersonalInfoByCtrCode(String ctrCode) throws SQLException;

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
}
