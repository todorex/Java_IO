package com.todorex.bio;

import com.todorex.bio.client.Client;
import com.todorex.bio.server.Server;
import com.todorex.bio.server.ServerBetter;

import java.io.IOException;
import java.util.Random;

/**
 * @Author rex
 * 2018/5/21.
 */
public class BIODemo {
    //测试主方法
    public static void main(String[] args) throws InterruptedException {

        // 用一个线程运行服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    Server.start();
                    ServerBetter.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);

        // 运行客户端（可多开几个客户端，模拟多线程）
        char operators[] = {'+','-','*','/'};
        Random random = new Random(System.currentTimeMillis());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    //随机产生算术表达式
                    String expression = random.nextInt(10)+""+operators[random.nextInt(4)]+(random.nextInt(10)+1);
                    try {
                        // 客户端发送数据
                        Client.send(expression);
                        // 随机睡眠一段时间
                        Thread.currentThread().sleep(random.nextInt(1000));
                    } catch (InterruptedException|IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
