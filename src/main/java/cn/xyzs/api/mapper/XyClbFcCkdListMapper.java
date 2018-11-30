package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbFcCkdList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbFcCkdListMapper extends Mapper<XyClbFcCkdList> {

    /**
     * 获取辅材出库明细
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/23 10:08
     * @param: [ckdCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txcfcl.CKD_CODE, \n" +
            "\txcfcl.CKD_ROW, \n" +
            "\txcfcl.FC_PRICE_ID, \n" +
            "\txcfcl.FC_PP, \n" +
            "\txcfcl.FC_NAME, \n" +
            "\txcfcl.FC_UNIT, \n" +
            "\txcfcl.FC_QTY, \n" +
            "\txcfcl.FC_PRICE, \n" +
            "\txcfcl.FC_JE, \n" +
            "\txcfcl.FC_PER, \n" +
            "\txcfcl.FC_YF,\n" +
            "\txcfcl.FC_XJ, \n" +
            "\txcfcl.FC_MARK, \n" +
            "\txcfcl.FC_ISWC, \n" +
            "\txcfcl.FC_ISEDIT, \n" +
            "\txcfcl.FC_PRINT_GROUP\n" +
            "FROM\n" +
            "\tXY_CLB_FC_CKD_LIST xcfcl\t\n" +
            "WHERE\n" +
            "\txcfcl.CKD_CODE = #{ckdCode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String ,Object>> getFcCkdList(String ckdCode) throws SQLException;

    /**
     * 添加出库单list表（ckdCode，FC_MARK）
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/12 14:46
     * @param: [xyClbFcCkdList, list]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_CLB_FC_CKD_LIST \n" +
            "\tSELECT\n" +
            "\t\t#{ckdCode,jdbcType=VARCHAR},\n" +
            "\t\tSYS_GUID(),\n" +
            "\t\tC.FC_PRICE_CODE,\n" +
            "\t\tB.BRAND_NAME,\n" +
            "\t\tA.FC_NAME,\n" +
            "\t\tA.FC_UNIT,\n" +
            "\t\t0,\n" +
            "\t\tC.FC_PRICE_OUT,\n" +
            "\t\t0,\n" +
            "\t\t0.06,\n" +
            "\t\t0,\n" +
            "\t\t0,\n" +
            "\t\t#{fcMark,jdbcType=VARCHAR},\n" +
            "\t\tA.FC_PUR_STY,\n" +
            "\t\t1,\n" +
            "\t\tA.FC_C1 \n" +
            "\tFROM\n" +
            "\t\tXY_CLB_FC_DB A,\n" +
            "\t\tXY_CLB_FC_BRAND B,\n" +
            "\t\tXY_CLB_FC_DB_PRICE C \n" +
            "\tWHERE\n" +
            "\t\tC.BRAND_ID = b.BRAND_ID\n" +
            "\tAND\n" +
            "\t\tb.FC_CODE = A.FC_CODE\n" +
            "\tAND\n" +
            "\t\tc.FC_PRICE_CODE IN " +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> "+
            "#{item,jdbcType=VARCHAR} "+
            "</foreach>"+
            "</script>")
    public void addFcCkdList(@Param("ckdCode") String ckdCode ,@Param("fcMark") String fcMark , @Param("list") List<String> list) throws SQLException;

    /**
     * 获取退库商品
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/12 18:10
     * @param: [ctrCode, ckdFcType]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT B.FC_PRICE_ID,\n" +
            "\t\tB.FC_NAME,\n" +
            "\t\tB.FC_PP,\n" +
            "\t\tB.FC_UNIT,\n" +
            "\t\tB.FC_PRICE,\n" +
            "\t\tb.fc_iswc,\n" +
            "\t\tB.FC_PRINT_GROUP,\n" +
            "\t\tSUM(CASE A.CKD_TYPE\n" +
            "\t\tWHEN '0' THEN\n" +
            "\t\tB.FC_QTY\n" +
            "\t\tELSE\n" +
            "\t\t-B.FC_QTY\n" +
            "\t\tEND) SL\n" +
            "\t\tFROM XY_CLB_FC_CKD_MAIN A,\n" +
            "\t\tXY_CLB_FC_CKD_LIST B\n" +
            "\t\tWHERE A.CKD_CODE = B.CKD_CODE\n" +
            "\t\tAND A.CKD_STATU = '3'\n" +
            "\t\tAND A.CKD_FC_TYPE = #{ckdFcType,jdbcType=VARCHAR}\n" +
            "\t\tAND A.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}\n" +
            "\t\tGROUP BY\n" +
            "\t\tB.FC_PRICE_ID, B.FC_PP, B.FC_NAME,\n" +
            "\t\tB.FC_UNIT,b.fc_iswc,B.FC_PRICE,B.FC_PRINT_GROUP\n" +
            "\t\tORDER BY 3,2" +
            "</script>")
    public List<Map<String ,Object>> getTKFcGood(@Param("ctrCode") String ctrCode ,@Param("ckdFcType") String ckdFcType) throws SQLException;

    /**
     * 根据ckdCode删除
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/14 14:03
     * @param: [ckdCode]
     * @return: void
     */
    @Select("<script>" +
            "DELETE FROM XY_CLB_FC_CKD_LIST WHERE CKD_CODE = #{ckdCode,jdbcType=VARCHAR}" +
            "</script>")
    public void deleteByCkdCode(String ckdCode) throws SQLException;

    /**
     * 一键开单添加list表
     * @Description:
     * @author: zheng shuai
     * @date: 2018/11/16 16:46
     * @param: [ctrCode, rgStage]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_CLB_FC_CKD_LIST \n" +
            "SELECT\n" +
            "\t#{ckdCode,jdbcType=VARCHAR},\n" +
            "\tA.ROW_ID,\n" +
            "\tA.FC_PRICE_CODE,\n" +
            "\tA.BRAND_NAME,\n" +
            "\tA.FC_NAME,\n" +
            "\tA.FC_UNIT,\n" +
            "\tA.SL,\n" +
            "\tA.DJ,\n" +
            "\tA.XJ,\n" +
            "\tA.YFL,\n" +
            "\tA.YF,\n" +
            "\tA.HJ,\n" +
            "\tA.BZ,\n" +
            "\tA.FC_PUR_STY,\n" +
            "\tA.ISEDIT,\n" +
            "\tA.FC_C1 \n" +
            "FROM\n" +
            "\tVW_XY_CLB_FC_CKD_FIRST A \n" +
            "WHERE\n" +
            "\tA.CTR_CODE = #{ctrCode,jdbcType=VARCHAR} \n" +
            "\tAND A.RG_STAGE = #{rgStage,jdbcType=VARCHAR}" +
            "</script>")
    public void autoOpenOrderAddCkdLsit(@Param("ckdCode") String ckdCode ,@Param("ctrCode") String ctrCode ,@Param("rgStage") String rgStage) throws SQLException;

}
