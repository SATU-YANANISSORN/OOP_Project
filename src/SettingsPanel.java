import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SettingsPanel extends JPanel {

    private MainPanel main;

    private String[] options = {
        "Music Volume",
        "Sound Effects",
        "Back"
    };

    private int selected = 0;
    private int musicVolume = 50;
    private int sfxVolume = 50;

    public SettingsPanel(MainPanel main) {
        this.main = main;

        setOpaque(false);
        setFocusable(true);

        // ===== Key Bindings =====
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("UP"), "up");
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("DOWN"), "down");
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("LEFT"), "left");
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("RIGHT"), "right");
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ENTER"), "select");
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ESCAPE"), "back");

        getActionMap().put("up", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                selected = (selected - 1 + options.length) % options.length;
                repaint();
            }
        });

        getActionMap().put("down", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                selected = (selected + 1) % options.length;
                repaint();
            }
        });

        getActionMap().put("left", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                adjust(-5);
            }
        });

        getActionMap().put("right", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                adjust(5);
            }
        });

        getActionMap().put("select", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                execute();
            }
        });

        getActionMap().put("back", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                main.goBack();
            }
        });
    }

    private void adjust(int value) {
        if (selected == 0) {
            musicVolume = Math.max(0, Math.min(100, musicVolume + value));
            main.setMusicVolume(musicVolume);
        } else if (selected == 1) {
            sfxVolume = Math.max(0, Math.min(100, sfxVolume + value));
            main.setSFXVolume(sfxVolume);
        }
        repaint();
    }

    private void execute() {
        if (selected == 2) {
            main.goBack();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Overlay ดำโปร่ง
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Title
        g2.setFont(new Font("Serif", Font.BOLD, 70));
        g2.setColor(new Color(180, 0, 0));
        drawCentered(g2, "SETTINGS", getHeight() / 4);

        g2.setFont(new Font("Serif", Font.PLAIN, 36));

        for (int i = 0; i < options.length; i++) {

            if (i == selected) {
                g2.setColor(new Color(230, 230, 230));
            } else {
                g2.setColor(new Color(120, 120, 120));
            }

            String text = options[i];

            if (i == 0) text += " : " + musicVolume;
            if (i == 1) text += " : " + sfxVolume;

            drawCentered(g2, text, getHeight() / 2 + i * 60);
        }
    }

    private void drawCentered(Graphics2D g2, String text, int y) {
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        g2.drawString(text, x, y);
    }
}