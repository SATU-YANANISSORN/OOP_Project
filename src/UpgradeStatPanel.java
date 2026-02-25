import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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
    private Image bgImage;

    public UpgradeStatPanel(String name, int amount, Upgrade upgrade , JLabel showKarma , String imagePath) {
        this.name = name;
        this.upgrade = upgrade;
        this.amount = amount;
        this.showKarma = showKarma;
        this.bgImage = UpgradeUIManager.loadImage(imagePath);
        setLayout(new GridBagLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(275, 400));
        this.gbc = new GridBagConstraints();

        // 2. แอดชื่อ Stat ลงใน "this" โดยตรง
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(FontLoader.customFont
                            .deriveFont(20f)
                            .deriveFont(Font.BOLD));
        nameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        // ปรับ Insets ตัวแรก (300) ให้ชื่อมาอยู่ในตำแหน่งที่ต้องการบนรูป
        gbc.insets = new Insets(250, 5, 15, 0); 
        this.add(nameLabel, gbc); // ใช้ this.add แทน panel.add

        // 3. ปุ่มเพิ่ม/ลด และตัวเลข แอดลงใน "this"
        gbc.gridwidth = 1;
        gbc.gridy = 1;

        upgradeIncreased = createButton("");
        gbc.gridx = 0; 
        gbc.insets = new Insets(0, 10, 40, 10);
        this.add(upgradeIncreased, gbc);

        showStat = createLabel(Integer.toString(upgrade.getStatus(name)), 18);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 40, 10);
        this.add(showStat, gbc);

        upgradeDecrease = createButton("");
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 10, 40, 0);
        this.add(upgradeDecrease, gbc);

        Color amberColor = new Color(255, 180, 50);
        UpgradeUIManager.makeButtonInvisible(upgradeIncreased, amberColor);
        UpgradeUIManager.makeButtonInvisible(upgradeDecrease, amberColor);

        this.upgradeDecrease.addActionListener(e -> {this.upgrade.UpgradeStatus(name, amount, false);updateValue();});
        this.upgradeIncreased.addActionListener(e ->{this.upgrade.UpgradeStatus(name, amount, true);updateValue();});

    }
    
    public Image getImagePanel(){
        return this.bgImage;
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
        label.setForeground(Color.WHITE);
        label.setFont(FontLoader.customFont
                        .deriveFont((float)size)
                        .deriveFont(Font.BOLD));
        return  label;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // ถ้าโหลดรูปไม่สำเร็จ ให้วาดสีแดงไว้ จะได้รู้ว่า Error ที่การวาดหรือการโหลด
            g.setColor(Color.RED);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
