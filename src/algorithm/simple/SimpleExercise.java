package algorithm.simple;

import java.util.Arrays;
import java.util.Random;

public class SimpleExercise {

	
	public static void main(String[] args) {
		int[] array = new int[10];
		for (int i = 0; i < array.length; i++) {
			array[i] = new Random().nextInt(1000);
		}
		System.err.println(Arrays.toString(array));
		int f = new Random().nextInt(array.length) + 1;
		System.err.println(f);
		
//		checkF(array, f);
		checkFspile(array, f);
	}
	
	
	public static void checkF(int[] array , int f){
		if(array == null || array.length == 0 || array.length < f) return;
		blublu(array);
		System.err.println(Arrays.toString(array));
		System.err.println(array[f-1]);
	}

	public static void checkFspile(int[] array , int f){
		if(array == null || array.length == 0 || array.length < f) return;
		int[] temp = new int[f];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = array[i];
		}
		blublu(temp);
		System.err.println(Arrays.toString(temp));
		for (int i = f; i < array.length; i++) {
			if(temp[f-1] < array[i]){
				temp[f-1] = array[i];
				for (int j = f-1; j > 0; j--) {
					if(temp[j] > temp[j - 1]){
						int interim =  temp[j];
						 temp[j]  = temp[j - 1];
						 temp[j - 1] = interim;
					}
				}
			}
		}
		System.err.println(Arrays.toString(temp));
		System.err.println(temp[f-1]);
	}
	
	
	
	public static void blublu(int[] array){
		if(array == null || array.length == 0) return;
		for (int i = 0; i < array.length -1; i++) {
			for (int j = i+1; j < array.length; j++) {
				if(array[i] < array[j]){
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	} 
}
