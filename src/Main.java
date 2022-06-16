import java.util.Iterator;
import java.util.LinkedList;
import Controller.*;

public class Main {
    public static void main (String [] args){

    GameController gameController = new GameController("C:\\Users\\kfir1\\IdeaProjects\\OOP-Assigment3\\levels_dir");
    gameController.getView().printPlayerList(gameController.getTileFactory().getPlayersList());
    gameController.selectPlayer();
//    GameBoard gameBoard = gameController.buildBoard(gameController.levels_dir+"\\level"+1+".txt");
//    gameBoard.printGameBoard();
    gameController.startGame();
//        Tile warrior=new Warrior(new Position(1,1),"kfir",300,30,4,3);
//        Tile rogue=new Rogue(new Position(10,1),"SHRIECK", 450,40,2,20);
//        Tile monster=new Monster('s',new Position(0,1),"mon",20,8,3,40,3);
//        Tile trap=new Trap('t',new Position(1,2),"trap",250,50,10,100,2,1);
//        Tile empty=new Empty(new Position(2,1));
//        Tile empty1=new Empty(new Position(0,0));
//        Tile wall=new Wall(new Position(1,2));
//        LinkedList<Tile> tiles = new LinkedList<>();
//        tiles.addLast(rogue);
//        tiles.addLast(monster);
//        tiles.addLast(empty);
//        tiles.addLast(empty1);
//        tiles.addLast(trap);
//        GameBoard gameBoard=new GameBoard(tiles);
//        System.out.println(trap.tile);
//        trap.enemyTick(gameBoard);
//        System.out.println(trap.tile);
//        trap.enemyTick(gameBoard);
//        System.out.println(trap.tile);
//        System.out.println(rogue.position.getX() + " " + rogue.position.getY());
//        System.out.println(trap.position.getX() + " " + trap.position.getY());
//        System.out.println(rogue.getHealth_amount());
//        System.out.println();
//        System.out.println(((Player)rogue).getExperience());
//        rogue.playerTick('w',gameBoard);
//        System.out.println(rogue.position.getX() + " " + rogue.position.getY());
//        System.out.println(trap.position.getX() + " " + trap.position.getY());
//        System.out.println(trap.getHealth_amount());
//        System.out.println(((Rogue)rogue).getCurrent_energy());
//        System.out.println(((Player)rogue).getExperience());
//        System.out.println(((Player)rogue).getLevel());
//        System.out.println(rogue.getHealth_amount());
//        Tile check=null;
//        for (Tile tile: tiles) {
//            if (tile.position.equals(new Position(0, 1))) {
//                check = tile;
//            }
//        }
//        System.out.println(check instanceof Enemy);
//        System.out.println(check instanceof Empty);
    }
}
