package pattern.porxy.dynamixproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

import org.junit.Test;

import pattern.porxy.staticproxy.IMarket;
import pattern.porxy.staticproxy.Market;

public class UserDao implements IUserDao{

	@Override
	public void save() {
		System.err.println("save user");
	}

	public static void main(String[] args) {
		try {
			newclassTest();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void newInstanceTest(){
		IUserDao userDao = new UserDao();
		InvocationHandler handler = new PersistenceInvocationHandler(userDao);
		
		IUserDao proxy  = (IUserDao)Proxy.newProxyInstance(userDao.getClass().getClassLoader(), 
				                           userDao.getClass().getInterfaces(), handler);
		proxy.save();
	}
	
	

	public static void newclassTest() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		IUserDao userDao = new UserDao();
		InvocationHandler handler = new PersistenceInvocationHandler(userDao);
		
		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");  // 项目下 位置 com.sun.proxy 生成代理类的.class文件
//		System.err.println(System.getProperty("sun.misc.ProxyGenerator.saveGeneratedFiles"));
		Class proxyclass = Proxy.getProxyClass(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces());
		Constructor constructor = proxyclass.getConstructor(InvocationHandler.class);
		IUserDao userDaoPeoxy = (IUserDao)constructor.newInstance(handler);
		
		userDaoPeoxy.save();
	}
}
