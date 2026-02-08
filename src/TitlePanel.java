import java.awt.*;
import javax.swing.*;

public class TitlePanel extends JPanel {
    
    private GridBagConstraints gbc;
    private JLabel title;
    private JButton startBtn;
    private JButton settingsBtn;
    private JButton exitBtn;

    public TitlePanel(MainPanel main) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        title = new JLabel("MY GAME");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        add(title, gbc);

        gbc.gridy++;
        startBtn = new JButton("Start Game");
        startBtn.addActionListener(e -> main.showScene("GAME"));
        add(startBtn, gbc);

        gbc.gridy++;
        settingsBtn = new JButton("Settings");
        settingsBtn.addActionListener(e -> main.showScene("SETTINGS"));
        add(settingsBtn, gbc);

        gbc.gridy++;
        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(e -> System.exit(0));
        add(exitBtn, gbc);
    }
}
