import Controller.*;

public class Main {
    public static void main (String [] args){
        GameController gameController = new GameController(args[2]);
        gameController.getView().printPlayerList(gameController.getTileFactory().getPlayersList());
        gameController.selectPlayer();
        gameController.startGame();
    }
}
