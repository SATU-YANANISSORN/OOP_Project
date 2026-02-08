import javax.swing.*;

public class GameFrame extends JFrame {
    MainPanel mainPanel;
    String title = "JPanel Game";

    public GameFrame() {
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        mainPanel = new MainPanel();
        add(mainPanel);
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
