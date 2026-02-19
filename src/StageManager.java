
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StageManager{
    private List<Enemy> monsterList;
    private List<Enemy> bossList;

    public StageManager(){
        setupData();
    }

    private void setupData(){
        monsterList = new ArrayList<>();
        monsterList.add(new Monster(100,10,"Skeleton",5));
        monsterList.add(new Monster(500,50,"Tank",7));

        bossList = new ArrayList<>();
        bossList.add(new Boss1(5000, 50, "Bigboss", 7));
    }

    public Enemy selectEnemy(int stage){
        Random random = new Random();
        Enemy ori;
        if(stage % 5 == 0){
            
            ori = bossList.get(random.nextInt(bossList.size()));
        }
        else{
            ori = monsterList.get(random.nextInt(monsterList.size()));
        }
        Enemy newEnemy = ori.clone();
        newEnemy.takeDamage(10);
        
        System.out.println(ori);
        System.out.println(newEnemy);

        return newEnemy;
    }
}