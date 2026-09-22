public class ResizingArrayQueueOfStrings {
    private String[] queue;
    int first = -1; int last = -1;

    public ResizingArrayQueueOfStrings() {
        queue = new String[2];
    }

    public void enqueue(String item) {
        if (size() == queue.length)
            resize(queue.length * 2);

        queue[next(last)] = item;
        last = next(last);

        if (first == -1)
            first = 0;
    }

    public String dequeue() {
        if (first == -1)
            throw new IllegalStateException("Error: Queue underflow");

        String item = queue[first];
        queue[first] = null;

        if (first == last) {
            first = -1;
            last = -1;
        }
        else
            first = next(first);

        if (queue.length > 1 && size() == queue.length / 4)
            resize(queue.length / 2);

        return item;
    }

    public boolean isEmpty() {
        return first == last && first == -1;
    }

    public int size() {
        if (first == -1)
            return 0;
        else if (first <= last)
            return last - first + 1;
        else
            return queue.length - first + last + 1;
    }

    public void shift() {
        if (isEmpty() || first == last)
            throw new IllegalStateException("Unable to shift if Queue is empty.");

        String lastItem = queue[last];
        queue[last] = null;

        last = (last - 1 + queue.length) % queue.length;
        first = (first - 1 + queue.length) % queue.length;

        queue[first] = lastItem;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];

        if (first == -1) {
            queue = copy;
            return ;
        }

        int i = 0;
        int current = first;

        while (current != last) {
            copy[i] = queue[current];
            current = next(current);
            i++;
        }

        copy[i] = queue[last];

        first = 0;
        last = i;
        queue = copy;
    }

    private int next(int i) {
        return (i + 1) % queue.length;
    }
}
