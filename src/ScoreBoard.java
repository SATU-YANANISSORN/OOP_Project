import java.io.*;

public class ScoreBoard {

    private int maxStage;
    private int maxCombo;

    private File saveFile;

    public ScoreBoard() {

        // สร้าง path แบบเครื่องใครเครื่องมัน
        String folderPath = System.getProperty("user.home")
                + File.separator + ".mytypinggame";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // สร้างโฟลเดอร์ถ้ายังไม่มี
        }

        saveFile = new File(folder, "score.dat");

        load(); // โหลดข้อมูลตอนเริ่มเกม
    }

    // ==============================
    // Update
    // ==============================

    public void updateStage(int stage) {
        if (stage > maxStage) {
            maxStage = stage;
            save();
        }
    }

    public void updateCombo(int combo) {
        if (combo > maxCombo) {
            maxCombo = combo;
            save();
        }
    }

    // ==============================
    // Save
    // ==============================

    public void save() {
        try (DataOutputStream out =
                     new DataOutputStream(new FileOutputStream(saveFile))) {

            out.writeInt(maxStage);
            out.writeInt(maxCombo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ==============================
    // Load
    // ==============================

    public void load() {

        if (!saveFile.exists()) return;

        try (DataInputStream in =
                     new DataInputStream(new FileInputStream(saveFile))) {

            maxStage = in.readInt();
            maxCombo = in.readInt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ==============================
    // Reset (ลบคะแนนทั้งหมด)
    // ==============================

    public void reset() {
        maxStage = 0;
        maxCombo = 0;
        save();
    }

    // ==============================
    // Getter
    // ==============================

    public int getMaxStage() {
        return maxStage;
    }

    public int getMaxCombo() {
        return maxCombo;
    }
}