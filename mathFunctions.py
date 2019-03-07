def sqrt(val):#crappy implementation
    answer=val/2
    while abs((answer*answer)-val)>0.0001 : #while the difference is>0.1
        if (answer*answer)-val>0: #check if we overshot
            answer=answer*.75
        else:
            answer=answer*1.25
    return answer

def printLoop(listOfStrings):
    for strings in listOfStrings:
        print(strings)

functions=[]
functions.append("sqrt")

def applyFunction(name, value):
    answer=""
    if name == "sqrt":
        answer=sqrt(int(value))
    return answer

def main():
    print("This is a program to test math functions")
    print("Implemented functions: ")
    printLoop(functions)
    mathFunction=input("\nType the name of the function\n")
    mathValue=input("\nType the value to use\n")
    answer=applyFunction(mathFunction,mathValue)
    print(mathFunction+"("+mathValue+") = "+str(answer))
main()
