public class Boss extends Enemy{
    public Boss(int maxHp,int baseAtk,int def,String name,float  cdAttack){
        super(maxHp, baseAtk, def, name, cdAttack);
    }

    public Boss(Boss ori){
        super(ori);
    }

    @Override
    public void dealDamage(Entity target){
        target.dealDamage(target);
    }
}