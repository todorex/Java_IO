package com.todorex.aio.server;

/**
 * Created by
 *
 * @Author rex
 * on 2018/5/20.
 */
public class Server {
    // 默认端口号
    private static int DEFAULT_PORT = 12345;
    // 异步服务端处理
    private static AsyncServerHandler serverHandle;
    // 记录客户端数量
    public volatile static long clientCount = 0;
    // 利用默认端口开启服务端
    public static void start(){
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port){
        if(serverHandle!=null)
            return;
        serverHandle = new AsyncServerHandler(port);
        new Thread(serverHandle,"Server").start();
    }
    public static void main(String[] args){
        Server.start();
    }
}
