package exercise.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareTest {

	public int level;
	public int limit;
	public long time;

	public int getLevel() {
		return level;
	}

	public int getMemLimit() {
		return limit;
	}

	public long getCreateTime() {
		return time;
	}

	
	public CompareTest() {
		super();
	}

	public CompareTest(int level, int limit, long time) {
		super();
		this.level = level;
		this.limit = limit;
		this.time = time;
	}

	
	@Override
	public String toString() {
		return "CompareTest [level=" + level + ", limit=" + limit + ", time=" + time + "]";
	}
	
	
	public void test(){


		Comparator<CompareTest> myComparator = new Comparator<CompareTest> (){
			@Override
			public int compare(CompareTest o1, CompareTest o2) {
				int value = o1.getLevel() - o2.getLevel();
				if (value != 0) {
					return -value;
				} else {
					value = o1.getMemLimit() - o2.getMemLimit();
					if (value != 0) {
						return -value;
					} else {
						value = (int) (o1.getCreateTime() - o2.getCreateTime());
						if (value != 0) {
							return value;
						}
					}
				}
				return value;
			}
		};
		
		
		List<CompareTest> list = new ArrayList<CompareTest>();

		long current = System.currentTimeMillis();
		
		list.add(new CompareTest(3, 2, current));
		list.add(new CompareTest(3, 2, current));
		list.add(new CompareTest(4, 3, 345L));
//		list.add(new CompareTest(4, 3, 123L));
//		list.add(new CompareTest(3, 2, 345L));
//		list.add(new CompareTest(1, 2, 345L));
//		list.add(new CompareTest(2, 2, 345L));
		
		Comparators.verifyTransitivity(myComparator, list);
		
		Collections.sort(list, myComparator);
		
		System.out.println(Arrays.toString(list.toArray()));
	
	}

}

