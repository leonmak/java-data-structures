//import java.lang.reflect.Array;

public class ArrayStructures {
	// INIT: CAP.
	private int[] theArray = new int[10]; 
	// Creates an array with 10 indexes, cap is fixed.	

	// LENGTH.
	private int arrayLength = theArray.length; // indexes in theArray

	// COUNT. tracks size
	private int arraySize = 0; // elements in theArray

	// int (min) int (max)> {Populates array}
	public void generateRandomArray(){
		for(int i = 0; i < arraySize; i++){
			theArray[i] = (int)(Math.random()*10)+10;
		}
	}
	public void generateRandomArray(int min, int max){
		if(max<min){
			int a = min;
			min = max;
			max = a;
		}
		for(int i = 0; i < arrayLength; i++){
			theArray[i] = (int)(Math.random()*(max-min))+min;
			arraySize ++;
		}
	}

	// > array
	public int[] getTheArray(){
		return theArray;
	}
	public int getArraySize(){
		return arraySize;
	}
	// > print
	public void printArray(){
		System.out.println("----------");
		for(int i = 0; i < arraySize; i++){
			System.out.print("| " + i + " | ");
			System.out.println(theArray[i] + " |");
			System.out.println("----------");
		}
	}
	// int (index) > int (value)
	public int getValueAtIndex(int index){
		if(index < arraySize && index>=0) {
			return theArray[index];
		}
		return 0;
	}
	// int (value) > T/F
	public boolean isInArray(int value){
		for(int i=0; i<arraySize; i++){
			if(theArray[i]== value){
				return true;
			}
		}
		return false;
	}

	// int (index) > {deletes value, shifts all up}
	public void deleteIndex(int index){
		if(index < arraySize){
			for(int i = index; i < (arraySize - 1); i++){
				theArray[i] = theArray[i+1];
			}
			arraySize--;
		}
	}
	// int (value) > {insert value, inc count}
	public void insertValue(int value){
		if(arraySize < 50){
			theArray[arraySize] = value;
			arraySize++;
		}
	}

	// Linear Search : Every index must be looked at
	// better for duplicates in input
	public String linearSearchForValue(int value){
		boolean valueInArray = false;
		String indexsWithValue = "";
		System.out.print("The Value was Found in the Following Indexes: ");
		for(int i = 0; i < arraySize; i++){
			if(theArray[i] == value) {
				valueInArray = true;
				System.out.print(i + " ");
				indexsWithValue+= i + " ";
			}
		}
		if(!valueInArray){
			indexsWithValue = "None";
			System.out.print(indexsWithValue);
		}
		System.out.println();
		return indexsWithValue;
	}

	// BINARY SEARCH - faster than linear as values are sorted
	public void binarySearchForValue(int value){
		int lowIndex = 0;
		int highIndex = arraySize - 1;
		while(lowIndex <= highIndex){
			int middleIndex = (highIndex + lowIndex) / 2;
			if(theArray[middleIndex] < value) lowIndex = middleIndex + 1;
			else if(theArray[middleIndex] > value) highIndex = middleIndex - 1;
			else {
				System.out.println("\nFound a Match for " + value + " at Index " + middleIndex);
				lowIndex = highIndex + 1;
			}
			printHorzArray(middleIndex, -1);
		}
	}
	// i,j track index, -1 to hide.
	public void printHorzArray(int i, int j){
		for(int n = 0; n < 51; n++)System.out.print("-");
		System.out.println();
		for(int n = 0; n < arraySize; n++){
			System.out.print("| " + n + "  ");
		}
		System.out.println("|");
		for(int n = 0; n < 51; n++)System.out.print("-");
		System.out.println();
		for(int n = 0; n < arraySize; n++){
			System.out.print("| " + theArray[n] + "  ");
		}
		System.out.println("|");
		for(int n = 0; n < 51; n++)System.out.print("-");
		System.out.println();
		// END OF FIRST PART
		// ADDED FOR BUBBLE SORT
		if(j != -1){
			// ADD THE +2 TO FIX SPACING
			for(int k = 0; k < ((j*5)+2); k++)System.out.print(" ");
			System.out.print(j);
		}
		// THEN ADD THIS CODE
		if(i != -1){
			// ADD THE -1 TO FIX SPACING
			for(int l = 0; l < (5*(i - j)-1); l++)System.out.print(" ");
			System.out.print(i);
		}
		System.out.println();
	}

	// BUBBLE SORT
	// Sort right most first.
	public void bubbleSort(){ 
		// simplified example. 1+..+n. (n/2)(n+1), O(n^2)
		for(int i = arraySize - 1; i > 1; i--){
			for(int j = 0; j < i; j++){
				if(theArray[j] > theArray[j + 1]){
					swapValues(j, j+1);
			        printHorzArray(-1,-1);

				}
			}
		}
	}

	// int (index), int (index)> {swap position}
	protected void swapValues(int first, int second){
		int temp = theArray[first];
		theArray[first] = theArray[second];
		theArray[second] = temp;
	}		

	// Selection sort
	// Sort left most first.
	public void selectionSort(){
		for(int i=0; i<arraySize-1; i++){
			int min =i;
			for(int j=i+1; j<arraySize; j++){
				if(theArray[j]<theArray[min]){
					min = j;
				}
			}
			swapValues(i,min);
			printHorzArray(i,-1);
		}
	}
	// The Insertion Sort is normally the best of 
	// the elementary sorts. Unlike the other sorts that
	// had a group sorted at any given time, groups are
	// only partially sorted here.
	public void insertionSort(){
		
//		for(int i=1; i<arraySize; i++){
//			int save = theArray[i]; 
//			int s=i-1; // M - pass j outside
//			for(int j=s; j>=0; j--){
//				printHorzArray(i, j);
//				if(theArray[j]>save){
//					theArray[j+1]=theArray[j];
//				}else{ 
//					s=j+1; // M - non-end insertion
//					break;
//				}
//				s=j;
//			}
//			theArray[s]=save;
//		}
		// point & lo
		for (int i = 1; i < arraySize; i++){
			int j = i;
			int toInsert = theArray[i];
			while ((j > 0) && (theArray[j-1] > toInsert)){ //must be && to shortcircuit
				theArray[j] = theArray[j-1];
				j--;
				printHorzArray(i, j);
			}
			theArray[j] = toInsert;
			printHorzArray(i, j);
			System.out.println("\nArray[i] = " + theArray[i] + " Array[j] = " + theArray[j] + " toInsert = " + toInsert + "\n");
		}
	}
	public static void main(String[] args){
		ArrayStructures newArray = new ArrayStructures();
		newArray.generateRandomArray(0,6);
		newArray.printHorzArray(-1,-1);
		// newArray.linearSearchForValue(10);
		// newArray.bubbleSort();
		// We must Sort first
		// newArray.binarySearchForValue(17);
		// newArray.selectionSort();
		newArray.insertionSort();
	}
}

