package game;

import java.util.Calendar;


public class RiskTiken {
	
	/**
	 * 登录时的刷新检测
	 * @param notify
	public void refreshRiskTokenLogin(){
		int max = getMaxEnergy();
		if(this.m_risktoken >= max) return;
		Calendar currentFiveclock = DateUtils.getCurrent5Time();
		if(Calendar.getInstance().after(currentFiveclock)){
        	//今天没有刷新的
			if(this.m_freshTime.before(currentFiveclock)){
				refreshRiskToken(false);
			}
        }else if(Calendar.getInstance().before(currentFiveclock)){
        	//昨天没有刷新
        	if(this.m_freshTime.before(DateUtils.getYesterday5Time())){
        		refreshRiskToken(false);
        	}
        }
	}

	// 当前05:00的时间
	public static Calendar getCurrent5Time() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 5);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}
	
	// 昨天05:00的时间
	public static Calendar getYesterday5Time() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 5);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DATE, -1);
		return c;
	}
	 */
	
}
