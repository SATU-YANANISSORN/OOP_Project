public class Player extends Entity {

    private boolean isdodge = false;

    public Player(int maxHp, int baseAtk, int def, String name) {
        super(maxHp, baseAtk, def, name);
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

    @Override
    public boolean takeDamage(int damage){
        if(!isdodge){
            super.takeDamage(damage);
            return true;
        }
        return false;
    }

    public void Dodge(){
        this.isdodge = true;
    }

    public void resetdodge(){
        this.isdodge = false;
    }



}
