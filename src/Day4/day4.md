# Dia 4 — record, enum e imutabilidade

Quarto dia do meu desafio de 30 dias de Java. Aqui está tudo que eu aprendi, do meu jeito, pra não esquecer.

## `record` — a classe imutável de uma linha

Um `record` (Java 16+) é uma classe imutável escrita numa linha. A partir da declaração, o Java **gera automaticamente**: o construtor, os acessadores, o `equals()`, o `hashCode()` e o `toString()`. Perfeito pra DTOs e value objects.

```java
public record RecordPonto(int x, int y) {}
```

Usando:

```java
RecordPonto r1 = new RecordPonto(3, 4);
r1.x();                          // 3  -> acessador gerado (com parênteses, não getX())
r1.y();                          // 4
System.out.println(r1);          // RecordPonto[x=3, y=4]  -> toString gerado
r1.equals(new RecordPonto(3, 4)); // true -> equals por CONTEÚDO
```

Prova que rodei: dois `RecordPonto(3, 4)` diferentes deram `equals = true` (mesmo conteúdo), mas `==` daria `false` (objetos distintos na memória). Isso é o Dia 1 voltando: o `equals` gerado compara conteúdo, o `==` compara referência — e o record já faz a coisa certa.

A economia é enorme: aquela linha única substitui ~45 linhas de classe tradicional (campos `private final`, construtor, um getter por campo, `equals`, `hashCode`, `toString`). Foi o que medi no desafio.

Detalhe importante que descobri: o `toString()` gerado **imprime todos os campos** — inclusive senha em texto puro, se tiver. Num sistema real isso é vazamento em log. Dá pra sobrescrever o `toString()` do record pra esconder campo sensível (o record gera tudo, mas deixa eu sobrescrever quando precisar).

## `enum` — conjunto fixo de constantes (com campos e métodos)

Um `enum` representa um conjunto **fixo** de valores. No Java ele é poderoso: pode ter campos e métodos, não é só uma lista de nomes.

```java
public enum EnumStatus {
    ATIVO("A"),
    INATIVO("I");

    private final String sigla;      // final: enum é constante, campo não muda

    EnumStatus(String sigla) {
        this.sigla = sigla;
    }

    public String sigla() {
        return sigla;
    }
}
```

Usando:

```java
for (EnumStatus s : EnumStatus.values()) {
    System.out.println(s + " -> sigla " + s.sigla());
}
// ATIVO -> sigla A
// INATIVO -> sigla I

EnumStatus atual = EnumStatus.ATIVO;
atual == EnumStatus.ATIVO;   // true
```

Por que é melhor que string solta: segurança de tipo. Se um método recebe `EnumStatus`, é impossível passar um valor inválido — o compilador barra. Com string, um typo `"atvio"` só quebra em runtime.

Exceção à regra do Dia 1: enum compara com `==` numa boa, porque cada constante é única e existe uma vez só. O campo do enum quase sempre é `final` (é constante, não deveria mudar).

Ponte com o TS: é o union type de strings (`"ativo" | "inativo"`), mas que pode carregar dados e comportamento junto.

## Imutabilidade

**Imutável** = o objeto não muda depois de criado. O `record` é imutável (campos `final`, sem setter). `r1.x = 99` nem compila.

Pra "mudar" um imutável, eu não mudo — crio um novo:

```java
RecordPonto movido = new RecordPonto(r1.x() + 1, r1.y());
// original: RecordPonto[x=3, y=4]  |  novo: RecordPonto[x=4, y=4]
```

O original fica intacto. Vantagens: seguro entre threads (ninguém muda), previsível, e confiável como chave de `HashMap` (chave que muda depois de entrada bagunça o mapa). `String` e records são imutáveis.

## Desafio feito — DTO como record

Reescrevi um DTO como record. Em vez de uma classe com campo, construtor, getters, equals, hashCode e toString (dezenas de linhas), ficou:

```java
public record RecordDesafio(
        String nome,
        String email,
        int idade,
        String sexo,
        String senha,
        String telefone
) {}
```

Economia medida: ~45 linhas de classe tradicional viram ~1 (as quebras de linha são só pra legibilidade). E `equals` por conteúdo já vem de graça.

## LeetCode #217 — Contains Duplicate

**O que pede:** retornar `true` se algum valor do array aparece pelo menos duas vezes, `false` se todos distintos.

**A ideia:** preciso saber rápido "esse número já apareceu?". A estrutura certa pra "já vi ou não" é o **`HashSet`** (conjunto sem duplicados). Sacada: o `set.add()` devolve `false` quando o elemento já existe — então, se o add falhar, achei duplicado.

```java
public static boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
        if (!set.add(num)) {   // add devolveu false -> ja existia -> duplicado
            return true;
        }
    }
    return false;
}
```

**Eficiência:** O(n) no tempo, O(n) de memória. Alternativa sem memória extra: ordenar e comparar vizinhos, O(n log n) — é o trade-off "memória por tempo".

Conexões com os dias anteriores:
- autoboxing do Dia 1: `int` vira `Integer` ao entrar no `Set` (coleção só guarda objetos).
- for-each do Dia 3 e negação `!` do Dia 2, aplicados aqui.
- lição de estilo: prefira a condição que fala a intenção direta (`if (!set.add(num))` lê como frase), em vez de um `if` que só faz `continue`.

## Resumão do dia

- `record`: classe imutável de uma linha; gera construtor, acessadores, equals, hashCode e toString. Ótimo pra DTO.
- `enum`: conjunto fixo de constantes, pode ter campo (`final`) e método; compara com `==`; seguro contra valor inválido.
- Imutável = não muda depois de criado; pra "mudar", cria um novo. Seguro, previsível, boa chave de mapa.
- `HashSet` resolve "já vi este valor?" em O(1); `add()` retorna false se já existia.