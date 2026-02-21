package UXUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class UIManager {

    private BufferedImage[] backgrounds;
    private int totalLevels = 5;
    private BufferedImage hpEnemyBar;
    private BufferedImage prof;
    private BufferedImage mpBar;
    private BufferedImage hpPlayerBar;

    private BufferedImage[] enemies;
    private int currentLevel = 1;

    public UIManager() {
        loadImages();
    }

    private void loadImages() {
        try {

            backgrounds = new BufferedImage[totalLevels];
            enemies = new BufferedImage[totalLevels];

            for (int i = 0; i < totalLevels; i++) {

                backgrounds[i] = ImageIO.read(
                        getClass().getResource("/img/bg" + (i + 1) + ".png"));

                enemies[i] = ImageIO.read(
                        getClass().getResource("/img/ch" + (i + 1) + ".png"));
            }

            hpEnemyBar = ImageIO.read(getClass().getResource("/img/hpBar.png"));
            prof = ImageIO.read(getClass().getResource("/img/prof.png"));
            mpBar = ImageIO.read(getClass().getResource("/img/ch_Manabar.png"));
            hpPlayerBar = ImageIO.read(getClass().getResource("/img/ch_HPbar.png"));

        } catch (Exception e) {
            System.out.println("โหลดรูปไม่สำเร็จ!");
            e.printStackTrace();
        }
    }

    // ให้ GamePanel เรียกเปลี่ยนด่าน
    public void setLevel(int level) {
        currentLevel = level;
    }

    // เรียกใช้ draw ทั้งหมด
    public void draw(Graphics2D g2, int w, int h) {

        BufferedImage currentEnemy = enemies[currentLevel - 1];

        drawBackGround(g2, w, h, currentLevel);
        drawEnemy(g2, w, h, currentEnemy);
        drawUI(g2, w, h);
    }

    // วาดพื้นหลัง (เปลี่ยนทุก 5 ด่าน)
    private void drawBackGround(Graphics2D g2, int w, int h, int stage) {

        // คำนวณ index ทุก 5 ด่านเปลี่ยนที
        int bgIndex = (stage - 1) / 5;

        // กัน index เกินจำนวนรูป
        bgIndex = bgIndex % totalLevels;

        BufferedImage bg = backgrounds[bgIndex];

        if (bg != null) {
            g2.drawImage(bg, 0, 0, w, h, null);
        }
    }

    // วาดศัตรู
    private void drawEnemy(Graphics2D g2, int w, int h, BufferedImage enemy) {
        if (enemy != null) {

            int enemyW = (int) (w * 0.40);
            int enemyH = (enemyW * enemy.getHeight()) / enemy.getWidth();
            int x = (w - enemyW) / 2;
            int y = (int) (h * 0.30);

            g2.drawImage(enemy, x, y, enemyW, enemyH, null);
        }
    }

    // วาด UI
    private void drawUI(Graphics2D g2, int w, int h) {

        if (prof != null) {
            int prW = (int) (w * 0.10);
            int prH = (prW * prof.getHeight()) / prof.getWidth();
            int prX = 20;
            int prY = h - prH - 20;
            g2.drawImage(prof, prX, prY, prW, prH, null);
        }

        if (hpEnemyBar != null) {
            int hpW = (int) (w * 0.40);
            int hpH = (hpW * hpEnemyBar.getHeight()) / hpEnemyBar.getWidth();
            int hpX = (w - hpW) / 2;
            int hpY = (int) (h * 0.02);
            g2.drawImage(hpEnemyBar, hpX, hpY, hpW, hpH, null);
        }

        if (mpBar != null) {
            int mnW = (int) (w * 0.30);
            int mnH = (mnW * mpBar.getHeight()) / mpBar.getWidth();
            int mnX = (int) (w * 0.10);
            int mnY = h - mnH - (int) (h * 0.06);
            g2.drawImage(mpBar, mnX, mnY, mnW, mnH, null);
        }

        if (hpPlayerBar != null) {
            int hpW = (int) (w * 0.30);
            int hpH = (hpW * hpPlayerBar.getHeight()) / hpPlayerBar.getWidth();
            int hpX = (int) (w * 0.10);
            int hpY = h - hpH - (int) (h * 0.012);
            g2.drawImage(hpPlayerBar, hpX, hpY, hpW, hpH, null);
        }
    }
}