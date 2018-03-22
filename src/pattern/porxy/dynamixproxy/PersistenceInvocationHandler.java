package pattern.porxy.dynamixproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersistenceInvocationHandler implements InvocationHandler {
	
	public Object target;
	
	public PersistenceInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.err.println("start transment");
		
		method.invoke(target, args);
		
		System.err.println("end transment!");
		return null;
	}

}
