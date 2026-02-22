
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class StageManager{
    private List<Enemy> monsterList;
    private List<Enemy> bossList;

    public StageManager(){
        setupData();
    }

    private void setupData(){
        BufferedImage[] enemies = new BufferedImage[3];

        try {
            for (int i = 0; i < 3; i++) {
                enemies[i] = ImageIO.read(
                    getClass().getResource("/img/ch" + (i + 1) + ".png"));
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        monsterList = new ArrayList<>();
        monsterList.add(new Monster(10,2,"Skeleton",5,enemies[0]));
        monsterList.add(new Monster(15,4,"Tank",7,enemies[1]));

        bossList = new ArrayList<>();
        bossList.add(new Boss1(20, 4, "Bigboss", 7,enemies[2]));
    }

    public Enemy selectEnemy(int stage){
        Random random = new Random();
        Enemy ori;
        if(stage % 5 == 0){
            ori = bossList.get(random.nextInt(bossList.size()));
            System.out.println(ori);
        }
        else{
            ori = monsterList.get(random.nextInt(monsterList.size()));
        }
        Enemy newEnemy = ori.clone();
        newEnemy.setBaseAtk(newEnemy.getBaseAtk() + 5*(stage/5));
        newEnemy.setMaxHp(newEnemy.getMaxHp() + 20*(stage/5));
        newEnemy.setCurHp(newEnemy.getMaxHp());
        System.out.println(newEnemy);
        return newEnemy;
    }
}