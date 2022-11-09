package com.sunlx.serviceedu.websocket.controller;

 import com.sunlx.serviceedu.websocket.server.WebSocketServer;
 import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/websocket")
public class WebSocketController {
    //@NotLogin
    @GetMapping("/sendinfo")
    public void sendinfo() {
        log.info("后端推送消息");
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String date = dateFormat.format(new Date());
            WebSocketServer.sendInfo("后端服务推送信息:"+date,"admin");
        } catch (Exception e) {
            log.error("分页查询数据接口列表失败", e);
        }
    }

}
