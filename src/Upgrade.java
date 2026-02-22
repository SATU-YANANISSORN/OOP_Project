import java.util.HashMap;
import java.util.Map;

public class Upgrade {
    private int points;
    private int use = 0;
    private Map<String,Integer> current = new HashMap<>();
    private Map<String,Integer> original = new HashMap<>();
    private Player player;
    private MainPanel main;
 
    public Upgrade(int points, Player player , MainPanel main){
        this.points = points;
        this.player = player;
        this.main = main;
        getCurrentStatus();
    }

    public void getCurrentStatus(){
        current.put("Damage" , this.player.getBaseAtk());
        current.put("MaxHp", this.player.getMaxHp());
        original.put("Damage" , this.player.getBaseAtk());
        original.put("MaxHp", this.player.getMaxHp());
    }

    public int getStatus(String type){
        return current.get(type);
    }

    public void setPoints(int points){
        this.points = points;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public int getUse(){
        return this.use;
    }

    public int getDifUseAndPoints(){
        return this.points - this.use;
    }
    
    public void UpgradeStatus(String type , int amount , boolean Increase){
        if(Increase){
            if (this.use < this.points){
                current.put(type, current.get(type) + amount);
                this.use += 10;
            }
        }else {
            if (this.use >= 10 && current.get(type) > original.get(type)) {
                current.put(type, current.get(type) - amount);
                this.use -= 10;
            }
        }
    }

    public void accept(){
        this.player.setMaxHp(current.get("MaxHp"));
        this.player.setCurHp(current.get("MaxHp"));
        this.player.setBaseAtk(current.get("Damage"));
        this.points = 0;
        main.decreseKarma(use);
        current.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }

    public void cancel(){
        getCurrentStatus();
    }
}
