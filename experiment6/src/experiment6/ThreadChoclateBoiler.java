package experiment6;


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
