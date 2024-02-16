class ThreadClass extends Thread {
    private int startRow;
    private int endRow;
    private int[][] m1;
    private int[][] m2;
    private int[][] result;
    private String threadName;

    public ThreadClass(int startRow, int endRow, int[][] m1, int[][] m2, int[][] result, String threadName) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.m1 = m1;
        this.m2 = m2;
        this.result = result;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        // Displaying the thread that is running
        System.out.println(threadName + " is running");

        // Perform matrix multiplication for assigned rows
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
    }
}