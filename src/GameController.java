import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.*;

public class GameController {

    private TileFactory tileFactory;


    public GameController(TileFactory tileFactory) {
        this.tileFactory = tileFactory;
    }

    public void selectPlayer(){
        Scanner input = new Scanner(System.in);
        int option=input.nextInt();
        while (option<1 || option>tileFactory.getPlayersList().size()){
            option=input.nextInt();
        }
        tileFactory.setSelected(tileFactory.getPlayersList().get(option));
    }

    public GameBoard buildBoard(){
        return null;
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
            GameBoard gameBoard=buildBoard();
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
