
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
        BufferedImage[][] m = new BufferedImage[3][4];

        BufferedImage[][] b = new BufferedImage[2][4];
        try {

            for (int i=0;i<m.length;i++){
                for(int j=0;j<m[i].length;j++){
                    m[i][j] = ImageIO.read(
                        getClass().getResource("/img/M" + (i+1) + (char)('A'+j) + ".png")
                    );
                }
            }

            for (int i=0;i<b.length;i++){
                for(int j=0;j<b[i].length;j++){
                    b[i][j] = ImageIO.read(
                        getClass().getResource("/img/B" + (i+1) + (char)('A'+j) + ".png")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        monsterList = new ArrayList<>();
        monsterList.add(new Monster(15,3,"Skeleton",5,m[0],2));
        monsterList.add(new Monster(10,4,"necromancer",5,m[1],1));
        monsterList.add(new Monster(20,1,"tank",7,m[2],2));

        bossList = new ArrayList<>();
        bossList.add(new Boss1(20, 7, "claw", 5,b[0],1));
        bossList.add(new Boss2(10,5,"Maruko",5,b[1],1));
    }

    public Enemy selectEnemy(int stage){
        Random random = new Random();
        Enemy ori;
        if(stage == 5){
            ori = bossList.get(0);
        }
        else if (stage == 10){
            ori = bossList.get(1);
        }
        else if(stage % 5 == 0){
            ori = bossList.get(random.nextInt(bossList.size()));
        }
        else{
            ori = monsterList.get(random.nextInt(monsterList.size()));
        }
        Enemy newEnemy = ori.clone();
        newEnemy.setBaseAtk(newEnemy.getBaseAtk() + 5*(stage/5));
        newEnemy.setMaxHp(newEnemy.getMaxHp() + 30*(stage/5));
        newEnemy.setCurHp(newEnemy.getMaxHp());
        return newEnemy;
    }
}