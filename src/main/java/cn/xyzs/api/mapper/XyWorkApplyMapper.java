package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.sys.XyWorkApply;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyWorkApplyMapper extends Mapper<XyWorkApply> {

    /**
     * 获取信息历史处理记录
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/3 17:12
     * @param: [custId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t(SELECT USER_NAME FROM XY_USER WHERE USER_ID = B.XY_USER_ID) USER_NAME,\n" +
            "\tTO_CHAR( B.WAD_ADDTIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ), 'yyyy-MM-dd' ) WAD_ADDTIME,\n" +
            "\tB.WAD_REMARK,\n" +
            "\tC.NODE_NAME\n" +
            "FROM\n" +
            "\tXY_WORK_APPLY A\n" +
            "LEFT JOIN XY_WORK_ADETAIL B\n" +
            "ON A.APPLY_ID = B.APPLY_ID\n" +
            "LEFT JOIN XY_WORK_NODE C\n" +
            "ON B.NODE_ID = C.NODE_ID\n" +
            "WHERE A.APPLY_CONTENT = #{custId,jdbcType=VARCHAR}\n" +
            "AND B.WAD_OPERATION <![CDATA[!=]]> '0'\n" +
            "ORDER BY B.WAD_ADDTIME " +
            "</script>")
    public List<Map<String ,Object>> getInfoHistoryFlow(String custId) throws SQLException;

    /**
     * 根据custId获取记录数
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/5 12:07
     * @param: [custId]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM XY_WORK_APPLY WHERE APPLY_CONTENT = #{custId,jdbcType=VARCHAR}" +
            "</script>")
    public Integer getCountByCustId(String custId) throws SQLException;

    /**
     * 添加流程申请
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/5 15:20
     * @param: [xyWorkApply]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_WORK_APPLY(\n" +
            "\tAPPLY_USERID,\n" +
            "\tACT_ID,\n" +
            "\tAPPLY_TITLE,\n" +
            "\tAPPLY_CONTENT,\n" +
            "\tAPPLY_ADDTIME\n" +
            ") VALUES(\n" +
            "\t#{applyUserid,jdbcType=VARCHAR},\n" +
            "\t#{actId,jdbcType=VARCHAR},\n" +
            "\t#{applyTitle,jdbcType=VARCHAR},\n" +
            "\t#{applyContent,jdbcType=VARCHAR},\n" +
            "\t#{applyAddtime,jdbcType=VARCHAR}\n" +
            ")" +
            "</script>")
    @Options(useGeneratedKeys=true, keyProperty="applyId", keyColumn="APPLY_ID")
    public void addWorkApply(XyWorkApply xyWorkApply) throws SQLException;

    /**
     * 修改流程状态
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/5 15:47
     * @param: [custId, wadId]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_WORK_APPLY SET \n" +
            "\tAPPLY_STATE = (\n" +
            "\t\tSELECT \n" +
            "\t\t\tB.NODE_TYPE\n" +
            "\t\tFROM \n" +
            "\t\t\tXY_WORK_ADETAIL A\n" +
            "\t\tLEFT JOIN XY_WORK_NODE B\n" +
            "\t\tON A.NODE_ID = B.NODE_ID\n" +
            "\t\tWHERE WAD_ID = #{wadId,jdbcType=VARCHAR}\n" +
            "\t)\n" +
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
            "</script>")
    public void updateApplyState(@Param("custId") String custId , @Param("wadId") String wadId) throws SQLException;

    /**
     * 根据custId获取applyId
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 9:14
     * @param: [custId]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT B.APPLY_ID  FROM ( SELECT A.*, ROWNUM RN \n" +
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
            "WHERE RN BETWEEN 1 AND 1" +
            "</script>")
    public String getApplyId(String custId) throws SQLException;

    /**
     * 判断是否可显示
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/9 10:08
     * @param: [custId]
     * @return: java.lang.String
     */
    @Select("<script>" +
            "SELECT \n" +
            "\tCASE \n" +
            "\tWHEN TO_CHAR(add_months(APPLY_ADDTIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ) ,1) , 'yyyy-MM-dd' ) <![CDATA[<=]]> TO_CHAR(SYSDATE,'yyyy-MM-dd') THEN\n" +
            "\t\t'0'\n" +
            "\tELSE\n" +
            "\t\t'1'\n" +
            "END CASEAPPLY_ADDTIME\n" +
            "FROM \n" +
            "\tXY_WORK_APPLY \n" +
            "WHERE\n" +
            "\tAPPLY_CONTENT = #{custId,jdbcType=VARCHAR}" +
            "AND\n" +
            "\tACT_ID = '3B258CA43E8D46D68BB19EBA8772596C'" +
            "</script>")
    public String isShow(String custId) throws SQLException;
}
