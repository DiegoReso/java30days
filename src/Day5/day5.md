# Dia 5 — String, StringBuilder e char[]

Quinto dia do meu desafio de 30 dias de Java. Aqui está tudo que eu aprendi, do meu jeito, pra não esquecer.

## String é imutável

A ideia-chave do dia: uma `String` **nunca muda** depois de criada. Toda "modificação" cria uma String **nova**.

```java
String s = "java";
String antes = s;
s = s + " 21";     // NÃO alterou "java" — criou "java 21" e apontou s pra ela
// antes continua "java"  (o original ficou intacto)
```

Isso conecta com o Dia 4: `String` é imutável, igual aos records.

## Por que `+` em loop é caro

Como cada `+` cria uma String nova (copiando tudo que já existia), concatenar num loop de N voltas cria N strings intermediárias — o custo cresce perto de **O(n²)**.

```java
// RUIM em loop grande:
String s = "";
for (int i = 0; i < 100_000; i++) s += i;   // cria uma String nova a cada volta
```

## StringBuilder — o mutável

A solução: `StringBuilder` é mutável. Tem um buffer que cresce com `append()`, e só vira String no fim com `toString()`. Custo total O(n).

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 100_000; i++) sb.append(i);
String resultado = sb.toString();
```

O `append` devolve o próprio objeto, então dá pra encadear: `sb.append("a").append(1)`.

Regra: concatenação pontual (2-3 strings) → `+` está ok. Concatenação em loop → sempre `StringBuilder`.

## Benchmark do desafio (medido por mim!)

Comparei 100.000 concatenações com `+=` vs `StringBuilder`, cronometrando com `System.nanoTime()`:

```java
int N = 100_000;

String string = "";
long inicio = System.nanoTime();
for (int i = 0; i < N; i++) string += i;
long fim = System.nanoTime();
System.out.println(( fim - inicio) / 1_000_000 + " ms");   // nanos -> ms

StringBuilder sb = new StringBuilder();
long inicio2 = System.nanoTime();
for (int i = 0; i < N; i++) sb.append(i);
long fim2 = System.nanoTime();
System.out.println((fim2 - inicio2) / 1_000_000 + " ms");
```

Resultado na minha máquina: **1467 ms** com `+=` contra **1 ms** com `StringBuilder` — cerca de **1400× mais rápido**. Não li isso num livro, eu medi.

Coisas que aprendi cronometrando:
- os dois loops têm que rodar o MESMO número de voltas pra comparação ser justa.
- pra converter nanossegundos em milissegundos, divido por `1_000_000` (um milhão), não `100_000` (esse é o número de voltas — coisa diferente).
- a JVM tem um "aquecimento" (JIT): a primeira coisa medida costuma sair inflada. Pro objetivo de hoje (ver a diferença brutal) o método simples já serve.

## Métodos úteis de String

- `length()` — o tamanho. É **método, com parênteses**! (no array vai ser `length` sem parênteses — pegadinha do Dia 6).
- `charAt(i)` — o caractere na posição `i`.
- `substring(ini, fim)` — um pedaço.
- `indexOf(x)` — posição de `x`, ou `-1` se não achar.
- `toCharArray()` — vira `char[]`.
- `toUpperCase()` / `toLowerCase()`.

## char é um número

O `char` é um primitivo de 16 bits, e por baixo é o código Unicode. Dá pra fazer conta:

```java
char c = 'A';
int codigo = c;               // 65
char proximo = (char)(c + 1); // 'B'
```

O truque de ouro pra problemas de string: as letras minúsculas são consecutivas na tabela (`'a'`=97, `'b'`=98, ... `'z'`=122). Então `c - 'a'` devolve "a quantos passos a letra está do 'a'": `'a'-'a'`=0, `'b'-'a'`=1, ..., `'z'-'a'`=25. É uma mudança de referencial que transforma uma letra num índice de 0 a 25 — perfeito pra indexar um `int[26]`.

Ponte com o JS: não existe template literal (`${x}`). Pra texto dinâmico uso `String.format("Oi %s, %d anos", nome, idade)`, `+` em casos simples, ou `StringBuilder` em loops.

## LeetCode #242 — Valid Anagram

**O que pede:** verificar se duas strings são anagramas (mesmas letras, mesma quantidade, ordem diferente).

**A ideia:** contar a frequência de cada letra num `int[26]`, usando `c - 'a'` como índice. Incremento na primeira palavra, decremento na segunda. Se tudo zerar no fim, é anagrama.

```java
public static boolean isAnagram(String string, String string2) {
    if (string.length() != string2.length()) {
        return false;
    }
    int[] c = new int[26];
    for (int i = 0; i < string.length(); i++) {
        c[string.charAt(i) - 'a']++;    // conta a 1a palavra
    }
    for (int i = 0; i < string2.length(); i++) {
        c[string2.charAt(i) - 'a']--;   // desconta a 2a
    }
    for (int contagem : c) {
        if (contagem != 0) {
            return false;               // sobrou letra descompensada
        }
    }
    return true;
}
```

**Eficiência:** O(n) no tempo, O(1) de memória (o array de 26 é fixo, não cresce com a entrada).

Conexões: usei o truque `c - 'a'` do próprio Dia 5, e `int[26]` (primitivo, não `Integer[26]` — a escolha certa do Dia 1, contador nunca é null). Passou até no caso traiçoeiro `"aacc"`/`"ccac"` (mesmas letras, quantidades diferentes → false).

Existe uma versão mais esperta que retorna cedo: durante o `--`, se um contador ficar negativo, já dá pra retornar `false` sem o loop final (funciona porque os tamanhos iguais garantem que, sem negativos, tudo fecha em zero). As duas são O(n).

Boa prática: colocar a verificação de saída rápida (tamanhos diferentes) como primeira linha, antes de alocar o array.

## Resumão do dia

- String é imutável; toda "mudança" cria uma nova. Por isso `+` em loop é O(n²) e caro.
- StringBuilder é mutável (append + toString no fim) → O(n). Medi: ~1400× mais rápido que `+=`.
- `length()` é método na String (com parênteses); vai ser campo no array.
- `char` é número: `c - 'a'` vira índice de 0 a 25, ótimo pra contar letras num `int[26]`.
- Contagem de frequência resolve anagrama em O(n)/O(1).