package algorithm.sort;

import java.util.Arrays;
import java.util.Random;

public class BluCocktailSort {

	
	
	public static void sort(int[] param){
		int left = 0;
		int right = param.length -1;
		int temp = 0;
		while(left < right){
			for (int i = left; i < right; i++) {
				if(param[i] > param[i+1]){
					temp = param[i];
					param[i] = param[i + 1];
					param[i + 1] = temp;
				}
			}
			right--;
			
			for (int i = right; i > left; i--) {
				if(param[i - 1] > param[i]){
					temp = param[i -1];
					param[i - 1] = param[i];
					param[i] = temp;
				}
			}
			left++;
		}
	}
	
	
	public static void main(String[] args) {

		int[] param = new int[10];
		for (int i = 0; i < param.length; i++) {
			param[i] = new Random().nextInt(1000);
		}
		System.err.println(Arrays.toString(param));

		sort(param);

		System.err.println(Arrays.toString(param));
	}
}
