import java.awt.*;
import java.io.InputStream;

public class FontLoader {
    public static Font customFont;
    public static void loadGlobalFont() {
        try {
            InputStream is = FontLoader.class.getResourceAsStream("/font/Myfont.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            customFont = customFont.deriveFont(16f);
            
            setUIFont(new javax.swing.plaf.FontUIResource(customFont));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration<Object> keys = javax.swing.UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = javax.swing.UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                javax.swing.UIManager.put(key, f);
            }
        }
    }
}