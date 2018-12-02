package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyClbZcFl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyClbZcFlMapper extends Mapper<XyClbZcFl>{

    /**
     * 查询下级目录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/20 14:42
     * @param: [zcflCode]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>SELECT xczf.* FROM XY_CLB_ZC_FL xczf WHERE xczf.P_CODE = #{zcflCode,jdbcType=VARCHAR}</script>")
    public List<Map<String, Object>> getSubdirectory(String zcflCode) throws SQLException;

    @Select("<script>" +
            "SELECT ZCFL_NAME FROM XY_CLB_ZC_FL WHERE ZCFL_CODE IN" +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> "+
                "#{item,jdbcType=VARCHAR} "+
            "</foreach> " +
            "</script>")
    public List<String> getZcFl(@Param("list") List zcflList);

    /**
     * 获取所有下级目录
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/12 18:22
     * @param: [zcflCode]
     * @return: \
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tXY_CLB_ZC_FL xczf START WITH xczf.ZCFL_CODE = #{zcflCode,jdbcType=VARCHAR} CONNECT BY xczf.P_CODE = PRIOR xczf.ZCFL_CODE" +
            "</script>")
    public List<String> getLowerDirectory(String zcflCode) throws SQLException;
}
