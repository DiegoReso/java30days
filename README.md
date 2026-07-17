# Java em 30 dias 🚀

Meu plano de estudos para me aprofundar em **Java** e me preparar para vagas backend, vindo de uma base em **TypeScript, React e NestJS**.

A ideia não é "aprender Java do zero" — é sair de júnior confortável em JS/TS para candidato competitivo em Java, focando em **Java core + OOP avançado** e **algoritmos/estruturas de dados** no estilo entrevista técnica.

> 📅 **1 conceito + 1 exercício por dia · ~3h/dia · 1 commit por dia**

---

## 🎯 Objetivos

- Dominar Java core: tipos, memória (stack/heap), Collections, Generics, Streams, lambdas
- Escrever OOP idiomático: encapsulamento, herança, interfaces, `record`, `enum`, imutabilidade
- Entender a JVM: bytecode, garbage collector, concorrência (threads, `ExecutorService`, `CompletableFuture`)
- Praticar algoritmos e estruturas de dados resolvendo um problema de LeetCode por dia **em Java**
- Escrever testes com JUnit 5 + Mockito
- Construir consistência com commits diários

---

## 🗂 Estrutura do repositório

```
Java30days/
├── src/
│   ├── Day1/          # solução e anotações do dia
│   ├── Day2/
│   └── ...            # um pacote por dia de estudo
├── README.md
└── ...
```

Cada dia vive em seu próprio pacote (`src/DayN`), com a solução do exercício e, quando fizer sentido, um comentário no topo explicando a abordagem e o Big-O.

---

## 📚 Roteiro dos 30 dias

Cada dia segue o mesmo formato: **conceito** do dia → **exercício LeetCode** (resolvido em Java) → **desafio** prático opcional. As semanas terminam com um dia de revisão.

### Semana 1 — Fundamentos, OOP e arrays/strings

| Dia | Tema | LeetCode |
|-----|------|----------|
| 1 | Tipos, memória, `==` vs `.equals()`, autoboxing | Two Sum (#1) |
| 2 | Classes, construtores, encapsulamento | Valid Palindrome (#125) |
| 3 | Herança, interfaces, polimorfismo | Best Time to Buy and Sell Stock (#121) |
| 4 | `record`, `enum`, imutabilidade | Contains Duplicate (#217) |
| 5 | `String`, `StringBuilder`, `char[]` | Valid Anagram (#242) |
| 6 | Arrays e a classe `Arrays` | Two Sum II (#167) |
| 7 | Revisão — two pointers | 3Sum (#15) |

### Semana 2 — Collections, Generics, Streams e hashing

| Dia | Tema | LeetCode |
|-----|------|----------|
| 8 | `List`, `ArrayList`, `LinkedList` | Group Anagrams (#49) |
| 9 | `Map`, `HashMap`, hashing | Top K Frequent Elements (#347) |
| 10 | `Set`, `HashSet`, `TreeSet` | Product of Array Except Self (#238) |
| 11 | Generics e wildcards | Longest Substring Without Repeating Characters (#3) |
| 12 | Lambdas e interfaces funcionais | Longest Repeating Character Replacement (#424) |
| 13 | Streams API | Valid Parentheses (#20) |
| 14 | Revisão — hashing e pilha | Min Stack (#155) |

### Semana 3 — Exceptions, Optional, JVM e estruturas ligadas

| Dia | Tema | LeetCode |
|-----|------|----------|
| 15 | Exceptions (checked vs unchecked) | Reverse Linked List (#206) |
| 16 | `Optional` | Merge Two Sorted Lists (#21) |
| 17 | Linked List | Linked List Cycle (#141) |
| 18 | Recursão e call stack | Invert Binary Tree (#226) |
| 19 | Árvores binárias | Maximum Depth of Binary Tree (#104) |
| 20 | JVM, memória e garbage collector | Same Tree (#100) |
| 21 | Revisão — árvores e recursão | Binary Tree Level Order Traversal (#102) |

### Semana 4 — Concorrência, testes, patterns e algoritmos clássicos

| Dia | Tema | LeetCode |
|-----|------|----------|
| 22 | Concorrência: `Thread` e `Runnable` | Number of Islands (#200) |
| 23 | Concorrência moderna (`ExecutorService`, `CompletableFuture`) | Binary Search (#704) |
| 24 | Design patterns (Singleton, Factory, Builder, Strategy) | Search in Rotated Sorted Array (#33) |
| 25 | Testes com JUnit 5 e Mockito | Climbing Stairs (#70) |
| 26 | Programação dinâmica — introdução | House Robber (#198) |
| 27 | Ordenação e busca binária a fundo | Coin Change (#322) |
| 28 | Revisão — DP e busca binária | Kth Largest Element (#215) |

### Consolidação

| Dia | Tema |
|-----|------|
| 29 | Simulado de entrevista técnica (2 problemas medium, cronometrados) |
| 30 | Mini-projeto de portfólio |

---

## 🛠 Ambiente

- **JDK 21 (LTS)**
- **IntelliJ IDEA**
- Git para versionar o progresso diário

---

## 📖 Recursos de apoio

- [dev.java](https://dev.java) — tutoriais oficiais
- [Baeldung](https://www.baeldung.com) — artigos práticos de Java e Spring
- **Effective Java** (Joshua Bloch) — boas práticas
- [NeetCode 150](https://neetcode.io) — trilha de algoritmos para entrevistas

---

## 🌱 Próximos passos

Depois dos 30 dias, a sequência é mergulhar em **Spring Boot** JPA/Hibernate e Docker.

---

> _"Consistência > intensidade."_ O objetivo aqui é aprender construindo — um dia, um conceito, um problema de cada vez.
