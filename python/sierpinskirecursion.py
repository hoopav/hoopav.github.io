#HouPha Vang
#08/23/2016
#This is a program that draws the sierpinski triangle using the turtle module and
#recursion


import turtle

def drawTriangle(t,p1,p2,p3):
    t.up()
    t.goto(p1)
    t.down()
    t.goto(p2)
    t.goto(p3)
    t.goto(p1)

def midPoint(p1,p2):
    return ((p1[0]+p2[0])/2.0,(p1[1]+p2[1])/2.0)

def sierpinski(myTurtle,p1,p2,p3,depth):
    if depth > 0:
        sierpinski(myTurtle,p1,midPoint(p1,p2),midPoint(p1,p3),depth-1,)
        sierpinski(myTurtle,p2,midPoint(p2,p3),midPoint(p2,p1),depth-1,)
        sierpinski(myTurtle,p3,midPoint(p3,p1),midPoint(p3,p2),depth-1,)
    else:
        drawTriangle(myTurtle,p1,p2,p3)
        
        
fred = turtle.Turtle()
win = turtle.Screen()

sierpinski(fred, (-100,-100), (100,-100), (0,100), 4)
win.exitonclick()
