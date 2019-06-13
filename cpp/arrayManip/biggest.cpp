// File:    biggest.cpp
// Author:  HouPha Vang
// Program: Assignment 5
// Date:    10/9/2016

// Description: This file contains the biggestValue function.
// The program receives an integer array and the number of integers within the
// array and then outputs the largest integer within the array.

// Function: biggestValue
// Description: a function to find the largest integer in an integer array
// Input:   numbers (array of int)
//		    count (int) - the number of values in the array
// Output:  biggest (int) - the biggest number in the array
// Preconditions: count contains the number of values stored in the array
// Postconditions: <none>

int biggestValue(const int numbers[], int count)
{
    int biggest=numbers[0];//set biggest to the first value in the array.
    for(int i=0;i<count;i++)
    {
        if(numbers[i]>biggest)
        {
            biggest=numbers[i];
        }
    }
    return biggest;
}