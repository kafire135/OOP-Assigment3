import java.util.Objects;

public abstract class Tile {

    protected char tile;
    protected Position position;


    public Tile(char tile, Position position) {
        this.tile = tile;
        this.position = position;
    }


    public char getTile() {
        return tile;
    }

    public void setTile(char tile) {
        this.tile = tile;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position) {
        this.position=position;
    }

    public abstract void moveTo(Tile other, GameBoard gameBoard);
    public abstract void moveFowardMe(Unit unit, GameBoard gameBoard);
    public abstract void combat(Unit unit, GameBoard gameBoard);
    public abstract void combatPlayer(Player player, GameBoard gameBoard, boolean special_ability);
    public abstract void combatEnemy(Enemy enemy, GameBoard gameBoard);
    public abstract boolean canYouAttackMe(Player player);
    public abstract void SpecialAbility(GameBoard gameBoard);
    public abstract String getSpecialAbility();
    public abstract void updateSpecialAbility();
    public abstract String getName();
    public abstract int getHealth_pool();
    public abstract int getHealth_amount();
    public abstract int getAttack_points();
    public abstract int getDefense_points();
    public abstract int getExperience();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile1 = (Tile) o;
        return tile == tile1.tile && position.equals(tile1.position);
    }

}
