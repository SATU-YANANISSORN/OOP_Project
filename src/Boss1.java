public class Boss1 extends Enemy{

    private  int attackNum=0;
    public Boss1(int maxHp,int baseAtk,int def,String name,float  cdAttack){
        super(maxHp, baseAtk, def, name, cdAttack);
    }

    public Boss1(Boss1 ori){
        super(ori);
    }

    @Override
    public void dealDamage(Entity target,int damage){
        target.takeDamage(damage);
    }

    public void bossskill(Entity target,int combo){
        Skill Bs = (t,c,d) ->{
            if(target.takeDamage(d));{
                curHp = Math.min(curHp + d/2, maxHp);
            }
        };
        Bs.skill(target,0,10);
    }
    public int getAttack(){
        return attackNum;
    }

    public void resetAttack(){
        this.attackNum = 0;
    }



}
