import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel {

    private CardLayout layout = new CardLayout();
    private boolean paused = false;

    private Timer gameLoop;

    private int karma = 0;
    private Player player;

    private int stage = 1;

    private SoundManager sound = new SoundManager();

    private String prevScene;
    private String curScene;
    private Combo combo;

    private UpgradePanel upgradePanel;

    public MainPanel() {

        setLayout(layout);
        setPreferredSize(new Dimension(1280, 720));
        newPlayer();
        combo = new Combo();
        sound.playBGM("/Sound/Tt_ost.wav");

        add(new TitlePanel(this), "TITLE");
        add(new FightPanel(this), "GAME");
        add(new PausePanel(this), "PAUSE");
        add(new SettingsPanel(this), "SETTINGS");
        add(new UpgradePanel(this), "UPGRADES");
        add(new GameOverPanel(this), "GAMEOVER");
        add(new WinPanel(this),"WIN");

        gameLoop = new Timer(16,es -> update());
        gameLoop.start();

        showScene("TITLE");

    }

    public void newPlayer(){
        player = new Player(20,5,"Player",10);
    }

    private void update(){
        //if(paused) return;
        Component c = getCurScene();
        if(c instanceof Updateable u){
            u.update();
        }
    }

    private Component getCurScene(){
        for(Component c : getComponents()){
            if(c.isVisible()) return c;
        }
        return null;
    }

    public void showScene(String name) {
        prevScene = curScene;
        curScene = name;
        layout.show(this, name);
        Component c = getCurScene();
        if(c instanceof Onenterable u){
            u.onEnter(this);
        }
    }

    public void goBack() {
        if(prevScene != null){
            String tem = curScene;
            curScene = prevScene;
            prevScene = tem;
            layout.show(this, curScene);
        }
    }

    public void win(){
        showScene("WIN");
        karma += 1 + (stage/5);

    }

    public UpgradePanel getUpgradePanel(){
        return this.upgradePanel;
    }

    public void gameOver(){
        resetData();
        showScene("GAMEOVER");
    }

    public void resetData(){
        karma = 0;
        stage = 1;
        newPlayer();
        combo.reset();
        // System.out.println(karma + ":" +player);
    }

    public void pauseGame(){
        paused = true;
        layout.show(this,"PAUSE");
    } 

    public void resumeGame(){
        paused = false;
        layout.show(this, "GAME");
    }

    public int getKarma(){
        return karma;
    }

    public boolean decreseKarma(int decrese){
        if(decrese<=karma){
            karma -= decrese;
            return true;
        }

        return false;
    }

    // public void addKarama(int add){
    //     karma += add;
    // }

    public Player getPlayer(){
        return player;
    }    

    public int getStage(){
        return stage;
    }

    public void setStage(int stage){
        this.stage = stage;
    }

    public void nextStage(){
        stage++;
    }

    public Combo getCombo(){
        return combo;
    }
    

    public SoundManager getSoundManager() {
        return sound;
    }
    
    public void setMusicVolume(int volume){
    sound.setBGMVolume(volume);
    }

    public void setSFXVolume(int volume){
        sound.setSFXVolume(volume);
    }
}
