public class Monster extends Enemy{

    public Monster(int maxHp,int baseAtk,int def,String name,float cdAttack){
        super(maxHp, baseAtk, def, name, cdAttack);
    }

    public Monster(Monster ori){
        super(ori);
    }

    @Override
    public void dealDamage(Entity target) {
        target.takeDamage(baseAtk);
    }
}