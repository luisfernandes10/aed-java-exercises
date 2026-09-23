public class QuickSorting {
    public static void sort(Comparable[] a) {
        shuffle(a);
        sortSubarray(a, 0, a.length - 1);
    }

    public static void sortSubarray(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int j = partition(a, lo, hi);
        sortSubarray(a, lo, j - 1);
        sortSubarray(a, j + 1, hi);
    }

    public static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            for (int j = i; j > lo; j--)
                if (lessOrEqual(a[j], a[j - 1]))
                    exchange(a, j, j - 1);
                else
                    break;
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (i < j) {
            while(lessOrEqual(a[++i], a[lo]))
                if (i == hi)
                    break;

            while(lessOrEqual(a[lo], a[--j]))
                if (j == lo)
                    break;

            if (i >= j)
                break;

            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    public static int medianOfThree(Comparable[] a, int lo, int hi) {
        if (hi - lo < 2) {
            return lo;
        }

        int i1 = lo + (int) (Math.random() * ((hi - lo) + 1));
        int i2 = lo + (int) (Math.random() * ((hi - lo) + 1));
        int i3 = lo + (int) (Math.random() * ((hi - lo) + 1));

        if (lessOrEqual(a[i1], a[i2])) {
            if (lessOrEqual(a[i2], a[i3])) return i2;       // i1 <= i2 <= i3
            else if (lessOrEqual(a[i1], a[i3])) return i3;  // i1 <= i3 < i2
            else return i1;                                 // i3 < i1 <= i2
        } else {
            if (lessOrEqual(a[i1], a[i3])) return i1;       // i2 < i1 <= i3
            else if (lessOrEqual(a[i2], a[i3])) return i3;  // i2 <= i3 < i1
            else return i2;                                 // i3 < i2 < i1
        }
    }

    public static boolean lessOrEqual(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void shuffle(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int r = (int) (Math.random() * (i + 1));
            exchange(a, i, r);
        }
    }
}
