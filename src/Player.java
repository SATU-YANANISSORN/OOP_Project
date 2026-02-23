public class Player extends Entity {

    public Player(int maxHp, int baseAtk,String name,int Maxmp) {
        super(maxHp,baseAtk,name,Maxmp);
    }

    public void thunder(Entity target,int combo){
        Skill s = (t,c,d) ->{
            if(curMp < c){
                return;
            }
            target.takeDamage(d);
            decreseCurMp(c);
        };

        s.skill(target,2,combo*2);
    }

    public void untimate(Entity target,int combo){
        Skill s = (t,c,d) ->{
            if(curMp < c){
                return;
            }
            target.takeDamage(d);
            decreseCurMp(c);
        };

        s.skill(target,7,baseAtk*10 + (baseAtk *combo/10));
    }

    public void Heal(){
        if(curHp != maxHp){
            if(curMp < 2){
                return;
            }
            curHp = Math.min(curHp + maxHp/10, maxHp);
            decreseCurMp(2);
        }
    }
}
