
import java.awt.image.BufferedImage;

public abstract class Enemy extends Entity implements Cloneable{
    protected float  cdAttack;
    protected float  curCdAttack = 0;
    protected BufferedImage enemyImage;

    public Enemy(int maxHp,int baseAtk,String name,float cdAttack,BufferedImage enemyImage){
        super(maxHp, baseAtk, name,10);
        this.cdAttack = cdAttack;
        this.enemyImage = enemyImage;
    }

    public Enemy(Enemy ori){
        super(ori.maxHp, ori.baseAtk, ori.name,10);
        this.cdAttack = ori.cdAttack;
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
    public Enemy clone(){
        try {
            return (Enemy) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}