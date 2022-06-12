import java.util.LinkedList;

public class Main {
    public static void main (String [] args){
        Tile warrior=new Warrior(new Position(1,1),"kfir",300,30,4,3);
        Unit monster=new Monster('s',new Position(0,1),"mon",20,8,3,60,3);
        Tile empty=new Empty(new Position(2,1));
        Tile empty1=new Empty(new Position(0,0));
        Tile wall=new Wall(new Position(1,2));
        LinkedList<Tile> tiles = new LinkedList<>();
        tiles.addLast(warrior);
        tiles.addLast(monster);
        tiles.addLast(empty);
        tiles.addLast(empty1);
        tiles.addLast(wall);
        GameBoard gameBoard=new GameBoard(tiles);
        ((Monster)monster).enemyTick(gameBoard);
        System.out.println(warrior.position.getX() + " " + warrior.position.getY());
        System.out.println(monster.position.getX() + " " + monster.position.getY());
        System.out.println(warrior.getHealth_amount());
        System.out.println();
        Step special=new CastSpecialAbility();
        System.out.println(((Warrior)warrior).getExperience());
        special.step(warrior,gameBoard);
        System.out.println(warrior.position.getX() + " " + warrior.position.getY());
        System.out.println(monster.position.getX() + " " + monster.position.getY());
        System.out.println(monster.getHealth_amount());
        System.out.println(((Warrior)warrior).getRemaining_cooldown());
        System.out.println(((Warrior)warrior).getExperience());
        System.out.println(((Warrior)warrior).getLevel());
        System.out.println(warrior.getHealth_amount());
        Tile check=null;
        for (Tile tile: tiles){
            if (tile.position.equals(new Position(1,1))){
                check=tile;
            }
            System.out.println(tile.position.getX()+" "+tile.position.getY());
        }
        System.out.println(check instanceof Enemy);
        System.out.println(check instanceof Empty);
    }
}
