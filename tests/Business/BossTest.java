package Business;

import Business.*;
import CLI.MassageCallBack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class BossTest {
    Tile warrior;
    Tile empty;
    Tile wall;
    Tile boss;
    Tile trap;
    LinkedList<Tile> tiles;
    LinkedList<Tile> enemies;
    GameBoard gameBoard;
    @BeforeEach
    void setUp() {
        MassageCallBack m= (s) -> {};
        warrior = new Warrior(m,new Position(1,1),"Jon Snow", 300, 30, 0, 3);
        empty = new Empty(new Position(0,1));
        wall = new Wall(new Position(2,1));
        boss = new Boss(m,'K',new Position(1,0), "Night's King", 5000, 300, 150, 5000, 8, 3);
        trap = new Trap(m,'D',new Position(0,0), "Death Trap", 500, 100, 20, 250, 1, 1);
        tiles = new LinkedList<>();
        tiles.addLast(warrior);
        tiles.addLast(empty);
        tiles.addLast(wall);
        tiles.addLast(boss);
        tiles.addLast(trap);
        enemies = new LinkedList<>();
        enemies.addLast(boss);
        enemies.addLast(trap);
        gameBoard = new GameBoard(tiles,enemies,warrior);
    }

    @Test
    void specialAbility() {
        boss.SpecialAbility(gameBoard);
        assertTrue(((Unit)warrior).health_amount<((Unit)warrior).health_pool);
    }
}