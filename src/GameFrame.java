import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.*;

public class GameFrame extends JFrame {
    MainPanel mainPanel;
    String title = "WordAttack";

    public GameFrame() {
        FontLoader.loadGlobalFont();
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        mainPanel = new MainPanel();
        add(mainPanel);
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setUndecorated(true);
        GraphicsDevice gd = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
        setVisible(true);

    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
