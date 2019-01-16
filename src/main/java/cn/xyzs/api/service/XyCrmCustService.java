package cn.xyzs.api.service;

import cn.xyzs.api.mapper.*;
import cn.xyzs.common.pojo.XyCrmCust;
import cn.xyzs.common.pojo.XyCrmRelation;
import cn.xyzs.common.pojo.XyLog;
import cn.xyzs.common.pojo.XyRoleFuc;
import cn.xyzs.common.pojo.sys.XyWorkAdetail;
import cn.xyzs.common.pojo.sys.XyWorkApply;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class XyCrmCustService {

    @Resource
    private XyCrmCustMapper xyCrmCustMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private XyWorkApplyMapper xyWorkApplyMapper;

    @Resource
    private XyWorkAdetailMapper xyWorkAdetailMapper;

    @Resource
    private XyCrmRelationMapper xyCrmRelationMapper;

    @Resource
    private XyWorkNodeMapper xyWorkNodeMapper;

    @Resource
    private XyCustomerInfoMapper xyCustomerInfoMapper;

    @Resource
    private XyLogMapper xyLogMapper;

    @Resource
    private  XyRoleFucMapper xyRoleFucMapper;

    @Resource
    private VwXyWorkInfoMapper vwXyWorkInfoMapper;

    /**
     * 添加意向信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 9:42
     * @param: [xyCrmCust]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> addCrmCust(XyCrmCust xyCrmCust ,String xyUserId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try {
            Date date = new Date();
            Long nowTime = date.getTime();
            xyCrmCust.setCreateTime(String.valueOf(nowTime));
            xyCrmCustMapper.addCrmCust(xyCrmCust);
            XyWorkApply xyWorkApply = new XyWorkApply();
            xyWorkApply.setApplyUserid(xyUserId);
            xyWorkApply.setApplyContent(xyCrmCust.getCustId());
            xyWorkApply.setActId("ABBF0CF0C1C64B388AC2675DCE85B677");
            xyWorkApply.setApplyTitle("有效信息转换");
            xyWorkApply.setApplyAddtime(String.valueOf(nowTime));
            xyWorkApplyMapper.addWorkApply(xyWorkApply);
            XyWorkAdetail xyWorkAdetail = new XyWorkAdetail();
            xyWorkAdetail.setNodeId(String.valueOf(xyWorkNodeMapper.getFristNodeInfoByFlowId("ABBF0CF0C1C64B388AC2675DCE85B677").get("NODE_ID")));
            xyWorkAdetail.setApplyId(xyWorkApply.getApplyId());
            xyWorkAdetailMapper.addAfterNodeInfo(xyWorkAdetail);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 修改信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 9:43
     * @param: [xyCrmCust]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> updateCrmCustByCustId(XyCrmCust xyCrmCust){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            Map<String ,Object> custCust = xyCrmCustMapper.getCrmCustInfoByCustId(xyCrmCust.getCustId());
            if ("2".equals(custCust.get("CUST_STATE"))){
                obj.put("custCust",custCust);
                code = "300";
                msg = "此信息已为有效信息无法进行修改";
            } else {
                xyCrmCustMapper.updateCrmCustByCustId(xyCrmCust);
                custCust = xyCrmCustMapper.getCrmCustInfoByCustId(xyCrmCust.getCustId());
                obj.put("custCust",custCust);
                code = "200";
                msg = "修改成功";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取信息提供人
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 10:02
     * @param: [condition]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getCustProvider(String condition){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            List<Map<String ,Object>> custProviderList = userMapper.getCustProvider(condition);
            obj.put("custProviderList",custProviderList);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取提交的意向信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/1 14:10
     * @param: [xyCrmCust, condition, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getCrmCustByCondition(XyCrmCust xyCrmCust , String condition , Integer startNum ,
                                                     Integer endNum ,String selectFlag ,String roleId ,String userId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            List<Map<String ,Object>> custCustList = xyCrmCustMapper.getCrmCustByCondition(xyCrmCust,condition,
                    startNum,endNum,selectFlag,roleId,userId);
            obj.put("custCustList",custCustList);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取下属人数
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/4 16:58
     * @param: [express, userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getSubordinateCount(String express ,String userId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try {
            Integer subordinateCount = xyCrmRelationMapper.getSubordinateCount(express,userId);
            if (subordinateCount < 1){
                code = "404";
                msg = "您暂无下属";
            } else {
                code = "200";
                msg = "成功";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 根据userId和roleId获取下属
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/4 13:27
     * @param: [xyCrmRelation]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getSubordinateByUserIdAndRoleId(String express ,String userId ,String condition){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            List<Map<String ,Object>> subordinateList = xyCrmRelationMapper.getSubordinateByUserIdAndRoleId(express,userId,condition);
            obj.put("subordinateList",subordinateList);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 根据custId获取提交信息的详情
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/2 9:16
     * @param: [custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getInfoDetailData(String custId ,String roleId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        Map<String ,Object> waitDisposeMatterNodeInfo = new HashMap<>();
        try {

            //八个节点数据
            Map<String ,Object> nodeStatuInfo = xyCrmCustMapper.getNodeStatu(custId);
            List<Map<String ,Object>> nodeStatuData = getNodeStatuData(nodeStatuInfo);
            obj.put("nodeStatuData",nodeStatuData);

            //信息详情信息
            Map<String ,Object> custCust = xyCrmCustMapper.getCrmCustInfoByCustId(custId);
            obj.put("custCust",custCust);

            //初始化操作按钮list
            List<Map<String ,Object>> btnsInfo = new ArrayList<>();
            //判断是否已申请
            Integer count = xyWorkApplyMapper.getCountByCustId(custId);
            //判断是否为店面成效人且count<1
            if (count < 1 && "5E9F63D495F8490BA6EF69F37322408B".equals(roleId)){
               /* //虚拟第一个节点
                //节点名称
                waitDisposeMatterNodeInfo.put("NODE_NAME","有效信息转换");
                //操作按钮map
                Map<String ,Object> tempMap = new HashMap<>();
                //按钮名称
                tempMap.put("buttonName","提交");
                //下一个节点id
                tempMap.put("afterNodeId",xyWorkNodeMapper.getFristNodeInfoByFlowId("ABBF0CF0C1C64B388AC2675DCE85B677").get("NODE_ID"));
                //节点操作进程状态
                tempMap.put("wadOperation","0");
                //判断是否为第一个虚拟节点（0：第一个虚拟节点   1：不为第一个虚拟节点）
                tempMap.put("flag","0");
                //判断是否为第一个流程（0：第一个流程   1：第二个流程）
                tempMap.put("nodeFlag","0");
                btnsInfo.add(tempMap);
                obj.put("btnsInfo",btnsInfo);
                obj.put("waitDisposeMatterNodeInfo",waitDisposeMatterNodeInfo);*/
            } else {
                //获取下一个代办节点信息
                waitDisposeMatterNodeInfo = xyWorkAdetailMapper.getWaitDisposeMatterNodeInfo(custId,roleId,"ABBF0CF0C1C64B388AC2675DCE85B677");
                //判断第一个节点是否已结束
                if (waitDisposeMatterNodeInfo != null && !"3".equals(waitDisposeMatterNodeInfo.get("NODE_TYPE"))){
                    //是按钮
                    if (!"".equals(waitDisposeMatterNodeInfo.get("CONFIRMNODE_BUTTON")) &&
                            !"null".equals(waitDisposeMatterNodeInfo.get("CONFIRMNODE_BUTTON")) &&
                            waitDisposeMatterNodeInfo.get("CONFIRMNODE_BUTTON") != null){
                        Map<String ,Object> tempMap = new HashMap<>();
                        tempMap.put("wadId",waitDisposeMatterNodeInfo.get("WAD_ID"));
                        tempMap.put("buttonName",waitDisposeMatterNodeInfo.get("CONFIRMNODE_BUTTON"));
                        tempMap.put("afterNodeId",waitDisposeMatterNodeInfo.get("CONFIRMNODE_ID"));
                        tempMap.put("wadOperation","1");
                        tempMap.put("flag","1");
                        tempMap.put("nodeFlag","0");
                        btnsInfo.add(tempMap);
                    }

                    //否按钮
                    if (!"".equals(waitDisposeMatterNodeInfo.get("CANCENODE_BUTTON")) &&
                            !"null".equals(waitDisposeMatterNodeInfo.get("CANCENODE_BUTTON")) &&
                            waitDisposeMatterNodeInfo.get("CANCENODE_BUTTON") != null){
                        Map<String ,Object> tempMap = new HashMap<>();
                        tempMap.put("wadId",waitDisposeMatterNodeInfo.get("WAD_ID"));
                        tempMap.put("buttonName",waitDisposeMatterNodeInfo.get("CANCENODE_BUTTON"));
                        tempMap.put("afterNodeId",waitDisposeMatterNodeInfo.get("CANCENODE_ID"));
                        tempMap.put("wadOperation","3");
                        tempMap.put("flag","1");
                        tempMap.put("nodeFlag","0");
                        btnsInfo.add(tempMap);
                    }
                    obj.put("btnsInfo",btnsInfo);
                    obj.put("waitDisposeMatterNodeInfo",waitDisposeMatterNodeInfo);
                } else {
                    //进入第二个节点流程
                    waitDisposeMatterNodeInfo = xyWorkAdetailMapper.getWaitDisposeMatterNodeInfo(custId,roleId,"3B258CA43E8D46D68BB19EBA8772596C");
                    String tempCtrCode = String.valueOf(custCust.get("CTR_CODE"));
                    if (waitDisposeMatterNodeInfo == null && count == 1 && !"".equals(tempCtrCode) && !"null".equals(tempCtrCode)){
                        boolean isShowB = false;
                        //如果角色为设计成效人
                        if ("0081BD4E4C9B46BCBD0BD0F23FC481EA".equals(roleId)){
                            String isShow = xyCustomerInfoMapper.isShow(String.valueOf(custCust.get("CTR_CODE")));
                            if ("0".equals(isShow)){
                                isShowB = true;
                            }
                        } else if ("4BCA6B6242354837A8A990BB207B2FFF".equals(roleId)){
                            //如果角色为合约成效人
                            isShowB = true;
                        }
                        if (isShowB){
                            waitDisposeMatterNodeInfo = new HashMap<>();
                            waitDisposeMatterNodeInfo.put("NODE_NAME","申请攻关单");
                            Map<String ,Object> tempMap = new HashMap<>();
                            tempMap.put("buttonName","申请攻关单");
                            tempMap.put("afterNodeId",xyWorkNodeMapper.getFristNodeInfoByFlowId("3B258CA43E8D46D68BB19EBA8772596C").get("NODE_ID"));
                            tempMap.put("wadOperation","1");
                            tempMap.put("flag","0");
                            tempMap.put("nodeFlag","1");
                            btnsInfo.add(tempMap);
                            obj.put("btnsInfo",btnsInfo);
                            obj.put("waitDisposeMatterNodeInfo",waitDisposeMatterNodeInfo);
                        }
                    } else if (count == 2){

                        if (waitDisposeMatterNodeInfo != null){

                            boolean isShowB = false;
                            //如果角色为普通员工
                            if ("CE554D012D8C4E9B857B1228A33997EB".equals(roleId)){
                                String isShow = xyWorkApplyMapper.isShow(custId);
                                if ("0".equals(isShow)){
                                    isShowB = true;
                                }
                            } else if ("A3224B89C92A4F4593C8B755FE0BC645".equals(roleId)){
                                //如果角色为品推
                                String isShow = xyWorkAdetailMapper.isShow(
                                        String.valueOf(xyWorkNodeMapper.getFristNodeInfoByFlowId("3B258CA43E8D46D68BB19EBA8772596C").get("NODE_ID")),
                                        custId);
                                if ("0".equals(isShow)){
                                    isShowB = true;
                                }
                            }

                            if (isShowB){
                                //是按钮
                                if (!"".equals(waitDisposeMatterNodeInfo.get("CONFIRMNODE_BUTTON")) &&
                                        !"null".equals(waitDisposeMatterNodeInfo.get("CONFIRMNODE_BUTTON")) &&
                                        waitDisposeMatterNodeInfo.get("CONFIRMNODE_BUTTON") != null){
                                    Map<String ,Object> tempMap = new HashMap<>();
                                    tempMap.put("wadId",waitDisposeMatterNodeInfo.get("WAD_ID"));
                                    tempMap.put("buttonName",waitDisposeMatterNodeInfo.get("CONFIRMNODE_BUTTON"));
                                    tempMap.put("afterNodeId",waitDisposeMatterNodeInfo.get("CONFIRMNODE_ID"));
                                    tempMap.put("wadOperation","1");
                                    tempMap.put("flag","1");
                                    tempMap.put("nodeFlag","1");
                                    btnsInfo.add(tempMap);
                                }

                                //否按钮
                                if (!"".equals(waitDisposeMatterNodeInfo.get("CANCENODE_BUTTON")) &&
                                        !"null".equals(waitDisposeMatterNodeInfo.get("CANCENODE_BUTTON")) &&
                                        waitDisposeMatterNodeInfo.get("CANCENODE_BUTTON") != null){
                                    Map<String ,Object> tempMap = new HashMap<>();
                                    tempMap.put("wadId",waitDisposeMatterNodeInfo.get("WAD_ID"));
                                    tempMap.put("buttonName",waitDisposeMatterNodeInfo.get("CANCENODE_BUTTON"));
                                    tempMap.put("afterNodeId",waitDisposeMatterNodeInfo.get("CANCENODE_ID"));
                                    tempMap.put("wadOperation","3");
                                    tempMap.put("flag","1");
                                    tempMap.put("nodeFlag","1");
                                    btnsInfo.add(tempMap);
                                }
                                obj.put("btnsInfo",btnsInfo);
                                obj.put("waitDisposeMatterNodeInfo",waitDisposeMatterNodeInfo);
                            }
                        }



                    }
                }

            }
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    private List<Map<String ,Object>> getNodeStatuData(Map<String ,Object> nodeStatuInfo){
        List<Map<String ,Object>> nodeStatuData = new ArrayList<>();
        String tempVariable = String.valueOf(nodeStatuInfo.get("JOB_SCHEDULE"));
        //量房图纸
        String lftz = String.valueOf(nodeStatuInfo.get("LF"));
        //量房验证
        String lfyz = tempVariable.substring(0,1);
        //设计方案
        String sjfa = tempVariable.substring(1,2);
        //协议
        String xy = String.valueOf(nodeStatuInfo.get("XY"));
        //基础报价
        String jcbj = String.valueOf(nodeStatuInfo.get("JCBJ"));
        //人工费深度告知
        String rgf = tempVariable.substring(2,3);
        //合同
        String ht = String.valueOf(nodeStatuInfo.get("HT"));
        //双馆体验
        String sg = tempVariable.substring(3);

        Map<String ,Object> tempMap = new HashMap<>();
        tempMap.put("nodeName","量房图纸");
        tempMap.put("backGroundColor","0".equals(lftz)?"#ccc":"#FF7F00");
        nodeStatuData.add(tempMap);

        tempMap = new HashMap<>();
        tempMap.put("nodeName","量房验证");
        tempMap.put("backGroundColor","0".equals(lfyz)?"#ccc":"#FF7F00");
        nodeStatuData.add(tempMap);

        tempMap = new HashMap<>();
        tempMap.put("nodeName","设计方案");
        tempMap.put("backGroundColor","0".equals(sjfa)?"#ccc":"#FF7F00");
        nodeStatuData.add(tempMap);

        tempMap = new HashMap<>();
        tempMap.put("nodeName","协议");
        tempMap.put("backGroundColor","0".equals(xy)?"#ccc":"#FF7F00");
        nodeStatuData.add(tempMap);

        tempMap = new HashMap<>();
        tempMap.put("nodeName","基础报价");
        tempMap.put("backGroundColor","0".equals(jcbj)?"#ccc":"#FF7F00");
        nodeStatuData.add(tempMap);

        tempMap = new HashMap<>();
        tempMap.put("nodeName","人工费深度告知");
        tempMap.put("backGroundColor","0".equals(rgf)?"#ccc":"#FF7F00");
        nodeStatuData.add(tempMap);

        tempMap = new HashMap<>();
        tempMap.put("nodeName","合同");
        tempMap.put("backGroundColor","0".equals(ht)?"#ccc":"#FF7F00");
        nodeStatuData.add(tempMap);

        tempMap = new HashMap<>();
        tempMap.put("nodeName","双馆体验");
        tempMap.put("backGroundColor","0".equals(sg)?"#ccc":"#FF7F00");
        nodeStatuData.add(tempMap);

        return nodeStatuData;
    }

    /**
     * 获取信息历史处理记录
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/3 17:15
     * @param: [custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getInfoHistoryFlow(String custId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            Map<String ,Object> custCust = xyCrmCustMapper.getCrmCustInfoByCustId(custId);
            List<Map<String ,Object>> infoHistoryFlowList = vwXyWorkInfoMapper.getInfoHistoryFlowList(custId);
            for (Map<String, Object> map : infoHistoryFlowList) {
                if ("初次报价".equals(map.get("RESULT"))){
                    map.put("RESULT","基础报价");
                } else if ("收款".equals(map.get("RESULT"))){
                    map.put("RESULT","协议");
                } else if ("确认图纸".equals(map.get("RESULT"))){
                    map.put("RESULT","量房图纸");
                } else if ("生成草稿合同".equals(map.get("RESULT"))){
                    map.put("RESULT","合同");
                }

                map.put("BTIME",String.valueOf(map.get("BTIME")).substring(0,11));
            }
            obj.put("custCust",custCust);
            obj.put("infoHistoryFlowList",infoHistoryFlowList);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取分析图数据
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/4 10:35
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getAnalyzeImgData(String express , String userId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            Map<String ,Object> analyzeImgData = xyWorkAdetailMapper.getAnalyzeImgData(express,userId);
            List<Object> dateList = new ArrayList<>();
            dateList.add(analyzeImgData.get("A_DATE"));
            dateList.add(analyzeImgData.get("B_DATE"));
            dateList.add(analyzeImgData.get("C_DATE"));
            dateList.add(analyzeImgData.get("D_DATE"));
            dateList.add(analyzeImgData.get("E_DATE"));
            List<Object> countList = new ArrayList<>();
            countList.add(analyzeImgData.get("A"));
            countList.add(analyzeImgData.get("B"));
            countList.add(analyzeImgData.get("C"));
            countList.add(analyzeImgData.get("D"));
            countList.add(analyzeImgData.get("E"));

            Integer count = Integer.valueOf(String.valueOf(analyzeImgData.get("A")));
            count += Integer.valueOf(String.valueOf(analyzeImgData.get("B")));
            count += Integer.valueOf(String.valueOf(analyzeImgData.get("C")));
            count += Integer.valueOf(String.valueOf(analyzeImgData.get("D")));
            count += Integer.valueOf(String.valueOf(analyzeImgData.get("E")));

            obj.put("dateList",dateList);
            obj.put("countList",countList);
            obj.put("count",count);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 信息处理
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 9:35
     * @param: [custId, flag, afterNodeId, wadOperation, wadId, nodeName, wadRemark, xyUserId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> dispose(String custId ,String flag ,String afterNodeId ,String wadOperation ,
                                       String wadId ,String nodeName,String wadRemark ,String xyUserId ,String nodeFlag){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {

            Integer isDispose = null;
            if (!"".equals(wadId) &&  !"null".equals(wadId) && wadId != null){
                isDispose = xyWorkAdetailMapper.isDispose(custId,wadId);
            } else {
                isDispose = xyWorkAdetailMapper.isDispose1(custId,afterNodeId);
            }
            if (isDispose < 1){
                if ("0".equals(flag)){
                    XyWorkApply xyWorkApply = new XyWorkApply();
                    xyWorkApply.setApplyUserid(xyUserId);
                    xyWorkApply.setApplyContent(custId);
                    XyCrmCust xyCrmCust = new XyCrmCust();
                    xyCrmCust.setCustState("3");
                    xyCrmCust.setCustId(custId);
                    xyCrmCustMapper.updateCrmCustByCustId(xyCrmCust);
                    xyWorkApply.setActId("3B258CA43E8D46D68BB19EBA8772596C");
                    xyWorkApply.setApplyTitle("攻关单处理");
                    Date date = new Date();
                    Long nowTime = date.getTime();
                    xyWorkApply.setApplyAddtime(String.valueOf(nowTime));
                    xyWorkApplyMapper.addWorkApply(xyWorkApply);
                    XyWorkAdetail xyWorkAdetail = new XyWorkAdetail();
                    xyWorkAdetail.setNodeId(afterNodeId);
                    xyWorkAdetail.setApplyId(xyWorkApply.getApplyId());
                    xyWorkAdetailMapper.addAfterNodeInfo(xyWorkAdetail);
                    code = "200";
                    msg = "";
                } else {
                    if ("合约成效人派单".equals(nodeName)){
                        //告知前台需要选择设计成效人
                        Map<String ,Object> resultDisposeInfo = new HashMap<>();
                        resultDisposeInfo.put("afterNodeId",afterNodeId);
                        resultDisposeInfo.put("wadOperation",wadOperation);
                        resultDisposeInfo.put("wadId",wadId);
                        resultDisposeInfo.put("custId",custId);
                        obj.put("resultDisposeInfo",resultDisposeInfo);
                        code = "801";
                        msg = "需要选择设计成效人";
                    } else if ("设计派单".equals(nodeName)) {
                        //告知前台需要选择下属设计师
                        Map<String ,Object> resultDisposeInfo = new HashMap<>();
                        resultDisposeInfo.put("afterNodeId",afterNodeId);
                        resultDisposeInfo.put("wadOperation",wadOperation);
                        resultDisposeInfo.put("wadId",wadId);
                        resultDisposeInfo.put("custId",custId);
                        obj.put("resultDisposeInfo",resultDisposeInfo);
                        code = "802";
                        msg = "需要选择下属设计师";
                    } else {
                        XyWorkAdetail xyWorkAdetail = new XyWorkAdetail();
                        xyWorkAdetail.setXyUserId(xyUserId);
                        xyWorkAdetail.setWadRemark(wadRemark);
                        xyWorkAdetail.setWadOperation(wadOperation);
                        Date date = new Date();
                        Long nowTime = date.getTime();
                        xyWorkAdetail.setWadAddtime(String.valueOf(nowTime));
                        xyWorkAdetail.setWadId(wadId);
                        xyWorkAdetailMapper.updateWorkDetail(xyWorkAdetail);
                        if (!"".equals(afterNodeId) && !"null".equals(afterNodeId) && afterNodeId != null){
                            xyWorkAdetail.setNodeId(afterNodeId);
                            xyWorkAdetail.setApplyId(xyWorkApplyMapper.getApplyId(custId));
                            xyWorkAdetailMapper.addAfterNodeInfo(xyWorkAdetail);
                        }
                        xyWorkApplyMapper.updateApplyState(custId,wadId);
                        XyCrmCust xyCrmCust = new XyCrmCust();

                        if(!"流单确认".equals(nodeName) && !"品推信息转入".equals(nodeName) ){
                            if ("1".equals(wadOperation)){
                                xyCrmCust.setCustState("2");
                            } else {
                                xyCrmCust.setCustState("1");
                            }
                            xyCrmCust.setCustId(custId);
                            xyCrmCustMapper.updateCrmCustByCustId(xyCrmCust);
                        }

                        code = "200";
                        msg = "";
                    }
                }
            } else {
                code = "401";
                msg = "此节点已被他人处理";
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取设计成效人
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 9:48
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getSjcxrInfoList(){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            List<Map<String ,Object>> sjcxrInfoList = userMapper.getUserInfoByRoleId("0081BD4E4C9B46BCBD0BD0F23FC481EA");
            obj.put("sjcxrInfoList",sjcxrInfoList);
            code = "200";
            msg = "";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 选择设计成效人执行派单
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 10:42
     * @param: [custId, afterNodeId, wadOperation, wadId, wadRemark, xyUserId, sjcxr]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> selectSjcxr(String custId ,String afterNodeId ,String wadOperation ,
                                           String wadId ,String wadRemark ,String xyUserId ,String sjcxr){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try {
            Integer isDispose = null;
            if (!"".equals(wadId) &&  !"null".equals(wadId) && wadId != null){
                isDispose = xyWorkAdetailMapper.isDispose(custId,wadId);
            } else {
                isDispose = xyWorkAdetailMapper.isDispose1(custId,afterNodeId);
            }
            if (isDispose < 1){

                XyWorkAdetail xyWorkAdetail = new XyWorkAdetail();
                xyWorkAdetail.setXyUserId(xyUserId);
                xyWorkAdetail.setWadRemark(wadRemark);
                xyWorkAdetail.setWadOperation(wadOperation);
                Date date = new Date();
                Long nowTime = date.getTime();
                xyWorkAdetail.setWadAddtime(String.valueOf(nowTime));
                xyWorkAdetail.setWadId(wadId);
                xyWorkAdetailMapper.updateWorkDetail(xyWorkAdetail);
                xyWorkAdetail.setNodeId(afterNodeId);
                xyWorkAdetail.setApplyId(xyWorkApplyMapper.getApplyId(custId));
                xyWorkAdetailMapper.addAfterNodeInfo(xyWorkAdetail);
                xyWorkApplyMapper.updateApplyState(custId,wadId);
                XyCrmCust xyCrmCust = new XyCrmCust();
                xyCrmCust.setCustId(custId);
                xyCrmCust.setJoinUserid(sjcxr);
                xyCrmCustMapper.updateCrmCustByCustId(xyCrmCust);
                code = "200";
                msg = "";
            } else {
                code = "401";
                msg = "此节点已被他人处理";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     *  获取设计师
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 12:45
     * @param: [custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getSjsList(String custId) {
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            List<Map<String ,Object>> sjsInfoList = userMapper.getShopSjsByCustId(custId);
            obj.put("sjsInfoList",sjsInfoList);
            code = "200";
            msg = "";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 选择设计师执行派单
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/6 13:51
     * @param: [custId, afterNodeId, wadOperation, wadId, wadRemark, xyUserId, sjs]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> selectSjs(String custId ,String afterNodeId ,String wadOperation ,
                                           String wadId ,String wadRemark ,String xyUserId ,String sjs){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try {
            Integer isDispose = null;
            if (!"".equals(wadId) &&  !"null".equals(wadId) && wadId != null){
                isDispose = xyWorkAdetailMapper.isDispose(custId,wadId);
            } else {
                isDispose = xyWorkAdetailMapper.isDispose1(custId,afterNodeId);
            }
            if (isDispose < 1){
                Map<String ,Object> custInfo = xyCrmCustMapper.getCrmCustInfoByCustId(custId);
                String tempVariable = String.valueOf(custInfo.get("CUST_ADDRESS")) + String.valueOf(custInfo.get("CUST_ADDRESS_DETAIL"));
                Integer exists = xyCustomerInfoMapper.existsByCtrAddr(tempVariable);
                if (exists > 0){
                    code = "400";
                    msg = "此信息地址与现有客户地址重复，请核实后重试！";
                } else {
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy");
                    String newestCtrCode = xyCustomerInfoMapper.getNewestCtrCode(format.format(date));
                    if (newestCtrCode == null || newestCtrCode == ""){
                        newestCtrCode = format.format(date) + "000001";
                    }
                    xyCustomerInfoMapper.pdAddCustInfo(newestCtrCode,sjs,custId);
                    XyCrmCust xyCrmCust = new XyCrmCust();
                    xyCrmCust.setCustId(custId);
                    xyCrmCust.setCtrCode(newestCtrCode);
                    //xyCrmCust.setJoinUserid(sjs);
                    xyCrmCustMapper.updateCrmCustByCustId(xyCrmCust);
                    XyWorkAdetail xyWorkAdetail = new XyWorkAdetail();
                    xyWorkAdetail.setXyUserId(xyUserId);
                    xyWorkAdetail.setWadRemark(wadRemark);
                    xyWorkAdetail.setWadOperation(wadOperation);
                    Long nowTime = date.getTime();
                    xyWorkAdetail.setWadAddtime(String.valueOf(nowTime));
                    xyWorkAdetail.setWadId(wadId);
                    xyWorkAdetailMapper.updateWorkDetail(xyWorkAdetail);
                    //xyWorkAdetail.setNodeId(afterNodeId);
                    //xyWorkAdetail.setApplyId(xyWorkApplyMapper.getApplyId(custId));
                    //xyWorkAdetailMapper.addAfterNodeInfo(xyWorkAdetail);
                    xyWorkApplyMapper.updateApplyState(custId,wadId);
                    code = "200";
                    msg = "";
                }
            } else {
                code = "401";
                msg = "此节点已被他人处理";
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 处理节点
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/10 10:25
     * @param: [jobSchedule, custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String ,Object> disposeNode(HttpServletRequest request ,String roleId ,String userId ,String jobSchedule ,
                                           String custId ,String nodeName) {
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        try {
            XyRoleFuc xyRoleFuc = new XyRoleFuc();
            xyRoleFuc.setRoleId(roleId);
            xyRoleFuc.setCompoId("FA7C6EC14556431494C88F9A5B9F3EB9");
            List<Map<String ,Object>> roleFucList = xyRoleFucMapper.getRolefuc(xyRoleFuc);

            XyLog xyLog = new XyLog();
            //操作人id
            xyLog.setUserId(userId);
            xyLog.setCompoId(String.valueOf(roleFucList.get(0).get("COMPO_ID")));
            xyLog.setOpId(String.valueOf(roleFucList.get(0).get("OP_ID")));
            //操作人ip
            xyLog.setLogIp(getIpAddr(request));
            //节点名称
            xyLog.setLogResult(nodeName);
            //ctrCode
            xyLog.setLogDataid(String.valueOf(xyCrmCustMapper.getCrmCustInfoByCustId(custId).get("CTR_CODE")));
            xyLogMapper.addXyLog(xyLog);
            XyCrmCust xyCrmCust = new XyCrmCust();
            xyCrmCust.setCustId(custId);
            xyCrmCust.setJobSchedule(jobSchedule);
            xyCrmCustMapper.updateCrmCustByCustId(xyCrmCust);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 获取提交信息详情信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/10 11:29
     * @param: [custId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getCustInfoData(String custId){
        String code = "500";
        String msg = "系统异常";
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        try {
            Map<String ,Object> custInfoData = xyCrmCustMapper.getCrmCustInfoByCustId(custId);
            obj.put("custInfoData",custInfoData);
            code = "200";
            msg = "";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
        }
        return resultMap;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/12 9:50
     * @param: [request]
     * @return: java.lang.String
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            System.out.println("Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            System.out.println("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            System.out.println("HTTP_CLIENT_IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            System.out.println("X-Real-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            System.out.println("getRemoteAddr ip: " + ip);
        }
        System.out.println("获取客户端ip: " + ip);
        return ip;
    }

}
