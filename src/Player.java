public class Player extends Entity {

    public Player(int maxHp, int baseAtk,String name,int Maxmp) {
        super(maxHp,baseAtk,name,Maxmp);
    }


    @Override
    public void dealDamage(Entity target,int damage) {

        target.takeDamage(damage);
    }


    public void thunder(Entity target,int combo){
        Skill s = (t,c,d) ->{
            target.takeDamage(d);
        };

        s.skill(target,10,combo*2);
    }

    public void untimate(Entity target,int combo){
        Skill s = (t,c,d) ->{
            target.takeDamage(d);
        };

        s.skill(target,50,combo*10);
    }


    public void Heal(){
        curHp = Math.min(curHp + 10, maxHp );
    }



}
