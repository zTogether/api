package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.VwXyJdjs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface VwXyJdjsMapper extends Mapper<VwXyJdjs> {

    /***
     *
     * @Description: 工程阶段计算
     * @author: GeWeiliang
     * @date: 2018\10\15 0015 15:27
     * @param: [ctrCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM VW_XY_JDJS WHERE CTR_CODE=#{ctrCode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> getJdjs(String ctrCode) throws SQLException;

    /**
     * 根据CtrCode和Jd获取jdJs
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/27 14:23
     * @param: [ctrCode, jd]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tVW_XY_JDJS \n" +
            "WHERE\n" +
            "\tCTR_CODE = #{ctrCode,jdbcType=VARCHAR}\n" +
            "AND\n" +
            "\tJD = #{jd,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,Object> getJdjsVByCtrCodeAndJd(@Param("ctrCode") String ctrCode, @Param("jd") String jd) throws SQLException;
}
