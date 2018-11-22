package cn.xyzs.api.ws.controller;

import cn.xyzs.api.ws.radis.RedisOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/App/redis")
public class RedisAction {

    @Resource
    private RedisOperation redisOperation;

    @RequestMapping("getJson")
    public Object getJson(@RequestParam(defaultValue = "") String key){
        return redisOperation.get(key);
    }

    @RequestMapping("getJsonIndex")
    public Object getJsonIndex(String key, @RequestParam(defaultValue = "0") long start, @RequestParam(defaultValue = "-1") long end){
        return redisOperation.lrange(key,start,end);
    }

    @RequestMapping("setJson")
    public Object setJson(String key,String value){
        return redisOperation.set(key,value);
    }

    @RequestMapping("setMap")
    public Object setMap(@RequestParam Map map){
        if (map.get("key")!=null){
            return redisOperation.set(map.get("key").toString(),map);
        }
        return "ERROR";
    }


}
