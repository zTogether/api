package cn.xyzs.api.service;

import cn.xyzs.api.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FieldSupervisionService {

    @Resource
    private XyCwbSkMapper xyCwbSkMapper;

    @Resource
    private XyBjdMainMapper xyBjdMainMapper;

    @Resource
    private XyPgMapper xyPgMapper;

    @Resource
    private XyClbFcCkdMainMapper xyClbFcCkdMainMapper;

    @Resource
    private XyPgLsitMapper xyPgLsitMapper;

    @Resource
    private XyClbFcCkdListMapper xyClbFcCkdListMapper;

    @Resource
    private XyBjdRgListMapper xyBjdRgListMapper;

    /**
     * 获取收款情况
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/22 11:20
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getAccountCollection(String ctrCode){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String,Object>> AAccountCollectionLsit = xyCwbSkMapper.skCondition(ctrCode);
            //工程
            Map<String, Object> projectItem = new HashMap<>();
            projectItem.put("earnestMoney","0");
            projectItem.put("interimPayment","0");
            projectItem.put("finalPayment","0");
            //代购
            Map<String, Object> dgItem = new HashMap<>();
            dgItem.put("earnest","0");
            dgItem.put("prepaidDeposit","0");
            dgItem.put("finalPayment","0");
            //主材套系
            Map<String, Object> zcItem = new HashMap<>();
            zcItem.put("earnestMoney","0");
            zcItem.put("interimPayment","0");
            zcItem.put("earnest","0");
            zcItem.put("finalPayment","0");
            //软装套系
            Map<String, Object> rzItem = new HashMap<>();
            rzItem.put("earnestMoney","0");
            rzItem.put("interimPayment","0");
            rzItem.put("earnest","0");
            rzItem.put("finalPayment","0");
            //其他
            Map<String, Object> otherItem = new HashMap<>();
            //设置收款情况，各项目的初始金额
            otherItem.put("money","0");
            //设置收款情况，各项目的金额
            for (Map<String, Object> map : AAccountCollectionLsit) {
                String money = String.valueOf(map.get("MONEY"));
                String tempItem = String.valueOf(map.get("B"));
                String[] tempItemArray = tempItem.split(",");
                //判断项目（1 ：工程   2 ：代购   3 ：主材套系   4 ：软装套系   5 ：其他）
                if ("1".equals(tempItemArray[0])){
                    if("1".equals(tempItemArray[1])){
                        //诚意金
                        projectItem.put("earnestMoney",money);
                    } else  if("2".equals(tempItemArray[1])){
                        //进度款
                        projectItem.put("interimPayment",money);
                    } else  if("3".equals(tempItemArray[1])){
                        //尾款
                        projectItem.put("finalPayment",money);
                    }
                } else if ("2".equals(tempItemArray[0])){
                    if("1".equals(tempItemArray[1])){
                        //定金
                        dgItem.put("earnest",money);
                    } else  if("2".equals(tempItemArray[1])){
                        //预存款
                        dgItem.put("prepaidDeposit",money);
                    } else  if("3".equals(tempItemArray[1])){
                        //尾款
                        dgItem.put("finalPayment",money);
                    }
                } else if ("3".equals(tempItemArray[0])){
                    if("1".equals(tempItemArray[1])){
                        //诚意金
                        zcItem.put("earnestMoney",money);
                    } else  if("2".equals(tempItemArray[1])){
                        //进度款
                        zcItem.put("interimPayment",money);
                    } else  if("3".equals(tempItemArray[1])){
                        //定金
                        zcItem.put("earnest",money);
                    } else  if("4".equals(tempItemArray[1])){
                        //尾款
                        zcItem.put("finalPayment",money);
                    }
                } else if ("4".equals(tempItemArray[0])){
                    if("1".equals(tempItemArray[1])){
                        //诚意金
                        rzItem.put("earnestMoney",money);
                    } else  if("2".equals(tempItemArray[1])){
                        //进度款
                        rzItem.put("interimPayment",money);
                    } else  if("3".equals(tempItemArray[1])){
                        //定金
                        rzItem.put("earnest",money);
                    } else  if("4".equals(tempItemArray[1])){
                        //尾款
                        rzItem.put("finalPayment",money);
                    }
                } else if ("5".equals(tempItemArray[0])){
                    //金额
                    otherItem.put("money",money);
                }
                //工程
                obj.put("projectItem",projectItem);
                //代购
                obj.put("dgItem",dgItem);
                //主材套系
                obj.put("zcItem",zcItem);
                //软装套系
                obj.put("rzItem",rzItem);
                //其他
                obj.put("otherItem",otherItem);
                code = "200";
                msg = "成功";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取报价清单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/22 14:13
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getXyBjdList(String ctrCode){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            List<Map<String,Object>> bjdList = xyBjdMainMapper.bjdList(ctrCode);
            for (Map<String, Object> map : bjdList) {
                String bjdCode = String.valueOf(map.get("BJD_CODE"));
                //判断是原始单还是增减项，然后添加至map中
                if ("01".equals(bjdCode.substring(10))){
                    map.put("bjType","原始");
                } else {
                    map.put("bjType","增减项");
                }
                obj.put("bjdList",bjdList);
                code = "200";
                msg = "成功";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取工程清单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/22 15:35
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getEngineeringListList(String ctrCode){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            //工程清单
            List<Map<String,Object>> engineeringListList = xyPgMapper.getPrjList(ctrCode);
            obj.put("engineeringListList",engineeringListList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取收款明细
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/22 16:12
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getSkList(String ctrCode){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            //收款明细
            List<Map<String,Object>> skList = xyCwbSkMapper.skList(ctrCode);
            //设置收款内容
            for (Map<String, Object> map : skList) {
                String[] tempItemArray = String.valueOf(map.get("CWB_SK_CONTENT")).split(",");
                if ("1".equals(tempItemArray[0])){
                    String tempVariable = "工程，";
                    if ("1".equals(tempItemArray[1])){
                        tempVariable += "诚意金";
                    } else if("2".equals(tempItemArray[1])){
                        tempVariable += "进度款";
                    } else if("3".equals(tempItemArray[1])){
                        tempVariable += "尾款";
                    }
                    map.put("CWB_SK_CONTENT",tempVariable);
                } else if ("2".equals(tempItemArray[0])){
                    String tempVariable = "代购，";
                    if ("1".equals(tempItemArray[1])){
                        tempVariable += "定金";
                    } else if("2".equals(tempItemArray[1])){
                        tempVariable += "预存款";
                    } else if("3".equals(tempItemArray[1])){
                        tempVariable += "尾款";
                    }
                    map.put("CWB_SK_CONTENT",tempVariable);
                } else if ("3".equals(tempItemArray[0]) || "4".equals(tempItemArray[0])){
                    String tempVariable = "主材套系";
                    if ("4".equals(tempItemArray[0])){
                        tempVariable = "软装套系，";
                    }
                    if ("1".equals(tempItemArray[1])){
                        tempVariable += "诚意金";
                    } else if("2".equals(tempItemArray[1])){
                        tempVariable += "进度款";
                    } else if("3".equals(tempItemArray[1])){
                        tempVariable += "定金";
                    } else if("4".equals(tempItemArray[1])){
                        tempVariable += "尾款";
                    }
                    map.put("CWB_SK_CONTENT",tempVariable);
                }else if ("5".equals(tempItemArray[0])){
                    String tempVariable = "金额";
                    map.put("CWB_SK_CONTENT",tempVariable);
                }
            }
            obj.put("skList",skList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取辅材清单
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/22 16:59
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getFcList(String ctrCode){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            //辅材明细
            List<Map<String,Object>> fcList = xyClbFcCkdMainMapper.fcList(ctrCode);
            obj.put("fcList",fcList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取派单明细
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/23 9:28
     * @param: [pgId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getPgList(String pgId){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            //派工明细
            List<Map<String ,Object>> pgLsit = xyPgLsitMapper.getPgLsit(pgId);
            obj.put("pgLsit",pgLsit);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     * 获取辅材出库明细
     * @Description:
     * @author: zheng shuai
     * @date: 2018/9/23 10:15
     * @param: [ckdCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String ,Object> getFcCkList(String ckdCode){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try {
            //辅材出库明细
            List<Map<String ,Object>> fcCkdList = xyClbFcCkdListMapper.getFcCkdList(ckdCode);
            obj.put("fcCkdList",fcCkdList);
            code = "200";
            msg = "成功";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
        return resultMap;
    }

    /**
     *
     * @Description: 获取报价单详情
     * @author: GeWeiliang
     * @date: 2018\9\23 0023 9:49
     * @param: [ctrCode, rgStage, bjdCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getRgList(String ctrCode,String bjdCode){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        Map<String,Object> prjZJ = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            List<Map<String,Object>> rgList = xyBjdRgListMapper.getBjdRgList(ctrCode,bjdCode);
            List<Map<String,Object>> chaichuList = new ArrayList<>();
            List<Map<String,Object>> biaozhunList = new ArrayList<>();
            List<Map<String,Object>> shuidianList = new ArrayList<>();
            List<Map<String,Object>> wagongList = new ArrayList<>();
            List<Map<String,Object>> mugongList = new ArrayList<>();
            List<Map<String,Object>> youqiList = new ArrayList<>();
            List<Map<String,Object>> otherList = new ArrayList<>();

            for (Map o : rgList) {
                String stage = (String) o.get("BJD_RG_STAGE");
                if ("-5".equals(stage)){
                    chaichuList.add(o);
                    Map<String,Object> chaichuZJ = xyBjdRgListMapper.prjZongJi(ctrCode,bjdCode,stage);
                    System.err.println(chaichuZJ==null);
                    if(chaichuZJ==null){
                        prjZJ.put("chaichuZJ","0");
                    }else{
                        prjZJ.put("chaichuZJ",chaichuZJ);
                    }
                }else if("10".equals(stage)){
                    biaozhunList.add(o);
                    Map<String,Object> biaozhunZJ = xyBjdRgListMapper.prjZongJi(ctrCode,bjdCode,stage);
                    if(biaozhunZJ==null){
                        prjZJ.put("biaozhunZJ","0");
                    }else{
                        prjZJ.put("biaozhunZJ",biaozhunZJ);
                    }
                }else if("20".equals(stage)){
                    shuidianList.add(o);
                    Map<String,Object> shuidianZJ = xyBjdRgListMapper.prjZongJi(ctrCode,bjdCode,stage);
                    if(shuidianZJ==null){
                        prjZJ.put("shuidianZJ","0");
                    }else{
                        prjZJ.put("shuidianZJ",shuidianZJ);
                    }
                }else if("30".equals(stage)){
                    wagongList.add(o);
                    Map<String,Object> wagongZJ = xyBjdRgListMapper.prjZongJi(ctrCode,bjdCode,stage);
                    if(wagongZJ==null){
                        prjZJ.put("wagongZJ","0");
                    }else{
                        prjZJ.put("wagongZJ",wagongZJ);
                    }
                }else if("40".equals(stage)){
                    mugongList.add(o);
                    Map<String,Object> mugongZJ = xyBjdRgListMapper.prjZongJi(ctrCode,bjdCode,stage);
                    if(mugongZJ==null){
                        prjZJ.put("mugongZJ","0");
                    }else{
                        prjZJ.put("mugongZJ",mugongZJ);
                    }
                }else if("50".equals(stage)){
                    youqiList.add(o);
                    Map<String,Object> youqiZJ = xyBjdRgListMapper.prjZongJi(ctrCode,bjdCode,stage);
                    if(youqiZJ==null){
                        prjZJ.put("youqiZJ","0");
                    }else{
                        prjZJ.put("youqiZJ",youqiZJ);
                    }
                }else{
                    otherList.add(o);
                    Map<String,Object> otherZJ = xyBjdRgListMapper.prjZongJi(ctrCode,bjdCode,stage);
                    if(otherZJ==null){
                        prjZJ.put("otherZJ","0");
                    }else{
                        prjZJ.put("otherZJ",otherZJ);
                    }
                }
            }
            obj.put("chaichuList",chaichuList);
            obj.put("biaozhunList",biaozhunList);
            obj.put("shuidianList",shuidianList);
            obj.put("wagongList",wagongList);
            obj.put("mugongList",mugongList);
            obj.put("youqiList",youqiList);
            obj.put("otherList",otherList);
            obj.put("prjZJ",prjZJ);
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

}
