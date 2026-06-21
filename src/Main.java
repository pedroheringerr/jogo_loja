import gui.Tela;
import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    int width = 800;
    int height = 600;

    if (args.length >= 2) {
      try {
        width = Integer.parseInt(args[0]);
        height = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        System.out.println("Argumentos de tamanho inválidos. Usando o padrão 800x600.");
      }
    }

    final int finalWidth = width;
    final int finalHeight = height;

    SwingUtilities.invokeLater(
        () -> {
          Tela janela = new Tela("Empório Mágico do Kadur", finalWidth, finalHeight);
          janela.setVisible(true);
        });
  }
}
