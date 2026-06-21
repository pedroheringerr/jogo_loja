package item;

public class Pocao extends Item {
  // Atributos de classe
  private String tipoDeEfeito;
  private String tamanho;

  // Métodos construtores
  public Pocao() {}

  public Pocao(
      int id, String nome, double preco, int quantEmEstoque, String tipoDeEfeito, String tamanho) {
    super(id, nome, preco, quantEmEstoque);
    this.tipoDeEfeito = tipoDeEfeito;
    this.tamanho = tamanho;
  }

  // Métodos de acesso
  public String getTipoDeEfeito() {
    return tipoDeEfeito;
  }

  public void setTipoDeEfeito(String tipoDeEfeito) {
    this.tipoDeEfeito = tipoDeEfeito;
  }

  public String getTamanho() {
    return tamanho;
  }

  public void setTamanho(String tamanho) {
    this.tamanho = tamanho;
  }
}
