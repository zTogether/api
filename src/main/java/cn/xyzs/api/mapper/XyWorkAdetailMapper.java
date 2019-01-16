package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.sys.XyWorkAdetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.Map;

public interface XyWorkAdetailMapper extends Mapper<XyWorkAdetail> {

    /**
     * 获取分析图数据
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/4 10:28
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCOUNT( \n" +
            "\t\tCASE WHEN \n" +
            "\t\tTO_CHAR( WAD_ADDTIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\t\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ), 'yyyy-MM-dd' ) = TO_CHAR(SYSDATE - 4, 'yyyy-MM-dd')\n" +
            "\t\tTHEN '1' END \n" +
            "\t) a,\n" +
            "\tCOUNT( \n" +
            "\t\tCASE WHEN \n" +
            "\t\tTO_CHAR( WAD_ADDTIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\t\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ), 'yyyy-MM-dd' ) = TO_CHAR(SYSDATE - 3, 'yyyy-MM-dd')\n" +
            "\t\tTHEN '2' END \n" +
            "\t) b,\n" +
            "\tCOUNT( \n" +
            "\t\tCASE WHEN \n" +
            "\t\tTO_CHAR( WAD_ADDTIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\t\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ), 'yyyy-MM-dd' ) = TO_CHAR(SYSDATE - 2, 'yyyy-MM-dd')\n" +
            "\t\tTHEN '3' END \n" +
            "\t) c,\n" +
            "\tCOUNT( \n" +
            "\t\tCASE WHEN \n" +
            "\t\tTO_CHAR( WAD_ADDTIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\t\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ), 'yyyy-MM-dd' ) = TO_CHAR(SYSDATE - 1, 'yyyy-MM-dd')\n" +
            "\t\tTHEN '4' END \n" +
            "\t) d,\n" +
            "\tCOUNT( \n" +
            "\t\tCASE WHEN \n" +
            "\t\tTO_CHAR( WAD_ADDTIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\t\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ), 'yyyy-MM-dd' ) = TO_CHAR(SYSDATE, 'yyyy-MM-dd')\n" +
            "\t\tTHEN '5' END \n" +
            "\t) e,\n" +
            "\tTO_CHAR(SYSDATE - 4, 'MM-dd') a_date,\n" +
            "\tTO_CHAR(SYSDATE - 3, 'MM-dd') b_date,\n" +
            "\tTO_CHAR(SYSDATE - 2, 'MM-dd') c_date,\n" +
            "\tTO_CHAR(SYSDATE - 1, 'MM-dd')\td_date,\n" +
            "\tTO_CHAR(SYSDATE, 'MM-dd') e_date\n" +
            "FROM\n" +
            "\tXY_WORK_ADETAIL\n" +
            "WHERE\n" +
            "\tNODE_ID = 'A6DF3A6B7E2F4718B4EE6EADB0643926'\n" +
            "AND\n" +
            "\tWAD_OPERATION = 1\n" +
            "AND\n" +
            "\tTO_CHAR( WAD_ADDTIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ), 'yyyy-MM-dd' ) <![CDATA[>=]]> TO_CHAR(SYSDATE - 4, 'yyyy-MM-dd')\n" +
            "AND\n" +
            "\tTO_CHAR( WAD_ADDTIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ), 'yyyy-MM-dd' ) <![CDATA[<=]]> TO_CHAR(SYSDATE, 'yyyy-MM-dd')\n" +
            "AND \n" +
            "\tAPPLY_ID IN (\n" +
            "\t\tSELECT APPLY_ID FROM XY_WORK_APPLY WHERE APPLY_CONTENT IN (\n" +
            "\t\t\tSELECT CUST_ID FROM XY_CRM_CUST WHERE CUST_PROVIDER IN (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tB.LOWER_USER_ID\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tXY_CRM_RELATION B\n" +
            "\t\t\t\tWHERE \n" +
            "\t\t\t\t\tB.EXPRESS = #{express,jdbcType=VARCHAR}\n" +
            "\t\t\t\tAND\n" +
            "\t\t\t\t\tB.USER_ID = #{userId,jdbcType=VARCHAR}\n" +
            "\t\t\t\tUNION ALL\n" +
            "\t\t\t\tSELECT TO_NCHAR(#{userId,jdbcType=VARCHAR}) LOWER_USER_ID FROM dual\n" +
            "\t\t\t)\t\n" +
            "\t\t)\n" +
            "\t)" +
            "</script>")
    public Map<String ,Object> getAnalyzeImgData(@Param("express") String express , @Param("userId") String userId) throws SQLException;

    /**
     * 获取待办事项的节点信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/5 12:10
     * @param: [custId, roleId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tA.WAD_ID,\n" +
            "\tB.*\n" +
            "FROM\n" +
            "\tXY_WORK_ADETAIL A\n" +
            "LEFT JOIN XY_WORK_NODE B\n" +
            "ON A.NODE_ID = B.NODE_ID\n" +
            "WHERE\n" +
            "\tA.XY_USER_ID IS NULL \n" +
            "AND \n" +
            "\tA.WAD_OPERATION = 0 \n" +
            "AND \n" +
            "\tA.APPLY_ID = (\n" +
            "\t\tSELECT\n" +
            "\t\t\tAPPLY_ID \n" +
            "\t\tFROM\n" +
            "\t\t\tXY_WORK_APPLY \n" +
            "\t\tWHERE\n" +
            "\t\tAPPLY_CONTENT = #{custId,jdbcType=VARCHAR} \n" +
            "\t\tAND\n" +
            "\t\tACT_ID = #{actId,jdbcType=VARCHAR} \n" +
            ")\n" +
            "AND\n" +
            "\tB.NODE_OPERATION_GROUP = #{roleId,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,Object> getWaitDisposeMatterNodeInfo(@Param("custId") String custId ,
                                                            @Param("roleId") String roleId ,
                                                            @Param("actId") String actId) throws SQLException;

    /**
     * 修改工作流明细表
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/5 14:59
     * @param: [xyWorkAdetail]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Update("<script>" +
            "UPDATE XY_WORK_ADETAIL SET \n" +
            "\tXY_USER_ID = #{xyUserId,jdbcType=VARCHAR} ,\n" +
            "\tWAD_REMARK = #{wadRemark,jdbcType=VARCHAR} ,\n" +
            "\tWAD_OPERATION = #{wadOperation,jdbcType=VARCHAR} ,\n" +
            "\tWAD_ADDTIME = #{wadAddtime,jdbcType=VARCHAR}\n" +
            "WHERE\n" +
            "\tWAD_ID = #{wadId,jdbcType=VARCHAR}" +
            "</script>")
    public void updateWorkDetail(XyWorkAdetail xyWorkAdetail) throws SQLException;

    /**
     * 添加下一节点信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/5 15:05
     * @param: [xyWorkAdetail]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_WORK_ADETAIL(\n" +
            "\tAPPLY_ID,\n" +
            "\tNODE_ID\n" +
            ") VALUES(\n" +
            "\t#{applyId,jdbcType=VARCHAR},\n" +
            "\t#{nodeId,jdbcType=VARCHAR}\n" +
            ")" +
            "</script>")
    public void addAfterNodeInfo(XyWorkAdetail xyWorkAdetail) throws SQLException;

    /**
     * 判断是否可显示
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/9 10:10
     * @param: [nodeId, custId]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tCASE \n" +
            "\tWHEN TO_CHAR(add_months(WAD_ADDTIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ) ,1) , 'yyyy-MM-dd' ) <![CDATA[<=]]> TO_CHAR(SYSDATE,'yyyy-MM-dd') THEN\n" +
            "\t\t'0'\n" +
            "\tELSE\n" +
            "\t\t'1'\n" +
            "END CASEAPPLY_ADDTIME\n" +
            "FROM\n" +
            "\tXY_WORK_ADETAIL\n" +
            "WHERE\n" +
            "\tAPPLY_ID = (SELECT B.APPLY_ID  FROM ( SELECT A.*, ROWNUM RN \n" +
            "FROM ( \n" +
            "\tSELECT\n" +
            "\t\tAPPLY_ID \n" +
            "\tFROM\n" +
            "\t\tXY_WORK_APPLY \n" +
            "\tWHERE\n" +
            "\t\tAPPLY_CONTENT = #{custId,jdbcType=VARCHAR}\n" +
            "\tORDER BY APPLY_ADDTIME DESC\n" +
            "\t) A  \n" +
            ")B\n" +
            "WHERE RN BETWEEN 1 AND 1)" +
            "AND NODE_ID = #{nodeId,jdbcType=VARCHAR}" +
            "</script>")
    public String isShow(@Param("nodeId") String nodeId ,@Param("custId") String custId) throws SQLException;

    /**
     * 是否可以进行处理
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/13 14:54
     * @param: [custId, nodeId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tCOUNT(1) \n" +
            "FROM \n" +
            "\tXY_WORK_ADETAIL \n" +
            "WHERE \n" +
            "\tWAD_ID = #{wadId,jdbcType=VARCHAR} \n" +
            "AND \n" +
            "\tAPPLY_ID IN (SELECT APPLY_ID FROM XY_WORK_APPLY WHERE APPLY_CONTENT = #{custId,jdbcType=VARCHAR})" +
            "AND \n" +
            "\tXY_USER_ID IS NOT NULL" +
            "</script>")
    public Integer isDispose(@Param("custId") String custId ,@Param("wadId") String wadId) throws SQLException;

    /**
     * 是否可以进行处理
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/13 14:54
     * @param: [custId, nodeId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tCOUNT(1) \n" +
            "FROM \n" +
            "\tXY_WORK_ADETAIL \n" +
            "WHERE \n" +
            "\tNODE_ID = #{nodeId,jdbcType=VARCHAR} \n" +
            "AND \n" +
            "\tAPPLY_ID IN (SELECT APPLY_ID FROM XY_WORK_APPLY WHERE APPLY_CONTENT = #{custId,jdbcType=VARCHAR})" +
            "</script>")
    public Integer isDispose1(@Param("custId") String custId ,@Param("nodeId") String nodeId) throws SQLException;
}
