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
