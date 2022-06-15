import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class GameBoard {
    private int length;
    private int width;
    private LinkedList <Tile> tiles;
    private LinkedList <Tile> enemies;
    Tile player;
    private boolean livingPlayer;

    public GameBoard (LinkedList<Tile> tiles, int length, int width, LinkedList<Tile> enemies, Tile player)
    {
        this.tiles=tiles;
        this.enemies=enemies;
        this.player=player;
        livingPlayer=true;
        this.length=length;
        this.width=width;
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

    public void removeEnemy(Tile enemy, Player player,boolean specialAbility){
        Tile empty=new Empty(enemy.position);
        tiles.remove(enemy);
        tiles.addLast(empty);
        if (!specialAbility) {
            replace(empty, player);
        }
        enemies.remove(enemy);
    }

    public Tile findEnemy(Player player, int range){
        for (Tile tile: enemies){
            if (tile.position.distance(player.position)<range){
                return tile;
            }
        }
        return null;
    }

    public Tile findPlayer(Enemy enemy, int range){
            if (player.position.distance(enemy.position)<range){
                    return player;
            }
        return null;
    }

    public Tile findClosestEnemy(Player player, int range){
        double minRange=range;
        Tile output=null;
        for (Tile tile: enemies){
            if (tile.position.distance(player.position)<minRange){
                output = tile;
                minRange = tile.position.distance(player.position);
            }
        }
        return output;
    }

    public LinkedList<Tile> findAllEnemy(Player player, int range){
        LinkedList<Tile> output = new LinkedList<>();
        for (Tile tile: enemies){
            if (tile.position.distance(player.position)<range){
                output.addLast(tile);
            }
        }
        return output;
    }

    public void printGameBoard(){
        Iterator<Tile> iterator = tiles.iterator();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(iterator.next().tile);
            }
            System.out.println();
        }
    }

    public boolean isLivingPlayer() {
        return livingPlayer;
    }

    public void setLivingPlayer(boolean livingPlayer) {
        this.livingPlayer = livingPlayer;
    }

    public LinkedList<Tile> getEnemies() {
        return enemies;
    }

    public void setEnemies(LinkedList<Tile> enemies) {
        this.enemies = enemies;
    }
}
