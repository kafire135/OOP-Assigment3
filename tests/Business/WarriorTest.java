package Business;

import CLI.MassageCallBack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    Tile warrior;
    Tile empty;
    Tile wall;
    Tile monster;
    Tile trap;
    LinkedList<Tile> tiles;
    LinkedList<Tile> enemies;
    GameBoard gameBoard;


    @BeforeEach
    void setUp() {
        MassageCallBack m= (s) -> {};
        warrior = new Warrior(m,new Position(1,1),"Jon Snow", 300, 100, 4, 3);
        empty = new Empty(new Position(0,1));
        wall = new Wall(new Position(2,1));
        monster = new Monster(m,'s',new Position(1,0), "Lannister Solider", 80, 8, 0,25, 3);
        trap = new Trap(m,'D',new Position(0,0), "Death Trap", 500, 100, 0, 250, 1, 1);
        tiles = new LinkedList<>();
        tiles.addLast(warrior);
        tiles.addLast(empty);
        tiles.addLast(wall);
        tiles.addLast(monster);
        tiles.addLast(trap);
        enemies = new LinkedList<>();
        enemies.addLast(monster);
        enemies.addLast(trap);
        gameBoard = new GameBoard(tiles,enemies,warrior);

    }
    @Test
    void specialAbility() {
        warrior.playerTick('e', gameBoard);
        assertTrue((((Unit)monster).health_amount< ((Unit)monster).health_pool |
                ((Unit)trap).health_amount< ((Unit)trap).health_pool));
        assertFalse((((Unit)monster).health_amount< ((Unit)monster).health_pool &
                ((Unit)trap).health_amount< ((Unit)trap).health_pool));
    }
}