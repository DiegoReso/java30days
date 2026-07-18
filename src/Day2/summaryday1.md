# Resumão do Dia 1 (revisão rápida)

Só pra relembrar antes de começar o próximo dia.

## O essencial

- **Dois mundos:** primitivo guarda o valor direto; objeto (incluindo wrapper) guarda uma referência.
- **Stack** = variáveis locais e primitivos (rápida, some no fim do método).
  **Heap** = os objetos de verdade (limpa pelo Garbage Collector).
  A referência mora na stack e aponta pro objeto na heap.
- **Autoboxing** = o `new` escondido. `Integer y = 127` vira `Integer.valueOf(127)`. Tem custo.

## As pegadinhas

- **`==` vs `.equals()`:** objeto sempre com `.equals()`. `==` em objeto compara endereço, não valor.
- **Cache do Integer (-128 a 127):** dentro dessa faixa o `==` dá `true` por acidente; fora dela dá `false`.
- **`null` em wrapper -> NPE:** `int` nunca é null, mas `Integer` pode ser. Desembrulhar um `null` estoura em runtime (compila normal!).

## Os três casos do `==`

| Comparação | O que faz | Cache importa? |
|---|---|---|
| `int` vs `int` | valor | não |
| `Integer` vs `int` | desembrulha -> valor | não |
| `Integer` vs `Integer` | referência | sim |

## Regras de bolso

- Usar **`int`** por padrão; só usar `Integer` quando precisar de `null` ou de coleção (`List`/`Map` não aceitam primitivo).
- Usar **`int[]`** por padrão; `Integer[]` começa com `null` e é mais pesado.
- Objeto -> `.equals()`. Sempre.

## LeetCode #1 — Two Sum

Resolvido em O(n): pra cada número, pergunto ao `HashMap` se o complemento (`target - num`) já apareceu. Perguntar ANTES de guardar o número atual. ✅