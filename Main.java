import java.util.Random;

public class Main {

    public static void RandomMatrix(int size, int[][] matrix){
        Random random = new Random();

        // Populate the matrix with random numbers
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(100); // Generate a random integer between 0 and 99
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 20; // Size of the matrix
        int[][] m1 = new int[size][size];
        int[][] m2 = new int[size][size];
        int[][] result = new int[size][size];

        System.out.println("Matrix 1");
        RandomMatrix(size, m1);
        printMatrix(m1);

        System.out.println("Matrix 2");
        RandomMatrix(size, m2);
        printMatrix(m2);

        ThreadClass[] threads = new ThreadClass[5];
        int threadIndex = 0;
        for (int i = 0; i < 20; i += 4) {
            threads[threadIndex] = new ThreadClass(i, i + 4, m1, m2, result, "Thread " + (threadIndex + 1));
            threads[threadIndex].start();
            threadIndex++;
        }

        // Join all threads to wait for them to complete
        for (ThreadClass thread : threads) {
            thread.join();
        }

        // resultant product matrix
        System.out.println("Resultant Matrix:");
        printMatrix(result);
    }
}


