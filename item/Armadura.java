package item;

public class Armadura extends Item {
  // Atributos de classe
  private int pontosDeDefesa;
  private int durabilidade;

  // Métodos construtores
  public Armadura() {}

  public Armadura(
      int id, String nome, double preco, int quantEmEstoque, int pontosDeDefesa, int durabilidade) {
    super(id, nome, preco, quantEmEstoque);
    this.pontosDeDefesa = pontosDeDefesa;
    this.durabilidade = durabilidade;
  }

  // Métodos de acesso
  public int getPontosDeDefesa() {
    return pontosDeDefesa;
  }

  public void setPontosDeDefesa(int pontosDeDefesa) {
    this.pontosDeDefesa = pontosDeDefesa;
  }

  public int getDurabilidade() {
    return durabilidade;
  }

  public void setDurabilidade(int durabilidade) {
    this.durabilidade = durabilidade;
  }
}
