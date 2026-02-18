public class Player extends Entity {
    
    // ตัวแปรเก็บจำนวน Combo ปัจจุบัน
    private int currentCombo; 

    public Player(int maxHp, int baseAtk, int def, String name) {
        super(maxHp, baseAtk, def, name);
        this.currentCombo = 0; // เริ่มเกม Combo เป็น 0
    }

    // เมธอดหลักสำหรับรับผลการพิมพ์ (ถูก/ผิด) แล้วไปตี Monster
    public void attackMonster(Entity target, boolean isWordCorrect) {
        if (isWordCorrect) {
            // 1. พิมพ์ถูก เพิ่ม Combo
            currentCombo++;
            
            // 2. เรียกใช้ dealDamage เพื่อคำนวณดาเมจรวมและโจมตี
            dealDamage(target);
            
        } else {
            // 3. พิมพ์ผิด Combo หลุดเหลือ 0
            System.out.println("TT Combo X 0");
            currentCombo = 0;
        }
    }

    @Override
    public void dealDamage(Entity target) {
        // สูตร: Damage รวม = BaseAtk + (Combo ถ้ามากกว่าหรือเท่ากับ 2)
        int comboDamage = (currentCombo >= 2) ? currentCombo : 0;
        int totalDamage = this.baseAtk + comboDamage;

        // แสดงผล Combo (ตามโจทย์: แสดงหน้า Combo เมื่อถึง 2)
        if (currentCombo >= 2) {
            System.out.println("--- COMBO x" + currentCombo + "!! ---");
        }

        System.out.println(this.name + " (Base: " + baseAtk + " + Combo: " + comboDamage + ")");
        System.out.println("all damage: " + totalDamage);

        // สั่งให้เป้าหมายรับดาเมจ
        target.takeDamage(totalDamage);
    }

    // เมธอดสำหรับดูค่า Combo (เผื่อใช้แสดงผลบนหน้าจอ)
    public int getCurrentCombo() {
        return currentCombo;
    }

    public void castSkill(Skill skill, Entity target) {
        System.out.println(">>> " + this.name + " กำลังร่ายสกิล: " + skill.getName());
        skill.use(this, target);
    }
    
    // เพิ่ม method heal ตามที่แนะนำในข้อ 2.2 (ใส่ใน Entity หรือใส่ Override ที่นี่ก็ได้)
    public void heal(int amount) {
        this.curHp = Math.min(this.maxHp, this.curHp + amount);
        System.out.println("HP ปัจจุบัน: " + this.curHp + "/" + this.maxHp);
    }
}
