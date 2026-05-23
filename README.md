# Gerenciador de Empório Mágico

Um mini sistema CRUD (Create, Read, Update, Delete) desenvolvido em Java puro com interface via linha de comando (CLI). O projeto simula o gerenciamento de estoque de um lojista de RPG, permitindo o cadastro, atualização e venda de itens mágicos, armas e armaduras através dos conceitos de Programação Orientada a Objetos (Herança e Polimorfismo).

## Estrutura de Arquivos

```text
.
├── LICENSE
├── Main.java
├── README.md
└── item
    ├── Arma.java
    ├── Armadura.java
    ├── Item.java
    └── Pocao.java
```

## Como Executar o Projeto

Como o projeto não utiliza ferramentas de build complexas, é necessário compilar os arquivos manualmente através do terminal.

1. Abra o terminal na pasta raiz do projeto (onde o arquivo `Main.java` está localizado).
2. Compile os arquivos do pacote de itens:
```Bash
javac item/*.java
```
3. Compile a classe principal:
```Bash
javac Main.java
```
4. Execute o programa:
```Bash
java Main
```
