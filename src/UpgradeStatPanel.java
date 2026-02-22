import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UpgradeStatPanel extends JPanel implements panelCreate{

    private GridBagConstraints gbc;
    private JButton upgradeIncreased, upgradeDecrease;
    private JLabel showStat,showKarma;
    private String name;
    private Upgrade upgrade;
    private int amount;
    

    public UpgradeStatPanel(String name, int amount, Upgrade upgrade , JLabel showKarma) {
        this.name = name;
        this.upgrade = upgrade;
        this.amount = amount;
        this.showKarma = showKarma;
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        this.gbc = new GridBagConstraints();
        panel.setPreferredSize(new Dimension(200,400));
        
        //statPanel name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // use 3 column
        gbc.insets = new Insets(300, 0, 20, 0); // margin
        panel.add(nameLabel, gbc);

        // รีเซ็ต gridwidth กลับเป็น 1 สำหรับแถวถัดไป
        gbc.gridwidth = 1;
        gbc.gridy = 1; // แถวที่ 2 คือแถวที่มี ปุ่ม-เลข-ปุ่ม

        // increasedbutton
        upgradeIncreased = createButton("+");
        gbc.gridx = 0; 
        gbc.insets = new Insets(0, 0, 0, 10); // เว้นห่างจากตัวเลขทางขวา 10px
        panel.add(upgradeIncreased, gbc);

        // showStat
        showStat = createLabel(Integer.toString(upgrade.getStatus(name)), 18);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 0, 10); // เว้นห่างซ้ายและขวาอย่างละ 10px
        panel.add(showStat, gbc);

        // decreasebutton
        upgradeDecrease = createButton("-");
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 10, 0, 10); // เว้นห่างจากตัวเลขทางซ้าย 10px
        panel.add(upgradeDecrease, gbc);
        // panel
        add(panel,gbc);
        //respond
        this.upgradeDecrease.addActionListener(e -> {this.upgrade.UpgradeStatus(name, amount, false);updateValue();});
        this.upgradeIncreased.addActionListener(e ->{this.upgrade.UpgradeStatus(name, amount, true);updateValue();});

    }
    
    public void updateValue(){
        showStat.setText(Integer.toString(upgrade.getStatus(this.name)));
        this.showKarma.setText("Karma: " + upgrade.getDifUseAndPoints());
    }
    @Override
    public JButton createButton(String name ){
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(50 , 50));
        return button;
    }
    @Override
    public JLabel createLabel(String name,int size){
        JLabel label = new JLabel(name);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, size));
        return  label;
    }
}
