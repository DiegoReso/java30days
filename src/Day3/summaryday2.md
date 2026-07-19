# Resumão do Dia 2 (revisão rápida)

Só pra relembrar antes de começar o próximo dia.

## O essencial

- **Molde e instância:** a classe é o molde; o objeto é uma instância feita com `new`. Cada objeto tem seu próprio estado.
- **Uma classe tem três coisas:** campos (o estado), métodos (o comportamento) e construtor (inicializa no `new`, tem o nome da classe e não tem retorno).
- **`this`** = o objeto atual. Serve pra desambiguar campo x parâmetro: `this.saldo = saldo`.

## Encapsulamento

- **Esconder o estado, expor só o que faz sentido:** campos `private` + métodos que validam. Isso protege as invariantes (ex: saldo nunca fica inválido).
- Mexer no campo `private` por fora **nem compila** (`saldo has private access`). A única porta de entrada são os métodos.
- **`final` num campo** = imutável depois de inicializado (atribui uma vez e nunca mais).

## Modificadores de acesso

| Modificador | Quem acessa |
|---|---|
| `private` | só a própria classe |
| *(nada)* package-private | o mesmo pacote |
| `protected` | pacote + subclasses |
| `public` | todos |

## Regras de bolso

- Comece tudo **`private`**; abra só o que precisa (é mais fácil abrir depois do que fechar).
- Construtor sempre deixa o objeto num estado válido — valide os parâmetros nele.
- Prefira `char` primitivo a `Character` (evita autoboxing; `!=` entre `char` compara valor).

## LeetCode #125 — Valid Palindrome

Resolvido em O(n) e O(1): dois ponteiros (`esquerda`/`direita`) andando pro centro. Pulo o não-alfanumérico com `!Character.isLetterOrDigit(...)` e comparo em minúsculo com `Character.toLowerCase(...)`. Diferença -> `return false`; loop acaba sem diferença -> `return true`. ✅