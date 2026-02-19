public class Boss2 extends Enemy{

    public Boss2(int maxHp,int baseAtk,String name,float  cdAttack){
        super(maxHp, baseAtk, name, cdAttack);
    }

    @Override
    public void dealDamage(Entity target,int damage){
        target.takeDamage(damage);
    }

    public void bossskill(Entity target,int combo){
        Skill Bs = (t,c,d) ->{
                target.takeDamage(d);
        };
        Bs.skill(target,0,baseAtk);
    }

    
}
