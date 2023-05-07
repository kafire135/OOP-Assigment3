package Business;
public class Empty extends Tile{
    public Empty (Position position)
    {
       super ('.',position);
    }

    @Override
    public void moveTo(Tile other, GameBoard gameBoard) {

    }

    @Override
    public void moveFowardMe(Unit unit, GameBoard gameBoard) {
        gameBoard.replace(this,unit);
    }

    @Override
    public void combat(Unit unit, GameBoard gameBoard) {

    }

    @Override
    public void combatPlayer(Player player, GameBoard gameBoard, boolean special_ability) {

    }

    @Override
    public void combatEnemy(Enemy enemy, GameBoard gameBoard, boolean special_ability) {

    }

    @Override
    public void SpecialAbility(GameBoard gameBoard) {

    }

    @Override
    public void playerTick(char step, GameBoard gameBoard) {

    }

    @Override
    public void enemyTick(GameBoard gameBoard) {

    }

    @Override
    public Tile copy() {
        return new Empty(position);
    }
}
