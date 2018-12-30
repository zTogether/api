package cn.xyzs.api.controller;

import cn.xyzs.api.service.XyInfoManageConstrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/App/infoManage")
public class XyInfoManageConstrController {
    @Resource
    private XyInfoManageConstrService xyInfoManageConstrService;

    @ResponseBody
    @RequestMapping("/getConstrInfo")
    public Map<String,Object> getConstr(String ctrCode){
        return xyInfoManageConstrService.getConstrRecord(ctrCode);
    }

    @ResponseBody
    @RequestMapping("/addConstr")
    public Map<String,Object> addConstr(String ctrCode,String consType,String belongUser,String edRecode,
                                        String rbRecode,String userType,String xh){
        return xyInfoManageConstrService.addConstr(ctrCode,consType,belongUser,edRecode,rbRecode,userType,xh);
    }

    @ResponseBody
    @RequestMapping("/deleteConstr")
    public Map<String,Object> deleteConstr(String ctrCode,String consType,String userType,String xh){
        return xyInfoManageConstrService.deleteConstr(ctrCode,consType,userType,xh);
    }

    @ResponseBody
    @RequestMapping("/updateConstr")
    public Map<String,Object> updateConstr(String ctrCode,String consType,String belongUser,String edRecode,
                                           String rbRecode,String userType,String xh){
        return xyInfoManageConstrService.updateConstr(ctrCode,consType,belongUser,edRecode,rbRecode,userType,xh);
    }

    @ResponseBody
    @RequestMapping("/addComp")
    public Map<String,Object> addComp(String ctrCode,String compContent,String compType,String solveDate,
                                      String liableUser,String opUser){
        return xyInfoManageConstrService.addComp(ctrCode,compContent,compType,solveDate,liableUser,opUser);
    }

    @ResponseBody
    @RequestMapping("/getLiableUser")
    public Map<String,Object> getLiableUser(String ctrCode){
        return xyInfoManageConstrService.getLiableUser(ctrCode);
    }

    @ResponseBody
    @RequestMapping("/deleteComp")
    public Map<String,Object> deleteComp(String rowId){
        return xyInfoManageConstrService.deleteComp(rowId);
    }

    @ResponseBody
    @RequestMapping("/addRevisit")
    public Map<String,Object> addRevisit(String ctrCode,String content,String opUser){
        return xyInfoManageConstrService.addRevisit(ctrCode,content,opUser);
    }
}