// File:    average.cpp
// Author:  HouPha Vang
// Program: Assignment 5
// Date:    10/9/2016

// Description: This file contains the function calculateAverage

// Function: calculateAverage
// Description: given an array of integers and the number of values within, this
//				function returns the average of the array's values
// Input:  numbers (array of int) 
//		   count (int) - the number of values in the array
// Output: average (double) - the average of all the values in the array
// Preconditions:  count contains the number of values stored in the array
// Postconditions: <none>
double calculateAverage(const int numbers[], int count)
{
    double average;
    double sum=0;
    for(int i=0;i<count;i++)
    {
        sum+=numbers[i];
    }
    average=sum/count;
    return average;   
}