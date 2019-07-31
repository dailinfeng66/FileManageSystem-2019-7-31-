package com.hearkensummertask.hearkensummertask.service;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
@ServerEndpoint("/websocket/{username}")
public class WebSocket {

    public static int onlineCount = 0;
    public static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
//    public Session session;
    public Session session;
    public String username ;

    @OnOpen
    public void onOpen(Session session,@PathParam("username") String username) throws IOException {

        this.session = session;

        addOnlineCount();
        clients.put(username, this);
//        sendMessageAll(username+"进入了聊天室!\n");  //给全部的人发消息
//        System.out.println("connect !");
        System.out.println(username+"链接上了");
    }

    @OnClose
    public void onClose() throws IOException {
//        clients.remove(username);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println(message);


//            sendMessageAll(message);
//        JSONObject jsonTo = JSONObject.fromObject(message);
//
//        clients.get("zhangxi").session.getAsyncRemote().sendText("张喜属猪的"); //给指定的人发消息

    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public static void   sendMessageTo(String message, String To) throws IOException {
        System.out.println("clients:\n"+clients);
        WebSocket.clients.get(To).session.getAsyncRemote().sendText(message);
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    public static synchronized Map<String, WebSocket> getClients() {
        return clients;
    }
}