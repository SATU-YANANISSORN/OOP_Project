
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.Timer;

public abstract class Enemy extends Entity implements Cloneable{
    protected float  cdAttack;
    protected float  curCdAttack = 0;
    protected BufferedImage enemyImage;
    protected BufferedImage[] allEnemyImage;
    protected Random random;

    protected float prepareDuration;

    public Enemy(int maxHp,int baseAtk,String name,float cdAttack,BufferedImage[] enemyImage,float prepareDuration){
        super(maxHp, baseAtk, name,10);
        random = new Random();
        this.cdAttack = cdAttack;
        this.prepareDuration = prepareDuration * 1000;
        this.allEnemyImage = enemyImage;
        this.enemyImage = enemyImage[0];
    }

    public Enemy(Enemy ori){
        super(ori.maxHp, ori.baseAtk, ori.name,10);
        this.cdAttack = ori.cdAttack;
    }

    public void setIdle(){
        enemyImage = allEnemyImage[0];
    }

    public void setPrepare(){
        enemyImage = allEnemyImage[3];
    }

    public void setAttack(){
        enemyImage = allEnemyImage[1];
        Timer animationTimer = new Timer(1000, e-> {
            setIdle();
        });
        
        animationTimer.setRepeats(false);
        animationTimer.start();
    }

    public void setDeath(){
        enemyImage = allEnemyImage[2];
    }

    public float getCdAtk(){
        return cdAttack;
    }

    public void setCdAtk(float cdAttack){
        this.cdAttack = cdAttack;
    }

    public BufferedImage getEnemyImage(){
        return enemyImage;
    }

    @Override
    public int dealDamage(Entity target){
        int damage = baseAtk;
        target.takeDamage(damage);
        addCurMp(1);
        setAttack();
        return damage;
        // System.out.println(target);
    };

    @Override
    public Enemy clone(){
        try {
            return (Enemy) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public float getPrepareDuration(){
        return prepareDuration;
    }
}