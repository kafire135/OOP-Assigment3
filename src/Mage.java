public class Mage extends Player {

    private int mana_pool;
    private int current_mana;
    private final int mana_cost;
    private int spell_power;
    private final int hits_count;
    private final int ability_range;

    public Mage(Position position, String name, int health_pool, int attack_points, int defense_points,
                int mana_pool, int mana_cost, int spell_power, int hits_count, int ability_range){
        super(position, name, health_pool, attack_points,defense_points);
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
        }
    }


    @Override
    public void SpecialAbility(GameBoard gameBoard) {
        if(current_mana>= mana_cost){
            current_mana = current_mana - mana_cost;
            int hits=0;
            Tile enemy = gameBoard.findEnemy(this,ability_range);
            while(hits<hits_count & enemy!= null){
                enemy.combatPlayer(this,gameBoard,true);
                hits = hits +1;
                enemy = gameBoard.findEnemy(this,ability_range);
            }
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
        return null;
    }

    public int getMana_pool() {
        return mana_pool;
    }

    public int getCurrent_mana() {
        return current_mana;
    }

    public int getMana_cost() {
        return mana_cost;
    }

    public int getSpell_power() {
        return spell_power;
    }

    public int getHits_count() {
        return hits_count;
    }

    public int getAbility_range() {
        return ability_range;
    }
}
