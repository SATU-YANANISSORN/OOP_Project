import java.awt.*;
import java.awt.image.BufferedImage;

public class Bar {
    private BufferedImage frame;
    private Color color;

    public Bar(Color color) {
        this.color = color;
    }

    public void draw(Graphics2D g2,int x, int y, float w,int h, int currentValue, int maxValue) {
        // คำนวณเปอร์เซ็นต์
        double percent = (double) currentValue / maxValue;
        
        int paddingX = (int)(w * 0.095);
        int paddingY = (int)(h * 0.30);

        int innerW = (int)(w * 0.81);
        int innerH = (int)(h * 0.38);

        int fillW = (int)(innerW * percent);

        g2.setColor(color);
        g2.fillRect(
            x + paddingX,
            y + paddingY,
            fillW,
            innerH
        );
    }
}