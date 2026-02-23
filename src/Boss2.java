
import java.awt.image.BufferedImage;

public class Boss2 extends Enemy{

    public Boss2(int maxHp,int baseAtk,String name,float cdAttack,BufferedImage[] enemyImage,float prepareDuration){
        super(maxHp, baseAtk, name, cdAttack,enemyImage,prepareDuration);
    }

    public void bossskill(Entity target,int combo){
        Skill Bs = (t,c,d) ->{
                target.takeDamage(d);
        };
        Bs.skill(target,1,baseAtk);
    } 
}