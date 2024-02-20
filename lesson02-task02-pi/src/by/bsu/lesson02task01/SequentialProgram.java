package by.bsu.lesson02task01;


public class SequentialProgram {
	
	    
	public static void main(String[] args) {
		int n = 1_000_000;
		
		long startTime = System.currentTimeMillis(); // 记录开始时间
        double result = calculatePi(n);
        long finishTime = System.currentTimeMillis(); // 记录结束时间
       long elapsedTime = finishTime - startTime;
//        System.out.println("calcalatePi计算出的π的值: " + result);
//        System.out.println("运行时间: " + (finishTime - startTime) + " elapsedTime");
       System.out.println("result = " + result +
    		   "\ntime = "+ (elapsedTime/1000.0)+ "seconds. ");
	}
	private static double calculatePi(int n) {
		
		int i;
		int start;
		double h;
		double sum;
		double x;
		

		h = 1. / n;
		sum = 0.;
		start = 0;

		for (i = start; i < n; i += 1)
		{
			x = h * i;
			sum += 4. / (1. + x*x);
		}
		sum = h*sum;
		return sum;
		
	}
}
