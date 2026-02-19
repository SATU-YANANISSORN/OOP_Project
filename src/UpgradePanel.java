import javax.swing.JButton;
import javax.swing.JPanel;

public class UpgradePanel extends JPanel {
    private JButton upgradeBaseAtkIncreased;
    private JButton upgradeDefIncreased;
    private JButton upgradeMaxHpIncreased;
    private JButton upgradeBaseAtkDecrease;
    private JButton upgradeDefDecrease;
    private JButton upgradeMaxHpDecrease;
    private JButton acceptButton;
    private JButton nexButton;

    public UpgradePanel(MainPanel main) {
        upgradeMaxHpIncreased = new JButton("HP +");
        upgradeMaxHpIncreased.setPreferredSize(new Dimension(100, 40));
        upgradeMaxHpIncreased.setFocusable(false); 
        upgradeMaxHpIncreased.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        upgradeMaxHpIncreased.setMargin(new Insets(2, 2, 2, 2));
        upgradeMaxHpIncreased.addActionListener(e -> {
            // ใส่ Logic การอัปเกรด เช่น player.setMaxHp(player.getMaxHp() + 10);
            System.out.println("HP Increased!");
        });
    }
    
}
