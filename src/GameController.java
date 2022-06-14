import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.*;

public class GameController {

    private final TileFactory tileFactory;
    private final String levels_dir;


    public GameController(TileFactory tileFactory, String path) {
        this.tileFactory = tileFactory;
        levels_dir=path;
    }

    public void selectPlayer(){
        Scanner input = new Scanner(System.in);
        int option=input.nextInt();
        while (option<1 || option>tileFactory.getPlayersList().size()){
            option=input.nextInt();
        }
        tileFactory.setSelected(tileFactory.getPlayersList().get(option));
    }

    private LinkedList<String> readAllLines(String path) {
        LinkedList<String> lines = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String next;
            while ((next = reader.readLine()) != null) {
                lines.addLast(next);
            }
        } catch (FileNotFoundException e) {
            System.out.println ("File not found " + path);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return lines;
    }

    public GameBoard buildBoard(String path){
        LinkedList<Tile> tiles =new LinkedList<>();
        LinkedList<String> lines = readAllLines(path);
        for (int i = 0; i < lines.size() ; i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                Tile tile= tileFactory.createTile(line.charAt(j));
                if (tile==null){
                    throw new IllegalArgumentException("illegal file");
                }
                tile.position=new Position(i,j);
                tiles.addLast(tile);
            }
        }
        int length = lines.size();
        int width = lines.getFirst().length();
        return new GameBoard(tiles,length,width);
    }

    public boolean playersAndEnemiesOnTheBoard(GameBoard gameBoard){
        return (gameBoard.isLivingPlayer() && gameBoard.getEnemiesCount()>0);
    }

    public Character legalStep(String input){
        if(input.length()==1){
            String stepOpartion="asdqwe";
            char output=input.charAt(0);
            if(stepOpartion.indexOf(output)!=-1){
                return output;
            }
        }
        return null;
    }

    public void startGame(){
        Scanner input = new Scanner(System.in);
        for (int i = 1; i <=4 ; i++) {
            GameBoard gameBoard=buildBoard(levels_dir+"\\level"+i);
            gameBoard.setTiles(gameBoard.getTiles().stream().sorted(Tile::compareTo)
                    .collect(Collectors.toCollection(LinkedList::new)));
            gameBoard.printGameBoard();
            while (playersAndEnemiesOnTheBoard(gameBoard)){
                String option=input.nextLine();
                Character step = legalStep(option);
                if (step!=null){
                    gameBoard.getTiles().forEach((tile) -> tile.playerTick(step,gameBoard));
                    gameBoard.getTiles().forEach((tile) -> tile.enemyTick(gameBoard));
                    gameBoard.setTiles(gameBoard.getTiles().stream().sorted(Tile::compareTo)
                            .collect(Collectors.toCollection(LinkedList::new)));
                    gameBoard.printGameBoard();
                }
            }
            if(gameBoard.isLivingPlayer()){
                System.out.println("GAME OVER");//replace with final massage
            }
        }
        System.out.println("YOU WON!!!");//replace with final massage
    }
}
