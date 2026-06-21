# Gerenciador de Empório Mágico

Um sistema CRUD (Create, Read, Update, Delete) desenvolvido em Java puro com **Interface Gráfica (GUI) utilizando Java Swing**. O projeto simula o gerenciamento de estoque de um lojista de RPG, permitindo o cadastro, venda e remoção de itens mágicos, armas e armaduras através dos conceitos de Programação Orientada a Objetos (Herança e Polimorfismo) e salvamento persistente de dados em arquivo de texto.

## Funcionalidades

* **Interface Gráfica Dinâmica:** Janela principal com área de exibição atualizada em tempo real e formulários modais dinâmicos (`CardLayout`) que se adaptam aos diferentes atributos do item escolhido (Poção, Arma ou Armadura).
* **Persistência de Dados:** O inventário é salvo e carregado automaticamente utilizando um sistema de arquivos (`estoque.txt`), garantindo que os dados não sejam perdidos ao fechar o aplicativo.
* **Adicionar Itens (Create):** Cadastro de novos produtos categorizados, solicitando atributos genéricos e específicos dependendo do tipo de item escolhido no menu suspenso.
* **Listar Estoque (Read):** Exibição detalhada de todos os itens disponíveis na tela principal, mostrando status únicos (como efeito da poção ou dano da arma) através de polimorfismo.
* **Sistema de Vendas e Remoção (Update/Delete):** Opções interativas via pop-ups (`JOptionPane`) para vender unidades (dando baixa no estoque) ou remover o item permanentemente do catálogo.
* **Dimensões Customizáveis:** Suporte à passagem de argumentos via linha de comando para definir a resolução inicial da tela.

## Estrutura de Arquivos

```text
.
├── LICENSE
├── README.md
├── estoque.txt (gerado automaticamente na primeira execução)
└── src
    ├── Main.java
    ├── gui
    │   └── Tela.java
    └── item
        ├── Arma.java
        ├── Armadura.java
        ├── GerenciadorArquivo.java
        ├── Item.java
        └── Pocao.java
```

## Compilando e Executando
Como o projeto utiliza pacotes e não depende de ferramentas como Maven ou Gradle, a compilação e execução devem ser feitas da seguinte maneira:

1. Abra o terminal na pasta raiz do projeto (onde está o README.md).

2. Compile todos os arquivos de uma só vez, apontando para a pasta `src`:

```bash
javac src/item/*.java src/gui/*.java src/Main.java
```
3. Navegue para dentro da pasta src (fundamental para o Java encontrar os pacotes corretamente):
```bash
cd src
```
4. Execute o programa
```bash
java Main
```

## Configurando o Tamanho da Janela (Opcional)
Você pode iniciar o programa com uma resolução de tela personalizada passando a largura e a altura como argumentos.
Exemplo para iniciar em 1024x768:
```bash
java Main 1024 768
```
