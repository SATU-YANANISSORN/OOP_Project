package src;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    
    // ประกาศตัวแปร CardLayout และ Panel หลักที่ใช้เก็บทุกหน้า
    private CardLayout cardLayout;
    private JPanel mainContainer;
    
    public GameWindow() {
        setTitle("WORD ATTACK");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // 1. ตั้งค่า CardLayout
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        
        // 2. สร้างหน้าจอต่างๆ โดยส่ง this (ตัว GameWindow เอง) เข้าไป
        // เพื่อให้ Panel สั่งเปลี่ยนหน้าจอได้
        TitlePanel menu = new TitlePanel(this);
        GamePanel game = new GamePanel(this); // สมมติว่านี่คือหน้าตอนเล่นเกม
        
        // 3. เพิ่มหน้าต่างๆ ลงใน Container พร้อมตั้งชื่อ String กำกับไว้
        mainContainer.add(menu, "Menu");
        mainContainer.add(game, "Game");
        
        // 4. เอา Container หลักใส่ลงในหน้าต่าง
        this.add(mainContainer);
        
        setVisible(true);
    }
    
    // ฟังก์ชันนี้สำคัญมาก! เอาไว้ให้ปุ่มในหน้าต่างๆ เรียกเพื่อสลับหน้าจอ
    public void showView(String viewName) {
        cardLayout.show(mainContainer, viewName);
    }
}