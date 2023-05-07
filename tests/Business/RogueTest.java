package Business;

import CLI.MassageCallBack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class RogueTest {
    Tile rogue;
    Tile empty;
    Tile wall;
    Tile monster;
    Tile monster1;
    Tile trap;
    LinkedList<Tile> tiles;
    LinkedList<Tile> enemies;
    GameBoard gameBoard;
    @BeforeEach
    void setUp() {
        MassageCallBack m= (s) -> {};
        rogue = new Rogue(m,new Position(1,1),"Rogue", 150, 100, 2, 20);
        empty = new Empty(new Position(0,1));
        wall = new Wall(new Position(2,1));
        monster = new Monster(m,'s',new Position(1,2), "Lannister Solider", 80, 8, 0,25, 3);
        monster1 = new Monster(m,'s',new Position(1,0), "Lannister Solider", 80, 8, 0,25, 3);
        trap = new Trap(m,'D',new Position(0,0), "Death Trap", 500, 100, 0, 250, 1, 1);
        tiles = new LinkedList<>();
        tiles.addLast(rogue);
        tiles.addLast(empty);
        tiles.addLast(wall);
        tiles.addLast(monster);
        tiles.addLast(monster1);
        tiles.addLast(trap);
        enemies = new LinkedList<>();
        enemies.addLast(monster);
        enemies.addLast(monster1);
        enemies.addLast(trap);
        gameBoard = new GameBoard(tiles,enemies,rogue);
    }

    @Test
    void specialAbility() {
        rogue.playerTick('e', gameBoard);
        assertTrue((((Unit)monster).health_amount< ((Unit)monster).health_pool &
                ((Unit)monster1).health_amount< ((Unit)monster1).health_pool &
                ((Unit)trap).health_amount< ((Unit)trap).health_pool));
    }
}