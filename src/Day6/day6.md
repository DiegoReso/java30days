# Dia 6 — Arrays e a classe Arrays

Sexto dia do meu desafio de 30 dias de Java. Aqui está tudo que eu aprendi, do meu jeito, pra não esquecer.

## O array: sequência de tamanho fixo

O array é a estrutura mais básica: uma sequência de tamanho **fixo**, indexada a partir do **0**. Dois jeitos de declarar:

```java
int[] a = new int[5];      // 5 posições, tudo começa em 0
int[] b = {10, 20, 30};    // literal, já com os valores
```

Conexão com o Dia 1: `new int[5]` nasce `[0, 0, 0, 0, 0]` (se fosse `Integer[]`, começaria com `null`).

O "tamanho fixo" é a característica central: criado com 5 posições, tem 5 pra sempre. Não cresce. Quando preciso de algo que cresce sozinho, uso `ArrayList` (Dia 8).

## A pegadinha: `length` é CAMPO, não método

```java
b.length          // ARRAY: campo, SEM parênteses
"texto".length()  // STRING: método, COM parênteses
```

Mesma ideia (o tamanho), escrita diferente. No array é campo (`.length`), na String é método (`.length()`). Errar não compila. Grava: **array = campo, String = método.**

## Array 2D (matriz)

Um array de arrays. `new int[2][3]` é uma tabela de 2 linhas por 3 colunas.

```java
int[][] matriz = new int[2][3];
matriz[0][1] = 7;                              // linha 0, coluna 1
System.out.println(Arrays.deepToString(matriz)); // [[0, 7, 0], [0, 0, 0]]
```

Pra imprimir 2D uso `Arrays.deepToString()` (o `toString` normal mostra só os endereços dos arrays internos).

## A classe utilitária java.util.Arrays

Ferramentas prontas (precisa do `import java.util.Arrays`):

- `Arrays.sort(a)` — ordena **no lugar** (modifica o próprio array).
- `Arrays.toString(a)` — vira texto legível pra imprimir (`[1, 2, 5]`). Sem ele, imprimir array mostra lixo tipo `[I@1b6d3586`.
- `Arrays.copyOf(a, novoTam)` — cópia com tamanho diferente (preenche com 0 se maior).
- `Arrays.fill(a, valor)` — preenche tudo com o mesmo valor.
- `Arrays.binarySearch(a, x)` — busca binária, devolve o índice. **Só funciona em array ordenado!**

Ponte com o JS: array do JS é dinâmico (cresce com `push`); em Java é fixo (use `ArrayList` pra crescer). E a pegadinha do `length`: campo no array, método na String.

## Desafio + reflexão — dois ponteiros do zero

Escrevi dois ponteiros do zero (um `existePar` que diz se há um par somando o alvo num array ordenado):

```java
static boolean existePar(int[] nums, int alvo) {
    int esquerda = 0;
    int direita = nums.length - 1;
    while (esquerda < direita) {
        int soma = nums[esquerda] + nums[direita];
        if (soma == alvo) {
            return true;
        }
        if (soma > alvo) {
            direita--;          // grande demais -> diminui puxando a direita
        } else {
            esquerda++;         // pequena demais -> aumenta empurrando a esquerda
        }
    }
    return false;               // FORA do while: só conclui "não achou" no fim
}
```

Coisas que aprendi apanhando um pouco:
- o `return false` do "não achou" fica **fora** do `while` — se ficar dentro, o loop morre na primeira volta.
- calcular a `soma` uma vez numa variável evita bug: depois de `direita--` a soma muda, então recalcular no meio testava valores diferentes.
- `if/else` (não três `if` soltos) porque os casos são mutuamente exclusivos.

**A reflexão (por que dispensa o HashMap do Two Sum original):** num array **ordenado**, a ordem me diz pra que lado andar — soma grande demais, puxo a direita; pequena demais, empurro a esquerda. Essa "bússola" substitui a memória do HashMap. No Two Sum I o array vinha bagunçado, então eu não sabia pra que lado ir e precisava do mapa pra lembrar o que já tinha visto.

## LeetCode #167 — Two Sum II (Input Array Is Sorted)

**O que pede:** igual ao Two Sum, mas o array **já vem ordenado** e devo retornar os índices em **base-1**, com memória **O(1)** (sem HashMap).

```java
public int[] twoSum(int[] numbers, int target) {
    int esquerda = 0;
    int direita = numbers.length - 1;
    while (esquerda < direita) {
        int soma = numbers[esquerda] + numbers[direita];
        if (soma == target) {
            return new int[]{esquerda + 1, direita + 1};   // base-1
        }
        if (soma > target) {
            direita--;
        } else {
            esquerda++;
        }
    }
    return new int[]{};
}
```

**Eficiência:** O(n) no tempo, O(1) de memória.

Duas armadilhas em que tropecei (e a lição de cada uma):
- **NÃO ordenar dentro do método.** O array já vem ordenado. Se eu chamar `Arrays.sort`, os índices que devolvo passam a ser do array reordenado, não das posições originais — resposta errada.
- **índices base-1:** somar 1 em cada (`esquerda + 1`, `direita + 1`).

Two Sum I vs II lado a lado:
- **I** (Dia 1): array bagunçado → HashMap → O(n) tempo, O(n) memória (o mapa guarda os índices originais).
- **II** (hoje): array ordenado → dois ponteiros → O(n) tempo, O(1) memória (a ordenação dá a bússola de graça).

## Resumão do dia

- Array = tamanho fixo, indexado do 0, `int[]` começa com zeros. Pra crescer, use ArrayList.
- `length` é CAMPO no array (sem parênteses); na String é MÉTODO (com parênteses).
- Classe `Arrays`: sort, toString, copyOf, fill, binarySearch (essa só em array ordenado).
- Dois ponteiros: em array ordenado, a soma diz pra que lado andar → O(n)/O(1). Substitui o HashMap quando o array está ordenado.