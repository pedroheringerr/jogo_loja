import item.Arma;
import item.Armadura;
import item.Item;
import item.Pocao;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  private static ArrayList<Item> estoque = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);
  private static int proximoId = 1;

  public static void main(String[] args) {
    boolean rodando = true;

    while (rodando) {
      exibirMenu();
      int opcao = lerInteiro("Escolha uma opção: ");

      if (estoque.isEmpty() && opcao >= 2 && opcao <= 5) {
        System.out.println("Opção indisponível. O estoque está vazio no momento!");
        continue;
      }

      switch (opcao) {
        case 1:
          adicionarItem();
          break;
        case 2:
          listarItens();
          break;
        case 3:
          atualizarItem();
          break;
        case 4:
          removerItem();
          break;
        case 5:
          venderItem();
          break;
        case 6:
          System.out.println("Fechando o Empório Mágico. Até a próxima aventura!");
          rodando = false;
          break;
        default:
          System.out.println("Opção inválida. Tente novamente.");
      }
    }
    scanner.close();
  }

  private static void exibirMenu() {
    System.out.println("\n========================================");
    System.out.println("       EMPÓRIO MÁGICO DO KADUR  ");
    System.out.println("========================================");
    System.out.println("1. Adicionar novo item ao estoque");

    if (!estoque.isEmpty()) {
      System.out.println("2. Listar itens disponíveis");
      System.out.println("3. Atualizar item");
      System.out.println("4. Remover item do catálogo");
      System.out.println("5. Vender item (Baixa no estoque)");
    }

    System.out.println("6. Sair");
    System.out.println("========================================");
  }

  private static void adicionarItem() {
    System.out.println("\n--- ADICIONAR ITEM ---");
    System.out.println("1. Poção | 2. Arma | 3. Armadura");
    int tipo = lerInteiro("Qual o tipo de item que deseja adicionar? ");

    if (tipo < 1 || tipo > 3) {
      System.out.println("Tipo inválido!");
      return;
    }

    System.out.print("Nome do item: ");
    String nome = scanner.nextLine();

    double preco = lerDouble("Preço (ex: 15.50): ");
    int quantidade = lerInteiro("Quantidade inicial em estoque: ");

    Item novoItem = null;

    if (tipo == 1) {
      System.out.print("Tipo de efeito (ex: Cura, Mana): ");
      String efeito = scanner.nextLine();
      System.out.print("Tamanho (Pequena, Média, Grande): ");
      String tamanho = scanner.nextLine();
      novoItem = new Pocao(proximoId, nome, preco, quantidade, efeito, tamanho);

    } else if (tipo == 2) {
      int ataque = lerInteiro("Pontos de Ataque: ");
      int durabilidade = lerInteiro("Durabilidade: ");
      novoItem = new Arma(proximoId, nome, preco, quantidade, ataque, durabilidade);

    } else if (tipo == 3) {
      int defesa = lerInteiro("Pontos de Defesa: ");
      int durabilidade = lerInteiro("Durabilidade: ");
      novoItem = new Armadura(proximoId, nome, preco, quantidade, defesa, durabilidade);
    }

    if (novoItem != null) {
      estoque.add(novoItem);
      System.out.println("Item cadastrado com sucesso! ID: " + proximoId);
      proximoId++;
    }
  }

  private static void listarItens() {
    System.out.println("\n--- ESTOQUE ATUAL ---");
    for (Item item : estoque) {
      System.out.printf(
          "[ID: %d] %s | Preço: %.2f | Quantidade: %d\n",
          item.getId(), item.getNome(), item.getPreco(), item.getQuantEmEstoque());

      if (item instanceof Pocao) {
        Pocao p = (Pocao) item;
        System.out.printf("   ↳ Poção (%s) - Efeito: %s\n", p.getTamanho(), p.getTipoDeEfeito());
      } else if (item instanceof Arma) {
        Arma a = (Arma) item;
        System.out.printf(
            "   ↳ Arma - Ataque: %d | Durabilidade: %d\n",
            a.getPontosDeAtaque(), a.getDurabilidade());
      } else if (item instanceof Armadura) {
        Armadura ar = (Armadura) item;
        System.out.printf(
            "   ↳ Armadura - Defesa: %d | Durabilidade: %d\n",
            ar.getPontosDeDefesa(), ar.getDurabilidade());
      }
    }
  }

  private static void atualizarItem() {
    System.out.println("\n--- ATUALIZAR ITEM ---");
    int idBusca = lerInteiro("Digite o ID do item que deseja atualizar: ");
    Item item = buscarPorId(idBusca);

    if (item == null) {
      System.out.println("Item não encontrado!");
      return;
    }

    System.out.println("O que deseja atualizar?");
    System.out.println("1. Nome | 2. Preço | 3. Adicionar Estoque");
    int escolha = lerInteiro("Opção: ");

    switch (escolha) {
      case 1:
        System.out.print("Novo nome: ");
        item.setNome(scanner.nextLine());
        System.out.println("Nome atualizado!");
        break;
      case 2:
        double novoPreco = lerDouble("Novo preço: ");
        item.setPreco(novoPreco);
        System.out.println("Preço atualizado!");
        break;
      case 3:
        int qtd = lerInteiro("Quantidade a adicionar: ");
        item.adicionarEstoque(qtd);
        break;
      default:
        System.out.println("Opção inválida.");
    }
  }

  private static void removerItem() {
    System.out.println("\n--- REMOVER ITEM ---");
    int idBusca = lerInteiro("Digite o ID do item que deseja remover: ");
    Item item = buscarPorId(idBusca);

    if (item != null) {
      estoque.remove(item);
      System.out.println("Item removido do catálogo com sucesso!");
    } else {
      System.out.println("Item não encontrado!");
    }
  }

  private static void venderItem() {
    System.out.println("\n--- VENDER ITEM ---");
    int idBusca = lerInteiro("Digite o ID do item sendo vendido: ");
    Item item = buscarPorId(idBusca);

    if (item != null) {
      int qtd = lerInteiro("Quantidade vendida: ");
      item.venda(qtd);
    } else {
      System.out.println("Item não encontrado!");
    }
  }

  private static Item buscarPorId(int id) {
    for (Item item : estoque) {
      if (item.getId() == id) {
        return item;
      }
    }
    return null;
  }

  private static int lerInteiro(String mensagem) {
    while (true) {
      try {
        System.out.print(mensagem);
        return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
      }
    }
  }

  private static double lerDouble(String mensagem) {
    while (true) {
      try {
        System.out.print(mensagem);
        // Uso do replace para permitir usar , como separador também
        String entrada = scanner.nextLine().replace(",", ".");
        return Double.parseDouble(entrada);
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida! Por favor, digite um número válido (ex: 15.50).");
      }
    }
  }
}
