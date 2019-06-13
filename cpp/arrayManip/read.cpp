// File:    read.cpp
// Author:  HouPha Vang
// Program: Assignment 5
// Date:    10/9/2016

// Description: This file contains the function called input

#include <cstdlib>
#include <iostream>
#include "constants.h"

using namespace std;

// Function: input
// Description: reads ints from standard input (keyboard) until the sentinel
//              value is entered and stores them them in an array.
// Input:  <none>
// Output: numbers (array of int)
//         count (int) - the number of values read and stored into the array
// Preconditions:  <none>
// Postconditions: The array will be populated with the values entered
//                 from the keyboard and the count will contain the number 
//                 of values in the array.  An error message will be output
//                 if the array size was exceeded, and invalid data will be 
//				   stored if only the sentinel value is inputted.

void input(int numbers[], int& count)
{
    count=0;
    int userInput;
    cout<<"Type an integer to add to the array or type '0' to quit.\n";
    cin>>userInput;
    while(userInput!=sentinel_value)
    {
        if(count>9) //a check to ensure no more than ten integers are inputted
        {
            cout<<"Error: input exceeds array size\n";
        }
        else if(userInput > sentinel_value)//the case where the user inputs an integer in the array
        {
            numbers[count]=userInput;//add the inputted integer to next available spot
            //move index to next free spot
            count++;//increase count of integers stored in array
        }
        else//check for valid input
        {
            cout<<"Error: invalid input\n";
        }
       
        cout<<"Type an integer to add to the array or type '0' to quit.\n";
        cin>>userInput;
    }
}