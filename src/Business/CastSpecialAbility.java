package Business;

public class CastSpecialAbility implements Step{
    public void step(Tile tile, GameBoard gameBoard){
        tile.SpecialAbility(gameBoard);
    }
}
