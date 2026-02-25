import java.awt.*;
import javax.swing.*;

public class GameOverPanel extends JPanel implements Updateable, Onenterable {

    private float alpha = 0f;
    private Timer fadeTimer;
    private MainPanel main;

    public GameOverPanel(MainPanel main) {
        JButton btn = new JButton();

        this.main = main;
        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    main.showScene("TITLE");
                }
            }
        });

        fadeTimer = new Timer(30, e -> {
            if (alpha < 1f) {
                alpha += 0.02f;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        alpha = Math.max(0f, Math.min(1f, alpha));
        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha));

        g2.setFont(FontLoader.customFont
                    .deriveFont(120f)
                    .deriveFont(Font.BOLD));
        g2.setColor(new Color(139, 0, 0)); // แดงเข้ม

        String text = "YOU DIED";

        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = getHeight() / 2;

        g2.drawString(text, x, y);

        // ข้อความเล็กด้านล่าง
        g2.setFont(FontLoader.customFont
                    .deriveFont(30f)
                    .deriveFont(Font.PLAIN));
        fm = g2.getFontMetrics();
        g2.setColor(Color.GRAY);
        text = "Press ENTER to return";
        g2.drawString(text,
                (getWidth() - fm.stringWidth(text)) / 2,
                getHeight() / 2 + 80);
    }

    @Override
    public void onEnter(MainPanel main) {
        alpha = 0f;
        fadeTimer.start();
        requestFocusInWindow();
    }

    @Override
    public void update() {
        // ไม่ต้องอัพเดตอะไร
    }
}