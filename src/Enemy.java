public abstract class Enemy extends Entity implements Cloneable{
    protected float  cdAttack;
    protected float  curCdAttack = 0;

    public Enemy(int maxHp,int baseAtk,String name,float cdAttack){
        super(maxHp, baseAtk, name,10);
        this.cdAttack = cdAttack;
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

    @Override
    public String toString(){
        return name+"Spawned with [Hp:"+curHp+",baseAtk:"+baseAtk;
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