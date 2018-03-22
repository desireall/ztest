package algorithm.sort;

import java.util.Arrays;
import java.util.Random;

public class Mergesort {

	
	public static void sort(int[] param){
		int[] temp = new int[param.length];
		sort(param , temp , 0 , param.length -1);	
		
//		for (int i = 0; i < temp.length; i++) {
//			param[i] = temp[i];
//		}
	}
	
	
	public static void sort(int[] param , int[] temp , int start , int end){
		int startIndex = start;
		int endindex = end;
		if(startIndex >= endindex){
			return;
		}
		int mid = (startIndex + endindex) >>> 1;
		sort(param, temp, startIndex, mid);
		sort(param, temp, mid + 1, endindex);
		mergeSort(param, temp, startIndex, mid, endindex);
	}
	
	
	public static void mergeSort(int[] param , int[] temp , int leftstart , int mid , int rightEnd){
		int leftEnd = mid;
		int rightStart = mid +1;
		int pos = leftstart;
		int count = rightEnd - leftstart + 1;
		
		while(leftstart <= leftEnd && rightStart<= rightEnd){
			if(param[leftstart] > param[rightStart]){
				temp[pos++] = param[leftstart++];
			}else{
				temp[pos++] = param[rightStart++];
			}
		}
		
		while(leftstart <= leftEnd){
			temp[pos++] = param[leftstart++];
		}

		while(rightStart<= rightEnd){
			temp[pos++] = param[rightStart++];
		}
		
		int st = rightEnd - count + 1;
		System.arraycopy(temp, st, param, st, count);
		
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
