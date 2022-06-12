import java.util.LinkedList;
import java.util.NoSuchElementException;

public class GameBoard {
    private LinkedList <Tile> tiles;

    public GameBoard (LinkedList<Tile> tiles){
        this.tiles=tiles;
    }

    public LinkedList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(LinkedList<Tile> tiles) {
        this.tiles = tiles;
    }

    public Tile Search(Position position){
        for (Tile tile: tiles){
            if(position.equals(tile.getPosition())){
                return tile;
            }
        }
        throw new NoSuchElementException("position doesn't exist on the game board");
    }

    public void replace(Tile first, Tile second){
        Position fir= first.position;
        first.position= second.position;
        second.position=fir;

    }

    public void removeEnemy(Enemy enemy, Player player,boolean specialAbility){
        Tile empty=new Empty(enemy.position);
        tiles.remove(enemy);
        tiles.addLast(empty);
        if (!specialAbility) {
            replace(empty, player);
        }
    }

    public Tile findEnemy(Player player, int range){
        for (Tile tile: tiles){
            if (tile.position.distance(player.position)<range){
               if (tile.canYouAttackMe(player)){
                   return tile;
               }
            }
        }
        return null;
    }
}
