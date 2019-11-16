package mq.client;

import mq.server.BrokerServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author yueyi2019
 */
public class MqClient {
    /**
     * 生产消息
     * @param msg
     * @throws Exception
     */
    public static void produce(String msg) throws Exception{
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        out.println(msg);
        out.flush();
        socket.close();
    }

    public static String consumer() throws Exception{
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.PORT);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        out.println("consumer");
        out.flush();

        String message = in.readLine();
        socket.close();

        return message;
    }

    public static void main(String[] args) throws Exception {
        MqClient.produce("消息"+1);
        MqClient.produce("消息"+2);
        MqClient.produce("消息"+3);

        System.out.println(MqClient.consumer());
    }
}
