
import java.awt.*;
import javax.swing.*;

public class WinPanel extends JPanel implements Onenterable {

    private float alpha = 0f;
    private Timer fadeTimer;
    private MainPanel main;

    public WinPanel(MainPanel main) {
        this.main = main;
        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    int stage = main.getStage();
                    main.nextStage();
                    if (stage % 5 == 0) {
                        main.showScene("UPGRADES");
                    } else {
                        main.showScene("GAME");
                    }
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

        int w = getWidth();
        int h = getHeight();

        g2.setFont(new Font("Serif", Font.BOLD, 90));

        String text = "VICTORY ACHIEVED";
        FontMetrics fm = g2.getFontMetrics();
        int x = (w - fm.stringWidth(text)) / 2;
        int y = h / 2;

        // เงา
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 4, y + 4);

        // สีทอง
        g2.setColor(new Color(212, 175, 55));
        g2.drawString(text, x, y);

        // แสดงเลขด่าน
        g2.setFont(new Font("Serif", Font.PLAIN, 30));
        fm = g2.getFontMetrics();
        g2.setColor(Color.GRAY);
        String stageText = "Stage " + main.getStage();
        g2.drawString(stageText,
                (w - fm.stringWidth(stageText)) / 2,
                y - 100);

        g2.setColor(Color.GRAY);
        String desText = "Press ENTER to go next stage";
        g2.drawString(desText,
                (w - fm.stringWidth(desText)) / 2,
                h / 2 +60);
    }

    @Override
    public void onEnter(MainPanel main) {
        alpha = 0f;
        fadeTimer.start();
        requestFocusInWindow();
    }
}
