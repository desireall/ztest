package pattern.singleton;

/**
 * 懒汉式单例 
 * 
 * http://blog.csdn.net/lanzhizhuxia/article/details/7924373
 * 
 * http://blog.csdn.net/wikijava/article/details/5509504
 * 
 * http://blog.csdn.net/chenchaofuck1/article/details/51702129
 */
public class LazySingleton {

	private static volatile LazySingleton instance = null;
	
	private LazySingleton(){
		
	}
	
	/**
	 * 会出现 线程安全问题 
	 * @return
	 */
//	public static LazySingleton getInstance(){
//		if(instance == null) return new LazySingleton();
//				return instance;
//	}
	
	
//	public static synchronized LazySingleton getInstance(){
//		if(instance == null) {
//			instance = new LazySingleton();
//			return instance;
//		}
//			return instance;
//	}
	
	
	/**
	 * 双重检查锁定写法
	 * @return
	 */
	public static LazySingleton getInstance() {
        //第一重判断  
        if (instance == null) {
            //锁定代码块  
            synchronized (LazySingleton.class) {
                //第二重判断  
                if (instance == null) {
                    instance = new LazySingleton(); //创建单例实例  
                }
            }  
        }  
        return instance;   
    }
	

}
