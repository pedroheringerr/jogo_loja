package item;

public class Item {
  // Atributos de classe
  private int id;
  private String nome;
  private double preco;
  private int quantEmEstoque;

  // Métodos construtores
  public Item() {}

  public Item(int id, String nome, double preco, int quantEmEstoque) {
    this.id = id;
    this.nome = nome;
    this.preco = preco;
    this.quantEmEstoque = quantEmEstoque;
  }

  // Métodos de acesso
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public double getPreco() {
    return preco;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }

  public int getQuantEmEstoque() {
    return quantEmEstoque;
  }

  public void setQuantEmEstoque(int quantEmEstoque) {
    this.quantEmEstoque = quantEmEstoque;
  }

  // Métodos da classe
  public void venda(int quantidade) {
    if (this.quantEmEstoque - quantidade >= 0) {
      this.quantEmEstoque -= quantidade;
      System.out.printf(
          "%d %s vendidos. %d restando no estoque.\n", quantidade, this.nome, this.quantEmEstoque);
    } else {
      System.out.println("Estoque insuficiente.");
    }
  }

  public void adicionarEstoque(int quantidade) {
    this.quantEmEstoque += quantidade;
    System.out.printf("%d %s em estoque.\n", this.quantEmEstoque, this.nome);
  }
}
