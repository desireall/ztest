package algorithm.sort;

import java.util.Arrays;
import java.util.Random;

//http://www.cnblogs.com/jingmoxukong/p/4302718.html
public class BluBlu {

	public static void sort(int[] param) {
		int temp = 0;
		boolean exchange = false;
//		for (int i = 0; i < param.length - 1; i++) {
		for (int i = param.length - 1; i >= 0 ; i--) {
			exchange = false;
			for (int j = 0; j < i; j++) {
				if (param[j + 1] < param[j]) {
					temp = param[j];
					param[j] = param[j + 1];
					param[j + 1] = temp;
					exchange = true;
				}
			}
//			System.out.println("第" + (i + 1) + "趟" + Arrays.toString(param));
			if (!exchange) {
				break;
			}
		}
	}

	public static void sortU(int[] list) {
		int temp = 0;
		boolean exchange = false;
		for (int i = 0; i < list.length - 1; i++) {
			exchange = false;
			for (int j = list.length - 1; j > i; j--) {
				// 比较相邻的元素，如果前面的数大于后面的数，则交换
				if (list[j - 1] > list[j]) {
					temp = list[j - 1];
					list[j - 1] = list[j];
					list[j] = temp;
					exchange = true;
				}
//				System.out.format("第%s趟： %s---%s%n", i, j, Arrays.toString(list));
			}
			System.out.println("第" + (i + 1) + "趟" + Arrays.toString(list));
			if (!exchange) {
				break;
			}
		}
	}

	public static void main(String[] args) {

		int[] param = new int[10];
		for (int i = 0; i < param.length; i++) {
			param[i] = new Random().nextInt(1000);
		}
		System.err.println(Arrays.toString(param));

		sortU(param);
//		sort(param);

		System.err.println(Arrays.toString(param));
	}

}
