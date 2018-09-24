package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyBjdRgListMapper;
import cn.xyzs.api.mapper.XyPgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XyPrjService {
    @Resource
    private XyPgMapper xyPgMapper;

    @Resource
    private XyBjdRgListMapper xyBjdRgListMapper;

    /**
     *
     * @Description: 工程清单
     * @author: GeWeiliang
     * @date: 2018\9\19 0019 14:00
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getPrjList(String ctrCode){
        Map<String,Object> resultMap = new HashMap<>();
        List<Map<String,Object>> obj = new ArrayList<>();
        String code = "500";
        String msg = "系统异常";
        try{
            obj =  xyPgMapper.getPrjList(ctrCode);
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
     *
     * @Description: 收款明细
     * @author: GeWeiliang
     * @date: 2018\9\19 0019 14:16
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getSkList(String ctrCode){
        Map<String,Object> resultMap = new HashMap<>();
        List<Map<String,Object>> obj = new ArrayList<>();
        String code = "500";
        String msg = "msg";
        try{
            obj = xyPgMapper.skList(ctrCode);
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
     *
     * @Description: 辅材清单
     * @author: GeWeiliang
     * @date: 2018\9\19 0019 14:23
     * @param: [ctrCode]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> getFcList(String ctrCode){
        Map<String,Object> resultMap = new HashMap<>();
        List<Map<String,Object>> obj = new ArrayList<>();
        String code = "500";
        String msg = "系统异常";
        try{
            obj = xyPgMapper.fcList(ctrCode);
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
