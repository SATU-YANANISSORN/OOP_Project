package UXUI;

import java.awt.*;

public class Bar {

    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Bar(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // =========================
    // set size
    // =========================
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // =========================
    // set position
    // =========================
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // =========================
    // วาดหลอด
    // =========================
    public void draw(Graphics2D g2, int currentValue, int maxValue) {

        // คำนวณเปอร์เซ็นต์
        double percent = (double) currentValue / maxValue;
        int fillWidth = (int) (width * percent);

        // วาดด้านใน
        g2.setColor(color);
        g2.fillRect(x, y, fillWidth, height);

        // วาดกรอบ
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, width, height);
    }
}