import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PausePanel extends JPanel {
    private JButton resumeBtn;
    private JButton quitBtn;

    public PausePanel(MainPanel main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 0, 0, 0)); // ดำโปร่ง

        resumeBtn = new JButton("Resume");
        quitBtn = new JButton("Quit");

        resumeBtn.addActionListener(e -> main.resumeGame());
        quitBtn.addActionListener(e -> main.showScene("TITLE"));

        Box box = Box.createVerticalBox();
        box.add(resumeBtn);
        box.add(Box.createVerticalStrut(10));
        box.add(quitBtn);

        add(box);

        getInputMap(WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("ESCAPE"),"pause");
        
        getActionMap().put("pause", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                main.resumeGame();
            }
        });
    }
}
