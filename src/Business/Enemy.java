package Business;
import CLI.MassageCallBack;
public abstract class Enemy extends Unit{

    public Enemy(MassageCallBack m,char tile, Position position, String name, int health_pool, int attack_points, int defense_points, int experience) {
        super(m,tile, position, name, health_pool, attack_points, defense_points, experience);
    }

    public void combat(Unit unit, GameBoard gameBoard){
        unit.combatEnemy(this, gameBoard,false);
    }
    public void combatPlayer(Player player, GameBoard gameBoard, boolean special_ability){
        defend(player,special_ability);
        if(health_amount<=0){
            m.send(name+" died. "+player.name+" gained "+experience+" experience.");
            player.increaseExperience(experience);
            gameBoard.removeEnemy(this,player,special_ability);
            player.checkLevelUp();
        }
    }
    public void combatEnemy(Enemy enemy, GameBoard gameBoard,boolean special_ability){

    }
    public void SpecialAbility(GameBoard gameBoard){

    }
    public  String getSpecialAbility(){
        return null;
    }
    public void updateSpecialAbility(){

    }

    public int specialAbilityPower(){
        return 0;
    }

    public abstract String describe();
    public abstract void enemyTick(GameBoard gameBoard);
    public abstract Tile copy();
    public void playerTick(char step, GameBoard gameBoard){

    }

}
