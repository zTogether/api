package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.MvCommoMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MvCommoMenuMapper extends Mapper<MvCommoMenu>{

    /**
     * 清空用户常用菜单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/18 12:32
     * @param: [mvCommoMenu]
     * @return: void
     */
    @Delete("DELETE \n" +
            "FROM\n" +
            "\tMV_COMMO_MENU mcm \n" +
            "WHERE\n" +
            "\tmcm.USER_ID = #{userId}\n" +
            "\tAND mcm.ROLE_ID = #{roleId}\n")
    public void wipeCommoMenu(MvCommoMenu mvCommoMenu) throws SQLException;

    /**
     * 添加用户常用菜单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/18 12:36
     * @param: [mvCommoMenu]
     * @return: void
     */
    @Insert("INSERT INTO MV_COMMO_MENU ( USER_ID, ROLE_ID, COMPO_ID )\n" +
            "VALUES (#{userId},#{roleId},#{compoId})")
    public void addCommoMenu(MvCommoMenu mvCommoMenu) throws SQLException;

    /**
     * 获取用户的常用菜单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/8/18 13:59
     * @param: [mvCommoMenu]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tXY_COMPO xc \n" +
            "WHERE\n" +
            "\txc.COMPO_ID IN (\n" +
            "\tSELECT\n" +
            "\t\tMV_COMMO_MENU.COMPO_ID \n" +
            "\tFROM\n" +
            "\t\tMV_COMMO_MENU \n" +
            "\tWHERE\n" +
            "\t\tMV_COMMO_MENU.USER_ID = #{userId} \n" +
            "\tAND MV_COMMO_MENU.ROLE_ID = #{roleId} \n" +
            ")</script>")
    public List<Map<String ,Object>> getCommoMenu(MvCommoMenu mvCommoMenu) throws SQLException;
}
