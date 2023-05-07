package Business;
import java.util.HashMap;
import CLI.MassageCallBack;

public abstract class Player extends Unit implements HeroicUnit{

    protected int level;

    public Player(MassageCallBack m,Position position, String name, int health_pool, int attack_points, int defense_points) {
        super(m,'@', position, name, health_pool, attack_points, defense_points, 0);
        this.level=1;
    }

    public void playerTick(char input, GameBoard gameBoard){
        HashMap<Character,Step> stepHashMap = new HashMap<>();
        stepHashMap.put('w',new Up());
        stepHashMap.put('s',new Down());
        stepHashMap.put('d',new Right());
        stepHashMap.put('a',new Left());
        stepHashMap.put('q',new DoNothing());
        stepHashMap.put('e',new CastSpecialAbility());
        Step step = stepHashMap.get(input);
        step.step(this,gameBoard);
        updateSpecialAbility();
    }

    public void checkLevelUp(){
        if (experience>=50*level) {
            experience=experience-(50*level);
            level = level + 1;
            health_pool = health_pool+(10*level);
            health_amount = health_pool;
            attack_points = attack_points + (4*level);
            defense_points = defense_points + level;
        }
    }

    public void combat(Unit unit, GameBoard gameBoard)
    {
        unit.combatPlayer(this, gameBoard, false);
    }
    public void combatPlayer(Player player, GameBoard gameBoard, boolean special_ability){

    }
    public void combatEnemy(Enemy enemy, GameBoard gameBoard, boolean special_ability){
        defend(enemy,special_ability);
        if(health_amount<=0){
            m.send(name+" was killed by "+enemy.getName()+".");
            m.send("you lost.");
            tile='X';
            gameBoard.setLivingPlayer(false);
        }
    }

    public void increaseExperience(int otherExperience){
        experience=experience+otherExperience;
    }
    public abstract void SpecialAbility(GameBoard gameBoard);
    public abstract int specialAbilityPower();
    public abstract String getSpecialAbility();
    public abstract void updateSpecialAbility();
    public abstract String describe();
    public abstract Tile copy();
    public void enemyTick(GameBoard gameBoard){

    }
}
