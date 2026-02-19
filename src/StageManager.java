
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StageManager{
    private List<Monster> monsterList;
    private List<Boss> bossList;

    public StageManager(){
        setupData();
    }

    private void setupData(){
        monsterList = new ArrayList<>();
        monsterList.add(new Monster(100,10,5,"Skeleton",5));
        monsterList.add(new Monster(500,50,50,"Tank",7));

        bossList = new ArrayList<>();
        bossList.add(new Boss(5000, 50, 5, "Bigboss", 7));
    }

    public Enemy selectEnemy(int stage){
        Random random = new Random();
        Enemy newEnemy;
        if(stage % 5 == 0){
            newEnemy = new Boss((Boss)bossList.get(random.nextInt(bossList.size())));
        }
        else{
            newEnemy = new Monster((Monster) monsterList.get(random.nextInt(monsterList.size())));
        }
        System.out.println(newEnemy);
        return newEnemy;
    }
}