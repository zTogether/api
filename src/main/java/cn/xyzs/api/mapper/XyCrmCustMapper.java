package cn.xyzs.api.mapper;

import cn.xyzs.common.pojo.XyCrmCust;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyCrmCustMapper extends Mapper<XyCrmCust> {

    /**
     * 添加信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 9:20
     * @param: [xyCrmCust]
     * @return: void
     */
    @Insert("<script>" +
            "INSERT INTO XY_CRM_CUST(\n" +
            "\tCUST_NAME, \n" +
            "\tCUST_ADDRESS, \n" +
            "\tCUST_ADDRESS_DETAIL, \n" +
            "\tDECOR_TYPE, \n" +
            "\tCUST_MOBILE, \n" +
            "\tCREATE_TIME, \n" +
            "\tCUST_PROVIDER, \n" +
            "\tJOB_SCHEDULE \n" +
            ") VALUES(\n" +
            "\t#{custName,jdbcType=VARCHAR},\n" +
            "\t#{custAddress,jdbcType=VARCHAR},\n" +
            "\t#{custAddressDetail,jdbcType=VARCHAR},\n" +
            "\t#{decorType,jdbcType=VARCHAR},\n" +
            "\t#{custMobile,jdbcType=VARCHAR},\n" +
            "\t#{createTime,jdbcType=VARCHAR},\n" +
            "\t#{custProvider,jdbcType=VARCHAR},\n" +
            "\t'0000'\n" +
            ")" +
            "</script>")
    public void addCrmCust(XyCrmCust xyCrmCust) throws SQLException;

    /**
     * 修改信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 9:39
     * @param: [xyCrmCust]
     * @return: void
     */
    @UpdateProvider(type = updateCrmCustByCustId.class,method = "updateCrmCustByCustId")
    public void updateCrmCustByCustId(XyCrmCust xyCrmCust) throws SQLException;
    public class updateCrmCustByCustId{
        public String updateCrmCustByCustId(XyCrmCust xyCrmCust){
            return new SQL(){{
                UPDATE("XY_CRM_CUST");
                if (xyCrmCust.getCustName() != null && !"".equals(xyCrmCust.getCustName())){
                    SET("CUST_NAME = '"+xyCrmCust.getCustName()+"'");
                }
                if (xyCrmCust.getCustAddress() != null && !"".equals(xyCrmCust.getCustAddress())){
                    SET("CUST_ADDRESS = '"+xyCrmCust.getCustAddress()+"'");
                }
                if (xyCrmCust.getCustAddressDetail() != null && !"".equals(xyCrmCust.getCustAddressDetail())){
                    SET("CUST_ADDRESS_DETAIL = '"+xyCrmCust.getCustAddressDetail()+"'");
                }
                if (xyCrmCust.getDecorType() != null && !"".equals(xyCrmCust.getDecorType())){
                    SET("DECOR_TYPE = "+xyCrmCust.getDecorType());
                }
                if (xyCrmCust.getCustMobile() != null && !"".equals(xyCrmCust.getCustMobile())){
                    SET("CUST_MOBILE = '"+xyCrmCust.getCustMobile()+"'");
                }
                if (xyCrmCust.getPrivTime() != null && !"".equals(xyCrmCust.getPrivTime())){
                    SET("PRIV_TIME = '"+xyCrmCust.getPrivTime()+"'");
                }
                if (xyCrmCust.getPrivCompany() != null && !"".equals(xyCrmCust.getPrivCompany())){
                    SET("PRIV_COMPANY = '"+xyCrmCust.getPrivCompany()+"'");
                }
                if (xyCrmCust.getPrivAssess() != null && !"".equals(xyCrmCust.getPrivAssess())){
                    SET("PRIV_ASSESS = '"+xyCrmCust.getPrivAssess()+"'");
                }
                if (xyCrmCust.getNewContime() != null && !"".equals(xyCrmCust.getNewContime())){
                    SET("NEW_CONTIME = "+xyCrmCust.getNewContime());
                }
                if (xyCrmCust.getNewConact() != null && !"".equals(xyCrmCust.getNewConact())){
                    SET("NEW_CONACT = '"+xyCrmCust.getNewConact()+"'");
                }
                if (xyCrmCust.getCustState() != null && !"".equals(xyCrmCust.getCustState())){
                    SET("CUST_STATE = "+xyCrmCust.getCustState());
                }
                if (xyCrmCust.getCreateTime() != null && !"".equals(xyCrmCust.getCreateTime())){
                    SET("CREATE_TIME = "+xyCrmCust.getCreateTime());
                }
                if (xyCrmCust.getCustProvider() != null && !"".equals(xyCrmCust.getCustProvider())){
                    SET("CUST_PROVIDER = '"+xyCrmCust.getCustProvider()+"'");
                }
                if (xyCrmCust.getCtrCode() != null && !"".equals(xyCrmCust.getCtrCode())){
                    SET("CTR_CODE = '"+xyCrmCust.getCtrCode()+"'");
                }
                if (xyCrmCust.getCustDelay() != null && !"".equals(xyCrmCust.getCustDelay())){
                    SET("CUST_DELAY = "+xyCrmCust.getCustDelay());
                }
                if (xyCrmCust.getIsVaild() != null && !"".equals(xyCrmCust.getIsVaild())){
                    SET("IS_VAILD = "+xyCrmCust.getIsVaild());
                }
                if (xyCrmCust.getJoinUserid() != null && !"".equals(xyCrmCust.getJoinUserid())){
                    SET("JOIN_USERID = '"+xyCrmCust.getJoinUserid()+"'");
                }
                if (xyCrmCust.getJobSchedule() != null && !"".equals(xyCrmCust.getJobSchedule())){
                    SET("JOB_SCHEDULE = '"+xyCrmCust.getJobSchedule()+"'");
                }
                WHERE("CUST_ID = '"+xyCrmCust.getCustId()+"'");
            }}.toString();
        }
    }

    /**
     * 获取提交的意向信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 14:07
     * @param: [xyCrmCust, condition, startNum, endNum]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @SelectProvider(type = getCrmCustByCondition.class,method = "getCrmCustByCondition")
    public List<Map<String ,Object>> getCrmCustByCondition(XyCrmCust xyCrmCust , String condition , Integer startNum ,
                                                           Integer endNum ,String selectFlag ,String roleId ,String userId) throws SQLException;
    public class getCrmCustByCondition{
        public String getCrmCustByCondition(XyCrmCust xyCrmCust ,String condition ,Integer startNum ,Integer endNum ,String selectFlag ,String roleId ,String userId){
            String tempSql = "";
            tempSql += "SELECT B.*  FROM ( SELECT A.*, ROWNUM RN \n" +
                    "FROM ( \n" +
                    "\tSELECT * FROM (\n" +
                    "\t\tSELECT\n" +
                    "\t\t\tCUST_ID,\n" +
                    "\t\t\tCUST_NAME,\n" +
                    "\t\t\tCUST_ADDRESS,\n" +
                    "\t\t\tCUST_ADDRESS_DETAIL,\n" +
                    "\t\t\tDECOR_TYPE,\n" +
                    "\t\t\tCUST_MOBILE,\n" +
                    "\t\t\tPRIV_TIME,\n" +
                    "\t\t\tPRIV_COMPANY,\n" +
                    "\t\t\tPRIV_ASSESS,\n" +
                    "\t\t\tNEW_CONTIME,\n" +
                    "\t\t\tNEW_CONACT,\n" +
                    "\t\t\tCUST_STATE,\n" +
                    "\t\t\tTO_CHAR( CREATE_TIME / ( 1000 * 60 * 60 * 24 ) + \n" +
                    "\t\t\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ), 'yyyy-MM-dd HH24:mi:ss' ) CREATE_TIME,\n" +
                    "\t\t\tCUST_PROVIDER,\n" +
                    "\t\t\tCTR_CODE,\n" +
                    "\t\t\tCUST_DELAY,\n" +
                    "\t\t\tIS_VAILD,\n" +
                    "\t\t\tJOIN_USERID \n" +
                    "\t\tFROM\n" +
                    "\t\t\tXY_CRM_CUST \n";
            //判断是否为设计师
            if ("90FFCAB4999A4A4E87BC1CC1125E952F".equals(roleId)){
                tempSql += "\t\tWHERE JOIN_USERID = '"+userId+"' \n";
            } else if("0081BD4E4C9B46BCBD0BD0F23FC481EA".equals(roleId)){
                //判断是否为设计成效人
                tempSql += "\t\tWHERE JOIN_USERID = '"+userId+"' \n";
            } else {
                tempSql += "\t\tWHERE CUST_PROVIDER IN  \n";
                //判断是否为普通员工
                if ("CE554D012D8C4E9B857B1228A33997EB".equals(roleId)){
                    tempSql += "('"+userId+"')";
                } else {
                    //判断是否为首次进信息列表页
                    if ("".equals(selectFlag) || selectFlag == null || "0".equals(selectFlag)){
                        /*tempSql += "(SELECT\n" +
                                "\tA.LOWER_USER_ID\n" +
                                "FROM\n" +
                                "\tXY_CRM_RELATION A \n" +
                                "WHERE\n" +
                                "\tA.EXPRESS = '" + roleId + "'\n" +
                                "START WITH A.USER_ID = '" + userId + "'\n" +
                                "CONNECT BY A.USER_ID = PRIOR A.LOWER_USER_ID\n" +
                                "UNION ALL\n" +
                                "SELECT\n" +
                                "\tA.LOWER_USER_ID\n" +
                                "FROM\n" +
                                "\tXY_CRM_RELATION A \n" +
                                "START WITH A.USER_ID IN (\n" +
                                "\tSELECT\n" +
                                "\t\tB.LOWER_USER_ID\n" +
                                "\tFROM\n" +
                                "\t\tXY_CRM_RELATION B\n" +
                                "\tWHERE \n" +
                                "\t\tB.EXPRESS = '" + roleId + "'\n" +
                                "AND\n" +
                                "\t\tB.USER_ID = '" + userId + "'" +
                                ")\n" +
                                "CONNECT BY A.USER_ID = PRIOR A.LOWER_USER_ID)";*/
                        tempSql += "(" +
                                "SELECT N'" + userId + "' LOWER_USER_ID FROM dual\n" +
                                "UNION ALL\n" +
                                "SELECT B.LOWER_USER_ID FROM (\n" +
                                "\tSELECT\n" +
                                "\t\tA.* \n" +
                                "\tFROM\n" +
                                "\t\tXY_CRM_RELATION A \n" +
                                "\tSTART WITH A.USER_ID = '" + userId + "' CONNECT BY A.USER_ID = PRIOR A.LOWER_USER_ID\n" +
                                ") B\n" +
                                "WHERE B.EXPRESS = '" + roleId + "'" +
                                ")";
                    } else {
                        tempSql += "(SELECT N'" + userId + "' LOWER_USER_ID FROM dual\n" +
                                "UNION ALL\n" +
                                "SELECT\n" +
                                "\tA.LOWER_USER_ID\n" +
                                "FROM\n" +
                                "\tXY_CRM_RELATION A \n" +
                                "START WITH A.USER_ID = '" + userId + "'\n" +
                                "CONNECT BY A.USER_ID = PRIOR A.LOWER_USER_ID)";
                    }
                }
            }
            //判断是否根据信息状态查询
            if (xyCrmCust.getCustState() != null && !"".equals(xyCrmCust.getCustState())){
                tempSql += "\t\tAND CUST_STATE = "+xyCrmCust.getCustState()+"\n";
            }
            tempSql += "\t\tORDER BY CREATE_TIME DESC\n \t) \n";
            //判断是否有查询条件
            if (condition != null && !"".equals(condition)){
                tempSql += "\tWHERE CUST_NAME LIKE '%"+condition+"%' \n" +
                        "\tOR CUST_MOBILE = '"+condition+"' \n" +
                        "\tOR CUST_ADDRESS LIKE '%"+condition+"%' \n" +
                        "\tOR CUST_ADDRESS_DETAIL LIKE '%"+condition+"%' \n";
            }
            tempSql += "\t) A  \n" +
                    ")B\n" +
                    "WHERE RN BETWEEN "+startNum+" AND "+endNum;
            return tempSql;
        }
    }

    /**
     * 根据custId获取提交信息的详情
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/2 9:13
     * @param: [custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tCUST_ID,\n" +
            "\tCUST_NAME,\n" +
            "\tCUST_ADDRESS,\n" +
            "\tCUST_ADDRESS_DETAIL,\n" +
            "\tDECOR_TYPE,\n" +
            "\tCUST_MOBILE,\n" +
            "\tPRIV_TIME,\n" +
            "\tPRIV_COMPANY,\n" +
            "\tPRIV_ASSESS,\n" +
            "\tNEW_CONTIME,\n" +
            "\tNEW_CONACT,\n" +
            "\tCUST_STATE,\n" +
            "\tTO_CHAR( CREATE_TIME / ( 1000 * 60 * 60 * 24 ) + \n" +
            "\tTO_DATE( '1970-01-01 08:00:00', 'yyyy-MM-dd HH24:mi:ss' ), 'yyyy-MM-dd HH24:mi:ss' ) CREATE_TIME,\n" +
            "\t(SELECT USER_NAME FROM XY_USER WHERE USER_ID = CUST_PROVIDER ) CUST_PROVIDER,\n" +
            "\tCTR_CODE,\n" +
            "\tCUST_DELAY,\n" +
            "\tIS_VAILD,\n" +
            "\tJOIN_USERID, \n" +
            "\tJOB_SCHEDULE \n" +
            "FROM\n" +
            "\tXY_CRM_CUST \n" +
            "WHERE\n" +
            "\tCUST_ID = #{custId,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,Object> getCrmCustInfoByCustId(String custId) throws SQLException;

    /**
     * 获取节点情况
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/9 16:52
     * @param: [custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Select("<script>" +
            "SELECT \n" +
            "\t(SELECT COUNT( 1 ) FROM XY_CWB_SK WHERE CTR_CODE = A.CTR_CODE) XY,\n" +
            "\t(SELECT COUNT( 1 ) FROM XY_CUSTOMER_INFO WHERE CTR_CODE = A.CTR_CODE AND CTR_DRAW IS NOT NULL\t) LF,\n" +
            "\t(SELECT COUNT( 1 ) FROM XY_HT_INFO WHERE CTR_CODE = A.CTR_CODE) HT,\n" +
            "\t(SELECT COUNT( 1 ) FROM XY_BJD_MAIN WHERE CTR_CODE = A.CTR_CODE) JCBJ,\n" +
            "\tA.JOB_SCHEDULE\n" +
            "FROM \n" +
            "\tXY_CRM_CUST A\n" +
            "WHERE\n" +
            "\tCUST_ID = #{custId,jdbcType=VARCHAR}" +
            "</script>")
    public Map<String ,Object> getNodeStatu(String custId) throws SQLException;

}
