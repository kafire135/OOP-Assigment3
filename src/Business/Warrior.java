package Business;
import CLI.MassageCallBack;
public class Warrior extends Player{

    private final int ability_cooldown;
    private int remaining_cooldown;

    public Warrior(MassageCallBack m,Position position, String name, int health_pool, int attack_points, int defense_points, int ability_cooldown) {
        super(m,position, name, health_pool, attack_points, defense_points);
        this.ability_cooldown=ability_cooldown;
        remaining_cooldown=0;
    }

    public void checkLevelUp(){
        if(experience>=50*level) {
            super.checkLevelUp();
            remaining_cooldown = 0;
            health_pool = health_pool + (5 * level);
            health_amount = health_pool;
            attack_points = attack_points + (2 * level);
            defense_points = defense_points + level;
            m.send(name+" reached level "+level+": +"+(15*level)+" Health, +"+(6*level)+" Attack, +"+(2*level)+" Defense");
            if(experience>=50*level){
                checkLevelUp();
            }
        }
    }

    @Override
    public void SpecialAbility(GameBoard gameBoard) {
        if (remaining_cooldown==0){
            m.send(name+" used "+getSpecialAbility()+", healing for "+(10*defense_points)+".");
            Tile enemy = gameBoard.findRandomEnemy(this,3);
            if (enemy!=null) {
                enemy.combatPlayer(this, gameBoard, true);
            }
            remaining_cooldown=ability_cooldown+1;
            health_amount=Math.min(health_pool,health_amount+(10*defense_points));
        }
        else {
            m.send(name+ " tried to cast "+getSpecialAbility()+", but there is a cooldown: "+remaining_cooldown+".");
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
        String s=name+"\t\t"+"Health: "+health_amount+"/"+health_pool+"\t\t"+"Attack: "+attack_points+"\t\t"+"Defense: "
                +defense_points+"\t\t"+"Level: "+level+"\n\t\t"
                +"Experience: "+experience+"/"+50*level+"\t\t"+"Cooldown: "+remaining_cooldown+"/"+ability_cooldown;
        return s;
    }

    @Override
    public Tile copy() {
        return new Warrior(m,position,name,health_pool,attack_points,defense_points,ability_cooldown);
    }

}
