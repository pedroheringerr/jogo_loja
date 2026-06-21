package item;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GerenciadorArquivo {
  private static final String ARQUIVO = "estoque.txt";

  public static void salvarEstoque(ArrayList<Item> estoque) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
      for (Item item : estoque) {
        if (item instanceof Pocao) {
          Pocao p = (Pocao) item;
          bw.write(
              "Pocao;"
                  + p.getId()
                  + ";"
                  + p.getNome()
                  + ";"
                  + p.getPreco()
                  + ";"
                  + p.getQuantEmEstoque()
                  + ";"
                  + p.getTipoDeEfeito()
                  + ";"
                  + p.getTamanho());
        } else if (item instanceof Arma) {
          Arma a = (Arma) item;
          bw.write(
              "Arma;"
                  + a.getId()
                  + ";"
                  + a.getNome()
                  + ";"
                  + a.getPreco()
                  + ";"
                  + a.getQuantEmEstoque()
                  + ";"
                  + a.getPontosDeAtaque()
                  + ";"
                  + a.getDurabilidade());
        } else if (item instanceof Armadura) {
          Armadura ar = (Armadura) item;
          bw.write(
              "Armadura;"
                  + ar.getId()
                  + ";"
                  + ar.getNome()
                  + ";"
                  + ar.getPreco()
                  + ";"
                  + ar.getQuantEmEstoque()
                  + ";"
                  + ar.getPontosDeDefesa()
                  + ";"
                  + ar.getDurabilidade());
        }
        bw.newLine();
      }
    } catch (IOException e) {
      System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
    }
  }

  public static ArrayList<Item> carregarEstoque() {
    ArrayList<Item> estoque = new ArrayList<>();
    File file = new File(ARQUIVO);

    if (!file.exists()) return estoque;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        String[] partes = linha.split(";");
        String tipo = partes[0];
        int id = Integer.parseInt(partes[1]);
        String nome = partes[2];
        double preco = Double.parseDouble(partes[3]);
        int quant = Integer.parseInt(partes[4]);

        if (tipo.equals("Pocao")) {
          estoque.add(new Pocao(id, nome, preco, quant, partes[5], partes[6]));
        } else if (tipo.equals("Arma")) {
          estoque.add(
              new Arma(
                  id,
                  nome,
                  preco,
                  quant,
                  Integer.parseInt(partes[5]),
                  Integer.parseInt(partes[6])));
        } else if (tipo.equals("Armadura")) {
          estoque.add(
              new Armadura(
                  id,
                  nome,
                  preco,
                  quant,
                  Integer.parseInt(partes[5]),
                  Integer.parseInt(partes[6])));
        }
      }
    } catch (IOException | NumberFormatException e) {
      System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
    }
    return estoque;
  }
}
