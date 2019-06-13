// File:    getOddEven.cpp
// Author:  HouPha Vang
// Program: Assignment 5
// Date:    10/9/2016

// Description: This file contains the getOddEvenArrays function.
// The program receives an integer array and the number of integers within the
// array along with references for two additional arrays and their number of values
// that will be populated with the even numbers from the original integer array
// and the odd numbers from the original array, respectively.

// Function: getOddEvenArrays
// Description: a function to populate two arrays from a given array. One array will
//              contain all the even numbers from the original array, and the other
//				will contain all the odd numbers from the original array.
// Input:   numbers (array of int)
//		    count (int) - the number of values in the array
// Output:  evenNumbers (array of int) - an array containing all the even values from numbers
//			evenCount (int) - the number of values in the evenNumbers array
//			oddNumbers (array of int) - an array containing all the odd values from numbers
//			oddCount (int) - the number of values in the oddNumbers array.
// Preconditions:	count contains the number of values stored in the array
//				  	Both evenNumbers and oddNumbers are initialized to the same
//				  	size as numbers.
// Postconditions:  evenNumbers is populated with all the even values from the original array
//					evenCount is set to the number of even values stored in evenNumbers.
//					oddNumbers is populated with all the odd values from the original array
//					oddCount is set to the number of odd values stored in oddNumbers

void getOddEvenArrays(const int numbers[],int count, int evenNumbers[],
        int& evenCount, int oddNumbers[], int& oddCount)
{
	//initialize counts for the arrays to be populated to zero
    evenCount=0;
    oddCount=0;
    
	//determine the counts for the odd array and even array
    for(int i=0;i<count;i++)
    {
        if(numbers[i]%2==0)
        {
            evenNumbers[evenCount]=numbers[i];
            evenCount++;
        }
        else
        {
            oddNumbers[oddCount]=numbers[i];
            oddCount++;
        }
    }
}