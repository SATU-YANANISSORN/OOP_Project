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

public class UpgradeStatPanel extends JPanel{

    private GridBagConstraints gbc;
    private JButton upgradeDefIncreased, upgradeDefDecrease;
    private String name;

    public UpgradeStatPanel(MainPanel main,int right ,int left, String name) {
        // set layout
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, left, 10, right);
        gbc.gridy = 0;

        // set panel
        JPanel defGroup = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        defGroup.setPreferredSize(new Dimension(300, 400));
        defGroup.setBackground(Color.BLACK);
        
        JLabel label = new JLabel(name);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
    
        upgradeDefIncreased = new JButton("+");
        upgradeDefDecrease = new JButton("-");
        
        defGroup.add(upgradeDefIncreased);
        defGroup.add(upgradeDefDecrease);
        
        add(defGroup, gbc);
        gbc.gridy++;
        add(label,gbc);
    }

    
}
