import java.util.Arrays;
import java.util.Comparator;

public class OrderedArray<T extends Comparable<T>> {

    static final int SIZE = 10;

    private T[] arr;
    private int last;

    public OrderedArray() {
        arr = (T[]) new Comparable[SIZE];
    }

    public void add(T item) {
        if (last == SIZE)
            throw new IllegalStateException("Overflow");
        arr[last++] = item;
    }

    public void sort() {
        for (int i = 1; i < arr.length; i++)
            for (int j = i; j > 0; j--)
                if (less(arr[j], arr[j - 1]))
                    exchange(j, j - 1);
                else
                    break;
    }

    public boolean isSorted() {
        for (int i = 1; i < arr.length; i++)
            if (less(arr[i], arr[i - 1]))
                return false;
        return true;
    }

    public void shuffle() {
        for (int i = 1; i < arr.length; i++)
            exchange(i, (int) (Math.random() * (i + 1)));
    }

    private boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private void exchange(int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public String toString() {
        return Arrays.toString(arr);
    }

    static void main() {
        OrderedArray<Integer> list = new OrderedArray<>();
        String s;
        while (!(s = IO.readln()).equals("end")) {
            if (s.equals("sort"))
                list.sort();
            else if (s.equals("shuffle"))
                list.shuffle();
            else if (s.equals("isSorted"))
                IO.println(list.isSorted());
            else
                list.add(Integer.parseInt(s));
        }
    }
}
