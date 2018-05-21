package com.todorex.bio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by
 *
 * @Author rex
 * on 2018/5/20.
 */
public class Client {
    // 默认主机
    private static String DEFAULT_SERVER_IP = "127.0.0.1";
    // 默认的端口号
    private static int DEFAULT_SERVER_PORT = 12345;

    // 发送表达式(默认端口)
    public static void send(String expression) throws IOException {
        send(DEFAULT_SERVER_PORT, expression);
    }

    public static void send(int port, String expression) throws IOException {
        System.out.println("算术表达式为：" + expression);
        // 建立Socket
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(DEFAULT_SERVER_IP, port);
            // 得到服务器的输入
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 得到向服务器输出流
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(expression);
            System.out.println("客户端收到的结果为：" + in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //一下必要的清理工作
            in.close();
            out.close();
            // 使Socket等待回收
            socket = null;
        }
    }
}

