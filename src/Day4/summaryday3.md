# Resumão do Dia 3 (revisão rápida)

Só pra relembrar antes de começar o próximo dia.

## O essencial

- **Herança (`extends`):** uma classe herda campos e métodos de outra. Em Java só se herda de **uma** classe.
- **Interface (`implements`):** um contrato de métodos que a classe promete cumprir. Dá pra implementar **várias**.
- **Classe abstrata:** o meio-termo — tem estado + métodos concretos e abstratos, e **não pode ser instanciada** (só as filhas).

## Polimorfismo

- Uma variável do tipo **base** aponta pra qualquer **subtipo**, e o **objeto real** decide qual método roda (em runtime).
- Exemplo: uma `List<InterfaceForma>` com `Circulo`, `Quadrado` e `Retangulo` — o mesmo `f.area()` roda a fórmula certa de cada um, sem nenhum `if` de tipo.
- **`@Override`** ao redefinir/implementar um método: o compilador avisa se eu errar a assinatura.

## Regras de bolso

- Prefira **composição** ("tem um") a herança ("é um") — herança acopla demais.
- Use **interfaces pra desacoplar**: dependa do contrato, não da classe concreta.
- Uma classe de apoio no mesmo arquivo fica **sem `public`** (só a classe que nomeia o arquivo é `public`).

## Desafio feito — soma polimórfica

Criei `Retangulo` (mais uma implementação da interface), coloquei tudo numa `List<InterfaceForma>` e somei as áreas com um `for`, chamando `f.area()` em cada uma — sem checar tipo. Se eu adicionar uma forma nova amanhã, o loop continua funcionando sem mudar nada. ✅

## LeetCode #121 — Best Time to Buy and Sell Stock

Resolvido em O(n)/O(1) com uma passada: guardo o **menor preço até agora** e o **maior lucro até agora**. Pra cada preço: atualizo o menor, e calculo `preco - menorPreco` como lucro candidato. O `maiorLucro` começa em `0`, o que já cobre o caso "nunca dá lucro". ✅