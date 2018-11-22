package cn.xyzs.api.controller.getui;

import cn.xyzs.api.pojo.API.ClientAPI;
import cn.xyzs.api.service.getui.ClientService;
import cn.xyzs.api.util.GetuiUtil;
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
    @RequestMapping("pushTest.do")
    public String pushTest(@RequestParam(defaultValue = "轩辕装饰") String title, @RequestParam(defaultValue = "抽宝马") String text){
        try {
            GetuiUtil.pushmsg(title,text,"f55274e072f41c79357f97c7d08f98e4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
