public abstract class Entity{
    protected int curHp;
    protected int maxHp;
    protected int baseAtk;
    protected int def;
    protected String name;

    public Entity(int maxHp,int baseAtk,int def,String name){
        this.maxHp = maxHp;
        this.curHp = maxHp;
        this.baseAtk = baseAtk;
        this.def = def;
        this.name = name;
    }

    public void takeDamage(int damage){
        curHp = Math.max(0, curHp-damage);
    }

    public int getMaxHp(){
        return maxHp;
    }

    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }

    public int getCurHp(){
        return curHp;
    }

    public void setCurHp(int curHp){
        this.curHp = curHp;
    }
    public int getBaseAtk(){
        return baseAtk;
    }

    public void setBaseAtk(int baseAtk){
        this.baseAtk = baseAtk;
    }

    public int getDef(){
        return def;
    }

    public void setDef(int def){
        this.def = def;
    }

    public String getName(){
        return name;
    }

    public abstract void dealDamage(Entity Target);
}