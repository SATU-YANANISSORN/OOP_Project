
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
        // BufferedImage[] enemies = new BufferedImage[3];

        BufferedImage[][] m = new BufferedImage[3][4];

        BufferedImage[][] b = new BufferedImage[2][4];
        try {
            // for (int i = 0; i < 3; i++) {
            //     enemies[i] = ImageIO.read(
            //         getClass().getResource("/img/ch" + (i + 1) + ".png"));
            // }

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

            for (int i=0;i<b.length;i++){
                for(int j=0;j<b[i].length;i++){

                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        monsterList = new ArrayList<>();
        monsterList.add(new Monster(10,2,"Skeleton",5,m[0],2));
        monsterList.add(new Monster(15,4,"necromancer",5,m[1],1));
        monsterList.add(new Monster(20,1,"tank",7,m[2],2));

        bossList = new ArrayList<>();
        bossList.add(new Boss1(20, 5, "claw", 5,b[0],1));
        bossList.add(new Boss2(1,6,"Maruko",5,b[1],1));
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