package deliverable2;

import java.util.Random;

public class deliverable4 {
	public int[] billify(int[] array){
		if(array == null || array.length == 0){
			return null;
		}
		int sum = 0;
		int[] res = new int[array.length + 1];
		for(int i = 0; i < array.length; i++){
			res[i] = array[i] * array[i];
			sum += res[i];
		}
		res[array.length] = sum;
		return res;
	}
	
	public static void main(String [] args){
		int[] x = {2, 4, 5, 6};
		deliverable4 test = new deliverable4();
		for(int i = 0; i <= x.length; i++){
			System.out.print(test.billify(x)[i] + " ");
		}
		System.out.println();
		for(int i : test.generateRandomArray(10)){
			System.out.print(i + " ");
		}
	}
	
	public int[] generateRandomArray(int size){
		Random ran = new Random();
		int[] res = new int[size];
		for(int i = 0; i < size; i++){
			res[i] = ran.nextInt(100) + 1;
		}
		return res;
	}
}
