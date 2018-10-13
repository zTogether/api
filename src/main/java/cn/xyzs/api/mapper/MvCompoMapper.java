package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.MvCompo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MvCompoMapper extends Mapper<MvCompo>{

    /**
     * 获取移动端专属菜单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/12 16:55
     * @param: [roleId]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tmc.COMPO_ID,\n" +
            "\tmc.COMPO_CODE,\n" +
            "\tmc.COMPO_NAME\n" +
            "FROM\n" +
            "\tMV_COMPO mc \n" +
            "WHERE\n" +
            "\tmc.COMPO_ID IN (\n" +
            "\tSELECT\n" +
            "\t\tmrf.COMPO_ID \n" +
            "\tFROM\n" +
            "\t\tMV_ROLE_FUC mrf \n" +
            "\tWHERE\n" +
            "\tmrf.ROLE_ID = #{roleId,jdbcType=VARCHAR} \n" +
            "\t)" +
            "</script>")
    public List<Map<String ,Object>> getMvCompoByRoleId(String roleId) throws SQLException;
}
