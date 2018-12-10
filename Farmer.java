// Farmer.java
// Farmer class implemented as a thread for concurrency using a Semaphore
//
// Programmer:  Jonathan Godley - c3188072
// Course: Comp2240
// Last modified:  20/09/2017
import java.util.concurrent.Semaphore;

public class Farmer implements Runnable
{
private Semaphore bridgeAccess;
private char location; // current location of farmer
private int crossings; // number of crossings done by a farmer
private boolean done;
private SingletonController bridgeController = SingletonController.getInstance();

public Farmer(Semaphore linkSem, char startLocation)
{
        bridgeAccess = linkSem; // link a semaphore to the farmer object
        location = startLocation;
}

public void run()
{
  // method variables
        String message;
        String travelInfo[] =
        {
                "Crossing bridge Step 5.",
                "Crossing bridge Step 10.",
                "Crossing bridge Step 15.",
                "Across the bridge."
        };
        try
        {
                while (Thread.currentThread().isAlive())
                {
                        done = false;
                        // determine destination of farmer
                        if (location == 'N')
                        {
                                message = "Waiting for bridge. Going towards South";
                        }
                        else message = "Waiting for bridge. Going towards North";
                        threadMessage(message);

                        while (!done)
                        {
                                // attempt to acquire semaphore
                                bridgeAccess.acquire();

                                // if current farmer is on the opposite side as the last successful crossing AND it has done less crossings then the others
                                if (bridgeController.last() != location && crossings < bridgeController.getHighest())
                                {
                                        // loop through travel messages
                                        for (int i = 0; i < travelInfo.length; i++)
                                        {
                                                // Pause for 1 second
                                                Thread.sleep(1000);
                                                // Print a message
                                                threadMessage(travelInfo[i]);
                                        }
                                        // increment & print neon sign
                                        System.out.println("NEON = "+bridgeController.update());

                                        bridgeController.setLast(location);
                                        crossings++;
                                        done = true;
                                }
                                else if (bridgeController.last() != location && crossings == bridgeController.getHighest())
                                {
                                        // loop through travel messages
                                        for (int i = 0; i < travelInfo.length; i++)
                                        {
                                                // Pause for 1 second
                                                Thread.sleep(1000);
                                                // Print a message
                                                threadMessage(travelInfo[i]);
                                        }
                                        // increment & print neon sign
                                        System.out.println("NEON = "+bridgeController.update());

                                        bridgeController.setLast(location);
                                        bridgeController.incrementHighest();
                                        crossings++;
                                        done = true;
                                }
                                // release semaphore
                                bridgeAccess.release();
                        }

                }
        }
        catch (InterruptedException e)
        {
                // display exception if caught
                threadMessage(e.getMessage());
        }
}
// output message from thread displaying threadname
static void threadMessage(String message)
{
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
}
}
