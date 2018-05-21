package com.todorex.nio;

import com.todorex.nio.client.Client;
import com.todorex.nio.server.Server;

import java.util.Scanner;

/**
 * @Author rex
 * 2018/5/21.
 */
public class NIODemo {

    public static void main(String[] args) throws Exception {
        //运行服务器
        Server.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        Client.start();
        while (Client.sendMsg(new Scanner(System.in).nextLine())) ;
    }
}
