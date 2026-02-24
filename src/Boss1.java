
import java.awt.image.BufferedImage;

public class Boss1 extends Enemy{

    public Boss1(int maxHp,int baseAtk,String name,float cdAttack,BufferedImage[] enemyImage,float prepareDuration){
        super(maxHp, baseAtk, name, cdAttack,enemyImage,prepareDuration);
    }

    public int bossskill(Entity target,int combo){
        Skill Bs = (t,c,d) ->{
            if(curMp < c){
                return 0;
            }
            curHp = Math.min(curHp + target.takeDamage(d)/2, maxHp);
            decreseCurMp(c);
            return 0;
        };
        return Bs.skill(target,1,baseAtk*2);
    }

    @Override
    public int dealDamage(Entity target){
        int damage = target.takeDamage(baseAtk);
        addCurHp(damage);
        addCurMp(1);
        setAttack();
        return damage;
    }
}
