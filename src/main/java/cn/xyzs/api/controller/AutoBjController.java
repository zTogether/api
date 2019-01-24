package cn.xyzs.api.controller;

import cn.xyzs.api.service.AutoBjService;
import cn.xyzs.common.pojo.XyMainArea;
import cn.xyzs.common.pojo.XyMainHouser;
import cn.xyzs.common.pojo.XySysDistrict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/autoBj")
public class AutoBjController {

    @Resource
    private AutoBjService autoBjService;

    /**
     * 获取一键报价首页数据
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/18 12:23
     * @param: [xyMainHouser, xyMainArea, xySysDistrict]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getFontPageData")
    public Map<String ,Object> getFontPageData(XyMainHouser xyMainHouser, XyMainArea xyMainArea ,
                                               XySysDistrict xySysDistrict ,String flag,Integer startNum, Integer endNum){
        return autoBjService.getFontPageData(xyMainHouser,xyMainArea,xySysDistrict,flag,startNum,endNum);
    }

    /**
     * 获取模板主材list
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 17:21
     * @param: [xyClbZcpbTemplateList, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getMbZcList")
    public Map<String ,Object> getMbZcList(String houseId ,Integer startNum , Integer endNum){
        return autoBjService.getMbZcList(houseId,startNum,endNum);
    }

    /**
     * 获取模板软装list
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 17:22
     * @param: [xyClbZcpbTemplateList, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getMbRzList")
    public Map<String ,Object> getMbRzList(String houseId ,Integer startNum , Integer endNum){
        return autoBjService.getMbRzList(houseId,startNum,endNum);
    }

    /**
     * 获取模板人工费项目
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 17:30
     * @param: [houseId, startNum, endNum]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getMbRgList")
    public Map<String ,Object> getMbRgList( String houseId,Integer startNum , Integer endNum){
        return autoBjService.getMbRgList(houseId,startNum,endNum);
    }

    /**
     * 获取房屋信息
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/20 18:04
     * @param: [houseId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getHouseInfo")
    public Map<String ,Object> getHouseInfo( String houseId){
        return autoBjService.getHouseInfo(houseId);
    }

    /**
     * 获取造价表数据
     * @Description:
     * @author: zheng shuai
     * @date: 2019/1/23 16:02
     * @param: [houseId, zcArray, rzArray, rgArray]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/getTotalPriceTableData")
    public Map<String ,Object> getTotalPriceTableData(String houseId ,String []zcArray ,String []rzArray ,String []rgArray){
        return autoBjService.getTotalPriceTableData(houseId,zcArray,rzArray,rgArray);
    }

}
