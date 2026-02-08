import java.awt.*;
import javax.swing.*;

public class SettingsPanel extends JPanel {
    private JLabel title;
    private JButton backBtn;

    public SettingsPanel(MainPanel main) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);

        add(Box.createVerticalStrut(30));

        title = new JLabel("SETTINGS");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);
        add(title);

        add(Box.createVerticalStrut(30));

        backBtn = new JButton("Back");
        backBtn.setAlignmentX(CENTER_ALIGNMENT);
        backBtn.addActionListener(e -> main.showScene("TITLE"));
        add(backBtn);
    }

}
