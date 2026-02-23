
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class MovingText {
    float x, y;
    float alpha = 1f;
    float life = 0f;
    float maxLife = 2.0f;
    String textToMoving;
    Color color;

    public MovingText(String textToMoving, float startX, float startY,Color color) {
        this.textToMoving = textToMoving;
        this.x = startX;
        this.y = startY;
        this.color = color;
    }

    public void update(float delta) {
        life += delta;

        if (life >= maxLife / 2 * 1000) {
            alpha = 1 - ((life - maxLife/2) / (maxLife/2));
        }

        x += 200 * delta;
        y += 400 * delta;
    }

    public boolean isDead() {
        return life >= maxLife;
    }

    public void draw(Graphics2D g2) {
        alpha = Math.max(0f, Math.min(1f, alpha));
        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, Math.max(0, alpha)));

        String text = String.valueOf(textToMoving);
        float newX = x+10;
        g2.setColor(color);
        g2.setFont(new Font("Arial", Font.BOLD, 60));
        g2.drawString(text, (int)newX, (int)y);

        g2.setComposite(AlphaComposite.SrcOver);
    }
}