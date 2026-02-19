public class Monster extends Enemy{

    public Monster(int maxHp,int baseAtk,String name,float cdAttack){
        super(maxHp, baseAtk, name, cdAttack);
    }

    @Override
    public void dealDamage(Entity target,int damage) {
        target.takeDamage(baseAtk);
        addCurMp(1);
    }

    public void attackSkill(Entity target,int combo){
        Skill Bs = (t,c,d) ->{
            if(curMp >= c){
                target.takeDamage(d);
                decreseCurMp(c);
            }
        };
        Bs.skill(target,1,5);
    }
}
