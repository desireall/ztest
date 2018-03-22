package pattern.singleton;

/**
 * 改进的懒汉式单例模式 
 * @author Yu_Huo
 *
 */
public class LazySingletonImprove {
	
	private static class Singleton{
		
		private static LazySingletonImprove instance = new LazySingletonImprove(); 
		
		private Singleton(){
			
		}
	}
	
    private LazySingletonImprove(){
    	
    }  
	
	public static LazySingletonImprove getInstance(){
		return Singleton.instance;
	}

	
	
	public static void main(String[] args) {
		LazySingletonImprove s1 = LazySingletonImprove.getInstance();
		LazySingletonImprove s2 = LazySingletonImprove.getInstance();
		if(s1 == s2){
			System.err.println(".........");
		}
	}
}
