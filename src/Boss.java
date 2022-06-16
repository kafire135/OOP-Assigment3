import java.util.HashMap;

public class Boss extends Monster implements HeroicUnit{

    private final int ability_frequency;
    private int combat_ticks;

    public Boss(char tile, Position position, String name, int health_pool, int attack_points, int defense_points, int experience, int vision_range, int ability_frequency) {
        super(tile, position, name, health_pool, attack_points, defense_points, experience, vision_range);
        this.ability_frequency = ability_frequency;
        this.combat_ticks = 0;
    }

    @Override
    public Tile copy() {
        return new Boss(tile,position,name,health_pool,attack_points,defense_points,experience,vision_range,ability_frequency);
    }

    public void SpecialAbility(GameBoard gameBoard){
        Tile player = gameBoard.findPlayer(this, vision_range);
        player.combatEnemy(this, gameBoard, true);
    }
    public  String getSpecialAbility(){
        return "shooting";
    }
    public void updateSpecialAbility(){

    }

    public int specialAbilityPower(){
        return attack_points;
    }

    public void enemyTick(GameBoard gameBoard) {
        if (gameBoard.isLivingPlayer()) {
            Tile player = gameBoard.findPlayer(this, vision_range);
            if (player != null) {
                if (combat_ticks==ability_frequency){
                    combat_ticks=0;
                    Step specialAbility=new CastSpecialAbility();
                    specialAbility.step(this,gameBoard);
                }
                else {
                    combat_ticks++;
                    int dx = position.getX() - player.position.getX();
                    int dy = position.getY() - player.position.getY();
                    if (Math.abs(dx) > Math.abs(dy)) {
                        if (dx > 0) {
                            Left left = new Left();
                            left.step(this, gameBoard);
                        } else {
                            Right right = new Right();
                            right.step(this, gameBoard);
                        }
                    } else {
                        if (dy > 0) {
                            Up up = new Up();
                            up.step(this, gameBoard);
                        } else {
                            Down down = new Down();
                            down.step(this, gameBoard);
                        }
                    }
                }
            } else {
                combat_ticks=0;
                HashMap<Integer, Step> stepHashMap = new HashMap<>();
                stepHashMap.put(0, new Up());
                stepHashMap.put(1, new Down());
                stepHashMap.put(2, new Right());
                stepHashMap.put(3, new Left());
                stepHashMap.put(4, new DoNothing());
                int key = (int) (Math.random() * (5));
                stepHashMap.get(key).step(this, gameBoard);
            }

        }
    }

}
