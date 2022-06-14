public abstract class Unit extends Tile{

    protected String name;
    protected int health_pool;
    protected int health_amount;
    protected int attack_points;
    protected int defense_points;
    protected int experience;

    public Unit(char tile, Position position,String name, int health_pool, int attack_points, int defense_points, int experience) {
        super(tile, position);
        this.name=name;
        this.health_pool=health_pool;
        this.health_amount=health_pool;
        this.attack_points=attack_points;
        this.defense_points=defense_points;
        this.experience=experience;
    }

    @Override
    public void moveTo(Tile other, GameBoard gameBoard) {
        other.moveFowardMe(this, gameBoard);
    }

    @Override
    public void moveFowardMe(Unit unit, GameBoard gameBoard) {
        unit.combat(this,gameBoard);
    }


    public abstract String describe();
    public abstract void combat(Unit unit, GameBoard gameBoard);
    public abstract void combatPlayer(Player player, GameBoard gameBoard, boolean special_ability);
    public abstract void combatEnemy(Enemy enemy, GameBoard gameBoard);
    public abstract boolean canYouAttackMe(Player player);
    public abstract boolean canYouAttackMe(Enemy enemy);
    public abstract int specialAbilityPower();
    public abstract void SpecialAbility(GameBoard gameBoard);
    public abstract String getSpecialAbility();
    public abstract void updateSpecialAbility();
    public abstract void playerTick(char step, GameBoard gameBoard);
    public abstract void enemyTick(GameBoard gameBoard);
    public abstract Tile copy();
    public static void combat (Unit attacker, Unit defender, boolean special_ability){
        int attack_Roll;
        if (special_ability){
            attack_Roll=attacker.specialAbilityPower();
        }
        else {
            attack_Roll = (int) (Math.random() * (attacker.attack_points + 1));
        }
        int defend_Roll = (int)(Math.random()*(defender.defense_points+1));
        int damage = Math.max((attack_Roll-defend_Roll),0);
        System.out.println("attack= " + attack_Roll + " defense= " + defend_Roll + " damage= " + damage);
        defender.health_amount=Math.max((defender.health_amount-damage),0);
    }

    public String getName() {
        return name;
    }

    public int getHealth_pool() {
        return health_pool;
    }

    public int getHealth_amount() {
        return health_amount;
    }

    public int getAttack_points() {
        return attack_points;
    }

    public int getDefense_points() {
        return defense_points;
    }

    public int getExperience() {
        return experience;
    }
}
