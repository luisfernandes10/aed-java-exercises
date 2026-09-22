import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        T item;
        Node next;
    }

    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    public void enqueue(T item) {
        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            first = newNode;
            last = newNode;
            size++;
        }
        else {
            last.next = newNode;
            last = newNode;
            size++;
        }
    }

    public T dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Queue underflow");

        T item = first.item;
        first = first.next;
        size--;

        if (isEmpty()) last = null;

        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void shift() {
        Node current = first;
        Node oldFirst = first;

        if (size <= 1)
            return ;

        for (int i = 0; i < size() - 2; i++) {
            current = current.next;
        }
        first = last;
        last.next = oldFirst;
        last = current;
        last.next = null;
    }

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        public boolean hasNext() {
            return !(current == null);
        }

        public T next() {
            if (!hasNext())
                throw new IllegalStateException("No next element");
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
