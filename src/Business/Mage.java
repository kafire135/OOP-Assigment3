package Business;
import CLI.MassageCallBack;
public class Mage extends Player {

    private int mana_pool;
    private int current_mana;
    private final int mana_cost;
    private int spell_power;
    private final int hits_count;
    private final int ability_range;

    public Mage(MassageCallBack m,Position position, String name, int health_pool, int attack_points, int defense_points,
                int mana_pool, int mana_cost, int spell_power, int hits_count, int ability_range){
        super(m,position, name, health_pool, attack_points,defense_points);
        this.mana_pool= mana_pool;
        this.current_mana = (mana_pool/4);
        this.mana_cost = mana_cost;
        this.spell_power = spell_power;
        this.hits_count = hits_count;
        this.ability_range = ability_range;
    }

    public void checkLevelUp(){
        if (experience>=50*level) {
            super.checkLevelUp();
            mana_pool = mana_pool + (25*level);
            current_mana = Math.min(mana_pool, current_mana+ (mana_pool/4));
            spell_power = spell_power + (10*level);
            m.send(name+" reached level "+level+": +"+(10*level)+" Health, +"+(4*level)+" Attack, +"+level+" Defense, +"+(25*level)+" maximum mana, +"+(10*level)+" spell power");
            if(experience>=50*level){
                checkLevelUp();
            }
        }
    }


    @Override
    public void SpecialAbility(GameBoard gameBoard) {
        if(current_mana>= mana_cost){
            m.send(name+" cast "+getSpecialAbility()+".");
            current_mana = current_mana - mana_cost;
            int hits=0;
            Tile enemy = gameBoard.findRandomEnemy(this,ability_range);
            while(hits<hits_count & enemy!= null){
                enemy.combatPlayer(this,gameBoard,true);
                hits = hits +1;
                enemy = gameBoard.findRandomEnemy(this,ability_range);
            }
        }
        else {
            m.send(name+" tried to cast "+getSpecialAbility()+", but there was not enough mana: "+current_mana+"/"+mana_cost+".");
        }
    }
    @Override
    public int specialAbilityPower() {
        return spell_power;
    }

    @Override
    public String getSpecialAbility() {
        return "Blizzard";
    }

    @Override
    public void updateSpecialAbility() {
        current_mana = Math.min(mana_pool, current_mana + level);
    }

    @Override
    public String describe() {
        String s=name+"\t\t"+"Health: "+health_amount+"/"+health_pool+"\t\t"+"Attack: "+attack_points+"\t\t"+"Defense: "
                +defense_points+"\t\t"+"Level: "+level+"\n\t\t"
                +"Experience: "+experience+"/"+50*level+"\t\t"+"Mana: "+current_mana+"/"+mana_pool+"\t\t"+"Spell Power: "+spell_power;
        return s;
    }

    @Override
    public Tile copy() {
        return new Mage(m,position,name,health_pool,attack_points,defense_points,mana_pool,mana_cost,spell_power,hits_count,ability_range);
    }

}
