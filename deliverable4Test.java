package deliverable2;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class deliverable4Test {
	Random ran = new Random();

	// Test1: test the length of the billified array is one larger than the original array
	@Test
	public void lengthTest(){
		for(int i = 0; i < 100; i++){ //iterate 100 times to generate 100 random arrays
			int size = ran.nextInt(100) + 1; // generate random number from 1 - 100
			deliverable4 test = new deliverable4();
			int[] randomArray = test.generateRandomArray(size);				
			int[] billifiedArray = test.billify(randomArray); //billified the array;
			assertTrue(randomArray.length + 1 == billifiedArray.length); // the length should be one larger.
		}	
	}
	
	
	//Test2:  the last element of the billified array should always be the sum of its precedure
	@Test
	public void sumTest(){
		for(int i = 0; i < 100; i++){
			int sum = 0;
			int size = ran.nextInt(100) + 1;
			deliverable4 test = new deliverable4();
			int[] randomArray = test.generateRandomArray(size);				
			int[] billifiedArray = test.billify(randomArray);
			for(int j = 0; j < billifiedArray.length - 1; j++){
				sum += billifiedArray[j]; // calculate the sum of the array[j] before the last one
			}
			assertTrue(sum == billifiedArray[billifiedArray.length-1]);
		}
	}
	
	//Test3: the last element is lager than any one before it;
	@Test
	public void lastIsLargestTest(){
		for(int i = 0; i < 100; i++){
			int sum = 0;
			int size = ran.nextInt(100) + 1;
			deliverable4 test = new deliverable4();
			int[] randomArray = test.generateRandomArray(size);				
			int[] billifiedArray = test.billify(randomArray);
			for(int j = 0; j < billifiedArray.length - 1; j++){
				if(billifiedArray[j] >= sum){ // find the largest one before the last element;
					sum = billifiedArray[j];
				}
			}
			assertTrue(billifiedArray[billifiedArray.length - 1] >= sum);
			// the reason why there is an "=" is because considering the length as 1; 
		}
	}
}
