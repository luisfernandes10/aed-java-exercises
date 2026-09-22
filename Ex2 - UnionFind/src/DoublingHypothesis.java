public class DoublingHypothesis {
    private static final int START = 125;
    private static final long LIMIT = 128000;

    private static final int TRIALS = 5;

    public static double getElapsedTimeMillis(int N) {
        QuickFindUF qf = new QuickFindUF(N);

        double start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            int p = randomInt(0, N - 1);
            int q = randomInt(0, N - 1);
            qf.union(p, q);
        }
        double end = System.currentTimeMillis();
        return end - start;
    }

    public static double getAverageTimeMillis(int N) {
        double sum = 0.0;

        for (int i = 0; i < TRIALS; i++)
            sum += getElapsedTimeMillis(N);

        return sum / TRIALS;
    }

    public static double millisToSeconds(double millis) {
        return millis / 1000.0;
    }

    public static void applyDoublingHypothesis() {
        double previous = millisToSeconds(getAverageTimeMillis(START)); // T(N)
        double lgRatio = 0;

        IO.println("Doubling Hypothesis\n");
        IO.println("N\t\tT(N)\t\tratio\t\tlog(ratio)");

        for (int n = START * 2; n <= LIMIT; n += n) {
            double time = millisToSeconds(getAverageTimeMillis(n)); // T(2N)
            double ratio = time / previous; // ratio = T(2N) / T(N)
            lgRatio = Math.log(ratio) / Math.log(2);

            System.out.printf("%d\t\t%.3f\t\t%.3f\t\t%.3f\n", n, time, ratio, lgRatio);

            previous = time;
        }

        double b = lgRatio;
        double a = previous / Math.pow(LIMIT, b);

        IO.println("\nT(N) = " + a + "* N^" + b);
        IO.println("\nT(10^9) = " + a * Math.pow(10e9, b) + " seconds");
    }

    public static int randomInt(int start, int end) {
        return (int) (start + (end - start) * Math.random());
    }

    public static void main(String[] args) {
        applyDoublingHypothesis();
    }
}
