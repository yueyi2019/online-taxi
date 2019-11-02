package mq.server;

import mq.config.Broker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yueyi2019
 */
public class BrokerServer implements Runnable {

    public static int PORT = 9999;

    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while (true){
                String str = in.readLine();
                if (str == null){
                    continue;
                }
                System.out.println("收到消息："+str);

                if (("consumer").equals(str)){
                    String message = Broker.consumer();
                    out.println(message);
                    out.flush();
                }else{
                    Broker.produce(str);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        while (true){
            BrokerServer borkerServer = new BrokerServer(server.accept());
            new Thread(borkerServer).start();
        }
    }
}
