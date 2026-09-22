public class Main {
    public static void main(String[] args) {
        int n = 10;
        var uf = new WeightedQUPathCompressionUF(n);

        // Cenário: Criar dois grupos distintos
        // Grupo 1: {1, 2, 5, 6}
        uf.union(1, 2);
        uf.union(2, 5);
        uf.union(5, 6);

        // Grupo 2: {3, 8, 9}
        uf.union(3, 8);
        uf.union(8, 9);

        // Verificações
        System.out.println("1 e 6 ligados? " + uf.connected(1, 6)); // True
        System.out.println("3 e 9 ligados? " + uf.connected(3, 9)); // True
        System.out.println("1 e 9 ligados? " + uf.connected(1, 9)); // False

        // Testar a raiz (que é o teu 'find')
        System.out.println("Raiz de 6: " + uf.root(6));
        System.out.println("Raiz de 9: " + uf.root(9));

        // Unir os dois grupos
        uf.union(6, 9);
        System.out.println("Agora 1 e 9 estão ligados? " + uf.connected(1, 9)); // True

        // Verificação final: todos devem partilhar a mesma raiz
        System.out.println("Raiz de 1 e 9 é a mesma? " + (uf.root(1) == uf.root(9)));
    }
}