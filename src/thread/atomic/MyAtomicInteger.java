package thread.atomic;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class MyAtomicInteger {

	private static Unsafe unsafe = Unsafe.getUnsafe();
	private volatile int value;

	private static long valueOfferSet;

	static {
		try {
			  /**
			   * 获取失败  无法启动
			   */
			  Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
              theUnsafe.setAccessible(true);
              unsafe = (Unsafe) theUnsafe.get(null);
			valueOfferSet = unsafe.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int get() {
		return value;
	}

	public boolean compareandset(int expect, int update) {
		return unsafe.compareAndSwapInt(this, valueOfferSet, expect, update);
	}

	public int addAndGet() {
		for (;;) {
			int count = get();
			int next = count + 1;
			if (compareandset(count, next)) {
				return next;
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			MyAtomicInteger a = new MyAtomicInteger();
			System.err.println(a.addAndGet());
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
