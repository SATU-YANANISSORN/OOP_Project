public class Player extends Entity {

    public Player(int maxHp, int baseAtk,String name,int Maxmp) {
        super(maxHp,baseAtk,name,Maxmp);
    }

    public int thunder(Entity target,int combo){
        Skill s = (t,c,d) ->{
            if(curMp < c){
                return 0;
            }
            decreseCurMp(c);
            return target.takeDamage(d);
        };

        return s.skill(target,2,baseAtk + combo*2);
    }

    public int untimate(Entity target,int combo){
        Skill s = (t,c,d) ->{
            if(curMp < c){
                return 0;
            }
            decreseCurMp(c);
            return target.takeDamage(d);
        };

        return s.skill(target,7,baseAtk*10);
    }

    public int Heal(){
        int healNum = 0;
        if(curHp != maxHp){
            if(curMp < 2){
                return 0;
            }
            decreseCurMp(2);
            int tem = curHp;
            curHp = Math.min(curHp + maxHp/5, maxHp);
            healNum = curHp - tem;
        }
        return healNum;
    }
}
