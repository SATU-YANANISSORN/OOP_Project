import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class UpgradeUIManager {
    public static Image loadImage(String path) {
        try {
            // 1. ลองโหลดแบบ Relative Path
            File file = new File(path);

            if (file.exists()) {
                return ImageIO.read(file);
            } else {
                System.err.println("❌ File Not Found at: " + file.getAbsolutePath());
                
                // 2. ลองโหลดแบบ Resource (เผื่อไฟล์อยู่ใน Classpath/src)
                java.net.URL imgURL = UpgradeUIManager.class.getResource(path);
                if (imgURL != null) {
                    System.out.println("✅ Resource Found!");
                    return ImageIO.read(imgURL);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void makeButtonInvisible(JButton button, Color normalColor) {
        // 1. ลบพื้นหลังและขอบเดิมของปุ่ม
        button.setOpaque(false);
        button.setContentAreaFilled(false); // ปิดการวาดแผ่นสี่เหลี่ยมของปุ่ม
        button.setBorderPainted(false);      // ปิดเส้นขอบปุ่ม
        button.setFocusPainted(false);       // ปิดเส้นประเวลาปุ่มถูกเลือก
        
        // 2. ตั้งค่าลักษณะตัวอักษร
        button.setForeground(normalColor);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // เปลี่ยนเป็นรูปมือเมื่อชี้
        
        // 3. (Optional) เพิ่มเอฟเฟกต์ Hover เมื่อเอาเมาส์ไปชี้ให้เปลี่ยนสี
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setForeground(Color.WHITE); // สว่างขึ้นเมื่อชี้
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setForeground(normalColor); // กลับเป็นสีเดิม
            }
        });
    }

    public static void setButtonColor(JButton button, Color bgColor, Color textColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setContentAreaFilled(true); 
        button.setOpaque(true);
    }
}