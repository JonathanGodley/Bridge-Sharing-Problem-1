// SingletonController.java
// Singleton Design Pattern used to coordinate farmer threads
//
// Programmer:  Jonathan Godley - c3188072
// Course: Comp2240
// Last modified:  20/09/2017
public class SingletonController
{
// instance Variables
private int value = 0;
private char last = 'N';
private int highest = 0;
private static SingletonController instance = new SingletonController();
// private constructor
private SingletonController(){
}

// preconditon: N/a
// Postcondition: return the only instance/object available
public static SingletonController getInstance()
{
        return instance;
}
//precondition: N/A
//Postcondition value incremented
public int update()
{
        value++;
        return (value);
}

//Postcondition: returns last travelled direction
public char last()
{
        return last;
}

//Postcondition: set last travelled direction
public void setLast(char newLast){
        last = newLast;
}
//Postcondition: increments highest number of trips completed
public void incrementHighest(){
        highest++;
}

//Postcondition: returns the highest number of trips completed by a single farmer
public int getHighest()
{
        return highest;
}
}
