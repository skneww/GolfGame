import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(()->{
      JFrame window = new JFrame("Golf Game");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setResizable(false);

      GameWindow gameWindow = new GameWindow();
      window.add(gameWindow);
      window.pack();
      
      window.setLocationRelativeTo(null);
      window.setVisible(true);  
    });
  }
}