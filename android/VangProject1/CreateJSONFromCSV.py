import sys


#open file name
def getInput(prompt):
    if sys.version_info[0] < 3:
        return raw_input(prompt)
    else:
        return input(prompt)
    
#A function to read data of file
def printFile(filename):
    print(open(filename,"r").read())

  
filename=getInput("""Enter the name of your json file. Ex: for student.json, type 'student'.
If not existing, it will be created.
""")

jsonfilename=filename+".json"

file=open(jsonfilename,"w")

datafile=open(getInput("""Type the name of the file holding your data.
For example, if it is in a file called 'data.txt', type 'data.txt'\n"""),"r")

##The data file must be formatted the following way.
##The first line contains the attribute names, and the following
##lines contain their actual values. "," may be replaced with any other character except the newline
##
##Example file:
##name,age,id
##Alice,20,1
##Bob,32,2
##Candice,18,3

delim=getInput("""Type the character that separates your data.
For example, if your data looks like: Robert,21,12
Then type ','\n""")

jsonarrayname=getInput("""What name do you want for your JSONArray? For example, students, recipes, etc.\n""")
print("Creating json file...")

data=datafile.read()#read the lines from the file

file.write('{\n\t'+'"'+jsonarrayname+'":\n\t\t[')

#get list of attributes
lines=data.split("\n")
attributes=lines[0].split(delim)


#start writing data to file
for i in range (1,len(lines)-1):#write up to last line
    file.write('\n\t\t {')
    values=lines[i].split(delim)#split the line
    for j in range(0,len(values)-1):#write up to last attribute
        file.write('\n\t\t\t'+'"'+attributes[j]+'": '+'"'+values[j]+'",')
    file.write('\n\t\t\t'+'"'+attributes[len(values)-1]+'": '+'"'+values[len(values)-1]+'"\n\t\t},')
    
file.write('\n\t\t{')#write last object
values=lines[i].split(delim)#split the line
for j in range(0,len(values)-1):#write up to last attribute
    file.write('\n\t\t\t'+'"'+attributes[j]+'": '+'"'+values[j]+'",')
file.write('\n\t\t\t'+'"'+attributes[len(values)-1]+'": '+'"'+values[len(values)-1]+'"\n\t\t}]\n}')
file.close()
