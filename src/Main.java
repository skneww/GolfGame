import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(()->{
      JFrame window = new JFrame("GOlf Game");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setResizable(false);

      window.add(new GameWindow());
      window.pack();
      window.setLocationRelativeTo(null);
      window.setVisible(true);
    });
  }
}