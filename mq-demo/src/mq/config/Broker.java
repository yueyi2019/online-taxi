package mq.config;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author yueyi2019
 */
public class Broker {
    /**
     *    队列中消息最大数量
     */
    private final static int MAX_SIZE = 2;

    /**
     * 消息队列，阻塞式队列，底层是循环数组
     */
    private static ArrayBlockingQueue<String> msgQueue = new ArrayBlockingQueue<String>(MAX_SIZE);

    /**
     * 向队列插入消息
     * @param msg
     */
    public static  void produce(String msg){
        /**
         * 队列未满，插入成功返回true，队列满，插入失败返回false
         */
        if (msgQueue.offer(msg)){
            System.out.println("成功 生产消息："+msg+"当前队列大小："+msgQueue.size());
        }else{
            System.out.println("队列满了");
        }
    }

    /**
     * 从队列取消息
     * @return
     */
    public static String consumer(){
        /**
         * 队列中有数据返回头部数据，否则返回null
         */
        String msg = msgQueue.poll();
        if (msg != null){
            System.out.println("成功 消费消息："+msg+"当前队列大小："+msgQueue.size());
        }else{
            System.out.println("无消息可消费");
        }
        return msg;
    }
}
