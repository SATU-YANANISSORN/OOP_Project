public class Boss1 extends Enemy{

    public Boss1(int maxHp,int baseAtk,String name,float  cdAttack){
        super(maxHp, baseAtk, name, cdAttack);
    }

    @Override
    public void dealDamage(Entity target,int damage){
        target.takeDamage(damage);
    }

    public void bossskill(Entity target,int combo){
        Skill Bs = (t,c,d) ->{
            if(curMp >= c){
                curHp = Math.min(curHp + target.takeDamage(d)/2, maxHp);
                decreseCurMp(c);
            }
        };
        Bs.skill(target,1,baseAtk);
    }
}
