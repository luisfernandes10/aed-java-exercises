public class ST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        public Key key;
        public Value value;
        public Node left;
        public Node right;
        private int size;

        private Node(Key key, Value val) {
            this.key = key;
            this.value = val;
            this.size = 1;
        }
    }

    public ST() {
        root = null;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.value = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.value;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            if (x.right == null)
                return x.left;
            if (x.left == null)
                return x.right;
            Node tmp = x;
            x = min(tmp.right);
            x.right = deleteMin(tmp.right);
            x.left = tmp.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null)
            return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null)
            return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        else
            return x.key;
    }

    private Node floor(Node x, Key k) {
        if (x == null)
            return null;
        int cmp = k.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, k);
        Node t = floor(x.right, k);
        if (t != null)
            return t;
        else
            return x;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);

        if (x == null)
            return null;
        else
            return x.key;
    }

    private Node ceiling(Node x, Key k) {
        if (x == null)
            return null;
        int cmp = k.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp > 0)
            return ceiling(x.right, k);
        Node t = ceiling(x.left, k);
        if (t != null)
            return t;
        else
            return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }

    public Key select(int k) {
        Node x = select(root, k);

        if (x == null)
            return null;

        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null)
            return null;

        int t = size(x.left);
        if (k < t)
            return select(x.left, k);
        else if (k > t)
            return select(x.right, k - t - 1);
        else
            return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
}
