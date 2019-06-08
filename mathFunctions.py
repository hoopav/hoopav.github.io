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
functions.append("fact")

def applyFunction(name, value):
    answer=""
    if name == "sqrt":
        answer=sqrt(value)
    elif name == "fact":
        answer==fact(value)
    return answer

def checkImplemented(fName):
    isIn=fName in functions
    return isIn

def fact(value):
    answer=1
    for i in range (1,value+1):
        answer=answer*i
    return answer

def main():
    print("This is a program to test math functions")
    print("Implemented functions: ")
    printLoop(functions)
    mathFunction=input("\nType the name of the function\n")
    isImplemented=checkImplemented(mathFunction)
    while(not isImplemented):
        print(mathFunction+" is either unimplemented or invalid.\n")
        mathFunction=input("\nType the name of the function\n")
        isImplemented=checkImplemented(mathFunction)
    mathValue=input("\nType the value to use\n")

    isNumber=mathValue.isnumeric()
    while(isNumber == False):
        print(mathValue+" is not a number.")
        mathValue=input("\nType the value to use\n")
        isNumber=mathValue.isnumeric()
    #print(isNumber)
    answer=applyFunction(mathFunction,mathValue)
    print(mathFunction+"("+mathValue+") = "+str(answer))
#main()

def mathCommands():
    input("Type in math commands\n")
    
mathCommands()
