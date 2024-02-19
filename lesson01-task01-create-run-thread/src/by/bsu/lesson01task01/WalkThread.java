package by.bsu.lesson01task01;

public class WalkThread extends Thread {
public void run() {
try {
for (int i = 0; i < 7; i++) {
System.out.println("Walk " + i);
try {
Thread.sleep(1);
} catch (InterruptedException e) {
e.printStackTrace();
}
}
} finally {
System.out.println(Thread.currentThread().getName());
}
}
}