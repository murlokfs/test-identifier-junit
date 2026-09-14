# Identifier

Implementação e testes automatizados do programa **Identifier** (Teste Funcional - Particionamento em Classes de Equivalência e Análise de Valor Limite).

Aluno: Murilo Kauan Fontes da Silva

## Especificação

Um identificador é válido se começar com uma letra, contiver apenas letras ou dígitos e tiver entre 1 e 6 caracteres.

## Tecnologias

- Java 17+
- JUnit 5
- Maven

## Estrutura

```
src/main/java/testIdentifier/Identifier.java       -> lógica de validação
src/main/java/testIdentifier/IdentifierMain.java   -> programa de linha de comando
src/test/java/testIdentifier/IdentifierTest.java   -> testes automatizados
```

## Como executar

Rodar os testes:

```
mvn test
```

Rodar o programa:

```
mvn compile
java -cp target/classes testIdentifier.IdentifierMain abc12
```

## Casos de teste

- CT01 a CT05: um caso por classe de equivalência (válidas agrupadas, inválidas isoladas).
- VL01 a VL04: valores limite de comprimento (0, 1, 6, 7).
- VL05 a VL13: valores limite das faixas de caracteres ASCII.
- Exemplos da especificação (`string`, `stringmuitogrande`) e entrada `null`.

Cada teste está organizado em Setup, Invocation e Assessment.
