---
title: JAVA的IO操作
tags: JAVA,面向对象,基础
---

## 对实验三中的单例模式进行改造，使其支持多线程，并且是线程安全的。

#### 部分代码
```java
public class ThreadChoclateBoiler {
	private static ThreadChoclateBoiler uniqueInstance;
    private static boolean empty=true;
    private static boolean boiled=false;
    private ThreadChoclateBoiler() { }
    public static synchronized ThreadChoclateBoiler getInstance() {
	if(uniqueInstance == null) {
	    uniqueInstance = new ThreadChoclateBoiler();
	}
	return uniqueInstance;	
    }
    public static synchronized void fill(String name) {
    	if(empty==true&&boiled==false) {
    		System.out.println(name+"对锅炉进行填充");
    		empty=false;
    	}
    }
    public static synchronized void boil(String name) {
    	if(empty==false&&boiled==false) {
    		System.out.println(name+"煮沸锅炉里的材料");
    		boiled=true;
    	}
    }
    public static synchronized void drain(String name) {
    	if(empty==false&&boiled==true) {
    		System.out.println(name+"排出混合物");
    		boiled=false;
    		empty=true;
    	}
    }
    public static synchronized boolean isEmpty() {
    	return empty;
    }
    public static synchronized boolean isBoiled() {
    	return boiled;
    }
}

```
## 结果截图
![enter description here][1]


## 利用4个线程分段求和1~100
### 线程1计算1至25之和；线程2计算26至50之和；以此类推
### 要求线程1完成之后执行线程2，之后执行线程3，最后执行线程4
### 打印每段求和结果，以及最后的总结果。即分别打印第一段求和结果，第二段求和结果，第三段求和结果，第四段求和结果，最终的求和结果

#### 部分代码
```java
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

```
## 结果截图
![enter description here][2]


  [1]: ./1.png "1"
  [2]: ./2.png "2"