package Business;

import CLI.MassageCallBack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {
    Tile hunter;
    Tile empty;
    Tile wall;
    Tile monster;
    Tile monster1;
    Tile trap;
    Tile monster2;
    Tile monster3;
    Tile trap1;
    LinkedList<Tile> tiles;
    LinkedList<Tile> enemies;
    GameBoard gameBoard;
    @BeforeEach
    void setUp() {
        MassageCallBack m= (s) -> {};
        hunter = new Hunter(m,new Position(1,1),"Hunter", 220, 30, 2, 6);
        empty = new Empty(new Position(0,1));
        wall = new Wall(new Position(2,1));
        monster = new Monster(m,'s',new Position(1,3), "Lannister Solider", 80, 8, 0,25, 3);
        monster1 = new Monster(m,'s',new Position(1,0), "Lannister Solider", 80, 8, 0,25, 3);
        monster2 = new Monster(m,'s',new Position(10,10), "Lannister Solider", 80, 8, 0,25, 3);
        monster3 = new Monster(m,'s',new Position(11,11), "Lannister Solider", 80, 8, 0,25, 3);
        trap = new Trap(m,'D',new Position(0,0), "Death Trap", 500, 100, 20, 250, 1, 1);
        trap1 = new Trap(m,'D',new Position(9,9), "Death Trap", 500, 100, 20, 250, 1, 1);
        tiles = new LinkedList<>();
        enemies = new LinkedList<>();
        tiles.addLast(hunter);
        tiles.addLast(empty);
        tiles.addLast(wall);
        tiles.addLast(monster);
        tiles.addLast(monster1);
        tiles.addLast(trap);
        enemies.addLast(monster);
        enemies.addLast(trap);
        gameBoard = new GameBoard(tiles,enemies,hunter);
    }

    @Test
    void specialAbility() {
        hunter.playerTick('e', gameBoard);
        assertTrue((((Unit)monster).health_amount< ((Unit)monster).health_pool |
                ((Unit)trap).health_amount< ((Unit)trap).health_pool));
        assertFalse((((Unit)monster).health_amount< ((Unit)monster).health_pool &
                ((Unit)trap).health_amount< ((Unit)trap).health_pool));
    }
}