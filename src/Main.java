import Controller.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main (String [] args) throws FileNotFoundException {
        if (args.length==0){
            throw new NegativeArraySizeException("Error: this program needs a path to the levels directory as an argument.");
        }
        try {
            GameController gameController = new GameController(args[0]);
            gameController.printPlayerList();
            gameController.selectPlayer();
            gameController.startGame();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
