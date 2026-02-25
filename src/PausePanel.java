import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PausePanel extends JPanel {

    private MainPanel main;

    private String[] options = {"RESUME","SETTINGS","QUIT"};
    private int selected = 0;

    public PausePanel(MainPanel main) {
        this.main = main;

        setOpaque(false);
        setFocusable(true);

        // ===== Keyboard Control =====
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("UP"), "up");
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("DOWN"), "down");
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ENTER"), "select");
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ESCAPE"), "resume");

        getActionMap().put("up", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                selected--;
                if (selected < 0) selected = options.length - 1;
                repaint();
            }
        });

        getActionMap().put("down", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                selected++;
                if (selected >= options.length) selected = 0;
                repaint();
            }
        });

        getActionMap().put("select", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                execute();
            }
        });

        getActionMap().put("resume", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                main.resumeGame();
            }
        });
    }

    private void execute() {
        if (selected == 0) {
            main.resumeGame();
        } else if(selected == options.length - 1){
            main.showScene("TITLE");
            main.resetData();
        }else{
            main.showScene(options[selected]);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(0 ,0 ,0));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // ===== Title =====
        g2.setFont(FontLoader.customFont
                    .deriveFont(80f)
                    .deriveFont(Font.BOLD));
        g2.setColor(new Color(180, 0, 0));
        drawCentered(g2, "PAUSED", getHeight() / 3);

        // ===== Menu Options =====
        g2.setFont(FontLoader.customFont
                    .deriveFont(40f)
                    .deriveFont(Font.PLAIN));

        for (int i = 0; i < options.length; i++) {

            if (i == selected) {
                g2.setColor(new Color(200, 200, 200));
            } else {
                g2.setColor(new Color(120, 120, 120));
            }

            drawCentered(g2, options[i], getHeight() / 2 + i * 60);
        }

        // ===== Control Description =====

        int startX = 20;
        int startY = getHeight() / 2 - 100;

        Font descFont = FontLoader.customFont
                        .deriveFont(22f)
                        .deriveFont(Font.BOLD);
        g2.setFont(descFont);
        g2.setColor(Color.WHITE);
        g2.drawString("Description", startX, startY - 30);

        descFont = FontLoader.customFont
                    .deriveFont(26f)
                    .deriveFont(Font.PLAIN);
        g2.setFont(descFont);

        for (int i = 0; i < controlText.length; i++) {
            g2.drawString(controlText[i], startX, startY + i * 28);
        }
    }

    private void drawCentered(Graphics2D g2, String text, int y) {
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        g2.drawString(text, x, y);
    }

    private final String[] controlText = {
        "UP    : Ultimate:deal a lot of damage",
        "LEFT  : Thunder:deal high damage with combo",
        "RIGHT : Buff:x2 Combo",
        "DOWN  : Heal:heal 20% max hp",
        "SPACE : Dodge",
        "ESC   : Pause"
    };
}