# Dia 1 — Tipos, memória e o modelo mental

Primeiro dia do meu desafio de 30 dias de Java. Aqui está tudo que eu aprendi, do meu jeito, pra não esquecer.

## A ideia principal: Java tem dois mundos

Em Java existem duas categorias de tipo, e elas se comportam de formas bem diferentes:

- **Primitivos** (8 no total): `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`. São valores puros. Não são objetos.
- **Objetos**: todo o resto (`String`, `Integer`, arrays, minhas próprias classes...).

A diferença chave é **o que a variável guarda**:

```java
int a = 5;        // 'a' guarda o valor 5, direto
Integer b = 5;    // 'b' guarda uma referência (endereço) pra um objeto
```

## Stack vs Heap

- **Stack**: onde ficam as variáveis locais e os valores primitivos. É rápida e some sozinha quando o método termina.
- **Heap**: onde ficam os objetos de verdade (tudo que é `new`, `String`, arrays...). É limpa pelo Garbage Collector quando ninguém mais usa o objeto.

Na stack fica a *referência*, e ela aponta pro objeto que vive na heap.

Analogia que me ajudou: a **stack é a minha mesa** (pequena, tudo à mão, limpo no fim do dia) e a **heap é o armário** (grande, guarda as coisas de verdade, e o GC passa jogando fora o que ninguém usa).

```java
int x = 5;              // 5 vive na STACK
Integer y = 5;          // referência na stack -> objeto na HEAP
int[] arr = {1, 2, 3};  // referência na stack -> array na HEAP
```

Regra de bolso: **primitivo local -> stack. Objeto -> heap** (com a referência na stack).

## Autoboxing (o `new` escondido)

Quando eu uso um wrapper, o `new` acontece por baixo dos panos. Não preciso escrever.

```java
Integer y = 127;
// o compilador traduz pra:
Integer y = Integer.valueOf(127);
```

Ou seja: sempre que eu tipo com wrapper (`Integer`, `Long`, `Double`, `Boolean`...), estou lidando com objeto -> ele vive na heap, com a referência na stack.

Cada boxing cria (ou reusa) um objeto. Num loop grande isso pesa:

```java
Long soma = 0L;   // ARMADILHA: usar wrapper num acumulador cria milhares de objetos
long soma = 0L;   // CERTO: primitivo, zero objetos
```

## `==` vs `.equals()`

- **`==`** com objetos compara **referências** (é o mesmo objeto na memória?).
- **`.equals()`** compara **conteúdo** (tem o mesmo valor?).

Regra: **objeto sempre com `.equals()`**.

Primitivo só tem `==` — e tá tudo bem, porque com primitivo o `==` já compara valor. Não dá pra usar `.equals()` em primitivo (nem compila: "int cannot be dereferenced").

## O cache do Integer (a pegadinha de entrevista)

A JVM mantém em cache os `Integer` de **-128 a 127**. Dentro dessa faixa, `==` funciona por acidente (mesmo objeto cacheado). Fora dela, quebra.

```java
Integer x = 127, y = 127;
System.out.println(x == y);   // true  (mesmo objeto do cache)

Integer p = 128, q = 128;
System.out.println(p == q);   // false (objetos diferentes)
```

Por que -128 a 127? É a faixa de um byte com sinal, e cobre a maioria dos usos do dia a dia com custo mínimo (256 objetos criados uma vez só). O limite de cima dá pra mudar com a flag `-XX:AutoBoxCacheMax=N`.

O cache não é o problema — o problema é usar `==` em objeto, que sempre foi errado. O cache só torna o bug intermitente (funciona no número pequeno, quebra no grande).

### Os três casos do `==`

| Comparação | O que o `==` faz | Cache importa? |
|---|---|---|
| `int` vs `int` | valor | não |
| `Integer` vs `int` | desembrulha -> valor | não |
| `Integer` vs `Integer` | referência | **sim** |

Detalhe importante: se um lado é primitivo, o Java desembrulha o outro e compara valor. Só quando os dois lados são objetos é que compara referência.

## O perigo do `null` no wrapper

`int` nunca é `null`. `Integer` pode ser. E isso abre uma porta pra NullPointerException:

```java
Integer b = null;   // OK
int c = b;          // COMPILA, mas estoura em runtime (NPE)
```

Isso compila porque os tipos batem — o erro é de *valor*, não de tipo, e o compilador não vê. Em runtime, o auto-unboxing chama `b.intValue()` num `null` -> NPE.

Onde o `null` costuma aparecer sorrateiro:
- `Map.get(chave)` quando a chave não existe (devolve `null`)
- aritmética com wrapper `null`
- ternário misturando `Integer` e `int`

Como me proteger: usar `int` por padrão. Quando precisar de wrapper, tratar o `null` antes de desembrulhar (`if (x != null)` ou `getOrDefault`).

## `int[]` vs `Integer[]`

| | `int[]` | `Integer[]` |
|---|---|---|
| Valor inicial | `0` | `null` |
| O que guarda | valores | referências pra objetos na heap |
| Boxing | nenhum | a cada escrita |
| Risco de `null` | impossível | em cada elemento |
| Performance/memória | rápido, leve | mais lento, mais pesado |

Regra de bolso: **usar `int[]` por padrão**. Só usar `Integer[]` quando precisar mesmo de `null` ou quando for obrigado por coleções (`List<Integer>`, `Map<..., Integer>` não aceitam primitivo).

## Métodos de Map que mais usei

| Método | O que faz |
|---|---|
| `get(chave)` | devolve o valor da chave (ou `null` se não existir) |
| `containsKey(chave)` | true/false se a chave existe |
| `put(chave, valor)` | guarda o par |
| `getOrDefault(chave, padrao)` | igual ao get, mas devolve `padrao` em vez de `null` |

## LeetCode #1 — Two Sum

**O problema pede:** dado um array `nums` e um `target`, achar os dois números que somados dão o `target` e retornar os **índices** deles.

**A sacada:** em vez de procurar o par (dois loops = O(n²)), pra cada número eu pergunto "o complemento (`target - num`) já apareceu antes?". Uso um `HashMap` que responde isso na hora, então resolvo em **uma passada só** (O(n)).

**Ordem que importa:** perguntar pelo complemento ANTES de guardar o número atual. Se guardar antes, corro o risco de usar o mesmo elemento duas vezes.

```java
public static int[] twoSum(int[] num, int target) {
    Map<Integer, Integer> maper = new HashMap<>();
    for (int i = 0; i < num.length; i++) {
        if (maper.containsKey(target - num[i])) {
            return new int[]{ maper.get(target - num[i]), i };
        }
        maper.put(num[i], i);
    }
    return new int[]{};
}
```

Conexões com o Dia 1 que apareceram nesse código:
- o `Map<Integer, Integer>` só aceita objetos -> autoboxing silencioso no `put`
- `maper.get(...)` devolve `Integer`, e ao colocar no `int[]` rola auto-unboxing
- é seguro porque o `containsKey` garante que a chave existe (sem `null` -> sem NPE)
- retorno é `int[]` (índice nunca é `null`, então primitivo é a escolha certa)

## Resumão do dia

- Primitivo guarda valor na stack; objeto (incluindo wrapper) é referência na stack -> objeto na heap.
- Autoboxing é o `new` implícito; cada boxing tem custo.
- Objeto se compara com `.equals()`. `==` em objeto compara endereço.
- O cache do Integer (-128 a 127) faz o `==` funcionar "por acidente" em números pequenos.
- `null` em wrapper -> NPE no auto-unboxing. Usar `int` por padrão.
- `int[]` por padrão; `Integer[]` só quando precisar de `null` ou coleção.