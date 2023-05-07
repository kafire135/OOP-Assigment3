package Controller;
import Business.*;
import CLI.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;

public class GameController {



    private final View view;
    private final TileFactory tileFactory;
    private final String levels_dir;


    public GameController(String path) {
        view = new View();
        tileFactory = new TileFactory(view.getM());
        levels_dir=path;
    }

    public void selectPlayer(){
        Scanner input = new Scanner(System.in);
        String option=input.nextLine();
        while (!checkPlayerInput(option)){
            option=input.nextLine();
        }
        tileFactory.addPlayerToTileList(Integer.parseInt(option));
    }

    public boolean checkPlayerInput(String option){
        try{
            int output= Integer.parseInt(option);
            if (output<1 || output>tileFactory.getPlayersList().size()){
                return false;
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    private LinkedList<String> readAllLines(String path) throws FileNotFoundException {
        LinkedList<String> lines = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String next;
            while ((next = reader.readLine()) != null) {
                lines.addLast(next);
            }
        } catch (FileNotFoundException e) {
            System.out.println ("File not found " + path);
            throw e;
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return lines;
    }

    public GameBoard buildBoard(String path) throws FileNotFoundException {
        LinkedList<Tile> tiles =new LinkedList<>();
        LinkedList<Tile> enemies =new LinkedList<>();
        LinkedList<String> lines = readAllLines(path);
        for (int i = 0; i < lines.size() ; i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                Tile tile= tileFactory.createTile(line.charAt(j));
                if (tile==null){
                    throw new IllegalArgumentException("illegal file");
                }
                tile.setPosition(new Position(j,i));
                tiles.addLast(tile);
                if(tile.getTile()!='.' && tile.getTile()!='@' && tile.getTile()!='#'){
                    enemies.addLast(tile);
                }
            }
        }
        return new GameBoard(tiles,enemies,tileFactory.getSelected());
    }

    public void printPlayerList(){
        view.print("Select player:");
        HashMap<Integer,Player> playersList = tileFactory.getPlayersList();
        for (int i = 1; i <=playersList.size() ; i++) {
            view.print(i+". "+playersList.get(i).describe());
        }
    }

    public boolean playersAndEnemiesOnTheBoard(GameBoard gameBoard){
        return (gameBoard.isLivingPlayer() && !gameBoard.getEnemies().isEmpty());
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

    public void startGame() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        boolean deadPlayer=false;
        for (int i = 1; i <=4 ; i++) {
            GameBoard gameBoard=buildBoard(levels_dir+"\\level"+i+".txt");
            LinkedList <Tile> tiles;
            tiles = gameBoard.getTiles().stream().sorted(Tile::compareTo)
                    .collect(Collectors.toCollection(LinkedList::new));
            gameBoard.setTiles(tiles);
            view.printGameBoard(gameBoard.getTiles());
            view.getM().send("\n"+tileFactory.getSelected().describe());
            while (playersAndEnemiesOnTheBoard(gameBoard)){
                String option=input.nextLine();
                Character step = legalStep(option);
                if (step!=null){
                    tileFactory.getSelected().playerTick(step,gameBoard);
                    gameBoard.getEnemies().forEach((tile) -> tile.enemyTick(gameBoard));
                    if (!gameBoard.getEnemies().isEmpty()) {
                        tiles = gameBoard.getTiles().stream().sorted(Tile::compareTo)
                                .collect(Collectors.toCollection(LinkedList::new));
                        gameBoard.setTiles(tiles);
                        view.printGameBoard(gameBoard.getTiles());
                        view.getM().send("\n" + tileFactory.getSelected().describe());
                    }
                }
            }
            if(!gameBoard.isLivingPlayer()){
                view.getM().send("GAME OVER!!!");
                deadPlayer=true;
                break;
            }
        }
        if(!deadPlayer) {
            view.getM().send("YOU WON!!!");
        }
    }
}
