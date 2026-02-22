
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class FightPanel extends JPanel implements Updateable, Onenterable {

    private String curWord = "default";
    private RandomWord randomWord;
    private Combo combo;
    private StageManager stageManager;
    private Enemy enemy;
    private Player player;
    private int stage;

    private Bar enemyHp;
    private Bar playerHp;
    private Bar playerMp;

    private Boolean isGamewin = false;
    private float fadeTime = 0.5f;
    private float curfadeTime = 0f;

    private MainPanel main;

    private FightUIManager uiManager;

    private List<DamageText> damageTexts = new ArrayList<>();

    private int shakeDuration = 0;
    private float shakeStrength = 0.5f;
    private Random random = new Random();

    public FightPanel(MainPanel main) {

        this.main = main;

        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        stageManager = new StageManager();
        combo = new Combo();
        randomWord = new RandomWord();

        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ESCAPE"), "pause");

        getActionMap().put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.pauseGame();
            }
        });

        setKeyMap();
    }

    private void randomNewWord() {
        curWord = randomWord.randomWord();
    }

    private void setKeyMap() {
        InputMap inputMap = getInputMap(JRootPane.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();
        for (char c = 'a'; c <= 'z'; c++) {
            char keyChar = c;

            KeyStroke keyStroke = KeyStroke.getKeyStroke(keyChar);
            inputMap.put(keyStroke, "key_" + keyChar);

            actionMap.put("key_" + keyChar, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkChar(keyChar);
                }
            });
        }

        inputMap.put(KeyStroke.getKeyStroke("UP"), "skill_up");
        actionMap.put("skill_up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.untimate(enemy, combo.getValue());
                System.out.println("ULTIMATE");
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "skill_left");
        actionMap.put("skill_left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.thunder(enemy, combo.getValue());
                System.out.println("Thunderrrr");
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "skill_down");
        actionMap.put("skill_down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.Heal();
                System.out.println("HEAL");
            }
        });

    }

    private void checkChar(char keyChar) {
        String curW = curWord;
        if (keyChar == curW.charAt(0)) {
            curW = curW.substring(1, curW.length());

            if (curW.length() == 0) {
                damageTexts.add(new DamageText(player.dealDamage(enemy), getWidth() / 2, getHeight() / 2));
                combo.increase();
                randomNewWord();
            } else {
                curWord = curW;
            }
        } else {
            combo.reset();
        }
    }

    boolean preparingAttack = false;
    long prepareStart = 0;

    @Override
    public void update() {

        long now = System.currentTimeMillis();

        if (!isGamewin) {
            if (enemy.getCurHp() <= 0) {
                curWord = " ";
                curfadeTime += 16;
                if (curfadeTime >= fadeTime * 1000) {
                    isGamewin = true;
                }
            }
            if (player.getCurHp() <= 0) {
                main.gameOver();
            }

            if (!preparingAttack) {
                enemy.curCdAttack += 16;
                if (enemy.curCdAttack >= enemy.cdAttack * 1000) {
                    System.out.println("prepare");
                    preparingAttack = true;
                    prepareStart = now;
                }
            } else {
                if (now - prepareStart >= 1000) {
                    enemy.dealDamage(player);
                    shakeScreen(1);
                    System.out.println("Attack");
                    enemy.curCdAttack = 0;
                    preparingAttack = false;
                }
            }

        } else {
            main.win();
        }

        for (int i = 0; i < damageTexts.size(); i++) {
            DamageText dt = damageTexts.get(i);
            dt.update(0.016f);
            if (dt.isDead()) {
                damageTexts.remove(i);
            }
        }

        repaint();
    }

    private void shakeScreen(int duration) {
        shakeDuration = duration * 1000;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        if (shakeDuration > 0) {
            float progress = shakeDuration / 20f;
            int currentStrength = (int) Math.max(1,Math.abs(shakeStrength * progress));

            int offsetX = random.nextInt(currentStrength * 2) - currentStrength;
            int offsetY = random.nextInt(currentStrength * 2) - currentStrength;

            g2.translate(offsetX, offsetY);
            shakeDuration -= 16;
        }


        int w = getWidth();
        int h = getHeight();

        uiManager.draw(g2, w, h, stage);

        g2.setColor(Color.white);
        //curWord
        String word = curWord;
        Font fontBig = new Font("Serif", Font.BOLD, 80);
        g2.setFont(fontBig);

        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(word);
        int textHeight = fm.getAscent();

        int x = (w - textWidth) / 2;
        int y = (h + textHeight) / 2 - 225;

        g2.drawString(word, x, y);
        //

        Font fontSmall = new Font("Serif", Font.BOLD, 40);

        //combo
        String comboText = "Combo: " + Integer.toString(combo.getValue());
        g2.setFont(fontSmall);

        FontMetrics fm2 = g2.getFontMetrics();
        int comboWidth = fm2.stringWidth(comboText);

        int comboX = w - comboWidth - 20;
        int comboY = fm2.getAscent() + 20;

        g2.drawString(comboText, comboX, comboY);

        //stage
        String stageText = "Stage: " + Integer.toString(stage);
        int stageWidth = fm2.stringWidth(stageText);
        int stageX = 20;
        int stageY = fm2.getAscent() + 20;

        g2.drawString(stageText, stageX, stageY);

        for (DamageText dt : damageTexts) {
            dt.draw(g2);
        }
    }

    @Override
    public void onEnter(MainPanel main) {
        stage = main.getStage();
        enemy = stageManager.selectEnemy(stage);
        player = main.getPlayer();
        uiManager = new FightUIManager(this);
        curfadeTime = fadeTime;
        isGamewin = false;
        randomNewWord();
        damageTexts.clear();
        shakeDuration = 0;

        preparingAttack = false;
    }

    public Combo getCombo() {
        return combo;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Player getPlayer() {
        return player;
    }
}
