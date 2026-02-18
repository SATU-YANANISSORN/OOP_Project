import java.util.HashMap;
import java.util.Map;

public class Upgrade {
    private int points;
    private int use = 0;
    private Map<String,Integer> current = new HashMap<>();
    public Upgrade(int points, Player player){
        this.points = points;
        getCurrentStatus(player);
    }

    private void getCurrentStatus(Player player){
        current.put("damage" , player.getBaseAtk());
        current.put("MaxHp", player.getMaxHp());
        current.put("def", player.getDef());
    }

    
    public void UpgradeStatus(String type , int amount){
      if (this.points > 0){
        current.put(type, current.get(type) + amount);
        this.points -= 1;
        this.use += 1;
      }
    }

    public void accept(Player player){
        player.setMaxHp(current.get("MaxHp"));
        player.setCurHp(current.get("MaxHp"));
        player.setBaseAtk(current.get("damage"));
        player.setDef(current.get("def"));
    }

    public void cancel(Player player){
        this.points += this.use;
        getCurrentStatus(player);
    }
}
