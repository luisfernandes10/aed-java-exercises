public static void main(String[] args) {
    System.out.println("--- Iniciar Testes da Queue ---\n");
    var fila = new Queue<String>();

    // 1. Testar Enqueue e Size
    fila.enqueue("Primeiro");
    fila.enqueue("Segundo");
    fila.enqueue("Terceiro");
    fila.enqueue("Quarto");
    System.out.println("Tamanho após adicionar 4 elementos: " + fila.size());

    // 2. Testar o Iterator (Ciclo for-each)
    System.out.println("\nEstado atual da fila (usando Iterable):");
    for (String item : fila) {
        System.out.println(" -> " + item);
    }

    // 3. Testar o Shift (Mover o último para o início)
    System.out.println("\nA aplicar shift()...");
    fila.shift();

    System.out.println("Estado após shift (esperado: Quarto -> Primeiro -> Segundo -> Terceiro):");
    for (String item : fila) {
        System.out.println(" -> " + item);
    }

    // 4. Testar o Dequeue
    System.out.println("\nA remover o primeiro elemento (dequeue): " + fila.dequeue());
    System.out.println("Tamanho atual: " + fila.size());

    // 5. Esvaziar a fila para testar o Underflow
    fila.dequeue();
    fila.dequeue();
    fila.dequeue();

    System.out.println("\nA fila está vazia? " + fila.isEmpty());

    try {
        fila.dequeue();
    } catch (IllegalStateException e) {
        System.out.println("Erro apanhado com sucesso: " + e.getMessage());
    }
}