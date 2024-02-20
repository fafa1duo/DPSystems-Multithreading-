package by.bsu.lesson01homework;
public class LeibnizPi {

    // Класс, реализующий Runnable, который вычисляет часть суммы ряда Лейбница
    static class LeibnizTask implements Runnable {
        private int start; // Начальный индекс члена ряда
        private int end; // Конечный индекс члена ряда
        private double sum; // Сумма членов ряда от start до end

        // Конструктор, принимающий начальный и конечный индексы
        public LeibnizTask(int start, int end) {
            this.start = start;
            this.end = end;
            this.sum = 0;
        }

        // Метод, возвращающий сумму членов ряда
        public double getSum() {
            return sum;
        }

        // Метод, выполняющийся в отдельном потоке
        @Override
        public void run() {
            // Вычисляем сумму членов ряда по формуле
            for (int i = start; i <= end; i++) {
                sum += Math.pow(-1, i) / (2 * i + 1);
            }
        }
    }

    // Главный метод программы
    public static void main(String[] args) throws InterruptedException {
        // Задаем количество потоков
        int threads = 4;
        // Задаем количество членов ряда
        int n = 1000000;
        // Создаем массив для хранения задач
        LeibnizTask[] tasks = new LeibnizTask[threads];
        // Создаем массив для хранения потоков
        Thread[] threadsArray = new Thread[threads];
        // Разбиваем ряд на равные части для каждого потока
        int chunk = n / threads;
        // Запускаем каждый поток с соответствующей задачей
        for (int i = 0; i < threads; i++) {
            // Определяем начальный и конечный индексы для каждого потока
            int start = i * chunk;
            int end = (i == threads - 1) ? n : (i + 1) * chunk - 1;
            // Создаем задачу для вычисления части суммы ряда
            tasks[i] = new LeibnizTask(start, end);
            // Создаем поток для выполнения задачи
            threadsArray[i] = new Thread(tasks[i]);
            // Запускаем поток
            threadsArray[i].start();
        }
        // Объявляем переменную для хранения общей суммы ряда
        double totalSum = 0;
        // Дожидаемся завершения всех потоков
        for (int i = 0; i < threads; i++) {
            // Ожидаем завершения потока
            threadsArray[i].join();
            // Добавляем сумму членов ряда, вычисленную потоком, к общей сумме
            totalSum += tasks[i].getSum();
        }
        // Умножаем общую сумму на 4, чтобы получить приближение к числу $$\pi$$
        double pi = 4 * totalSum;
        // Выводим результат в стандартный выходной поток
        System.out.println("Приближение к числу $$\\pi$$ по ряду Лейбница с " + n + " членами: " + pi);
    }
}
