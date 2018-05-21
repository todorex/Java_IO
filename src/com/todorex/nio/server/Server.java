package com.todorex.nio.server;

/**
 * BIO服务端——同步阻塞I/O 1:N
 *
 * @Author rex
 * on 2018/5/20.
 */
public class Server {
    // 服务器默认端口
    private static int DEFAULT_PORT = 12345;
    // 服务器处理类
    private static ServerHandle serverHandle;
    // 以默认端口开启服务器
    public static void start() {
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port) {
        // 关闭执勤啊的处理线程
        if (serverHandle != null)
            serverHandle.stop();
        serverHandle = new ServerHandle(port);
        // 开启服务端线程
        new Thread(serverHandle, "Server").start();
    }

    public static void main(String[] args) {
        start();
    }
}
