import java.awt.Color;
import java.util.List;


public class EnemyBehavivor implements Updateable{
    private boolean preparingAttack = false;
    private Enemy enemy;
    private List<MovingText> movingTexts;
    private Player player;
    private FightPanel fightPanel;
    private MainPanel main;
    private long prepareStart = 0;

    public EnemyBehavivor(FightPanel fightPanel,MainPanel main) {
        this.main = main;
        this.fightPanel = fightPanel;
        this.movingTexts = fightPanel.getMovingTexts();
    }

    public void setPreparingAttack(boolean preparingAttack){
        this.preparingAttack = preparingAttack;
    }

    public void setUp(Enemy enemy,Player player){
        preparingAttack = false;
        this.enemy = enemy;
        this.player = player;

        if (enemy instanceof Boss2 b) {
            b.setup(fightPanel.getCombo(), main.getStage());
        }
    }

    @Override
    public void update(){
        long now = System.currentTimeMillis();

         if (!preparingAttack) {
                enemy.curCdAttack += 16;
                if (enemy.curCdAttack >= enemy.getCdAtk() * 1000) {
                    System.out.println("prepare");
                    preparingAttack = true;
                    enemy.setPrepare();
                    prepareStart = now;
                }
            } else {
                if (now - prepareStart >= enemy.getPrepareDuration() && enemy.getCurHp() > 0) {
                    if(enemy instanceof Boss1 u){
                        int healNum = u.dealDamage(player);
                        if(healNum > 0)
                            movingTexts.add(new MovingText("+"+String.valueOf(healNum), fightPanel.getWidth()/2 + 20, 20, Color.YELLOW));
                    }
                    else{
                        enemy.dealDamage(player);
                    }
                    if(!player.isDodge){
                        fightPanel.shakeScreen(1);
                        main.getSoundManager().playSFX("/Sound/Hit2.wav"); // เสียงโดนตี
                    }
                    enemy.curCdAttack = 0;
                    preparingAttack = false;
                }
            }
    }
}