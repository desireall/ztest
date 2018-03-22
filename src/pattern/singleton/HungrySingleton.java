package pattern.singleton;

/**
 * 饿汉式单例模式
 */
public class HungrySingleton {
	
	private static HungrySingleton instance = new HungrySingleton();
	
	private HungrySingleton(){
	}
	
	public static HungrySingleton getInstance(){
		return instance;
	}
}
