package com.online.taxi;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
/**
 * 	只需简单继承`HystrixCommand/HystrixObservableCommand`并重写`run()/construct()`，
 * 	然后调用程序实例化此class并执行`execute()/queue()/observe()/toObservable()`
 * 	@author yueyi2019
 *
 */
public class HelloWorldHystrixCommand2 extends HystrixCommand {  
	
    private final String name;
    
    public HelloWorldHystrixCommand2(String name) {   
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
        		.andCommandPropertiesDefaults(
        				HystrixCommandProperties.Setter()
        				.withCircuitBreakerErrorThresholdPercentage(5)// 错误率超过5%
        				.withCircuitBreakerRequestVolumeThreshold(9)//10秒以内超过9次
        				.withCircuitBreakerSleepWindowInMilliseconds(5000)//隔5s之后，熔断器会尝试半开(关闭)，重新放进来请求
        				
        				));     
        this.name = name; 
    }
    
    /*
     * 	 如果继承的是HystrixObservableCommand，要重写Observable construct()，
     * 	具体业务逻辑 
     */
    @Override 
    protected String run() {   
    	Random rand = new Random();
        // 模拟50% 错误率
        if(1==rand.nextInt(2)){
           int i = 1/0;
        }
        return "正常调用 Hello " + name; 
    } 
    
    /**
     * 	降级方法
     */
    @Override
	protected Object getFallback() {
		return "熔断：fallback,name:"+name;
	}

	public static void main(String[] args) throws InterruptedException {
    	/**
    	 * 这个例子，休眠半秒，一共执行15秒。
    	 * 10秒以内超过9次请求，才开始计算 是否要熔断。
    	 * 然后 判断 失败率，是否超过5%。
    	 * 熔断后，
    	 * 5秒，是 熔断开关打开，直接返回的。
    	 * 再后来，放一部分请求过去。
    	 */
		for(int i=0;i<30;i++){
            Thread.sleep(500);
            HystrixCommand<String> command = new HelloWorldHystrixCommand2("testCircuitBreaker");
            String result = command.execute();
            //本例子中从第10次，熔断器开始打开
            System.out.println("调用次数:"+(i+1)+"   结果:"+result +" 开关是否打开: "+command.isCircuitBreakerOpen());
            //本例子中5s以后，熔断器尝试关闭，放开新的请求进来
        }
		
	}
} 