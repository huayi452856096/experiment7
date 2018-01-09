package experiment6;


import secondtest.ChoclateBoiler;

public class ThreadTest implements Runnable {
	private String name;
	private ThreadTest(String name) {
		this.name=name;
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		ThreadChoclateBoiler.getInstance();
		ThreadChoclateBoiler.fill(this.name);
		ThreadChoclateBoiler.boil(this.name);
		ThreadChoclateBoiler.drain(this.name);
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Thread tA = new Thread(new ThreadTest("java")); //以该类对象分别实例化4个线程
		Thread tB = new Thread(new ThreadTest("android"));
		Thread tC = new Thread(new ThreadTest("google"));
		Thread tD = new Thread(new ThreadTest("git"));
		tA.start(); //分别启动线程
		tB.start();
		tC.start();
		tD.start();
	}

}
