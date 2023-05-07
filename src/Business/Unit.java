package Business;
import CLI.MassageCallBack;
public abstract class Unit extends Tile{

    protected MassageCallBack m;
    protected String name;
    protected int health_pool;
    protected int health_amount;
    protected int attack_points;
    protected int defense_points;
    protected int experience;

    public Unit(MassageCallBack m, char tile, Position position,String name, int health_pool, int attack_points, int defense_points, int experience) {
        super(tile, position);
        this.m=m;
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
    public abstract void combatEnemy(Enemy enemy, GameBoard gameBoard, boolean special_ability);
    public abstract int specialAbilityPower();
    public abstract void SpecialAbility(GameBoard gameBoard);
    public abstract String getSpecialAbility();
    public abstract void updateSpecialAbility();
    public abstract void playerTick(char step, GameBoard gameBoard);
    public abstract void enemyTick(GameBoard gameBoard);
    public abstract Tile copy();
    public void defend (Unit attacker, boolean special_ability){
        if (!special_ability) {
            m.send(attacker.name + " engaged in combat with " + name + ".");
            m.send(attacker.describe());
            m.send(describe());
        }
        int attack_Roll;
        if (special_ability){
            attack_Roll=attacker.specialAbilityPower();
        }
        else {
            attack_Roll = (int) (Math.random() * (attacker.attack_points + 1));
            m.send(attacker.name+" rolled "+attack_Roll+" attack points.");
        }
        int defend_Roll = (int)(Math.random()*(defense_points+1));
        m.send(name+" rolled " +defend_Roll+" defense points.");
        int damage = Math.max((attack_Roll-defend_Roll),0);
        if (special_ability){
            m.send(attacker.name+" hit "+name+" for "+damage+" ability damage.");
        }
        else {
            m.send(attacker.name + " dealt " + damage + " damage to " + name + ".");
        }
        health_amount=Math.max((health_amount-damage),0);
    }

    public String getName() {
        return name;
    }

}
