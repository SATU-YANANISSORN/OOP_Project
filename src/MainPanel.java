import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel {

    private CardLayout layout = new CardLayout();
    private boolean paused = false;

    private Timer gameLoop;

    private int karma = 0;
    private Player player;

    private int stage = 1;

    private String prevScene;
    private String curScene;

    public MainPanel() {

        setLayout(layout);
        setPreferredSize(new Dimension(1280, 720));

        add(new TitlePanel(this), "TITLE");
        add(new FightPanel(this), "GAME");
        add(new PausePanel(this), "PAUSE");
        add(new SettingsPanel(this), "SETTINGS");
        // add(new UpgradePanel(this), "UPGRADES");
        add(new GameOverPanel(this), "GAMEOVER");
        add(new WinPanel(this),"WIN");

        gameLoop = new Timer(16,e -> update());
        gameLoop.start();

        showScene("TITLE");

        newPlayer();
    }

    public void newPlayer(){
        player = new Player(100,20,"Player",10);
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
    }

    public void gameOver(){
        showScene("GAMEOVER");
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

    public void addKarama(int add){
        karma += add;
    }

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


}

