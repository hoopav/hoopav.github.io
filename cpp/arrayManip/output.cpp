// File:    output.cpp
// Author:  HouPha Vang
// Program: Assignment 5
// Date:    10/9/2016

// Description: This file contains the function outputArray that outputs a provided
//				array to the screen on a single line, separated by spaces

#include <iostream>

using namespace std;

// Function: outputArray
// Description: The function will output an array of ints to the screen on a single
//				line, separated by spaces
// Input:  numbers (array of int)
//         count (int) - the number of values in the array
// Output: <none>
// Preconditions:  count contains the number of values stored in the array
// Postconditions: The array of ints is outputted to the screen


void outputArray(const int numbers[], int count)
{
    for(int i=0;i<count;i++)
    {
        cout<<numbers[i]<<" ";
    }
}