public class Hunter extends Player{

    private final int range;
    private int arrows_count;
    private int tick_count;

    public Hunter(Position position, String name, int health_pool, int attack_points, int defense_points, int range) {
        super(position, name, health_pool, attack_points, defense_points);
        this.range = range;
        this.arrows_count = 10*level;
        this.tick_count = 0;
    }

    public void checkLevelUp(){
        if(experience>=50*level) {
            super.checkLevelUp();
            arrows_count=arrows_count + (10*level);
            attack_points = attack_points + (2 * level);
            defense_points = defense_points + level;
            if(experience>=50*level){
                checkLevelUp();
            }
        }
    }

    public void playerTick(char input, GameBoard gameBoard){
        super.playerTick(input,gameBoard);
        if (tick_count==10){
            arrows_count=arrows_count+level;
            tick_count=0;
        }
        else {
            tick_count++;
        }
    }

    @Override
    public void SpecialAbility(GameBoard gameBoard) {
        if (arrows_count>0){
            Tile enemy = gameBoard.findClosestEnemy(this,range);
            if (enemy!=null) {
                enemy.combatPlayer(this, gameBoard, true);
                arrows_count--;
            }
        }
    }

    @Override
    public int specialAbilityPower() {
        return attack_points;
    }

    @Override
    public String getSpecialAbility() {
        return "Shoot";
    }

    @Override
    public void updateSpecialAbility() {

    }

    @Override
    public String describe() {
        return null;
    }

    @Override
    public Tile copy() {
        return new Hunter(position,name,health_pool,attack_points,defense_points,range);
    }
}
