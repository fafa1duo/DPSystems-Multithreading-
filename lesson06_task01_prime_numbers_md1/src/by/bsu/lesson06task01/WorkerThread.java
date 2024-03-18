package by.bsu.lesson06task01;

import java.util.concurrent.*;

public class WorkerThread extends Thread{
  private int min ,max;//в диапазоне[min,max]считает количество простых
  private int result;
  private Semaphore Semaphore;
  public WorkerThread(int min,int max,Semaphore Semaphore) {
    super();
    this.min=min;
    this.max=max;
    this.Semaphore=Semaphore;
  }
  public int getResult() {
    return result;
  }
  @Override
  public void run() {
    //обработать запрос
    result = countPrimes(min, max);
    Semaphore.release();//счетчик ресурсов
  }
  static int countPrimes(int min,int max) {
    int count =0;
    for(int i=min;i<max;i++) {
      if(isPrime(i)) {
        count++;
      }
    }
    return count;
  }
    private static boolean isPrime(int num) {
        //assert num > 1;
        int top = (int) Math.sqrt(num);
        for (int i = 2; i <= top; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}