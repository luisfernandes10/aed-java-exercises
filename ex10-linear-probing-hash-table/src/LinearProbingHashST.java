import java.util.Iterator;

public class LinearProbingHashST<Key, Value> {
    private Value[] values;
    private Key[] keys;
    private int M;
    private int size;

    public LinearProbingHashST(int M) {
        values = (Value[]) new Object[M];
        keys = (Key[]) new Object[M];
        this.M = M;
        size = 0;
    }

    public void put(Key key, Value val) {
        if (size >= M / 2) resize(M * 2);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) { values[i] = val; return; }
        }
        keys[i] = key; values[i] = val; size++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }

    public void delete(Key key) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) break;
        if (keys[i] == null) return;

        keys[i] = null; values[i] = null; size--;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key k = keys[i]; Value v = values[i];
            keys[i] = null; values[i] = null; size--;
            put(k, v); // Usamos o put para reinserir corretamente!
            i = (i + 1) % M;
        }
    }

    public boolean contains(Key key) {
        return values[hash(key)] != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST(cap);
        for (int i = 0; i < M; i++)
            if (keys[i] != null) t.put(keys[i], values[i]);
        this.keys = t.keys; this.values = t.values; this.M = t.M;
    }

    public static void main(String[] args) {
        // Criamos tabela com tamanho inicial 5
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>(5);

        // 1. Inserir elementos normais
        st.put("A", 10);
        st.put("B", 20);

        // 2. Inserir 'F' vai forçar uma colisão com 'A'
        // (A e F geram o mesmo índice dependendo do tamanho da tabela)
        st.put("F", 30);
        st.put("C", 40);

        // 3. Vamos remover o 'A'.
        // O algoritmo vai ter de reposicionar o 'F' para tapar o buraco!
        st.delete("A");

        // 4. Testar se o 'F' continua encontrável
        Integer valorF = st.get("F");
        System.out.println("Valor do F: " + valorF);
    }
}
