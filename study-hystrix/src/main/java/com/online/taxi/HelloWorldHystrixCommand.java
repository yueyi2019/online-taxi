package com.online.taxi;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
/**
 * 只需简单继承`HystrixCommand/HystrixObservableCommand`并重写`run()/construct()`，然后调用程序实例化此class并执行`execute()/queue()/observe()/toObservable()`
 * @author yueyi2019
 *
 */
public class HelloWorldHystrixCommand extends HystrixCommand {  
	
    private final String name;
    
    public HelloWorldHystrixCommand(String name) {   
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));     
        this.name = name; 
    }
    
    // 如果继承的是HystrixObservableCommand，要重写Observable construct() 
    @Override 
    protected String run() {   
    	int i = 1/0;
        return "Hello " + name; 
    } 
    
    @Override
	protected Object getFallback() {
		// TODO Auto-generated method stub
		return "fallback,name:"+name;
	}

	public static void main(String[] args) {
    	/**
    	 * execute()：以同步阻塞方式执行run()。以demo为例，调用execute()后，hystrix先创建一个新线程运行run()，接着调用程序要在execute()调用处一直阻塞着，直到run()运行完成
		   queue()：以异步非阻塞方式执行run()。以demo为例，一调用queue()就直接返回一个Future对象，同时hystrix创建一个新线程运行run()，调用程序通过Future.get()拿到run()的返回结果，而Future.get()是阻塞执行的
    	 */
		String result = (String)new HelloWorldHystrixCommand("msb").execute();
		System.out.println(result);
	}
} 