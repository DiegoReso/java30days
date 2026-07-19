# Dia 2 — Classes, construtores e encapsulamento

Segundo dia do meu desafio de 30 dias de Java. Aqui está tudo que eu aprendi, do meu jeito, pra não esquecer.

## A ideia principal: molde e instância

Uma classe é o **molde**; um objeto é uma **instância** dela. `Conta` é a planta da casa; `new Conta(100.0)` é uma casa construída. De um molde eu faço quantos objetos quiser, e cada um tem seu próprio estado independente.

```java
Conta minha = new Conta(100.0);
Conta sua   = new Conta(500.0);   // outro objeto, saldo independente
```

Vindo do Nest/TS, o conceito de `class` é o mesmo — a diferença é que em Java o encapsulamento é levado a sério de verdade (o compilador me obriga).

## Os três ingredientes de uma classe

Uma classe junta três coisas:

- **Campos** (o *estado*): os dados que o objeto carrega. Ex: `private double saldo`.
- **Métodos** (o *comportamento*): o que o objeto sabe fazer. Ex: `depositar`, `getSaldo`.
- **Construtor** (a *inicialização*): roda uma vez, no `new`, pra deixar o objeto num estado válido desde o nascimento.

O construtor tem o **mesmo nome da classe** e **não tem tipo de retorno**. Se eu não escrever nenhum, o Java me dá um construtor vazio de graça — mas assim que eu escrevo um com parâmetros, o vazio some (por isso às vezes o `new Conta()` sem argumentos para de compilar).

## `this`

`this` é o "eu mesmo" — o objeto atual. Serve principalmente pra desambiguar quando o parâmetro tem o mesmo nome do campo:

```java
public Conta(double saldo) {
    this.saldo = saldo;   // this.saldo = o campo; saldo = o parâmetro
}
```

Sem o `this.`, eu estaria atribuindo o parâmetro a ele mesmo, e o campo ficaria zerado. É um bug clássico e silencioso.

## Encapsulamento

A ideia: **esconder o estado interno e expor só o que faz sentido**. Marco os campos como `private` e controlo o acesso por métodos. Isso protege as *invariantes* — as regras que sempre têm que ser verdade sobre o objeto (ex.: "saldo nunca fica inválido").

```java
public class Conta {
    private double saldo;                     // ninguém mexe direto

    public Conta(double saldoInicial) {       // construtor
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("valor invalido");
        this.saldo += valor;                  // regra protegida aqui
    }

    public double getSaldo() {
        return saldo;
    }
}
```

Prova que rodei: quando tentei depositar `-10`, a regra **bloqueou** e o saldo continuou intacto. E quando tentei mexer no campo direto por fora (`conta.saldo = -999`), **nem compilou** — deu `saldo has private access in Conta`. Esse é o coração do encapsulamento: o estado é blindado, e a única porta de entrada são os métodos que validam.

## Modificadores de acesso

| Modificador | Quem pode acessar |
|---|---|
| `private` | só a própria classe |
| *(nada)* — package-private | o mesmo pacote |
| `protected` | pacote + subclasses |
| `public` | todos |

Regra de bolso: **começo tudo `private`** e só abro (`public`) o que realmente precisa ser usado por fora. É mais fácil abrir depois do que fechar — fechar algo que já é público pode quebrar quem dependia dele.

## `final` num campo

`final` torna o campo **imutável depois de inicializado** — atribuo uma vez (na declaração ou no construtor) e nunca mais:

```java
private final String titular;   // definido no construtor, nunca muda depois
```

Ótimo pra dados que não deveriam mudar durante a vida do objeto (um id, o titular de uma conta). Se alguém tentar reatribuir, não compila.

## LeetCode #125 — Valid Palindrome

**O problema pede:** dada uma string, verificar se é palíndromo (lê igual de trás pra frente), considerando **só** caracteres alfanuméricos e **ignorando** maiúsculas/minúsculas. Ex.: `"A man, a plan, a canal: Panama"` é palíndromo.

**A técnica:** dois ponteiros — um índice no começo (`esquerda`) e outro no fim (`direita`), caminhando um em direção ao outro, comparando os pares e fechando pro centro.

**Os detalhes que fazem funcionar:**
- pular o que não é letra nem número, com `!Character.isLetterOrDigit(...)` — usei dois `while` internos que empurram cada ponteiro por cima do "lixo" (espaço, vírgula, `:`) até parar num caractere válido.
- comparar os dois lados **em minúsculo**, com `Character.toLowerCase(...)`, senão `'A'` e `'a'` pareceriam diferentes.
- se em algum ponto forem diferentes -> `return false`. Se o loop terminar sem achar diferença -> `return true`.

```java
static boolean isPalindrome(String palindrome) {
    int esquerda = 0;
    int direita = palindrome.length() - 1;
    while (esquerda < direita) {
        while (esquerda < direita && !Character.isLetterOrDigit(palindrome.charAt(esquerda))) {
            esquerda++;
        }
        while (esquerda < direita && !Character.isLetterOrDigit(palindrome.charAt(direita))) {
            direita--;
        }
        if (Character.toLowerCase(palindrome.charAt(esquerda)) != Character.toLowerCase(palindrome.charAt(direita))) {
            return false;
        }
        esquerda++;
        direita--;
    }
    return true;
}
```

**Eficiência:** O(n) no tempo (cada ponteiro cruza a string uma vez) e **O(1) de memória** — não criei string nova nem inverti nada, trabalhei direto na original. Essa é a solução ótima.

Conexões com o Dia 1 que apareceram nesse código:
- usei `char` primitivo (não `Character` wrapper) — é o tipo natural do `charAt` e evita autoboxing.
- o `!=` entre dois `char` compara **valor** (primitivo), não referência. Se fossem `Character`, o `!=` compararia referências e daria bug — mais um motivo pra usar `char`.
- `Character.isLetterOrDigit(...)` e `Character.toLowerCase(...)` são **métodos estáticos** — chamados pelo nome da classe (`Character.metodo()`), não por um objeto.

Casos de borda que testei e passaram: string vazia `""` (palíndromo por definição), só pontuação `".,"` (vira "vazio" -> true), `"0P"` (número vs letra -> false), e frases com muito lixo no meio como `"No 'x' in Nixon"`.

## Resumão do dia

- Classe é o molde; objeto é a instância. Campos (estado) + métodos (comportamento) + construtor (inicializa no `new`).
- `this` é o objeto atual; resolve o conflito de nome entre campo e parâmetro.
- Encapsulamento = campos `private` + métodos que validam. Protege as invariantes do objeto.
- Começo tudo `private` e abro só o necessário. `final` = imutável depois de inicializado.
- Modificadores: `private` (só a classe) < package-private < `protected` (+ subclasses) < `public` (todos).
- Dois ponteiros: técnica O(n)/O(1) pra varrer uma sequência das pontas pro centro (usei no Valid Palindrome).
