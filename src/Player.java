public class Player extends Entity {

<<<<<<< HEAD
    private boolean isdodge = false;

    public Player(int maxHp, int baseAtk, int def, String name) {
        super(maxHp, baseAtk, def, name);
=======
    public Player(int maxHp, int baseAtk,String name,int Maxmp) {
        super(maxHp,baseAtk,name,Maxmp);
>>>>>>> 18bd4e7ee6eab9ac25b5b7118eba8f970233b961
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

<<<<<<< HEAD
    @Override
    public  void takeDamage(int damage){
        if(!isdodge){
            super.takeDamage(damage);
        }
    }

    public void Dodge(){
        this.isdodge = true;
    }

    public void resetdodge(){
        this.isdodge = false;
    }

=======
>>>>>>> 18bd4e7ee6eab9ac25b5b7118eba8f970233b961


}
