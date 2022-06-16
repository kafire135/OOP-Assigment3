public class Right implements Step{
    public void step (Tile tile, GameBoard gameBoard){
        Position newPosition=new Position(tile.position.getX()+1,tile.position.getY());
        Tile moveTo=gameBoard.Search(newPosition);
        tile.moveTo(moveTo, gameBoard);
    }
}
