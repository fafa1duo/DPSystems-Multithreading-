package by.bsu.lesson02task01;

public class SequentialProgram {
private final static int MAX = 1_000_000_000;
public static void main(String[] args) {
System.out.println("Sequential Program starts");
long startTime = System.currentTimeMillis();
int resule = countPrimes(2,MAX);
long elapsedTime = System.currentTimeMillis() - startTime;
System.out.println("elapsedTime = " + (elapsedTime/1000.0 ) + "seconds.");
}
private static int countPrimes(int min, int max) {
int count = 0;
for (int i = min; i <= max; i++)
if (isPrime(i))
count++;
return count;
}


/**
* Test whether x is a prime number. 
* x is assumed to be greater than 1.
*/
private static boolean isPrime(int x) {
assert x > 1;
int top = (int)Math.sqrt(x);
for (int i = 2; i <= top; i++)
if ( x % i == 0 )
return false;
return true;
}
}
