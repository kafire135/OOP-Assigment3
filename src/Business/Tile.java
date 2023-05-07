package Business;

public abstract class Tile {

    protected char tile;
    protected Position position;


    public Tile(char tile, Position position) {
        this.tile = tile;
        this.position = position;
    }

    public int compareTo(Tile tile2) {
        return position.compareTo(tile2.position);
    }

    public char getTile() {
        return tile;
    }

    public void setTile(char tile) {
        this.tile = tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void moveTo(Tile other, GameBoard gameBoard);

    public abstract void moveFowardMe(Unit unit, GameBoard gameBoard);

    public abstract void combat(Unit unit, GameBoard gameBoard);

    public abstract void combatPlayer(Player player, GameBoard gameBoard, boolean special_ability);

    public abstract void combatEnemy(Enemy enemy, GameBoard gameBoard, boolean special_ability);

    public abstract void SpecialAbility(GameBoard gameBoard);

    public abstract void playerTick(char step, GameBoard gameBoard);

    public abstract void enemyTick(GameBoard gameBoard);

    public String toString() {
        String s = "" + tile;
        return s;
    }

    public String getName() {
        return null;
    }

    public abstract Tile copy();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile1 = (Tile) o;
        return tile == tile1.tile && position.equals(tile1.position);
    }

}