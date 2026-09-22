public static void main(String[] args) {
    System.out.println("🔥 INÍCIO DO TESTE ULTRA MÁXIMO 🔥\n");

    List<String> list = new List<>();

    System.out.println("[1] TESTE DE ARRANQUE E LIMITES (Lista Vazia)");
    System.out.println("Está vazia? " + (list.isEmpty() ? "PASSOU" : "FALHOU"));
    System.out.println("Tamanho é 0? " + (list.size() == 0 ? "PASSOU" : "FALHOU"));
    System.out.println("Remover índice 0 devolve null? " + (list.remove(0) == null ? "PASSOU" : "FALHOU"));
    System.out.println("RemoverFirst 'Fantasma' devolve false? " + (!list.removeFirst("Fantasma") ? "PASSOU" : "FALHOU"));
    System.out.println("Contains 'Fantasma' devolve false? " + (!list.contains("Fantasma") ? "PASSOU" : "FALHOU"));

    System.out.println("\n[2] TESTE DE PALÍNDROMO (Simetria)");
    List<String> esquema = new List<>();
    esquema.add("3");
    esquema.add("4");
    esquema.add("3");
    System.out.println("A disposição [3, 4, 3] é palíndromo? " + (esquema.isPalindrome() ? "PASSOU" : "FALHOU"));
    esquema.add("1");
    System.out.println("A disposição [3, 4, 3, 1] NÃO é palíndromo? " + (!esquema.isPalindrome() ? "PASSOU" : "FALHOU"));

    System.out.println("\n[3] TESTE DE INSERÇÃO E GET");
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.add("E");
    System.out.println("Tamanho é 5? " + (list.size() == 5 ? "PASSOU" : "FALHOU"));
    System.out.println("Índice 0 é 'A'? " + (list.get(0).equals("A") ? "PASSOU" : "FALHOU"));
    System.out.println("Índice 4 é 'E'? " + (list.get(4).equals("E") ? "PASSOU" : "FALHOU"));
    System.out.println("Índice 5 (fora dos limites) é null? " + (list.get(5) == null ? "PASSOU" : "FALHOU"));

    System.out.println("\n[4] TESTE DE REMOÇÃO POR ÍNDICE (remove)");
    System.out.println("Remover a meio (índice 2, 'C')? " + (list.remove(2).equals("C") ? "PASSOU" : "FALHOU"));
    System.out.println("Remover o primeiro (índice 0, 'A')? " + (list.remove(0).equals("A") ? "PASSOU" : "FALHOU"));
    System.out.println("Remover o último (índice 2, 'E')? " + (list.remove(2).equals("E") ? "PASSOU" : "FALHOU"));
    System.out.println("A lista ficou apenas com 'B' e 'D'? " + (list.size() == 2 && list.get(0).equals("B") && list.get(1).equals("D") ? "PASSOU" : "FALHOU"));

    System.out.println("\n[5] TESTE CIRÚRGICO (removeFirst e removeLast)");
    list.add("B"); // Fica: B, D, B
    list.add("X"); // Fica: B, D, B, X
    list.add("B"); // Fica: B, D, B, X, B
    System.out.println("Remover primeira ocorrência de 'B'? " + (list.removeFirst("B") ? "PASSOU" : "FALHOU"));
    // Estado esperado: D, B, X, B
    System.out.println("O novo primeiro nó é o 'D'? " + (list.get(0).equals("D") ? "PASSOU" : "FALHOU"));
    System.out.println("Remover última ocorrência de 'B'? " + (list.removeLast("B") ? "PASSOU" : "FALHOU"));
    // Estado esperado: D, B, X
    System.out.println("O último nó passou a ser o 'X'? " + (list.get(2).equals("X") ? "PASSOU" : "FALHOU"));

    System.out.println("\n[6] TESTE DE LIMPEZA GERAL (removeAll)");
    list.add("D");
    list.add("D");
    list.add("Z");
    // Estado atual: D, B, X, D, D, Z
    System.out.println("Remover TODOS os 'D'? " + (list.removeAll("D") ? "PASSOU" : "FALHOU"));
    // Estado esperado: B, X, Z
    System.out.println("O tamanho ajustou para 3? " + (list.size() == 3 ? "PASSOU" : "FALHOU"));
    System.out.println("A lista já não contém 'D'? " + (!list.contains("D") ? "PASSOU" : "FALHOU"));

    System.out.println("\n[7] TESTE DO ITERADOR E RESSURREIÇÃO");
    int passos = 0;
    for (String s : list) {
        passos++;
    }
    System.out.println("O Iterador conseguiu dar 3 passos? " + (passos == 3 ? "PASSOU" : "FALHOU"));

    // Esvaziar a lista por completo
    list.removeFirst("B");
    list.removeFirst("X");
    list.removeFirst("Z");
    System.out.println("Lista totalmente esvaziada. Tamanho é 0? " + (list.size() == 0 ? "PASSOU" : "FALHOU"));

    // Testar se as variáveis first e last foram bem repostas
    list.add("Renasceu");
    System.out.println("Conseguiu adicionar um novo nó numa lista que ficou vazia? " + (list.get(0).equals("Renasceu") ? "PASSOU" : "FALHOU"));

    System.out.println("\n🔥 TESTES CONCLUÍDOS 🔥");
}