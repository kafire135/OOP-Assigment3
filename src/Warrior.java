public class Warrior extends Player{

    private final int ability_cooldown;
    private int remaining_cooldown;

    public Warrior(Position position, String name, int health_pool, int attack_points, int defense_points, int ability_cooldown) {
        super(position, name, health_pool, attack_points, defense_points);
        this.ability_cooldown=ability_cooldown;
        remaining_cooldown=0;
    }

    public void checkLevelUp(){
        if(experience>=50*level) {
            super.checkLevelUp();
            remaining_cooldown = 0;
            health_pool = health_pool + (5 * level);
            attack_points = attack_points + (2 * level);
            defense_points = defense_points + level;
        }
    }

    @Override
    public void SpecialAbility(GameBoard gameBoard) {
        if (remaining_cooldown==0){
            Tile enemy = gameBoard.findEnemy(this,3);
            if (enemy!=null) {
                enemy.combatPlayer(this, gameBoard, true);
            }
            remaining_cooldown=ability_cooldown+1;
            health_amount=Math.min(health_pool,health_amount+(10*defense_points));
        }

    }

    @Override
    public int specialAbilityPower() {
        return health_pool/10;
    }

    @Override
    public String getSpecialAbility() {
        return "Avenger's Shield";
    }

    @Override
    public void updateSpecialAbility() {
        remaining_cooldown=Math.max(0,remaining_cooldown-1);
    }

    @Override
    public String describe() {
        return null;
    }

    public int getAbility_cooldown() {
        return ability_cooldown;
    }

    public int getRemaining_cooldown() {
        return remaining_cooldown;
    }
}
