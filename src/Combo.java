public class Combo {
    private int currentCount;

    public Combo() {
        this.currentCount = 0;
    }

    // เมื่อพิมพ์ถูก: เพิ่มคอมโบ
    public void increase() {
        this.currentCount++;
    }

    // เมื่อพิมพ์ผิด: รีเซ็ตเป็น 0
    public void reset() {
        this.currentCount = 0;
    }

    // ดึงค่าคอมโบปัจจุบัน (เอาไปโชว์ UI หรือใช้คำนวณ)
    public int getValue() {
        return this.currentCount;
    }

    // คำนวณดาเมจที่เพิ่มขึ้นตามกฎ (เริ่มนับดาเมจตอน Combo >= 2)
    public int getBonusDamage() {
        if (this.currentCount >= 2) {
            return this.currentCount;
        }
        return 0; // ถ้ายังไม่ถึง 2 ไม่บวกดาเมจ
    }
}
