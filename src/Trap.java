public class Trap extends Enemy{

    private final int visibility_time;
    private final int invisibility_time;
    private int ticks_count;
    boolean visible;
    private final char visualChar;

    public Trap(char tile, Position position, String name, int health_pool, int attack_points, int defense_points, int experience, int visibility_time, int invisibility_time) {
        super(tile, position, name, health_pool, attack_points, defense_points, experience);
        this.visibility_time = visibility_time;
        this.invisibility_time = invisibility_time;
        this.ticks_count=0;
        visible=true;
        visualChar = tile;
    }

    public void enemyTick(GameBoard gameBoard){
        ticks_count++;
        if (visible){
            if(ticks_count==visibility_time){
                ticks_count=0;
                tile='.';
                visible=false;
            }
        }
        else {
            if(ticks_count==invisibility_time){
                ticks_count=0;
                tile=visualChar;
                visible=true;
            }
        }
        Tile player=gameBoard.findPlayer(this,2);
        if (player!=null){
            player.combat(this,gameBoard);
        }
    }

    @Override
    public String describe() {
        return null;
    }
}
