// File:    main.cpp
// Author:  HouPha Vang
// Date:    10/9/2016

// Description: This file contains the main function
// The program will read ints from the keyboard into an array until the
// sentinel value is entered, output them to the screen, output the average of
// the values, output the biggest value in the array, output the smallest value
// in the array, output the number of values in the array less than or equal to the average,
// output the number of values in the array greater than or equal to the average
// output an array of all the even numbers in the array, and finally output an
// array of all the even numbers in the array


#include <cmath>     
#include <cstdlib>
#include <iostream>
#include <iomanip>
#include "constants.h"

using namespace std;

//function prototypes
void input (int[], int&);
void outputArray(const int[],int);
double calculateAverage(const int[], int);
int biggestValue(const int[], int);
int smallestValue(const int[],int);
void countLessEqualAndGreater(const int[],int, double, int&, int&);
void getOddEvenArrays(const int[],int,int[],int&,int[],int&);



// Function: main
// Description: the main function of the program that calls other functions
// Input:  <none>
// Output: <none>
// Preconditions: <none> 
// Postconditions: <none>

int main() {
    //strings for user prompt
    string prompt1="The array is: \n";
    string prompt2="The average for the list of ints is: ";
    string prompt3="The first value in the array less than or equal to the average is: ";
    string prompt4="The biggest value in the array is: ";
    string prompt5="The smallest value in the array is: ";
    string prompt6_1="The number of ints in the array less than or equal to the average is: ";
    string prompt6_2="The number of ints in the array greater than the average is:  ";
    string prompt7_1="The array of all the inputted even ints is: \n";
    string prompt7_2="The array of all the inputted odd ints is: \n";

    //variables for user input
    int list[array_size]; //the array that holds all the inputted integers
    int count=0;
    
    //variables for array processing
    int greaterCount=0;//variable to hold the number of ints greater than the average
    int lessEqualCount=0;//variable to hold the number of ints less than the average
    int evenCount=0;//variable to hold the number of even integers in the array
    int oddCount=0;//variable to hold the number of odd integers in the array
    int evenNumbers[array_size];//the array that will hold all even numbers inputted
    int oddNumbers[array_size];//the array that will hold all odd numbers inputted
    double average;//the number that will hold the average
    
    //part 1, begin user input
    input(list,count);
    
    //part 2, print array
    cout<<prompt1;
    outputArray(list,count);
    cout<<endl;
    
    //part 3, output the average formatted to two decimals
    average=calculateAverage(list,count);
    cout<<fixed<<setprecision(2)<<prompt2<<average<<endl;
    
    //part 4, output biggest value
    cout<<prompt4<< biggestValue(list,count)<<endl;
    
    //part 5, output smallest value
    cout<<prompt5<<smallestValue(list,count)<<endl;
    
    //part 6, output the number of ints less than or equal to the average 
    //as well as the number of ints greater than the average
    
    countLessEqualAndGreater(list,count,average,lessEqualCount,greaterCount);    
    //output the number of ints less than or equal to average
    cout<<prompt6_1<<lessEqualCount<<endl;
    //output the number of ints greater than or equal to average
    cout<<prompt6_2<<greaterCount<<endl;
    //part 7, create and output one array of all the odd inputted integers and
    //one array of all the even inputted integers
    getOddEvenArrays(list,count,evenNumbers,evenCount,oddNumbers,oddCount);
    cout<<prompt7_1;
    outputArray(evenNumbers,evenCount);
    cout<<endl<<prompt7_2;
    outputArray(oddNumbers,oddCount);
    
    return 0;
}

