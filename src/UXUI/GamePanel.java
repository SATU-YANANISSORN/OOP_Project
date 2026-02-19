
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel {

    // ===== Level System =====
    private BufferedImage[] backgrounds;
    private BufferedImage[] enemies;
    private int currentLevel = 1;
    private final int TOTAL_LEVELS = 5;

    // ===== UI Images =====
    private BufferedImage hpB;
    private BufferedImage prof;
    private BufferedImage mn;
    private BufferedImage hpC;

    private GameWindow window;

    // =========================
    // Constructor
    // =========================
    public GamePanel(GameWindow window) {
        this.window = window;
        this.setLayout(null);

        loadImages();
        createButtons();
    }

    // =========================
    // โหลดรูปทั้งหมด
    // =========================
    private void loadImages() {
        try {
            backgrounds = new BufferedImage[TOTAL_LEVELS];
            enemies = new BufferedImage[TOTAL_LEVELS];

            for (int i = 0; i < TOTAL_LEVELS; i++) {
                backgrounds[i] = ImageIO.read(
                        getClass().getResource("/img/bg" + (i + 1) + ".png"));

                enemies[i] = ImageIO.read(
                        getClass().getResource("/img/ch" + (i + 1) + ".png"));
            }

            hpB = ImageIO.read(getClass().getResource("/img/hpBar.png"));
            prof = ImageIO.read(getClass().getResource("/img/prof.png"));
            mn = ImageIO.read(getClass().getResource("/img/ch_Manabar.png"));
            hpC = ImageIO.read(getClass().getResource("/img/ch_HPbar.png"));

        } catch (Exception e) {
            System.out.println("โหลดรูปไม่สำเร็จ!");
            e.printStackTrace();
        }
    }

    // =========================
    // สร้างปุ่ม
    // =========================
    private void createButtons() {

        JButton backBtn = new JButton("Back to Menu");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 10));
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBounds(20, 20, 120, 25);
        backBtn.addActionListener(e -> window.showView("Menu"));
        this.add(backBtn);

        JButton nextBtn = new JButton("Next");
        nextBtn.setBounds(160, 20, 120, 25);
        nextBtn.addActionListener(e -> nextLevel());
        this.add(nextBtn);
    }

    // =========================
    // เปลี่ยนด่าน
    // =========================
    private void nextLevel() {
        currentLevel++;
        if (currentLevel > TOTAL_LEVELS) {
            currentLevel = 1;
        }
        repaint();
    }

    // =========================
    // วาดหน้าจอ
    // =========================
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int w = getWidth();
        int h = getHeight();

        BufferedImage currentBg = backgrounds[currentLevel - 1];
        BufferedImage currentEnemy = enemies[currentLevel - 1];

        drawUI(g2, w, h);
        drawBackground(g2, currentBg, w, h);
        drawEnemy(g2, currentEnemy, w, h);
    }

    // =========================
    // วาดพื้นหลัง
    // =========================
    private void drawBackground(Graphics2D g2, BufferedImage bg, int w, int h) {
        if (bg != null) {
            g2.drawImage(bg, 0, 0, w, h, null);
        }
    }

    // =========================
    // วาด UI ทั้งหมด
    // =========================
    private void drawUI(Graphics2D g2, int w, int h) {

        // โปรไฟล์
        if (prof != null) {
            int prW = (int) (w * 0.10);
            int prH = (prW * prof.getHeight()) / prof.getWidth();
            int prX = 20;
            int prY = h - prH - 20;
            g2.drawImage(prof, prX, prY, prW, prH, null);
        }

        // หลอดเลือดบอส
        if (hpB != null) {
            int hpW = (int) (w * 0.40);
            int hpH = (hpW * hpB.getHeight()) / hpB.getWidth();
            int hpX = (w - hpW) / 2;
            int hpY = (int) (h * 0.02);
            g2.drawImage(hpB, hpX, hpY, hpW, hpH, null);
        }

        // มานา
        if (mn != null) {
            int mnW = (int) (w * 0.30);
            int mnH = (mnW * mn.getHeight()) / mn.getWidth();
            int mnX = (int) (w * 0.10);
            int mnY = h - mnH - (int) (h * 0.06);
            g2.drawImage(mn, mnX, mnY, mnW, mnH, null);
        }

        // เลือดตัวละคร
        if (hpC != null) {
            int hpW = (int) (w * 0.30);
            int hpH = (hpW * hpC.getHeight()) / hpC.getWidth();
            int hpX = (int) (w * 0.10);
            int hpY = h - hpH - (int) (h * 0.012);
            g2.drawImage(hpC, hpX, hpY, hpW, hpH, null);
        }
    }

    // =========================
    // วาดศัตรู
    // =========================
    private void drawEnemy(Graphics2D g2, BufferedImage enemy, int w, int h) {
        if (enemy != null) {
            int enemyW = (int) (w * 0.40);
            int enemyH = (enemyW * enemy.getHeight()) / enemy.getWidth();
            int x = (w - enemyW) / 2;
            int y = (int) (h * 0.30);
            g2.drawImage(enemy, x, y, enemyW, enemyH, null);
        }
    }
}