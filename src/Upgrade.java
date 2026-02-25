import java.util.HashMap;
import java.util.Map;

public class Upgrade {
    private  int points;
    private int use = 0;
    private Map<String,Integer> current = new HashMap<>();
    private Map<String,Integer> original = new HashMap<>();
    private Player player;
    private MainPanel main;
    private int cost = 5;
 
    public Upgrade(MainPanel main , UpgradePanel upgradePanel){
        this.points = main.getKarma();
        this.player = main.getPlayer();
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

    public void setCost(int cost){
        this.cost = cost;
    }
    
    public void UpgradeStatus(String type , int amount , boolean Increase){
        if(Increase){
            if (this.use + cost <= this.points){
                current.put(type, current.get(type) + amount);
                this.use += cost;
            }
        }else {
            if (this.use >= cost && current.get(type) > original.get(type)) {
                current.put(type, current.get(type) - amount);
                this.use -= cost;
            }
        }
    }

    public void accept(){
        use = 0;
        this.player.setMaxHp(current.get("MaxHp"));
        this.player.setCurHp(current.get("MaxHp"));
        this.player.setBaseAtk(current.get("Damage"));
        main.decreseKarma(use);
        this.points = 0;
        this.use = 0;
        getDifUseAndPoints();
        // System.out.println(main.getKarma() + " " + this.use + " " + getDifUseAndPoints());
        // current.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }

    public void cancel(){
        use = 0;
        getCurrentStatus();
        player.setCurHp(player.getMaxHp());
    }
}
