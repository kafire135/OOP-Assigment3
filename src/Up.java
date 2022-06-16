public class Up implements Step{
    public void step (Tile tile, GameBoard gameBoard){
        Position newPosition=new Position(tile.position.getX(),tile.position.getY()-1);
        Tile moveTo=gameBoard.Search(newPosition);
        tile.moveTo(moveTo, gameBoard);
    }
}
