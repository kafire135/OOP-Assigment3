package Business;

import CLI.MassageCallBack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {

    Tile mage;
    Tile mage2;
    Tile empty;
    Tile wall;
    Tile monster;
    Tile monster1;
    Tile trap;
    LinkedList<Tile> tiles;
    LinkedList<Tile> enemies;
    GameBoard gameBoard1;
    GameBoard gameBoard2;

    @BeforeEach
    void setUp() {
        MassageCallBack m= (s) -> {};
        mage = new Mage(m,new Position(1,1),"Mage", 100, 100, 1, 300, 30, 15, 1, 6);
        mage2 = new Mage(m,new Position(1,1),"Mage", 100, 100, 1, 300, 30, 15, 3, 6);
        empty = new Empty(new Position(0,1));
        wall = new Wall(new Position(2,1));
        monster = new Monster(m,'s',new Position(1,2), "Lannister Solider", 80, 8, 0,25, 3);
        monster1 = new Monster(m,'s',new Position(1,0), "Lannister Solider", 80, 8, 0,25, 3);
        trap = new Trap(m,'D',new Position(0,0), "Death Trap", 500, 100, 0, 250, 1, 1);
        tiles = new LinkedList<>();
        tiles.addLast(mage);
        tiles.addLast(empty);
        tiles.addLast(wall);
        tiles.addLast(monster);
        tiles.addLast(monster1);
        tiles.addLast(trap);
        enemies = new LinkedList<>();
        enemies.addLast(monster);
        enemies.addLast(trap);
        gameBoard1 = new GameBoard(tiles,enemies,mage);
        gameBoard2 = new GameBoard(tiles,enemies,mage2);
    }

    @Test
    void updateSpecialAbility1() {
        mage.playerTick('e', gameBoard1);
        assertTrue((((Unit)monster).health_amount< ((Unit)monster).health_pool |
                ((Unit)monster1).health_amount< ((Unit)monster1).health_pool |
                ((Unit)trap).health_amount< ((Unit)trap).health_pool));
    }
    @Test
    void updateSpecialAbility2() {
        mage.playerTick('e', gameBoard2);
        assertTrue((((Unit)monster).health_amount< ((Unit)monster).health_pool |
                ((Unit)monster1).health_amount< ((Unit)monster1).health_pool |
                ((Unit)trap).health_amount< ((Unit)trap).health_pool));
    }
}