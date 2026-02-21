public abstract class Entity{
    protected int curHp;
    protected int maxHp;
    protected int baseAtk;
    protected String name;
    protected int maxMp;
    protected int curMp;
    protected boolean isDodge = false;

    public Entity(int maxHp,int baseAtk,String name,int maxMp){
        this.maxHp = maxHp;
        this.curHp = maxHp;
        this.baseAtk = baseAtk;
        this.name = name;
        this.maxMp = maxMp;
        this.curMp = maxMp;
    }

    public int takeDamage(int damage){
        if(isDodge){
            return 0;
        }
        curHp = Math.max(0, curHp-damage);
        return damage;
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

    public int getCurMp(){
        return curMp;
    }

    public int getMaxMp(){
        return maxMp;
    }

    public void addCurMp(int add){
        curMp = Math.min(curMp + add,maxMp);
    }

    public void addCurHp(int add){
        curHp = Math.min(curHp + add,maxHp);
    }

    public void decreseCurMp(int decrese){
        curMp = Math.max(curMp - decrese, 0);
    }

    public int getBaseAtk(){
        return baseAtk;
    }

    public void setBaseAtk(int baseAtk){
        this.baseAtk = baseAtk;
    }

    public String getName(){
        return name;
    }

    public void setDodge(boolean isDodge){
        this.isDodge = isDodge;
    }

    public boolean getIsDodge(){
        return isDodge;
    }

    public void setCurHp(int curHp){
        this.curHp = curHp;
    }

    @Override
    public String toString(){
        return name + ":CurHp="+curHp;
    }

    public void dealDamage(Entity target){
        target.takeDamage(baseAtk);
        addCurMp(1);
        // System.out.println(target);
    };
}