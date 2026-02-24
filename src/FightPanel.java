
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

    private SoundManager sound;

    private Bar enemyHp;
    private Bar playerHp;
    private Bar playerMp;

    private Boolean isGamewin = false;
    private float fadeTime = 0.5f;
    private float curfadeTime = 0f;

    private MainPanel main;

    private FightUIManager uiManager;

    private List<MovingText> movingTexts = new ArrayList<>();

    private int shakeDuration = 0;
    private float shakeStrength = 0.5f;
    private Random random = new Random();

    private float dodgeDuration = 0.5f;
    private float dodgeDurationCount = 0f;//count var
    private float dodgeCd = 2f;
    private float dodgeCdCount = 0f;//count var

    private EnemyBehavivor enemyBehavivor;
    
    private float prepareDuration = 750f;

    public FightPanel(MainPanel main) {

        this.main = main;
        this.sound = main.getSoundManager();

        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

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

        enemyBehavivor = new EnemyBehavivor(this,main);

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
                int damage = player.untimate(enemy, combo.getValue());
                if(damage > 0){
                    movingTexts.add(new MovingText(String.valueOf(damage), getWidth()/2,getHeight()/2,Color.RED));
                    sound.playSFX("/Sound/Ulti_test.wav");
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "skill_left");
        actionMap.put("skill_left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int damage = player.thunder(enemy, combo.getValue());
                if(damage > 0){
                    movingTexts.add(new MovingText(String.valueOf(damage),getWidth()/2,getHeight()/2,Color.red));
                    sound.playSFX("/Sound/Light.wav");
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "skill_right");
        actionMap.put("skill_right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.getCurMp() >= 5){
                    combo.x2Value();
                    player.decreseCurMp(5);
                    sound.playSFX("/Sound/Buff.wav");
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "skill_down");
        actionMap.put("skill_down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int healNum = player.Heal();
                if(healNum > 0){
                    movingTexts.add(new MovingText("+"+String.valueOf(healNum),getWidth()/2 - 130,getHeight() - 50,Color.YELLOW));
                    sound.playSFX("/Sound/Heal.wav");
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "dodge");
        actionMap.put("dodge", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dodgeCdCount <= 0){
                    player.setDodge(true);
                    dodgeCdCount = dodgeCd*1000;
                    dodgeDurationCount = dodgeDuration * 1000;
                    sound.playSFX("/Sound/Doge.wav");
                }
            }
        });

    }

    private void checkChar(char keyChar) {
        String curW = curWord;
        if (keyChar == curW.charAt(0)) {
            curW = curW.substring(1, curW.length());

            if (curW.length() == 0) {
                sound.playSFX("/Sound/Hit.wav"); // เสียงผู้เล่นตีโดน
                movingTexts.add(new MovingText(String.valueOf(player.dealDamage(enemy)), getWidth() / 2, getHeight() / 2,Color.RED));
                combo.increase();
                randomNewWord();
            } else {
                curWord = curW;
            }
        } else {
            combo.reset();
        }
    }

    @Override
    public void update() {

        // long now = System.currentTimeMillis();

        if(dodgeCdCount > 0){
            dodgeCdCount -= 16;
        }

        if(dodgeDurationCount > 0){
            dodgeDurationCount -= 16;
        }
        else{
            player.setDodge(false);
        }

        if (!isGamewin) {
            if (enemy.getCurHp() <= 0) {
                curWord = " ";
                curfadeTime += 16;
                enemy.setDeath();
                if (curfadeTime >= fadeTime * 1000) {
                    isGamewin = true;
                }
            }
            if (player.getCurHp() <= 0) {
                main.gameOver();
            }

            enemyBehavivor.update();

        } else {
            main.win();
        }

        for (int i = 0; i < movingTexts.size(); i++) {
            MovingText dt = movingTexts.get(i);
            dt.update(0.016f);
            if (dt.isDead()) {
                movingTexts.remove(i);
            }
        }

        repaint();
    }

    public void shakeScreen(int duration) {
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
        
        // ===== Control Description =====
        Font descFont = new Font("Serif", Font.PLAIN, 22);
        g2.setFont(descFont);
        g2.setColor(Color.WHITE);

        int startX = 40;
        int startY = h / 2 - 100;

        for (int i = 0; i < controlText.length; i++) {
            g2.drawString(controlText[i], startX, startY + i * 28);
        }

        for (MovingText dt : movingTexts) {
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
        movingTexts.clear();
        shakeDuration = 0;

        enemyBehavivor.setUp(enemy,player);
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

    private final String[] controlText = {
        "UP    : Ultimate:deal a lot of damage",
        "LEFT  : Thunder:deal high damage with combo",
        "RIGHT : Buff:x2 Combo",
        "DOWN  : Heal:heal 20% max hp",
        "SPACE : Dodge",
        "ESC   : Pause"
    };

    public List<MovingText> getMovingTexts(){
        return movingTexts;
    }
}
