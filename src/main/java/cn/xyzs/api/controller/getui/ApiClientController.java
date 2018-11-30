package cn.xyzs.api.controller.getui;

import cn.xyzs.common.pojo.API.ClientAPI;
import cn.xyzs.api.service.getui.ClientService;
import cn.xyzs.common.util.GetuiUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/App/client")
public class ApiClientController {
    @Resource
    private ClientService clientService;

    @RequestMapping("checkAppClient.do")
    public Integer checkAppClient(ClientAPI clientAPI){
        return clientService.saveClientId(clientAPI);
    }
//    @RequestMapping("pushTest.do")
//    public String pushTest(@RequestParam(defaultValue = "轩辕装饰") String title, @RequestParam(defaultValue = "抽宝马") String text){
//        try {
//            GetuiUtil getui = new GetuiUtil();
//            getui.title = title;
//            getui.text = text;
//            getui.CID = "0667727b6b83950a8dc30e2c9e9ea653";
//            getui.pushClient();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "success";
//    }
}
