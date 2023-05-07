package Business;
import java.util.HashMap;
import CLI.MassageCallBack;

public class Monster extends Enemy{

    protected int vision_range;

    public Monster(MassageCallBack m, char tile, Position position, String name, int health_pool, int attack_points, int defense_points, int experience, int vision_range) {
        super(m,tile, position, name, health_pool, attack_points, defense_points, experience);
        this.vision_range= vision_range;
    }

    @Override
    public String describe() {
        String s=name+"\t\t"+"Health: "+health_amount+"/"+health_pool+"\t\t"+"Attack: "+attack_points+"\t\t"+"Defense: "
                +defense_points+"\t\t"+"Experience: "+experience+"\n\t\t"+"Vision Range: "+vision_range;
        return s;
    }

    public void enemyTick(GameBoard gameBoard) {
        if (gameBoard.isLivingPlayer()) {
            Tile player = gameBoard.findPlayer(this, vision_range);
            if (player != null) {
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
            } else {
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

    @Override
    public Tile copy() {
        return new Monster(m,tile,position,name,health_pool,attack_points,defense_points,experience,vision_range);
    }

}
