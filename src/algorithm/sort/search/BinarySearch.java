package algorithm.sort.search;

import java.util.Arrays;
import java.util.Random;

import algorithm.sort.BluBlu;

public class BinarySearch {

	/**
	 * array 升序排列的
	 * 
	 * @param array
	 * @param key
	 * @return
	 */
	public static int search(int[] array, int key) {
		int low = 0;
		int hight = array.length - 1;
		//
		while (low <= hight) {
			int mid = (low + hight) >>> 1;
			
			int midValue = array[mid];
			if (key > midValue) {
				//
				low = mid + 1;
			} else if (key < midValue) {
				hight = mid - 1;
			} else {
				return mid;
			}
		}

		return -1;
	}
	
	
	
	
	
	public static void main(String[] args) {
//		int[] param = {68, 151, 155, 286, 303, 498, 574, 575, 734, 761};
		int[] param = new int[10];
		for (int i = 0; i < param.length; i++) {
			param[i] = new Random().nextInt(1000);
		}
		System.err.println(Arrays.toString(param));

		BluBlu.sort(param);
		System.err.println(Arrays.toString(param));
		
		int temp = new Random().nextInt(param.length - 1);
		System.err.format("查找位置 %s ,值:  %s%n" , temp, param[temp]);
		System.err.println(search(param, param[temp]));
		
		
	}
}
