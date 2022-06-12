import java.util.LinkedList;
import java.util.List;

public class Rogue extends Player{

    private int cost;
    private int current_energy;

    public Rogue(Position position, String name, int health_pool, int attack_points, int defense_points, int cost){
        super(position, name, health_pool, attack_points, defense_points);
        this.cost = cost;
        current_energy = 100;
    }

    public void checkLevelUp() {
        if (experience >= 50 * level) {
            super.checkLevelUp();
            current_energy = 100;
            attack_points = attack_points + (3*level);
        }
    }

    @Override
    public void SpecialAbility(GameBoard gameBoard) {
        if(current_energy>= cost){
            current_energy = current_energy - cost - 10;
            LinkedList<Tile> enemyList = gameBoard.findAllEnemy(this,2);
            while(!enemyList.isEmpty()){
                Tile enemy = enemyList.removeLast();
                enemy.combatPlayer(this,gameBoard,true);
            }
        }

    }

    @Override
    public int specialAbilityPower() {
        return attack_points;
    }

    @Override
    public String getSpecialAbility() {
        return "Fan of Knives";
    }

    @Override
    public void updateSpecialAbility() {
        current_energy = Math.min(current_energy+10, 100);
    }

    @Override
    public String describe() {
        return null;
    }
}