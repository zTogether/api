package cn.xyzs.api.service;

import cn.xyzs.api.util.DateUtil;
import cn.xyzs.api.util.SpringUtil;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{userId}/{ctrCode}")
public class WebsocketServer {

    //在线人数
    private  static  int COUNT = 0;
    //创建链接池
    private static CopyOnWriteArraySet<WebsocketServer> websocket = new CopyOnWriteArraySet<WebsocketServer>();
    //创建链接Map
    private static Map<String , WebsocketServer> websocketMap = new HashMap<>();
    //创建userIdMap
    private static Map<String ,String> userIdMap = new HashMap<>();
    //创建session会话
    private Session session;

    /**
     * 接入链接
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/3 14:29
     * @param: [session]
     * @return: void
     */
    @OnOpen
    public  void onOpen(Session session ){
        //设置接入会话
        this.session = session;
        //将用户接入链接
        websocket.add(this);
        //将用户添加至websocketMap（key ：链接id     value ：链接对象）
        websocketMap.put(this.session.getId(),this);
        //将链接Id添加至userIdMap（key ：userId    value ：链接ID）
        userIdMap.put(session.getPathParameters().get("userId") ,this.session.getId());
        //添加链接人数
        addOnlineCount();
        //控制台打印链接人数
        System.out.println("当前在线人数："+COUNT);
    }

    /**
     * 断开链接
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/3 14:28
     * @param: []
     * @return: void
     */
    @OnClose
    public void onclose(){
        //将谅解用户删除连接池
        websocket.remove(this);
        //减少链接人数
        subOnlineCount();
        //控制台大印当前链接人数
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }


    /**
     * 接收到客户端的信息
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/3 14:28
     * @param: [message, session]
     * @return: void
     */
    @OnMessage
    public  void shoudaoMessage(String message, Session session ){
        //获取链接地址占位符的ctrCode
        String ctrCode = session.getPathParameters().get("ctrCode");
        //获取链接idSet集合
        Set<String> idSet = getIdSet(ctrCode);
        //群发消息
        for (WebsocketServer item : websocket) {
            try {
                for (String id : idSet) {
                    //判断是否属于此客户聊天群中的人
                    if (websocketMap.get(id) == item ){
                        //将聊天内容推送至页面
                        item.sendMessage(message ,session.getPathParameters().get("userId"));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/3 14:29
     * @param: [session, error]
     * @return: void
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 将消息推送至客户端
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/3 14:30
     * @param: [message, userId]
     * @return: void
     */
    public void sendMessage(String message,String userId) throws IOException {
        DateUtil dateUtil = SpringUtil.getBean(DateUtil.class);
        //将聊天内容推送至页面
        //System.out.println(message);
        this.session.getBasicRemote().sendText(message + ","+userId + "," + dateUtil.getSysDate());
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 获取在线人数
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/3 14:30
     * @param: []
     * @return: int
     */
    public static synchronized int getOnlineCount() {
        return COUNT;
    }

    /**
     * 添加在线人数
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/3 14:31
     * @param: []
     * @return: void
     */
    public static synchronized void addOnlineCount() {
        WebsocketServer.COUNT++;
    }

    /**
     * 减少在线人数
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/3 14:31
     * @param: []
     * @return: void
     */
    public static synchronized void subOnlineCount() {
        WebsocketServer.COUNT--;
    }

    /**
     * 获取链接IDSet
     * @Description:
     * @author: zheng shuai
     * @date: 2018/10/3 14:31
     * @param: [ctrCode]
     * @return: java.util.Set<java.lang.String>
     */
    public Set<String> getIdSet(String ctrCode){
        //创建链接idSet集合
        Set<String> idSet = new HashSet<>();
        //通过SpringUtil获取WebsocketService
        WebsocketService websocketService = (WebsocketService)SpringUtil.getBean(WebsocketService.class);
        //获取用户所包含的服务人员（接待人员，设计师，执行总监，材料导购，区域老总，合约成效人，以及所有的施工工人）
        List<Map<String ,String>> userIdList = websocketService.getUserLsit(ctrCode);
        for (String userId : userIdMap.keySet()) {
            for (Map<String,String> useridMap : userIdList) {
                //设置要推送信息的链接id
                if (userId.equals(useridMap.get("userId"))){
                    idSet.add(userIdMap.get(userId));
                }
            }
        }
        return idSet;
    }
}
