
import java.awt.image.BufferedImage;

public class Monster extends Enemy{

    public Monster(int maxHp,int baseAtk,String name,float cdAttack,BufferedImage[] enemyImage,float prepareDuration){
        super(maxHp, baseAtk, name, cdAttack,enemyImage,prepareDuration);
    }

    public int attackSkill(Entity target,int combo){
        Skill Bs = (t,c,d) ->{
            if(curMp < c){
                return 0;
            }
            decreseCurMp(c);
            return target.takeDamage(d);
        };
        return Bs.skill(target,1,5);
    }

    
}
