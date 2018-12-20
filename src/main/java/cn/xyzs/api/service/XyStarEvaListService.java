package cn.xyzs.api.service;

import cn.xyzs.api.mapper.XyStarEvaListMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XyStarEvaListService {
    @Resource
    private XyStarEvaListMapper xyStarEvaMainMapper;


    /**
     *
     * @Description: 添加星级评论
     * @author: GeWeiliang
     * @date: 2018\11\2 0002 13:15
     * @param: [evaNo, evaType, level, evaluation, evaName]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @Transactional
    public Map<String,Object> addStarEva(String evaNo,String evaType,String quality,String evaluation,String evaName,
                                         String service,String days,String hygiene){
        Map<String,Object> resultMap = new HashMap<>();
        String msg = "系统异常";
        String code = "500";
        String pgStage1 = "";
        String pgStage2 = "";
        String priv = "0";
        String priv2 = "0";
        String grId = "";
        try{
            xyStarEvaMainMapper.addEvaList(evaNo,quality,evaluation,evaName,evaType,service,days,hygiene);
            //获取最小值评分
            String[] l = {quality,service,days,hygiene};
            int minScore = Integer.parseInt(l[0]);
            for (int i=0;i<l.length-1;i++){
                if(l[i].compareTo(l[i+1])<0){
                    minScore = Integer.parseInt(l[i]);
                }
            }

            if(evaName.equals("20")){
                evaName = "22";
            }
            //是否特权1:特权，0:非特权
            if(evaName.equals("30")){
                pgStage1 = "31";
                pgStage2 = "32";
                List<Map<String,Object>> list = xyStarEvaMainMapper.getIsPriv(evaNo,pgStage1,pgStage2);
                if(list.size()==1){
                    priv = String.valueOf(list.get(0).get("PRIV_YN"));
                }else{
                    priv = String.valueOf(list.get(0).get("PRIV_YN"));
                    priv2 = String.valueOf(list.get(1).get("PRIV_YN"));
                }
                grId = String.valueOf(list.get(0).get("GR_ID"));
                if(priv.equals("1")||priv2.equals("1")){
                    priv = "1";
                }
            }else{
                pgStage1 = evaName;
                List<Map<String,Object>> list = xyStarEvaMainMapper.getIsPriv(evaNo,pgStage1,pgStage2);
                priv = String.valueOf(list.get(0).get("PRIV_YN"));
                grId = String.valueOf(list.get(0).get("GR_ID"));
            }
            //修改工人级别
            UpdateGrlevel(grId,priv,minScore);
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

    public void UpdateGrlevel(String grId,String priv,int minScore){
        try {
            //获取当前等级
            int curLevel = Integer.parseInt(xyStarEvaMainMapper.getCruLevel(grId).get("GR_LEVEL").toString());
            if(priv.equals("1")){//特权
                if(minScore==5){//特权5星等级+1
                    curLevel = curLevel+1;
                }else if (minScore==2||minScore==1){//特权，1-2星等级-1
                    if(curLevel>0){
                        curLevel = curLevel-1;
                    }
                }
                //特权，3-4星等级不变
            }else{//非特权
                if(minScore==5){
                    if(curLevel<6){//5星小于6级等级+1
                        curLevel ++;
                    }else if(curLevel>6){//5星大于6级等级-1
                        curLevel --;
                    }
                    //5星等于6级不变
                }else if(minScore==3||minScore==4){//3-4星
                    if(curLevel>6){//等级大于6，等级-2
                        curLevel = curLevel - 2;
                    }else if(curLevel==6){//等级等于6,等级-1
                        curLevel = curLevel - 1;
                    }
                    //等级等于6不变
                }else if(minScore==1||minScore==2){
                    if (curLevel<6&&curLevel>0){
                        curLevel --;
                    }else if(curLevel==6){
                        curLevel = curLevel - 2;
                    }else if (curLevel>6){
                        curLevel = curLevel - 3;
                    }
                }
            }
            //更改工人等级
            xyStarEvaMainMapper.UpdateGrLevel(grId,String.valueOf(curLevel));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}


