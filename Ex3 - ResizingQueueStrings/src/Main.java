public static void main(String[] args) {
    ResizingArrayQueueOfStrings q = new ResizingArrayQueueOfStrings();

    test(q, "enqueue A", () -> q.enqueue("A"));
    test(q, "enqueue B", () -> q.enqueue("B"));
    test(q, "enqueue C", () -> q.enqueue("C"));
    test(q, "enqueue D", () -> q.enqueue("D"));

    test(q, "dequeue", () -> System.out.println("removed: " + q.dequeue()));
    test(q, "dequeue", () -> System.out.println("removed: " + q.dequeue()));

    test(q, "enqueue E", () -> q.enqueue("E"));
    test(q, "enqueue F", () -> q.enqueue("F"));
    test(q, "enqueue G", () -> q.enqueue("G"));

    test(q, "dequeue", () -> System.out.println("removed: " + q.dequeue()));
    test(q, "dequeue", () -> System.out.println("removed: " + q.dequeue()));

    test(q, "enqueue H", () -> q.enqueue("H"));
    test(q, "enqueue I", () -> q.enqueue("I"));

    while (!q.isEmpty()) {
        test(q, "dequeue", () -> System.out.println("removed: " + q.dequeue()));
    }
}

private static void test(ResizingArrayQueueOfStrings q, String op, Runnable r) {
    System.out.println("\n--- " + op + " ---");
    r.run();
    debug(q);
}

private static void debug(ResizingArrayQueueOfStrings q) {
    try {
        java.lang.reflect.Field f = q.getClass().getDeclaredField("queue");
        f.setAccessible(true);
        String[] arr = (String[]) f.get(q);

        java.lang.reflect.Field f1 = q.getClass().getDeclaredField("first");
        java.lang.reflect.Field f2 = q.getClass().getDeclaredField("last");
        f1.setAccessible(true);
        f2.setAccessible(true);

        int first = (int) f1.get(q);
        int last = (int) f2.get(q);

        System.out.print("array: [ ");
        for (String s : arr)
            System.out.print(s + " ");
        System.out.println("]");

        System.out.println("first: " + first);
        System.out.println("last: " + last);
        System.out.println("size: " + q.size());
    } catch (Exception e) {
        e.printStackTrace();
    }
}