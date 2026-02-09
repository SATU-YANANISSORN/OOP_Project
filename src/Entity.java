public abstract class Entity{
    protected int curHp;
    protected int maxHp;
    protected int atk;
    protected int def;
    protected String name;

    public Entity(int maxHp,int atk,int def,String name){
        this.maxHp = maxHp;
        this.curHp = maxHp;
        this.atk = atk;
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

    public int getAtk(){
        return atk;
    }

    public void setAtk(int atk){
        this.atk = atk;
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