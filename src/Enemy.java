public abstract class Enemy extends Unit{

    public Enemy(char tile, Position position, String name, int health_pool, int attack_points, int defense_points, int experience) {
        super(tile, position, name, health_pool, attack_points, defense_points, experience);
    }

    public void combat(Unit unit, GameBoard gameBoard){
        unit.combatEnemy(this, gameBoard);
    }
    public void combatPlayer(Player player, GameBoard gameBoard, boolean special_ability){
        Unit.combat(player,this,special_ability);
        if(health_amount<=0){
            player.increaseExperience(experience);
            gameBoard.removeEnemy(this,player,special_ability);
            player.checkLevelUp();
        }
    }
    public void combatEnemy(Enemy enemy, GameBoard gameBoard){

    }
    public void SpecialAbility(GameBoard gameBoard){

    }
    public  String getSpecialAbility(){
        return null;
    }
    public void updateSpecialAbility(){

    }
    public boolean canYouAttackMe(Player player) {
        return true;
    }

    public int specialAbilityPower(){
        return 0;
    }

    public abstract String describe();
}
