package base.classload;

public class LoadProperty {

	public static void main(String[] args) {
		
		System.out.println(System.getProperty("sun.boot.class.path"));  // bootstrapclassloader
		System.out.println(System.getProperty("java.ext.dirs"));   // extclassloader
		System.out.println(System.getProperty("java.class.path")); // appclassloader
		
	}
}
