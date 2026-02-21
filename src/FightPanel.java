
import java.awt.*;
import java.awt.event.ActionEvent;
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

    private  MainPanel main;

    private UIManager uiManager;

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
                    repaint();
                }
            });
        }
    }

    private void checkChar(char keyChar) {
        String curW = curWord;
        if (keyChar == curW.charAt(0)) {
            curW = curW.substring(1, curW.length());

            if (curW.length() == 0) {
                player.dealDamage(enemy);
                randomNewWord();
                combo.increase();
                repaint();
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

        if(!preparingAttack){
            enemy.curCdAttack += 16;
            if (enemy.curCdAttack >= enemy.cdAttack * 1000) {
                enemy.dealDamage(player);
                System.out.println("prepare");
                preparingAttack = true;
                prepareStart = now;
                repaint();
            }
        }
        else{
            if(now - prepareStart >= 1000){
                System.out.println("Attack");
                enemy.curCdAttack = 0;
                preparingAttack = false;
            }
        }

        if(enemy.getCurHp() <= 0){
            main.win();
        }
        if(player.getCurHp() <= 0){
            main.gameOver();
        }

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        
        int w = getWidth();
        int h = getHeight();

        uiManager.draw(g2, w, h,stage);

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
        String comboText = "Combo: "+Integer.toString(combo.getValue());
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

        g2.drawString(stageText, stageX,stageY);
    }

    @Override
    public void onEnter(MainPanel main) {
        stage = main.getStage();
        enemy = stageManager.selectEnemy(stage);
        player = main.getPlayer();
        uiManager = new UIManager(this);
        repaint();
        randomNewWord();
    }

    public Combo getCombo() {
        return combo;
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public Player getPlayer(){
        return player;
    }
}
