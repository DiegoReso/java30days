# Dia 8 — List, ArrayList e LinkedList

Oitavo dia do meu desafio de 30 dias de Java. Começo da Semana 2: o framework de Collections. Aqui está tudo que aprendi, do meu jeito, pra não esquecer.

## A interface List<T>

O framework de coleções é o equivalente maduro e tipado dos arrays/objetos do JS. Tudo começa pela `List<T>`: uma **sequência ordenada, que aceita duplicados e permite acesso por índice**.

```java
List<String> nomes = new ArrayList<>();
nomes.add("Ana");           // adiciona no fim
nomes.add("Bru");
nomes.get(0);               // "Ana" - acesso por indice
nomes.set(1, "Bia");        // troca a posicao 1
nomes.remove("Ana");        // remove por valor
nomes.size();               // tamanho
for (String n : nomes) System.out.println(n);  // for-each
```

Características:
- **ordenada** — mantém a ordem de inserção.
- **aceita duplicados** — diferente do Set. `[10, 10, 5]` mantém os dois 10.
- **acesso por índice** — `get`, `set`, como num array.

Conexão com o Dia 6: a `List` resolve a limitação do array (tamanho fixo). Ela **cresce sozinha** — é o "array dinâmico" do JS, agora tipado.

## As duas implementações

`List` é só a interface. Duas implementações principais, com forças opostas:

**ArrayList** — um array que cresce sozinho por baixo.
- `get(i)` por índice: **O(1)** (vai direto na posição).
- adicionar no fim: **O(1) amortizado**.
- inserir/remover no meio: **O(n)** (empurra os seguintes).

**LinkedList** — lista duplamente ligada (cada nó aponta pro anterior e próximo).
- inserir/remover nas pontas: **O(1)** (só religa ponteiros).
- `get(i)` por índice: **O(n)** (percorre desde o início).

Forças opostas: ArrayList é rápido pra acessar por índice, lento pra inserir no meio; LinkedList é rápido pra inserir nas pontas, lento pra acessar por índice.

## Programe contra a interface

```java
List<String> nomes = new ArrayList<>();
//   ^ interface        ^ implementação
```

A variável é `List` (a interface), só o `new` usa a implementação concreta. Assim o código depende do contrato, não da implementação — trocar pra `LinkedList` muda só o `new`. É o desacoplamento do Dia 3.

Regra de bolso: **em 90% dos casos, ArrayList é a escolha certa.** Só use LinkedList se faz muitas inserções/remoções nas pontas. Na dúvida, ArrayList.

Ponte com o JS: ArrayList é o array do JS (cresce sozinho, índice rápido), só que tipado.

## Desafio feito — benchmark ArrayList vs LinkedList

Medi o custo de inserir no meio (`lista.add(size/2, valor)`) nas duas, com 100k elementos e 10k inserções:

```java
long inicio = System.nanoTime();
for (int i = 0; i < INSERCOES; i++) {
    arrayList.add(arrayList.size() / 2, i);
}
long fim = System.nanoTime();
System.out.println((fim - inicio) / 1_000_000 + " ms");
// mesma coisa pro linkedList
```

Resultado: **ArrayList ~71 ms** vs **LinkedList ~1377 ms**. Surpresa!

A explicação (e é o ponto do desafio): os dois são O(n) pra inserir no meio, mas por motivos diferentes. O ArrayList gasta o O(n) **empurrando elementos**, algo que a CPU faz voando porque os dados estão lado a lado na memória. O LinkedList gasta o O(n) **percorrendo nó por nó**, e cada nó está espalhado na memória, então a CPU perde tempo "pulando". Moral: LinkedList quase nunca vale a pena na prática. Por isso "na dúvida, ArrayList".

## LeetCode #49 — Group Anagrams

**O que pede:** agrupar as strings que são anagramas entre si. Retornar a lista de grupos.

**A sacada:** achar uma "chave" que seja igual pra anagramas. Se eu **ordeno as letras**, `"eat"`, `"tea"`, `"ate"` viram todas `"aet"` — essa é a chave. Agrupo num `HashMap<String, List<String>>`.

```java
public static List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> mapa = new HashMap<>();
    for (String palavra : strs) {
        char[] letras = palavra.toCharArray();   // 1. quebra em char[]  (Dia 5)
        Arrays.sort(letras);                      // 2. ordena as letras  (Dia 6)
        String chave = new String(letras);        // 3. vira a chave do anagrama
        mapa.computeIfAbsent(chave, k -> new ArrayList<>()).add(palavra);  // 4. agrupa
    }
    return new ArrayList<>(mapa.values());        // 5. os valores sao os grupos
}
```

**Eficiência:** O(n · k log k), onde n = número de palavras e k = tamanho médio (o k log k é a ordenação de cada palavra).

A peça nova é o **`computeIfAbsent(chave, k -> new ArrayList<>())`**: "se a chave não existe, cria uma lista vazia; me devolve a lista de qualquer forma" — aí eu `.add()` a palavra nela. Sem ele, eu precisaria de um `if (containsKey)` toda vez pra decidir se crio a lista. É o padrão de agrupamento do Java, e volta MUITO.

Conexões: reusa `toCharArray()` (Dia 5), `Arrays.sort()` (Dia 6) e HashMap (Dia 1).

## Resumão do dia

- `List<T>`: sequência ordenada, aceita duplicados, acesso por índice. Cresce sozinha (array dinâmico tipado).
- ArrayList: índice O(1), insere no meio O(n). LinkedList: pontas O(1), índice O(n).
- Programe contra a interface (`List x = new ArrayList<>()`). Na dúvida, ArrayList (na prática ganha até onde a teoria diz empate).
- `computeIfAbsent(chave, k -> new ArrayList<>()).add(x)` é o padrão pra agrupar num Map.