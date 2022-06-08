public class DoNothing implements Step{
    public void step(Tile tile, GameBoard gameBoard)
    {
        tile.updateSpecialAbility();
    }
}
