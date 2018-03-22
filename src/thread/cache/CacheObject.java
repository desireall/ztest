package thread.cache;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class CacheObject implements Delayed{
	
	public int id;

	public volatile long invalidTime;


	public CacheObject(int id) {
		this.id = id;
		this.invalidTime = System.currentTimeMillis()+ 3000;
		System.err.println(this.toString());
	}

	public CacheObject(int id, long invalidTime) {
		this.id = id;
		this.invalidTime = invalidTime;
		System.err.println(this.toString());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(long invalidTime) {
		this.invalidTime = invalidTime;
	}

	@Override
	public int compareTo(Delayed obj) {
		if(obj == this) return 0;
		if(obj instanceof CacheObject){
			CacheObject object = (CacheObject)obj;
			if(object.invalidTime > this.invalidTime){
				return -1;
			}else if(object.invalidTime == this.invalidTime){
				return 0;
			}else{
				return 1;
			}
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(invalidTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	
	public void update(long time){
		this.invalidTime = time;
		System.err.println("设置 "+id+"  失效时间为 "+time);
	}
	

	@Override
	public String toString() {
		return "CacheObject [id=" + id + ", invalidTime=" + invalidTime + "]";
	}
	
}
