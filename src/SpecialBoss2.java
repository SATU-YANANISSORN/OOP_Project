public class SpecialBoss2 {  //เฟส แล้วเปลี่ยนร่าง เช็คcomboเพื่อเปลี่ยนเฟส  bossเหลือดเหลือ1 แต่ไม่ตายถ้าโดนตี 
    private Combo combo;
    private int curcombo; //นับจำนวน combo
    private Player player;
    private int phase = 1;
    private Boss2 boss2;

    public SpecialBoss2(Combo combo,Player player,Boss2 boss2){
        this.combo = combo;
        this.player= player;
        this.boss2= boss2;
    }

    public void wave(){
        curcombo ++;
        if(curcombo == 5*phase ){
            if(combo.getValue() == 5*phase){
                phase ++;
            }else{
                boss2.dealDamage(player);
            }
        curcombo = 0;
        combo.reset();
        }
    }
}
