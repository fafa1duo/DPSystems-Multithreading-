/*
   A runnable that prints a countdown.
*/
package by.bsu.lesson01task04;

public class CountdownRunnable implements Runnable
{
   /*
      Constructs the runnable object.
      @param startingValue the starting value of the countdown
      @param aDelay the delay between counts
   */
   public CountdownRunnable(String newName,int startingValue, int aDelay)
   {
      counter = startingValue;
      delay = aDelay;
      name = newName;
   }
   
   // your work here

   private int counter;
   private int delay;
   private String name;

   public String getName() {return name;}
   
   // this method is used to test your work
   
   public static void main(String[] args)
   {
      CountdownRunnable r1 = new CountdownRunnable("Ranger",10, 10);
      CountdownRunnable r2 = new CountdownRunnable("Moon",20, 5);
      Thread t1 = new Thread(r1);
      Thread t2 = new Thread(r2);
      t1.setName(r1.getName());
      t2.setName(r2.getName());
      t1.start();
      t2.start();
   }   
   public void run() {
     System.out.println(Thread.currentThread());
     while (counter > 0) {
       System.out.println(name +": "+counter + "...");
       --counter;
       try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
     
      e.printStackTrace();
    }
     }
   }
}