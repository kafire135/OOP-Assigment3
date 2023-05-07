package Business;
import CLI.MassageCallBack;
public class Hunter extends Player{

    private final int range;
    private int arrows_count;
    private int tick_count;

    public Hunter(MassageCallBack m,Position position, String name, int health_pool, int attack_points, int defense_points, int range) {
        super(m, position, name, health_pool, attack_points, defense_points);
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
            m.send(name+" reached level "+level+": +"+(10*level)+" Health, +"+(6*level)+" Attack, +"+(2*level)+" Defense");
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
                m.send(name+" fired an arrow at "+enemy.getName()+".");
                enemy.combatPlayer(this, gameBoard, true);
                arrows_count--;
            }
            else {
                m.send(name+ " tried to shoot an arrow but there were no enemies in range.");
            }
        }
        else {
            m.send(name +" tried to shoot an arrow but there was not enough arrows: "+arrows_count);
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
        String s=name+"\t\t"+"Health: "+health_amount+"/"+health_pool+"\t\t"+"Attack: "+attack_points+"\t\t"+"Defense: "
                +defense_points+"\t\t"+"Level: "+level+"\n\t\t"
                +"Experience: "+experience+"/"+50*level+"\t\t"+"Arrows: "+arrows_count+"\t\t"+"Range: "+range;
        return s;
    }

    @Override
    public Tile copy() {
        return new Hunter(m,position,name,health_pool,attack_points,defense_points,range);
    }
}
