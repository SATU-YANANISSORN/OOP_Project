import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class FightPanel extends JPanel implements Updateable {
    private JTextField curWord;
    private RandomWord randomWord;
    private JLabel comboLb;
    private int combo = 0;

    public FightPanel(MainPanel main) {
        setLayout(new FlowLayout());

        randomWord = new RandomWord();
        curWord = new JTextField("default");
            curWord.setPreferredSize(new Dimension(500,100));
            curWord.setEditable(false);
            curWord.setFont(new Font("Serif", Font.BOLD,72));
        add(curWord);

        comboLb = new JLabel("Combo: 0");
        comboLb.setFont(new Font("Serif", Font.BOLD, 72));
        add(comboLb);

        getInputMap(WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("ESCAPE"),"pause");
        
        getActionMap().put("pause", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                main.pauseGame();
            }
        });

        setKeyMap();
        randomNewWord();

    }

    private void randomNewWord(){
        curWord.setText(randomWord.randomWord());
    }

    private void setKeyMap(){
        InputMap inputMap = getInputMap(JRootPane.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();
        for(char c = 'a' ; c <= 'z'; c++){
            char keyChar = c;
            
            KeyStroke keyStroke = KeyStroke.getKeyStroke(keyChar);
            inputMap.put(keyStroke,"key_"+keyChar);

            actionMap.put("key_"+keyChar, new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e){
                    checkChar(keyChar);
                    // System.out.println(keyChar);
                }
            });
        }
    }

    private void checkChar(char keyChar){
        String curW = curWord.getText();
        if(keyChar == curW.charAt(0)){
            curW = curW.substring(1, curW.length());

            if(curW.length() == 0){
                randomNewWord();
                addComboLb();
            }
            else{
                curWord.setText(curW);
            }
        }
        else{
            resetComboLb();
        }
    }

    private void addComboLb(){
        int curCombo = Integer.parseInt(comboLb.getText().split(" ")[1]);
        comboLb.setText("Streak: "+String.valueOf(curCombo+1));
    }

    private void resetComboLb(){
        comboLb.setText("Combo: 0");
    }

    @Override
    public void update(){
       
    }

    public int getCombo(){
        return combo;
    }

}
