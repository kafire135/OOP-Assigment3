package Business;
import java.util.LinkedList;
import CLI.MassageCallBack;

public class Rogue extends Player{

    private final int cost;
    private int current_energy;

    public Rogue(MassageCallBack m,Position position, String name, int health_pool, int attack_points, int defense_points, int cost){
        super(m,position, name, health_pool, attack_points, defense_points);
        this.cost = cost;
        current_energy = 100;
    }

    public void checkLevelUp() {
        if (experience >= 50 * level) {
            super.checkLevelUp();
            current_energy = 100;
            attack_points = attack_points + (3*level);
            m.send(name+" reached level "+level+": +"+(10*level)+" Health, +"+(7*level)+" Attack, +"+level+" Defense");
            if(experience>=50*level){
                checkLevelUp();
            }
        }
    }

    @Override
    public void SpecialAbility(GameBoard gameBoard) {
        if(current_energy>= cost){
            m.send(name+" cast "+getSpecialAbility()+".");
            current_energy = current_energy - cost - 10;
            LinkedList<Tile> enemyList = gameBoard.findAllEnemy(this,2);
            while(!enemyList.isEmpty()){
                Tile enemy = enemyList.removeLast();
                enemy.combatPlayer(this,gameBoard,true);
            }
        }
        else {
            m.send(name+" tried to cast "+getSpecialAbility()+", but there was not enough energy: "+current_energy+"/"+cost+".");
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
        String s=name+"\t\t"+"Health: "+health_amount+"/"+health_pool+"\t\t"+"Attack: "+attack_points+"\t\t"+"Defense: "
                +defense_points+"\t\t"+"Level: "+level+"\n\t\t"
                +"Experience: "+experience+"/"+50*level+"\t\t"+"Energy: "+current_energy+"/"+100;
        return s;
    }

    @Override
    public Tile copy() {
        return new Rogue(m,position,name,health_pool,attack_points,defense_points,cost);
    }

}