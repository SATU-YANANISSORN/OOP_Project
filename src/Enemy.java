public abstract class Enemy extends Entity{
    protected float  cdAttack;
    protected float  curCdAttack = 0;

    public Enemy(int maxHp,int baseAtk,int def,String name,float cdAttack){
        super(maxHp, baseAtk, def, name);
        this.cdAttack = cdAttack;
    }

    public Enemy(Enemy ori){
        super(ori.maxHp, ori.baseAtk, ori.def, ori.name);
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
        return name+"Spawned with [Hp:"+maxHp+",baseAtk:"+baseAtk+",def:"+def;
    }
}