
import java.awt.image.BufferedImage;

public class Boss2 extends Enemy{
    public int curCapHp;
    public Combo combo;
    public int stage;

    public Boss2(int maxHp,int baseAtk,String name,float cdAttack,BufferedImage[] enemyImage,float prepareDuration){
        super(maxHp, baseAtk, name, cdAttack,enemyImage,prepareDuration);
        curCapHp = 1;
    }

    public void setup(Combo combo,int stage){
        this.combo = combo;
        this.stage = stage;
    }

    @Override
    public int takeDamage(int damage){
        if(curHp==1&&combo.getValue() >= stage){
            curCapHp = 0;
        }
        curHp = Math.max(curCapHp,curHp - damage);
        return damage;
    }
}