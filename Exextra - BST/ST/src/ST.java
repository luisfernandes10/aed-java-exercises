public class ST<Key extends Comparable<Key>, Value> {
    static final boolean RED   = true;
    static final boolean BLACK = false;
    Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        boolean color;

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.value = val;
            this.color = color;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null)
            return new Node(key, val, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, key, val);
        else if (cmp > 0)
            h.right = put(h.right, key, val);
        else
            h.value = val;

        // Ramo direito vermelho e ramo esquerdo normal
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        // Dois vermelhos seguidos
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        // Duas ramificacoes vermelhas
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        return h;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x.value;
        }
        return null;
    }

    public int height() {
        int count = 0;
        Node x = root;
        while (x != null) {
            if (!isRed(x))
                count++;
            x = x.left;
        }
        return count;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;

        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;

        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void print() {
        print(root, "", true);
    }

    private void print(Node x, String indent, boolean noDireito) {
        if (x == null) return;

        // 1. Percorrer o ramo direito primeiro (topo da consola)
        print(x.right, indent + (noDireito ? "        " : " |      "), true);

        // 2. Imprimir o nó atual com a sua cor
        System.out.print(indent);
        System.out.print(noDireito ? " /--- " : " \\--- ");
        System.out.println(x.key + (isRed(x) ? " ❤️ (RED)" : " 🖤 (BLACK)"));

        // 3. Percorrer o ramo esquerdo (base da consola)
        print(x.left, indent + (noDireito ? " |      " : "        "), false);
    }

    public static void main(String[] args) {
        ST twoThree = new ST();

        twoThree.put('S', 1);
        twoThree.put('E', 1);
        twoThree.put('A', 1);
        twoThree.put('R', 1);
        twoThree.put('C', 1);
        twoThree.put('H', 1);
        twoThree.put('X', 1);
        twoThree.put('M', 1);
        twoThree.put('P', 1);
        twoThree.put('L', 1);

        twoThree.print();
    }
}
