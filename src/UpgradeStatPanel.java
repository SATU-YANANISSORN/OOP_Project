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

public class UpgradeStatPanel extends JPanel implements Updateable{

    private GridBagConstraints gbc;
    private JButton upgradeIncreased, upgradeDecrease;
    private JLabel showStat,showKarma;
    private String name;
    private Upgrade upgrade;
    private Player player;
    private int amount;
    private MainPanel main;

    public UpgradeStatPanel(MainPanel main,int right ,int left, String name, int amount, Upgrade upgrade , JLabel showKarma) {
        this.main = main;
        this.name = name;
        this.player = main.getPlayer();
        this.upgrade = upgrade;
        this.amount = amount;
        this.showKarma = showKarma;
        // set layout
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, left, 10, right);
        gbc.gridy = 0;

        // set panel
        JPanel GroupButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        GroupButton.setPreferredSize(new Dimension(300, 400));
        GroupButton.setBackground(Color.BLACK);
        
        JLabel nameStatLabel = new JLabel(name);
        nameStatLabel.setForeground(Color.WHITE);
        nameStatLabel.setFont(new Font("Arial", Font.BOLD, 18));

        showStat = new JLabel(Integer.toString(upgrade.getStatus(name)));
        showStat.setForeground(Color.WHITE);
        showStat.setFont(new Font("Arial", Font.BOLD, 18));
    
        upgradeIncreased = new JButton("+");
        upgradeDecrease = new JButton("-");
        
        GroupButton.add(upgradeIncreased);
        GroupButton.add(upgradeDecrease);
        
        add(this.showStat,gbc);
        gbc.gridy++;
        add(GroupButton, gbc);
        gbc.gridy++;
        add(nameStatLabel,gbc);

        this.upgradeDecrease.addActionListener(e -> {this.upgrade.UpgradeStatus(name, amount, false);update();});
        this.upgradeIncreased.addActionListener(e ->{this.upgrade.UpgradeStatus(name, amount, true);update();});
    }
    
    @Override
    public void update(){
        showStat.setText(Integer.toString(upgrade.getStatus(name)));
        this.showKarma.setText("Karma: " + upgrade.getDifUseAndPoints());
    }
}
