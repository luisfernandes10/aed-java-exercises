import java.util.Iterator;

public class List<T> implements Iterable<T> {
    Node first; Node last;
    int size;

    public List() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        public T item;
        public Node next;
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
            last = last.next;
            size++;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            return null;

        T item = null;
        Iterator<T> it = iterator();

        for (int i = 0; i <= index; i++){
            item = it.next();
        }
        return item;
    }

    public T remove(int index) {
        if (index < 0 || index >= size)
            return null;

        T item;

        if (index == 0) {
            item = first.item;
            first = first.next;

            if (first == null)
                last = null;
        }
        else {
            Node current = first;

            for (int i = 0; i < index - 1; i++)
                current = current.next;

            item = current.next.item;
            current.next = current.next.next;

            if (current.next == null)
                last = current;
        }
        size--;
        return item;
    }

    public boolean removeFirst(T item) {
        if (isEmpty())
            return false;

        if (first.item.equals(item)) {
            return remove(0) != null;
        }

        Node previous = first;
        Node current = first.next;

        while (current != null) {
            if (current.item.equals(item)) {
                previous.next = current.next;

                if (current.next == null) {
                    last = previous;
                }

                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public boolean removeLast(T item) {
        if (isEmpty())
            return false;

        int lastSeenIndex = -1;
        int currentIndex = 0;
        Node current = first;

        while (current != null) {
            if (current.item.equals(item))
                lastSeenIndex = currentIndex;
            current = current.next;
            currentIndex++;
        }

        if (lastSeenIndex == -1)
            return false;

        remove(lastSeenIndex);
        return true;
    }

    public boolean removeAll(T item) {
        if (isEmpty())
            return false;

        boolean removedAny = false;

        while (first != null && first.item.equals(item)) {
            first = first.next;
            size--;
            removedAny = true;
        }

        if (first == null) {
            last = null;
            return removedAny;
        }

        Node previous = first;
        Node current = first.next;

        while (current != null) {
            if (current.item.equals(item)) {
                previous.next = current.next;

                if (current.next == null)
                    last = previous;

                size--;
                removedAny = true;

                current = current.next;
            }
            else {
                previous = current;
                current = current.next;
            }
        }

        return removedAny;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T item) {
        if (isEmpty())
            return false;

        if (first.item.equals(item)) {
            return true;
        }

        Node current = first.next;

        while (current != null) {
            if (current.item.equals(item))
                return true;

            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isPalindrome() {
        if (isEmpty() || size == 1)
            return true;

        Object[] elements = new Object[size];
        Node current = first;
        int index = 0;

        while (current != null) {
            elements[index] = current.item;
            current = current.next;
            index++;
        }

        int start = 0;
        int end = size - 1;

        while (start < end) {
            if (!elements[start].equals(elements[end]))
                return false;
            start++;
            end--;
        }
        return true;
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
