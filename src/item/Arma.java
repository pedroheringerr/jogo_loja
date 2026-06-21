package item;

public class Arma extends Item {
  // Atributos de classe
  private int pontosDeAtaque;
  private int durabilidade;

  // Métodos construtores
  public Arma() {}

  public Arma(
      int id, String nome, double preco, int quantEmEstoque, int pontosDeAtaque, int durabilidade) {
    super(id, nome, preco, quantEmEstoque);
    this.pontosDeAtaque = pontosDeAtaque;
    this.durabilidade = durabilidade;
  }

  // Métodos de acesso
  public int getPontosDeAtaque() {
    return pontosDeAtaque;
  }

  public void setPontosDeAtaque(int pontosDeAtaque) {
    this.pontosDeAtaque = pontosDeAtaque;
  }

  public int getDurabilidade() {
    return durabilidade;
  }

  public void setDurabilidade(int durabilidade) {
    this.durabilidade = durabilidade;
  }
}
