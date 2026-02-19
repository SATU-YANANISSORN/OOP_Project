public class Monster extends Enemy{

    public Monster(int maxHp,int baseAtk,int def,String name,float cdAttack){
        super(maxHp, baseAtk, def, name, cdAttack);
    }

    public Monster(Monster ori){
        super(ori);
    }

    @Override
    public void dealDamage(Entity target,int damage) {
        target.takeDamage(baseAtk);
    }

    public void bossskill(Entity target,int combo){
        Skill Bs = (t,c,d) ->{
            target.takeDamage(d);
        };
        Bs.skill(target,0,5);
    }
}
