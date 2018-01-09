package experiment6;
public class ThreadSum implements Runnable{
	
	private static int num=1;
	private static int totalSum=0;
	private int threadID;
	private static Thread [] sum = new Thread[4];
	public ThreadSum(int ID) {
		threadID = ID;
		}
	public void run() {
		// TODO 自动生成的方法存根
		int sum=0;
		for(int i=num;i<num+25;i++) {
			sum+=i;
		}
		System.out.println("当前"+this.threadID+"线程总和:"+sum);
		totalSum+=sum;
		
		num+=25;
		
	}
	private static void totalsunf() {
		System.out.println("当前所有总数总和:"+totalSum);
	}
	public static void main(String [] args) {
		try {
		for(int i=0; i<sum.length; i++) {
			sum[i] = new Thread(new ThreadSum(i));
			sum[i].start();
			sum[i].join();
		}
		ThreadSum.totalsunf();
		} catch(InterruptedException e) { 
			System.out.println(e);
			}
	}


	
}
