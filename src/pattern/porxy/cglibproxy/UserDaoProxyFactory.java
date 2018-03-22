package pattern.porxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import pattern.porxy.dynamixproxy.UserDao;

public class UserDaoProxyFactory implements MethodInterceptor {

	public Object target;

	public UserDaoProxyFactory(Object object) {
		this.target = object;
	}

	public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args, MethodProxy proxy)
			throws Throwable {
		System.err.println("start cglib interceptor!");
		method.invoke(target, args);
		System.err.println("end cglib interceptor!");
		return null;
	}
	
	
	public Object getInstance(){
		Enhancer en = new Enhancer();
		en.setSuperclass(target.getClass());
		en.setCallback(this);
		return en.create();
	}
	
	
	public static void main(String[] args) {
		UserDao userDaoTest = new UserDao();
		System.err.println(userDaoTest.getClass());
		UserDao userDao= (UserDao)new UserDaoProxyFactory(new UserDao()).getInstance();
		userDao.save();
	}
}
