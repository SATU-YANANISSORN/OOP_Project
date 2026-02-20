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
    private Upgrade upgrade;
    private JLabel showKarma;

    public UpgradePanel(MainPanel main) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 0;
        this.upgrade = new Upgrade(main.getKarma(), main.getPlayer(), main);

        acceptButton = new JButton("accept");
        nextButton = new JButton("next");
        showKarma = new JLabel("Karma : " + main.getKarma());
        this.maxHpUpgradePanel = new UpgradeStatPanel(main, 10, 300, "MaxHp",15,upgrade,showKarma);
        this.baseAtkUpgradePanel = new UpgradeStatPanel(main, 300, 10, "Damage",35,upgrade,showKarma);
        JLabel label = new JLabel("UPGRADES");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel Group = new JPanel(new FlowLayout());
        Group.setBackground(Color.WHITE);

        Group.add(maxHpUpgradePanel);
        Group.add(baseAtkUpgradePanel);
        add(showKarma);
        gbc.gridy++;
        add(Group, gbc);
        gbc.gridy++;
        add(label, gbc);
        gbc.gridy++;
        add(acceptButton,gbc);
        add(nextButton,gbc);

        this.acceptButton.addActionListener(e -> {this.upgrade.accept(); main.showScene("GAME");});
        this.nextButton.addActionListener(e -> {this.upgrade.cancel(); main.showScene("GAME");});

    }
}
