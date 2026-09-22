import java.util.Iterator;

public class OrderedList<T extends Comparable<T>> implements Iterable<T> {
    Node first; Node last;
    int size;

    public OrderedList() {
        first = null; last = null;
        size = 0;
    }

    private class Node {
        T item;
        Node next; Node previous;
    }

    public void add(T item) {
        Node newItem = new Node();
        newItem.item = item;

        if (isEmpty()) {
            first = newItem;
            last = first;
            size++;
        }
        else {
            last.next = newItem;
            last.next.previous = last;
            last = last.next;
            size++;
        }
    }

    public boolean contains(T item) {
        Node current = first;

        if (isEmpty())
            return false;

        while (current != null) {
            if (current.item.compareTo(item) == 0)
                return true;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext())
                throw new IllegalStateException("No next element");
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    public void sort() {
        if (isEmpty() || size == 1)
            return ;

        for (Node current = first.next; current != null; current = current.next)
            for (Node j = current; j != first; j = j.previous) {
                if (j.item.compareTo(j.previous.item) < 0) {
                    exchange(j, j.previous);
                }
                else
                    break;
            }
    }

    private void exchange(Node a, Node b) {
        T temp = a.item;
        a.item = b.item;
        b.item = temp;
    }

    public void shuffle() {
        Node current = first;
        T[] array = (T[]) new Comparable[size];

        for (int i = 0; i < size; i++) {
            array[i] = current.item;
            current = current.next;
        }

        for (int i = 0; i < size; i++) {
            exchangeArr(array, i, (int) (Math.random() * (i + 1)));
        }

        current = first;
        for (int i = 0; i < size; i++) {
            current.item = array[i];
            current = current.next;
        }
    }

    private void exchangeArr(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public boolean isSorted() {
        if (size == 1 || isEmpty())
            return true;

        for (Node current = first.next; current != null; current = current.next)
            if (current.item.compareTo(current.previous.item) < 0)
                return false;
        return true;
    }
}
