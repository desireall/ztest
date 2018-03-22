package pattern.porxy.staticproxy;

public class MarketProxy implements IMarket{

	public IMarket target;
	
	
	public MarketProxy(IMarket target) {
		super();
		this.target = target;
	}


	@Override
	public void bugfood() {
		System.err.println("check");
		target.bugfood();
		System.err.println("end");
	}
	
	
	public static void main(String[] args) {
		IMarket target = new Market();
		IMarket proxy = new MarketProxy(target);
		proxy.bugfood();
	}
}
