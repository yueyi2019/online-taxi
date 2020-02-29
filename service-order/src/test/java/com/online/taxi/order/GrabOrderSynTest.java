package com.online.taxi.order;

public class GrabOrderSynTest implements Runnable {
	
	private static PassengerOrder order = new PassengerOrder();
	
	String driverName ;
	
	public GrabOrderSynTest(String driverName) {
		this.driverName = driverName;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			new Thread(new GrabOrderSynTest("司机"+i)).start();
		}
	}
	
	@Override
	public void run() {
		synchronized (order) {
			if(order.count > 0) {
				System.out.println(driverName +"抢到了	yes");
				order.count = 0;
			}else {
				System.out.println(driverName +"没抢到  ");
			}
		}
		
		
	}
		
}
class PassengerOrder{
	String address = "天安门";
	int count = 1;
}