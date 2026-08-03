# Dia 7 — Revisão: o padrão dois ponteiros

Sétimo dia do meu desafio de 30 dias de Java. Dia de consolidar a Semana 1, não de conteúdo novo.

## O padrão dois ponteiros — o fio da semana

Os dois ponteiros apareceram várias vezes esta semana, em roupagens diferentes:
- **Palíndromo (Dia 2):** um ponteiro em cada ponta, andando pro centro, comparando.
- **existePar / Two Sum II (Dia 6):** um em cada ponta de um array ordenado, ajustando conforme a soma.

A ideia central: **dois índices que se movem por um array pra reduzir uma solução O(n²) para O(n).** Em vez de testar todos os pares (loop duplo), uso a ordenação pra saber pra que lado andar e descartar possibilidades sem testá-las.

Quando serve: achar pares/triplas com uma soma dada, verificar palíndromos, remover duplicados de array ordenado, janela deslizante (Semana 2).

Intuição-chave: **em array ordenado, comparar as pontas diz pra que lado andar.** Soma grande demais → puxa a direita (o maior) pra dentro. Pequena demais → empurra a esquerda. Essa "bússola" substitui a memória do HashMap.

## Big-O das soluções da semana

| Dia | Problema | Técnica | Tempo | Memória |
|---|---|---|---|---|
| 1 | Two Sum | HashMap | O(n) | O(n) |
| 2 | Valid Palindrome | dois ponteiros | O(n) | O(1) |
| 3 | Best Time Buy/Sell Stock | uma passada | O(n) | O(1) |
| 4 | Contains Duplicate | HashSet | O(n) | O(n) |
| 5 | Valid Anagram | contagem `int[26]` | O(n) | O(1) |
| 6 | Two Sum II | dois ponteiros | O(n) | O(1) |

Padrão que noto: memória O(1) sempre foi dois ponteiros ou contagem; memória O(n) sempre foi hash (map/set). É o trade-off "memória por tempo".

## Desafio feito — refazer de memória

Refiz de memória o Valid Palindrome e o Two Sum II (os dois que mais me custaram). Diagnóstico: lembrei toda a **mecânica** dos dois ponteiros (a parte conceitual), mas esqueci o detalhe do **base-1** no Two Sum II. Ou seja, o conceito grudou; o que falta é detalhe de enunciado. Recall ativo expõe exatamente isso.

## LeetCode #15 — 3Sum

**O que pede:** encontrar todas as triplas **únicas** que somam **zero**. Retornar a lista.

**A sacada:** 3Sum é o Two Sum II com um "chapéu" por cima. Ordeno o array, fixo um número `nums[i]`, e procuro no resto um **par** que some `-nums[i]` (dois ponteiros). O loop do `i` é o chapéu; o miolo é o Two Sum II que eu já sabia.

```java
public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> resultado = new ArrayList<>();
    Arrays.sort(nums);                                  // 1. ordena

    for (int i = 0; i < nums.length - 2; i++) {         // 2. fixa um numero
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue;                                   // 3a. pula i repetido
        }
        int esquerda = i + 1;
        int direita = nums.length - 1;
        while (esquerda < direita) {                    // 4. dois ponteiros
            int soma = nums[i] + nums[esquerda] + nums[direita];
            if (soma == 0) {
                resultado.add(Arrays.asList(nums[i], nums[esquerda], nums[direita]));
                // 3b. pula duplicados apos achar
                while (esquerda < direita && nums[esquerda] == nums[esquerda + 1]) esquerda++;
                while (esquerda < direita && nums[direita] == nums[direita - 1]) direita--;
                esquerda++;
                direita--;
            } else if (soma < 0) {
                esquerda++;                             // soma pequena -> aumenta
            } else {
                direita--;                              // soma grande -> diminui
            }
        }
    }
    return resultado;
}
```

**Eficiência:** O(n²) no tempo (loop do `i` × dois ponteiros); a ordenação O(n log n) some diante disso.

O que trava todo mundo (e travou eu): **pular duplicados**, em dois lugares.
- **3a:** no loop externo, se `nums[i] == nums[i-1]` (com `i > 0`), pula — senão registra triplas repetidas começando com o mesmo número.
- **3b:** depois de achar uma tripla, avança os ponteiros por cima dos valores repetidos antes de continuar.

Dica de como construir: faço primeiro a versão **sem** os duplicados (só estrutura + dois ponteiros), rodo, vejo a tripla repetida aparecer, e **depois** adiciono os pula-duplicados. Em duas etapas é bem mais fácil que tudo de uma vez.

## Resumão do dia

- Dois ponteiros: dois índices movendo por um array (geralmente ordenado) pra virar O(n²) em O(n). Em array ordenado, a soma diz pra que lado andar.
- Sei explicar o Big-O de cada problema da semana em voz alta (essencial em entrevista).
- Recall ativo (refazer de memória) expõe o que grudou vs o que só copiei.
- 3Sum = Two Sum II com um loop externo fixando um número + tratamento de duplicados em 2 lugares. O(n²).