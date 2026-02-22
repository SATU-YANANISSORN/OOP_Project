import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FightUIManager {

    private BufferedImage[] backgrounds;
    private int totalLevels = 5;
    private BufferedImage hpEnemyBarFrame;
    private BufferedImage prof;
    private BufferedImage mpPlayerBarFrame;
    private BufferedImage hpPlayerBarFrame;

    private Bar hpEnemyBar;
    private Bar hpPlayerBar;
    private Bar mpPlayerBar;

    private Enemy enemy;
    private Player player;

    public FightUIManager(FightPanel fightPanel) {
        loadImages();

        enemy = fightPanel.getEnemy();
        player = fightPanel.getPlayer();

        hpEnemyBar = new Bar(Color.RED);
        hpPlayerBar = new Bar(Color.RED);
        mpPlayerBar = new Bar(Color.blue);
    }

    private void loadImages() {
        try {

            backgrounds = new BufferedImage[totalLevels];

            for (int i = 0; i < totalLevels; i++) {

                backgrounds[i] = ImageIO.read(
                        getClass().getResource("/img/bg" + (i + 1) + ".png"));
            }

            hpEnemyBarFrame = ImageIO.read(getClass().getResource("/img/hpBar.png"));
            prof = ImageIO.read(getClass().getResource("/img/prof.png"));
            mpPlayerBarFrame = ImageIO.read(getClass().getResource("/img/ch_Manabar.png"));
            hpPlayerBarFrame = ImageIO.read(getClass().getResource("/img/ch_HPbar.png"));

        } catch (Exception e) {
            System.out.println("โหลดรูปไม่สำเร็จ!");
            e.printStackTrace();
        }
    }

    // เรียกใช้ draw ทั้งหมด
    public void draw(Graphics2D g2, int w, int h,int stage) {

        drawBackGround(g2, w, h, stage);
        drawEnemy(g2, w, h, enemy.getEnemyImage());
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
            int margin = 50;
            g2.drawImage(bg, -margin, -margin, w+margin*2, h+margin*2, null);
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

        if (hpEnemyBarFrame != null && enemy != null) {
            int hpW = (int) (w * 0.40);
            int hpH = (hpW * hpEnemyBarFrame.getHeight()) / hpEnemyBarFrame.getWidth();
            int hpX = (w - hpW) / 2;
            int hpY = (int) (h * 0.02);
            hpEnemyBar.draw(g2, hpX+25, hpY+6, hpW-50, hpH, enemy.getCurHp(), enemy.getMaxHp());
            g2.drawImage(hpEnemyBarFrame, hpX, hpY, hpW, hpH, null);
        }

        if(player != null){
            if (mpPlayerBarFrame != null) {
                int mnW = (int) (w * 0.30);
                int mnH = (mnW * mpPlayerBarFrame.getHeight()) / mpPlayerBarFrame.getWidth();
                int mnX = (int) (w * 0.10);
                int mnY = h - mnH - (int) (h * 0.06);
                mpPlayerBar.draw(g2, mnX, mnY, mnW, mnH, player.getCurMp(), player.getMaxMp());
                g2.drawImage(mpPlayerBarFrame, mnX, mnY, mnW, mnH, null);
            }

            if (hpPlayerBarFrame != null) {
                int hpW = (int) (w * 0.30);
                int hpH = (hpW * hpPlayerBarFrame.getHeight()) / hpPlayerBarFrame.getWidth();
                int hpX = (int) (w * 0.10);
                int hpY = h - hpH - (int) (h * 0.012);
                hpPlayerBar.draw(g2, hpX, hpY, hpW, hpH, player.getCurHp(), player.getMaxHp());
                g2.drawImage(hpPlayerBarFrame, hpX, hpY, hpW, hpH, null);
            }
        }

    }
}