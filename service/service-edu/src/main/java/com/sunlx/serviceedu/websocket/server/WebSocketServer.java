package com.sunlx.serviceedu.websocket.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@ServerEndpoint("/api/pushMessage/{userId}")
public class WebSocketServer {

    private static int onlineCount = 0;
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap=new ConcurrentHashMap<>();
    private Session session;
    private String userId;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId){
        this.session=session;
        this.userId=userId;

        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
        }else{
            webSocketMap.put(userId,this);
            addOnlineCount();
        }

        log.info("用户连接"+userId+",当前在线人数为:"+getOnLineCount());
        sendMessage("连接成功");
    }

    @OnClose
    public void onClose(){
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            subOnlineCount();
        }
        log.info("用户退出:"+userId+",当前在线人数为:" + getOnLineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        log.info("用户消息:"+userId+",报文:"+message);

        if(StringUtils.isNotBlank(message)){
            try{
                JSONObject jsonObject= JSON.parseObject(message);
                jsonObject.put("fromUserId",this.userId);
                String toUserId=jsonObject.getString("toUserId");
                if(StringUtils.isNotBlank(toUserId) && webSocketMap.containsKey(toUserId)){
                    webSocketMap.get(toUserId).sendMessage(message);
                }else{
                    //否则不在这个服务器上，发送到mysql或者redis
                    log.error("请求的userId:"+toUserId+"不在该服务器上");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @OnError
    public void onErroe(Session session,Throwable error){
        log.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }

    private void sendMessage(String message) {
        try{
            this.session.getBasicRemote().sendText(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     *发送自定
     *义消息
     **/
    public static void sendInfo(String message, String userId) {
        log.info("发送消息到:"+userId+"，报文:"+message);
        if(StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            log.error("用户"+userId+",不在线！");
        }
    }

    private static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }



    private static synchronized int getOnLineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

}
