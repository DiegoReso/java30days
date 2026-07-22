# Resumão do Dia 5 (revisão rápida)

Só pra relembrar antes de começar o próximo dia.

## O essencial

- **String é imutável:** nunca muda depois de criada. Toda "modificação" cria uma String nova.
- **`+` em loop é caro:** cada `+` cria uma String nova copiando tudo → O(n²). Em loop, sempre use `StringBuilder`.
- **`StringBuilder` é mutável:** cresce com `append()`, vira String no fim com `toString()` → O(n). Mede: ~1400× mais rápido que `+=` em 100k voltas.

## As pegadinhas

- **`length()` na String é MÉTODO** (com parênteses); no array vai ser CAMPO (`.length`, sem parênteses).
- **`char` é número:** `'a'`=97, `'b'`=98... Dá pra fazer conta. `(char)('A'+1)` = `'B'`.
- **`c - 'a'`** transforma uma letra em índice de 0 a 25 (0 pra 'a', 25 pra 'z') — ótimo pra contar letras num `int[26]`.

## Regras de bolso

- Concatenação pontual (2-3 strings) → `+` ok. Concatenação em loop → `StringBuilder`.
- Pra texto dinâmico: `String.format("Oi %s, %d anos", nome, idade)` (não existe template literal `${x}`).
- Cronometrar: `nanoTime()` antes e depois, subtrai, divide por `1_000_000` pra virar ms.

## LeetCode #242 — Valid Anagram

Resolvido em O(n)/O(1): atalho se os tamanhos diferem → false. Conto num `int[26]` usando `c - 'a'` como índice: incremento na 1ª palavra, decremento na 2ª. Se tudo zera no fim, é anagrama. Passou até no caso `"aacc"`/`"ccac"` (mesmas letras, quantidades diferentes → false). ✅