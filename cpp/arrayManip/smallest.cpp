// File:    smallest.cpp
// Author:  HouPha Vang
// Program: Assignment 5
// Date:    10/9/2016

// Description: This file contains the function smallestValue

// Function: smallestValue
// Description: given an array of integers and the number of values within, this
//				function returns the smallest integer from that array.
// Input:  numbers (array of int) 
//		   count (int) - the number of values in the array
// Output: smallest (int) - the smallest number in the array
// Preconditions:  count contains the number of values stored in the array
// Postconditions: <none>
int smallestValue(const int numbers[], int count)
{
    int smallest=numbers[0];//set smallest to the first value in the array
    for(int i=0;i<count;i++)
    {
        if(numbers[i]<smallest)
        {
            smallest=numbers[i];
        }
    }
    return smallest;
}