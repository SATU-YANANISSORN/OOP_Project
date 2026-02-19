import javax.swing.*;

public class MainPanel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameWindow(); 
        });
    } 
}