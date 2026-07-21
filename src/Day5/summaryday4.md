# Resumão do Dia 4 (revisão rápida)

Só pra relembrar antes de começar o próximo dia.

## O essencial

- **`record`:** classe imutável numa linha. O Java gera de graça o construtor, os acessadores (`p.x()`, com parênteses), o `equals()` (por conteúdo), o `hashCode()` e o `toString()`. Ótimo pra DTO — substitui ~45 linhas de classe tradicional.
- **`enum`:** conjunto fixo de constantes, mas com superpoderes — pode ter campo (`final`) e método. Seguro contra valor inválido (o compilador barra), e compara com `==`.
- **Imutável:** objeto que não muda depois de criado. Pra "mudar", cria um novo. `record` e `String` são imutáveis.

## As pegadinhas

- **`equals` vs `==` no record:** o `equals` gerado compara **conteúdo** (dá `true` pra mesmos valores); o `==` compara **referência** (dá `false` pra objetos distintos). É o Dia 1 de novo.
- **`toString` do record vaza tudo:** ele imprime todos os campos, inclusive senha. Num sistema real, sobrescreva o `toString` pra esconder campo sensível.
- **Campo de enum quase sempre é `final`:** enum é constante, o campo não deveria mudar.

## Regras de bolso

- Use `record` pra DTO / value object (dado imutável, sem comportamento).
- Use `enum` no lugar de strings soltas pra representar um conjunto fixo de opções.
- Prefira a condição que **fala a intenção direta** (`if (!set.add(num)) return true`) a um `if` que só faz `continue`.

## Desafio feito — DTO como record

Reescrevi um DTO como `record` de uma linha. Contei: ~45 linhas de classe tradicional (campos, construtor, getters, equals, hashCode, toString) viraram ~1. E o `equals` por conteúdo veio de graça. ✅

## LeetCode #217 — Contains Duplicate

Resolvido em O(n) com um `HashSet`: pra cada número, `if (!set.add(num)) return true`. O `add()` devolve `false` quando o elemento já existe — então add falhou = duplicado achado. Se o loop acaba sem falha, todos eram únicos → `false`. ✅