import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UpgradePanel extends JPanel {

    private GridBagConstraints gbc;
    private JButton acceptButton,nextButton;
    private UpgradeStatPanel maxHpUpgradePanel;
    private UpgradeStatPanel baseAtkUpgradePanel;

    public UpgradePanel(MainPanel main) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 0;

        this.maxHpUpgradePanel = new UpgradeStatPanel(main, 10, 300, "maxHp",15);
        this.baseAtkUpgradePanel = new UpgradeStatPanel(main, 300, 10, "damage",35);

        JLabel label = new JLabel("UPGRADES");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel Group = new JPanel(new FlowLayout());
        Group.setBackground(Color.WHITE);

        Group.add(maxHpUpgradePanel);
        Group.add(baseAtkUpgradePanel);
        
        add(Group, gbc);
        gbc.gridy++;
        add(label, gbc);
        

    }
}
