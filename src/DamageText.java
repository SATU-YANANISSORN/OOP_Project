
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class DamageText {
    float x, y;
    float alpha = 1f;        // เริ่มโปร่งใส
    float life = 0f;         // เวลาที่ผ่านไป
    float maxLife = 2.0f;    // อายุ 1 วินาที
    int damage;

    public DamageText(int damage, float startX, float startY) {
        this.damage = damage;
        this.x = startX;
        this.y = startY;
    }

    public void update(float delta) {
        life += delta;

        if (life < maxLife / 2 * 1000) {
        } 
        // Fade out ครึ่งหลัง
        else {
            alpha = 1 - ((life - maxLife/2) / (maxLife/2));
        }

        // เคลื่อนลงขวาล่าง
        x += 200 * delta;
        y += 500 * delta;
    }

    public boolean isDead() {
        return life >= maxLife;
    }

    public void draw(Graphics2D g2) {
        alpha = Math.max(0f, Math.min(1f, alpha));
        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, Math.max(0, alpha)));

        String text = String.valueOf(damage);
        float newX = x+10;
        g2.setColor(Color.red);
        g2.setFont(new Font("Arial", Font.BOLD, 60));
        g2.drawString(text, (int)newX, (int)y);

        g2.setComposite(AlphaComposite.SrcOver); // reset
    }
}