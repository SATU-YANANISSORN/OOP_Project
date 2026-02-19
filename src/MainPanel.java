import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel {

    private CardLayout layout = new CardLayout();
    private boolean paused = false;

    private Timer gameLoop;

    private int karma = 0;
    private Player player;

    public MainPanel() {


        setLayout(layout);
        setPreferredSize(new Dimension(800, 600));

        add(new TitlePanel(this), "TITLE");
        add(new FightPanel(this), "GAME");
        add(new PausePanel(this), "PAUSE");
        add(new SettingsPanel(this), "SETTINGS");


        gameLoop = new Timer(16,e -> update());
        gameLoop.start();

        layout.show(this, "TITLE");
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
        layout.show(this, name);
        Component c = getCurScene();
        if(c instanceof Onenterable u){
            u.onEnter(this);
        }
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


}

