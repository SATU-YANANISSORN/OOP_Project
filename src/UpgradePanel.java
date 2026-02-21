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

public class UpgradePanel extends JPanel implements panelCreate {

    private GridBagConstraints gbc;
    private JButton acceptButton,nextButton;
    private UpgradeStatPanel maxHpUpgradePanel;
    private UpgradeStatPanel baseAtkUpgradePanel;
    private Upgrade upgrade;
    private JLabel showKarma;

    public UpgradePanel(MainPanel main) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        gbc = new GridBagConstraints(); // attribute gbc
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 0;
        this.upgrade = new Upgrade(main.getKarma(), main.getPlayer(), main); // attribute upgrade

        acceptButton = createButton("accept");
        nextButton = createButton("next");
        this.showKarma = createLabel("Karma : " + main.getKarma(),36); // attribute panel
   
        this.maxHpUpgradePanel = new UpgradeStatPanel("MaxHp",15,upgrade,showKarma); // attribute maxHpPanel and damage
        this.baseAtkUpgradePanel = new UpgradeStatPanel("Damage",35,upgrade,showKarma);

        JLabel label = createLabel("UPGRADES", 54);
        JPanel statGroup = new JPanel(new FlowLayout());
        statGroup.setBackground(Color.BLACK);
        
        JPanel acceptGroup = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        acceptGroup.setOpaque(false);

        statGroup.add(maxHpUpgradePanel);
        statGroup.add(baseAtkUpgradePanel);
        add(label, gbc);
        gbc.gridy++;
        add(showKarma,gbc);
        gbc.gridy++;
        add(statGroup, gbc);
        gbc.gridy++;
        acceptGroup.add(acceptButton);
        acceptGroup.add(nextButton);
        add(acceptGroup,gbc);

        this.acceptButton.addActionListener(e -> {this.upgrade.accept(); main.showScene("GAME");});
        this.nextButton.addActionListener(e -> {this.upgrade.cancel(); main.showScene("GAME");});

    }
    @Override
    public JButton createButton(String name){
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(100 , 40));
        return button;
    }
    @Override
    public JLabel createLabel(String name,int size){
        JLabel label = new JLabel(name);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, size));
        return  label;
    }

}
