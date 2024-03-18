package by.bsu.lesson02task03;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ParallelSummation {
	private final static int MAX_SIZE = 100_000_000;

	private static class SummationThread extends Thread {
		private Integer[] array;
		private int id; // the unique id of the array
		private int minIndex;
		private int maxIndex;

		public SummationThread(Integer[] array, int id, int minIndex, int maxIndex) {
			super();
			this.array = array;
			this.id = id;
			this.minIndex = minIndex;
			this.maxIndex = maxIndex;
		}

		private long result;

		public long getResult() {
			return result;
		}

		public void run() {
			// sum items
//			long startTime = System.currentTimeMillis();
			long sum = 0;
			for (int i = minIndex; i < maxIndex; ++i) {
				sum += array[i];
			}
			result = sum;
//			long finishTime = System.currentTimeMillis();
//			long elapsedTime = finishTime - startTime;
//			System.out.println("Thread " + id + ": sum = " + sum + 
//					" time = " + elapsedTime);	

		}
	}

	public static void main(String[] args) {
		Integer[] array = new Integer[MAX_SIZE];

		// fill the array
		Random random = new Random();
		for (int i = 0; i < MAX_SIZE; ++i) {
			array[i] = random.nextInt(-49, 50);

		}

		{
			// sum items
			// Вычислить сумму последовательным способом, замерить время
			long startTime = System.currentTimeMillis();
			long sum = 0;
			for (Integer item : array) {
				sum += item;

			}
			long finishTime = System.currentTimeMillis();
			long elapsedTime = finishTime - startTime;
			System.out.println("Sequantial code: sum = " + sum + " time = " + elapsedTime);
		}
		{
			//int[] -> Integer[]
			//Реализация 3. Коллекция List<Integer>
			List<Integer> list = Arrays.asList(array);
			long startTime = System.currentTimeMillis();
			long sum = 0;
			for (Integer item : list) {
				sum += item;
			}
			long finishTime = System.currentTimeMillis();
			long elapsedTime = finishTime - startTime;
			System.out.println("Sequantial code with list<Integer>: sum = " + 
					sum + " time = " + elapsedTime);
			
		}
		{
			//Реализация 4. Stream
			List<Integer> list = Arrays.asList(array);
			long startTime = System.currentTimeMillis();

			int sum = list.stream().reduce(0, Integer::sum);
			
			long finishTime = System.currentTimeMillis();
			long elapsedTime = finishTime - startTime;
			System.out.println("Sequantial code with list.stream: sum = " + 
					sum + " time = " + elapsedTime);
		}
		{
			//Реализация 5. ParallelStream
			List<Integer> list = Arrays.asList(array);
			long startTime = System.currentTimeMillis();

			int sum = list.parallelStream().reduce(0, Integer::sum);
			
			long finishTime = System.currentTimeMillis();
			long elapsedTime = finishTime - startTime;
			System.out.println("Sequantial code with list.parallelStream: sum = " + 
					sum + " time = " + elapsedTime);
		}

		// sum of elements calculated using threads
		// 并行计算总和，测量时间
		// sum of elements calculated using 2 threads
        int numberThreads = 2;
        long startTime = System.currentTimeMillis();
        SummationThread[] worker = new SummationThread[numberThreads];
        int start = 0;
        int delta = MAX_SIZE / numberThreads;
        int end = delta;
        for (int i = 0; i < numberThreads; ++i) {
            worker[i] = new SummationThread(array, i, start, end);
            start += delta;
            end += delta;
        }

        for (int i = 0; i < numberThreads; ++i) {
            worker[i].start();
        }

        for (int i = 0; i < numberThreads; ++i) {
            try {
                worker[i].join();
            } catch (InterruptedException e) {
            }
        }

        long sum2Threads = 0;
        for (SummationThread thread : worker) {
            sum2Threads += thread.getResult();
        }
        long finishTime = System.currentTimeMillis();
        long elapsedTime = finishTime - startTime;

        System.out.println("Parallel code with 2 threads: sum = " + sum2Threads + 
                " time = " + elapsedTime);

		 // sum of elements calculated using 4 threads
        numberThreads = 4;
        startTime = System.currentTimeMillis();
        worker = new SummationThread[numberThreads];
        start = 0;
        delta = MAX_SIZE / numberThreads;
        end = delta;
        for (int i = 0; i < numberThreads; ++i) {
            worker[i] = new SummationThread(array, i, start, end);
            start += delta;
            end += delta;
        }

        for (int i = 0; i < numberThreads; ++i) {
            worker[i].start();
        }

        for (int i = 0; i < numberThreads; ++i) {
            try {
                worker[i].join();
            } catch (InterruptedException e) {
            }
        }

        long sum4Threads = 0;
        for (SummationThread thread : worker) {
            sum4Threads += thread.getResult();
        }
        finishTime = System.currentTimeMillis();
        elapsedTime = finishTime - startTime;

        System.out.println("Parallel code with 4 threads: sum = " + sum4Threads + 
                " time = " + elapsedTime);
	}

}
