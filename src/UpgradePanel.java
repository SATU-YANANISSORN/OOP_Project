
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UpgradePanel extends JPanel implements panelCreate, Onenterable {

    private GridBagConstraints gbc;
    private JButton acceptButton, nextButton;
    private UpgradeStatPanel maxHpUpgradePanel;
    private UpgradeStatPanel baseAtkUpgradePanel;
    private Upgrade upgrade;
    private JLabel showKarma;
    private MainPanel main;

    public UpgradePanel(MainPanel main) {
        this.main = main;
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        gbc = new GridBagConstraints(); // attribute gbc
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridy = 0;
        gbc.gridx = 0;
        acceptButton = createButton("accept");
        nextButton = createButton("next");

        this.upgrade = new Upgrade(main, this); // attribute upgrade
        this.showKarma = createLabel("Karma : " + main.getKarma(), 36); // attribute panel

        this.maxHpUpgradePanel = new UpgradeStatPanel("MaxHp",2,upgrade,showKarma,"img/upgradePanel5.png"); // attribute maxHpPanel and damage
        this.baseAtkUpgradePanel = new UpgradeStatPanel("Damage",2,upgrade,showKarma,"img/upgradePanel2.png");

        JLabel label = createLabel("UPGRADES", 54);
        label.setFont(FontLoader.customFont);
        JPanel statGroup = new JPanel(new FlowLayout());
        statGroup.setBackground(Color.BLACK);

        JPanel acceptGroup = new JPanel(new FlowLayout(FlowLayout.RIGHT, 200, 0));
        acceptGroup.setOpaque(false);
        UpgradeUIManager.setButtonColor(acceptButton, Color.GREEN, Color.BLACK);
        UpgradeUIManager.setButtonColor(nextButton, Color.RED, Color.BLACK);
        statGroup.add(maxHpUpgradePanel);
        statGroup.add(baseAtkUpgradePanel);
        
        add(label, gbc);
        gbc.gridy++;
        add(showKarma, gbc);
        gbc.gridy++;
        add(statGroup, gbc);
        gbc.gridy++;
        acceptGroup.add(acceptButton);
        acceptGroup.add(nextButton);
        gbc.insets = new Insets(0, 320, 0, 10);
        add(acceptGroup, gbc);  

        this.acceptButton.addActionListener(e -> {
            this.upgrade.accept();
            main.showScene("GAME");
        });
        this.nextButton.addActionListener(e -> {
            this.upgrade.cancel();
            main.showScene("GAME");
        });

    }

    @Override
    public JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(100, 40));
        return button;
    }

    @Override
    public void onEnter(MainPanel main) {
        upgrade.setPlayer(main.getPlayer());
        upgrade.getCurrentStatus();
        upgrade.setPoints(main.getKarma());
        showKarma.setText("Karma: " + main.getKarma());
        maxHpUpgradePanel.updateValue();
        baseAtkUpgradePanel.updateValue();
    }

    @Override
    public JLabel createLabel(String name, int size) {
        JLabel label = new JLabel(name);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Serif", Font.BOLD, size));
        return  label;
    }

    public Upgrade getUpgrade() {
        return this.upgrade;
    }
}
