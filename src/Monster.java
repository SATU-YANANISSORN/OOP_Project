
import java.awt.image.BufferedImage;

public class Monster extends Enemy{

    public Monster(int maxHp,int baseAtk,String name,float cdAttack,BufferedImage enemyImage){
        super(maxHp, baseAtk, name, cdAttack,enemyImage);
    }

    public void attackSkill(Entity target,int combo){
        Skill Bs = (t,c,d) ->{
            if(curMp >= c){
                target.takeDamage(d);
                decreseCurMp(c);
            }
        };
        Bs.skill(target,1,5);
    }

    
}
