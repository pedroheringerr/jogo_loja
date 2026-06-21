package gui;

import item.Arma;
import item.Armadura;
import item.GerenciadorArquivo;
import item.Item;
import item.Pocao;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Tela extends JFrame {
  private JPanel mainPanel;
  private JTextArea displayArea;
  private JButton btnAdicionar;
  private JButton btnVender;
  private JButton btnRemover;

  private ArrayList<Item> estoque;
  private int proximoId = 1;

  public Tela(String windowTitle, int width, int height) {
    super(windowTitle);

    estoque = GerenciadorArquivo.carregarEstoque();
    atualizarProximoId();

    initWindow(width, height);
    atualizarDisplay();
  }

  private void initWindow(int width, int height) {
    setSize(width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    mainPanel = new JPanel(new BorderLayout(10, 10));

    JLabel titleLabel = new JLabel("EMPÓRIO MÁGICO DO KADUR", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
    mainPanel.add(titleLabel, BorderLayout.NORTH);

    displayArea = new JTextArea();
    displayArea.setEditable(false);
    displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
    JScrollPane scrollPane = new JScrollPane(displayArea);
    mainPanel.add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    btnAdicionar = new JButton("Adicionar Estoque");
    btnVender = new JButton("Vender Item");
    btnRemover = new JButton("Remover Item");

    buttonPanel.add(btnAdicionar);
    buttonPanel.add(btnVender);
    buttonPanel.add(btnRemover);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    add(mainPanel);
    configurarBotoes();
  }

  private void configurarBotoes() {
    btnAdicionar.addActionListener(e -> abrirDialogoAdicionarItem());

    // Vender
    btnVender.addActionListener(
        e -> {
          if (estoque.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O estoque está vazio!");
            return;
          }
          try {
            int idBusca =
                Integer.parseInt(
                    JOptionPane.showInputDialog(this, "Digite o ID do item para vender:"));
            Item item = buscarPorId(idBusca);

            if (item != null) {
              int qtd =
                  Integer.parseInt(
                      JOptionPane.showInputDialog(
                          this,
                          "Quantidade a vender (Disponível: " + item.getQuantEmEstoque() + "):"));
              String resultado = item.venda(qtd);
              JOptionPane.showMessageDialog(this, resultado);
              salvarEAtualizar();
            } else {
              JOptionPane.showMessageDialog(
                  this, "Item não encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
            }
          } catch (NumberFormatException ex) {
          }
        });

    // Remover
    btnRemover.addActionListener(
        e -> {
          if (estoque.isEmpty()) return;
          try {
            int idBusca =
                Integer.parseInt(
                    JOptionPane.showInputDialog(
                        this, "Digite o ID do item para remover totalmente:"));
            Item item = buscarPorId(idBusca);

            if (item != null) {
              estoque.remove(item);
              JOptionPane.showMessageDialog(this, "Item removido com sucesso!");
              salvarEAtualizar();
            } else {
              JOptionPane.showMessageDialog(
                  this, "Item não encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
            }
          } catch (NumberFormatException ex) {
          }
        });
  }

  private void abrirDialogoAdicionarItem() {
    JDialog dialog = new JDialog(this, "Adicionar Novo Item", true);
    dialog.setSize(400, 350);
    dialog.setLocationRelativeTo(this);
    dialog.setLayout(new BorderLayout(10, 10));

    // Dropdown
    JPanel topPanel = new JPanel();
    topPanel.add(new JLabel("Tipo de Item: "));
    JComboBox<String> cbTipo = new JComboBox<>(new String[] {"Arma", "Armadura", "Poção"});
    topPanel.add(cbTipo);
    dialog.add(topPanel, BorderLayout.NORTH);

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    JPanel commonPanel = new JPanel(new GridLayout(3, 2, 5, 5));
    JTextField txtNome = new JTextField();
    JTextField txtPreco = new JTextField();
    JTextField txtQuantidade = new JTextField();

    commonPanel.add(new JLabel("Nome do Item:"));
    commonPanel.add(txtNome);
    commonPanel.add(new JLabel("Preço (ex: 15.50):"));
    commonPanel.add(txtPreco);
    commonPanel.add(new JLabel("Quantidade Inicial:"));
    commonPanel.add(txtQuantidade);
    centerPanel.add(commonPanel);

    centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

    JPanel cardsPanel = new JPanel(new CardLayout());

    // Campos de Arma
    JPanel panelArma = new JPanel(new GridLayout(2, 2, 5, 5));
    JTextField txtAtaque = new JTextField();
    JTextField txtDurabilidadeArma = new JTextField();
    panelArma.add(new JLabel("Pontos de Ataque:"));
    panelArma.add(txtAtaque);
    panelArma.add(new JLabel("Durabilidade:"));
    panelArma.add(txtDurabilidadeArma);

    // Campos de Armadura
    JPanel panelArmadura = new JPanel(new GridLayout(2, 2, 5, 5));
    JTextField txtDefesa = new JTextField();
    JTextField txtDurabilidadeArmadura = new JTextField();
    panelArmadura.add(new JLabel("Pontos de Defesa:"));
    panelArmadura.add(txtDefesa);
    panelArmadura.add(new JLabel("Durabilidade:"));
    panelArmadura.add(txtDurabilidadeArmadura);

    // Campos de poção
    JPanel panelPocao = new JPanel(new GridLayout(2, 2, 5, 5));
    JTextField txtEfeito = new JTextField();
    JTextField txtTamanho = new JTextField();
    panelPocao.add(new JLabel("Tipo de Efeito (ex: Cura):"));
    panelPocao.add(txtEfeito);
    panelPocao.add(new JLabel("Tamanho (P, M, G):"));
    panelPocao.add(txtTamanho);

    // Painel especifico de cada item
    cardsPanel.add(panelArma, "Arma");
    cardsPanel.add(panelArmadura, "Armadura");
    cardsPanel.add(panelPocao, "Poção");
    centerPanel.add(cardsPanel);

    dialog.add(centerPanel, BorderLayout.CENTER);

    // Salvar
    JPanel bottomPanel = new JPanel();
    JButton btnSalvarItem = new JButton("Salvar no Estoque");
    bottomPanel.add(btnSalvarItem);
    dialog.add(bottomPanel, BorderLayout.SOUTH);

    // Muda opções visiveis dependendo do dropdown
    cbTipo.addActionListener(
        e -> {
          CardLayout cl = (CardLayout) (cardsPanel.getLayout());
          cl.show(cardsPanel, (String) cbTipo.getSelectedItem());
        });

    // Logica para salvar
    btnSalvarItem.addActionListener(
        e -> {
          try {
            String nome = txtNome.getText();
            String precoStr = txtPreco.getText().replace(",", "."); // Aceita virgula ou ponto
            double preco = Double.parseDouble(precoStr);
            int quant = Integer.parseInt(txtQuantidade.getText());

            if (nome.trim().isEmpty()) {
              JOptionPane.showMessageDialog(
                  dialog, "O nome não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
              return;
            }

            String tipoSelecionado = (String) cbTipo.getSelectedItem();

            if (tipoSelecionado.equals("Arma")) {
              int ataque = Integer.parseInt(txtAtaque.getText());
              int durabilidade = Integer.parseInt(txtDurabilidadeArma.getText());
              estoque.add(new Arma(proximoId++, nome, preco, quant, ataque, durabilidade));

            } else if (tipoSelecionado.equals("Armadura")) {
              int defesa = Integer.parseInt(txtDefesa.getText());
              int durabilidade = Integer.parseInt(txtDurabilidadeArmadura.getText());
              estoque.add(new Armadura(proximoId++, nome, preco, quant, defesa, durabilidade));

            } else if (tipoSelecionado.equals("Poção")) {
              String efeito = txtEfeito.getText();
              String tamanho = txtTamanho.getText();
              if (efeito.trim().isEmpty() || tamanho.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    dialog, "Preencha efeito e tamanho.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
              }
              estoque.add(new Pocao(proximoId++, nome, preco, quant, efeito, tamanho));
            }

            salvarEAtualizar();
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Item cadastrado com sucesso!");

          } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                dialog,
                "Por favor, verifique se todos os campos numéricos (Preço, Quantidade, Ataque, etc)"
                    + " contêm apenas números.",
                "Erro de Digitação",
                JOptionPane.ERROR_MESSAGE);
          }
        });

    dialog.setVisible(true);
  }

  private void atualizarProximoId() {
    if (!estoque.isEmpty()) {
      int maxId = 0;
      for (Item item : estoque) {
        if (item.getId() > maxId) maxId = item.getId();
      }
      proximoId = maxId + 1;
    }
  }

  private Item buscarPorId(int id) {
    for (Item item : estoque) {
      if (item.getId() == id) return item;
    }
    return null;
  }

  private void salvarEAtualizar() {
    GerenciadorArquivo.salvarEstoque(estoque);
    atualizarDisplay();
  }

  private void atualizarDisplay() {
    displayArea.setText("");
    if (estoque.isEmpty()) {
      displayArea.setText("\n   O estoque está vazio no momento.");
      return;
    }

    for (Item item : estoque) {
      displayArea.append(
          String.format(
              " [ID: %d] %s | Preço: R$%.2f | Estoque: %d\n",
              item.getId(), item.getNome(), item.getPreco(), item.getQuantEmEstoque()));

      if (item instanceof Arma) {
        Arma a = (Arma) item;
        displayArea.append(
            String.format(
                "    ↳ Arma (Ataque: %d | Durabilidade: %d)\n\n",
                a.getPontosDeAtaque(), a.getDurabilidade()));
      } else if (item instanceof Armadura) {
        Armadura ar = (Armadura) item;
        displayArea.append(
            String.format(
                "    ↳ Armadura (Defesa: %d | Durabilidade: %d)\n\n",
                ar.getPontosDeDefesa(), ar.getDurabilidade()));
      } else if (item instanceof Pocao) {
        Pocao p = (Pocao) item;
        displayArea.append(
            String.format(
                "    ↳ Poção (%s - Efeito: %s)\n\n", p.getTamanho(), p.getTipoDeEfeito()));
      }
    }
  }
}
