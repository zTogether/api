package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyBjdMain;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyBjdMainMapper extends Mapper<XyBjdMain>{

    /**
     *
     * @Description: 报价清单
     * @author: GeWeiliang
     * @date: 2018\9\20 0020 9:32
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\txbm.* ,\n" +
            "\tTO_CHAR(xbm.CREATE_DATE,'yyyy-MM-dd HH24:mi:ss') CREATEDATE\n" +
            "FROM\n" +
            "\tXY_BJD_MAIN xbm\t\n" +
            "WHERE\n" +
            "\tCTR_CODE = #{ctrCode}" +
            "</script>")
    public List<Map<String,Object>> bjdList(String ctrCode) throws SQLException;

    /**
     * 判断此工种是否可派工
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/10 15:33
     * @param: [ctrCode, pgStage]
     * @return: java.lang.Integer
     */
    @Select("<script>" +
            "select count(1) from (" +
            "SELECT * FROM \n" +
            "  XY_BJD_MAIN D,\n" +
            "  XY_BJD_RG_LIST E\n" +
            "  WHERE\n" +
            "  D.BJD_CODE = E.BJD_CODE\n" +
            "  AND D.CTR_CODE = #{ctrCode,jdbcType=VARCHAR}\n" +
            "  AND EXISTS(SELECT 1 FROM XY_GCB_RG_VER_LIST X WHERE X.RG_ID=E.RG_ID AND X.RG_SG_STAGE = #{pgStage,jdbcType=VARCHAR})\n" +
            "  AND D.BJD_STAGE='3' \n" +
            "  AND NOT EXISTS(SELECT 1 FROM XY_GCB_RG_DB C WHERE C.RG_ISGZ !=1 AND SUBSTR(E.RG_ID,1,10)=C.RG_CODE)\n" +
            "  AND NOT EXISTS(SELECT 1 FROM XY_PG A,XY_PG_LIST B WHERE A.PG_ID=B.PG_ID AND A.PG_STAGE=#{pgStage,jdbcType=VARCHAR} AND A.CTR_CODE= #{ctrCode,jdbcType=VARCHAR} AND B.BJD_CODE=D.BJD_CODE)" +
            ")" +
            "</script>")
    public Integer getIsPg(@Param("ctrCode") String ctrCode ,@Param("pgStage") String pgStage) throws SQLException;
}
