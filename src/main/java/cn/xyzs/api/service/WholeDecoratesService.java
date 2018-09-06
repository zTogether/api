package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyClbZctxMbMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WholeDecoratesService {
    @Resource
    private XyClbZctxMbMapper xyClbZctxMbMapper;

    /***
     *
     * @Description: 展示主材套系vr
     * @author: GeWeiliang
     * @date: 2018\9\2 0002 17:47
     * @param:
     * @return:
     */
    public Map<String,Object> showZctxVr(String vrStyle){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            List<Map<String,Object>> vrList = xyClbZctxMbMapper.showZctxVr(vrStyle);
            obj.put("zctxVr",vrList);
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

    public Map<String,Object> txDetail(String vrId){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            obj = xyClbZctxMbMapper.vrDetail(vrId);
            String pic = (String)obj.get("VR_PIC");
            GoodService goodService = new GoodService();
            List<String> picList = goodService.conversionList(pic);
            obj.put("picList",picList);
            code = "200";
            msg = "成功";
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            resultMap.put("resultData",obj);
        }
            return  resultMap;
    }

    /***
     *
     * @Description: 套系材料列表
     * @author: GeWeiliang
     * @date: 2018\9\3 0003 16:47
     * @param: [vrId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> txClList(String vrId,String flBh){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> obj = new HashMap<>();
        String code = "500";
        String msg = "系统异常";
        try{
            List<Map<String,Object>> clList = xyClbZctxMbMapper.txClList(vrId,flBh);
            obj.put("clList",clList);
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
