import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


public class TitlePanel extends JPanel {
    public JButton btnStart, btnSetting, btnExit, btnHW;
    private BufferedImage BGimg,Logo;
    private GameWindow window;

    public TitlePanel(GameWindow window) {
        this.window = window; 
        this.setLayout(null);

        try {
            // อ่านไฟล์จากโฟลเดอร์ res
            BGimg = ImageIO.read(new File("img/Tt1.png"));
        } catch (IOException e) {
            System.out.println("erorr");
            e.printStackTrace();
        }

        // 1. สร้างปุ่มทิ้งไว้ (ขนาดจะถูกกำหนดใหม่ใน updateLayout)
        btnStart = createCustomButton("START GAME");
        btnSetting = createCustomButton("SETTINGS");
        btnHW = createCustomButton("HOW TO PLAY");
        btnExit = createCustomButton("EXIT");

        btnStart.addActionListener(e -> {
            window.showView("Game"); // สั่งเปลี่ยนหน้าไปยังชื่อ "Game"
        });

        // 2. ใส่ Event ให้ปุ่ม Exit
        btnExit.addActionListener(e -> System.exit(0));

        this.add(btnStart);
        this.add(btnSetting);
        this.add(btnHW);
        this.add(btnExit);

        // เปลี่ยนขนาดจอ
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateLayout(); // คำนวณพิกัดใหม่ทุกครั้งที่จอเปลี่ยนขนาด
            }
        });
    }

    private void updateLayout() {
        int w = getWidth();
        int h = getHeight();
        if (w <= 0 || h <= 0) return;

        // --- คำนวณขนาดตาม % ของจอ ---
        int btnW = (int)(w * 0.30);  // กว้าง 25% ของจอ
        int btnH = (int)(h * 0.06);  // สูง 8% ของจอ
        int xPos = (w - btnW) / 2;    // กึ่งกลางแนวนอน
        int startY = (int)(h * 0.62); // เริ่มปุ่มแรกที่ 55% ของความสูงจอ
        int spacing = (int)(h * 0.08); // เว้นระยะห่างปุ่มละ 11%

        // อัปเดตตำแหน่งและขนาดปุ่ม
        btnStart.setBounds(xPos, startY, btnW, btnH);
        btnSetting.setBounds(xPos, startY + spacing, btnW, btnH);
        btnHW.setBounds(xPos, startY + (spacing * 2), btnW, btnH);
        btnExit.setBounds(xPos, startY + (spacing * 3), btnW, btnH);

        // อัปเดตขนาดตัวอักษรในปุ่ม
        int btnFontSize = Math.max(15, (int)(btnH * 0.2));
        Font btnFont = new Font("SansSerif", Font.BOLD, btnFontSize);
        btnStart.setFont(btnFont);
        btnSetting.setFont(btnFont);
        btnHW.setFont(btnFont);
        btnExit.setFont(btnFont);
        
        repaint(); // สั่งให้วาดชื่อเกมใหม่ด้วย
    }

    private JButton createCustomButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        
        // Hover Effect
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(Color.BLACK); btn.setForeground(new Color(139, 0, 0)); }
            
            public void mouseExited(MouseEvent e) { btn.setBackground(Color.BLACK); btn.setForeground(Color.WHITE); }
        });
        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // เปิดโหมดตัวหนังสือเนียน (Anti-aliasing)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        if (BGimg != null) {
            // สั่งวาดรูปที่ x=0, y=0 และยืดขยายให้เต็ม w, h
            g2.drawImage(BGimg, 0, 0, w, h, null);
        } else {
            // ถ้ารูปโหลดไม่ได้ ให้เทสีเทาแทน
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(0, 0, w, h);
        }
    }
}