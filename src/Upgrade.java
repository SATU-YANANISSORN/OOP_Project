import java.util.HashMap;
import java.util.Map;

public class Upgrade {
    private  int points;
    private int use = 0;
    private Map<String,Integer> current = new HashMap<>();
    private Map<String,Integer> original = new HashMap<>();
    private Player player;
    private MainPanel main;
 
    public Upgrade(MainPanel main , UpgradePanel upgradePanel){
        this.points = main.getKarma();
        this.player = main.getPlayer();
        this.main = main;
        getCurrentStatus();
    }

    public void getCurrentStatus(){
        this.points = this.main.getKarma();
        current.put("Damage" , this.player.getBaseAtk());
        current.put("MaxHp", this.player.getMaxHp());
        original.put("Damage" , this.player.getBaseAtk());
        original.put("MaxHp", this.player.getMaxHp());
    }

    public int getStatus(String type){
        return current.get(type);
    }

    public int getUse(){
        return this.use;
    }

    public int getDifUseAndPoints(){
        return this.points - this.use;
    }
    
    public void UpgradeStatus(String type , int amount , boolean Increase){
        if(Increase){
            if (this.use < points){
                current.put(type, current.get(type) + amount);
                this.use += 1;
            }
        }else {
            if (this.use >= 1 && current.get(type) > original.get(type)) {
                current.put(type, current.get(type) - amount);
                this.use -= 1;
            }
        }
    }

    public void accept(){
        this.player.setMaxHp(current.get("MaxHp"));
        this.player.setCurHp(current.get("MaxHp"));
        this.player.setBaseAtk(current.get("Damage"));
        main.decreseKarma(use);
        this.points = 0;
        this.use = 0;
        System.out.println(main.getKarma() + " " + this.use + " " + getDifUseAndPoints());
        current.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }

    public void cancel(){
        this.use = 0;
        getCurrentStatus();
    }


}
