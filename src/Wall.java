public class Wall extends Tile{

    public Wall(Position position){
        super('#',position);
    }

    @Override
    public void moveTo(Tile other, GameBoard gameBoard) {

    }

    @Override
    public void moveFowardMe(Unit other, GameBoard gameBoard) {

    }

    @Override
    public void combat(Unit unit, GameBoard gameBoard) {

    }

    @Override
    public void combatPlayer(Player player, GameBoard gameBoard, boolean special_ability) {

    }

    @Override
    public void combatEnemy(Enemy enemy, GameBoard gameBoard) {

    }

    @Override
    public boolean canYouAttackMe(Player player) {
        return false;
    }

    @Override
    public boolean canYouAttackMe(Enemy enemy) {
        return false;
    }

    @Override
    public void SpecialAbility(GameBoard gameBoard) {

    }

    @Override
    public String getSpecialAbility() {
        return null;
    }

    @Override
    public void updateSpecialAbility() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getHealth_pool() {
        return 0;
    }

    @Override
    public int getHealth_amount() {
        return 0;
    }

    @Override
    public int getAttack_points() {
        return 0;
    }

    @Override
    public int getDefense_points() {
        return 0;
    }

    @Override
    public int getExperience() {
        return 0;
    }

    @Override
    public void playerTick(char step, GameBoard gameBoard) {

    }

    @Override
    public void enemyTick(GameBoard gameBoard) {

    }

    @Override
    public Tile copy() {
        return new Wall(position);
    }
}
