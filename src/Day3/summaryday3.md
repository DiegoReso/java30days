# Dia 3 — Herança, interfaces e polimorfismo

Terceiro dia do meu desafio de 30 dias de Java. Aqui está tudo que eu aprendi, do meu jeito, pra não esquecer.

## Herança (`extends`)

Uma classe **herda** campos e métodos de outra. A filha ganha tudo que a mãe tem, pode adicionar o seu e redefinir o que herdou:

```java
class Animal {
    protected String nome;
    public void comer() { System.out.println(nome + " está comendo"); }
}

class Cachorro extends Animal {      // Cachorro É UM Animal
    public void latir() { System.out.println("Au au!"); }
}
```

Um `Cachorro` já sabe `comer()` (herdou) e ainda sabe `latir()` (adicionou). Regra crucial: em Java **só se herda de UMA classe** (herança simples). Não existe `extends A, B`.

## Interface (`implements`)

Uma interface é um **contrato**: uma lista de métodos que a classe promete cumprir, sem dizer *como*. Quem implementa é obrigado a escrever o corpo de cada método:

```java
interface Forma {
    double area();       // só a assinatura, sem corpo — é a promessa
}
```

Diferente da herança, dá pra implementar **quantas interfaces quiser**:

```java
class Pato extends Animal implements Nadador, Voador { ... }
```

É assim que se escapa do limite da herança única: uma classe herda de uma só mãe, mas cumpre vários contratos.

## Classe abstrata (o meio-termo)

Fica entre a classe normal e a interface. **Pode ter estado e métodos concretos** (prontos), mas também **métodos abstratos** (sem corpo, que as filhas precisam implementar). E o principal: **não pode ser instanciada** — não dá `new` nela, só nas filhas.

```java
abstract class FormaBase {
    abstract double area();                    // filha precisa implementar
    void descrever() {                         // já vem pronto pra todas
        System.out.println("Área: " + area());
    }
}
```

Diferença mental: **interface** = "o que a classe *sabe fazer*" (só contrato). **Classe abstrata** = "uma base parcial" que já traz algo pronto pra reaproveitar.

## Polimorfismo

O coração da OOP. Uma variável do tipo **base** pode apontar pra qualquer **subtipo**, e a chamada do método é resolvida em **tempo de execução** — o objeto real decide qual versão roda.

```java
interface Forma { double area(); }

class Circulo implements Forma {
    private double r;
    Circulo(double r) { this.r = r; }
    @Override public double area() { return Math.PI * r * r; }
}

class Quadrado implements Forma {
    private double l;
    Quadrado(double l) { this.l = l; }
    @Override public double area() { return l * l; }
}

// polimorfismo em ação:
List<Forma> formas = List.of(new Circulo(2), new Quadrado(3));
for (Forma f : formas) {
    System.out.println(f.area());   // cada objeto roda a SUA área
}
```

Prova que rodei: a lista era de `Forma`, mas o `Circulo` calculou `π·r²` (12.56...) e o `Quadrado` calculou `l·l` (9.0). O mesmo `f.area()` no código, comportamentos diferentes em runtime. Isso se chama *dynamic dispatch* — a JVM olha o objeto de verdade na hora e escolhe o método certo.

## `@Override`

Sempre que redefino um método herdado ou implemento um da interface, coloco `@Override` em cima:

```java
@Override
public double area() { return Math.PI * r * r; }
```

Não é obrigatório, mas é um seguro barato: se eu errar a assinatura (escrever `Area()` com A maiúsculo, trocar um parâmetro), o compilador reclama na hora — em vez de eu criar silenciosamente um método novo que nunca é chamado.

## Composição > herança

Herança cria acoplamento forte (a filha fica presa à mãe). A recomendação madura é **preferir composição** — em vez de "ser um", o objeto "tem um":

```java
// herança:    class Carro extends Motor           -> Carro É UM Motor? Não faz sentido
// composição: class Carro { private Motor motor }  -> Carro TEM UM Motor. Melhor.
```

E uso **interfaces pra desacoplar**: meu código depende do contrato (`Forma`), não da classe concreta (`Circulo`). Trocar a implementação não quebra nada.

Ponte com o Nest: eu já vivo disso na injeção de dependência — dependo de interfaces, não de classes concretas. Em Java as interfaces cumprem o mesmo papel de contrato, e a classe abstrata é como uma base que já traz algum estado/comportamento pronto.

## Desafio feito — soma polimórfica

Criei `Retangulo` (mais uma implementação da interface), coloquei tudo numa `List<InterfaceForma>` e somei as áreas iterando de forma polimórfica. Fiz direto no `main`, com dois `for`: um pra imprimir cada área, outro pra somar.

```java
Quadrado q = new Quadrado(3);
Circulo c = new Circulo(2);
Retangulo r = new Retangulo(3, 5);

List<InterfaceForma> formas = List.of(q, c, r);

for (InterfaceForma f : formas) {
    System.out.println(f.area());   // cada objeto roda a SUA área
}

System.out.println("Soma das formas");
double soma = 0;
for (InterfaceForma f : formas) {
    soma = soma + f.area();         // sem NENHUM if de tipo
}
System.out.println(soma);           // 9 + 12.566... + 15 = 36.566...
```

O clique: a soma **não pergunta** de que tipo cada forma é — só chama `f.area()` e cada objeto responde do seu jeito. Se eu criar uma `Triangulo` implementando a interface amanhã e adicionar na lista, esses `for` continuam funcionando sem tocar numa linha. Código aberto pra extensão, fechado pra modificação.

## LeetCode #121 — Best Time to Buy and Sell Stock

**O que pede:** dado um array `precos` onde `precos[i]` é o preço no dia `i`, escolher um dia pra comprar e um dia **futuro** pra vender, maximizando o lucro. Se não der lucro, retornar `0`.

**A ideia (uma passada só):** carrego duas informações enquanto percorro o array:
- o **menor preço visto até agora** (o melhor dia de compra até o presente)
- o **maior lucro até agora**

Para cada preço, calculo `preco - menorPreco` (lucro se vender hoje) e atualizo o recorde. O truque de "só vender depois de comprar" sai de graça: como o menor preço só olha pra trás, a compra é sempre de um dia anterior.

Resolvi direto no `main`, com um `for` só (sem criar método):

```java
int precos[] = {7, 1, 5, 3, 6, 4};
int menorPreco = precos[0];
int maiorLucro = 0;
for (int i = 0; i < precos.length; i++) {
    if (precos[i] < menorPreco) {
        menorPreco = precos[i];
    }
    if ((precos[i] - menorPreco) > maiorLucro) {
        maiorLucro = precos[i] - menorPreco;
    }
}
System.out.println(maiorLucro);   // 5
```

**Eficiência:** O(n) no tempo (uma passada) e O(1) de memória (só duas variáveis). É a solução ótima.

Detalhe que entendi: o `maiorLucro` começar em `0` já resolve o caso "nunca dá lucro" (`[7,6,4,3,1]` retorna `0` — não faz negócio nenhum). E a ordem dos dois `if` pode ser trocada sem mudar o resultado: se o preço de hoje é o menor, `preco - menorPreco` dá `0`, que nunca supera o maiorLucro, então não estraga nada.

## Resumão do dia

- Herança (`extends`): herda campos e métodos; só de UMA classe.
- Interface (`implements`): contrato de métodos; pode implementar VÁRIAS.
- Classe abstrata: meio-termo — tem estado + métodos concretos e abstratos, e não pode ser instanciada.
- Polimorfismo: variável do tipo base aponta pra subtipo; o objeto real decide qual método roda (em runtime).
- `@Override` protege contra errar a assinatura ao redefinir/implementar.
- Prefira composição a herança; use interfaces pra desacoplar.
- Uma passada + duas variáveis de estado (menor/maior até agora) resolve muito problema de array em O(n)/O(1).