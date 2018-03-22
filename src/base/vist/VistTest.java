package base.vist;

public class VistTest implements Vistor{

	@Override
	public boolean checkPression() {
//		pression = "456";
		return false;
	}
	
	
}


interface Vistor{
	
	String pression = "123";
	
	boolean checkPression();
}