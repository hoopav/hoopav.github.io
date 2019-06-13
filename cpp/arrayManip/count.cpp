// File:    count.cpp
// Author:  HouPha Vang
// Program: Assignment 5
// Date:    10/9/2016

// Description: This file contains the countLessEqualAndGreater function.

// The program receives an integer array and the number of integers within the
// array, the average of the integers in the array, as well as references to two
// integer variables, one that will hold the number of values in the array less
// than or equal to the average and the other that will hold the number of values
// in the array greater than the average. Upon completion of the function, the
// lessEqualCount and greaterCount will be set to the actual count of those numbers
// based on the array's values.

// Function: countLessEqualAndGreater
// Description: a function to find the count of numbers in the array greater than the
// 				average and to find the count of numbers in the array less than or equal
//				to the average.

// Input:   numbers (constant array of int)
//		    count (int) - the number of values in the array
//			average (double) - the average value of the integers in the array 
//			
// Output:  lessEqualCount (int) -  the count of values in the array less than or
//									equal to average
//			greaterCount (int) - the count of values in the array greater than the
//                               the average

// Preconditions: count contains the number of values stored in the array
// Postconditions: <none>

void countLessEqualAndGreater(const int numbers[], int count, double average,int& lessEqualCount, int& greaterCount)
{
    lessEqualCount=0; //initialize the count of numbers less than or equal to the average to zero
    greaterCount=0; //initialize the count of numbers greater than the average to zero
    
    for(int i=0;i<count;i++)
    {
        if(numbers[i]>average)
        {
            greaterCount++;
        }
    }
    
    for(int i=0;i<count;i++)
    {
        if(numbers[i]<=average)
        {
            lessEqualCount++;
        }
    }
}
