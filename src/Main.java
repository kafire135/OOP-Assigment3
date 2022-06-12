import java.util.LinkedList;

public class Main {
    public static void main (String [] args){
        Tile warrior=new Warrior(new Position(1,1),"kfir",300,30,4,3);
        Tile monster=new Monster('s',new Position(0,1),"mon",80,8,3,25,3);
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
        Step left=new Left();
        left.step(warrior,gameBoard);
        System.out.println(warrior.position.getX() + " " + warrior.position.getY());
        System.out.println(monster.position.getX() + " " + monster.position.getY());
        System.out.println();

    }
}
