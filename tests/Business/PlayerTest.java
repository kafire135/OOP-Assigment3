package Business;

import CLI.MassageCallBack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Tile warrior;
    Tile empty;
    Tile monster1;
    Tile monster2;
    Tile trap;
    LinkedList<Tile> tiles;
    LinkedList<Tile> enemies;
    GameBoard gameBoard;
    @BeforeEach
    void setUp() {
        MassageCallBack m= (s) -> {};
        warrior = new Warrior(m,new Position(1,1),"Jon Snow", 300, 100, 4, 3);
        empty = new Empty(new Position(0,1));
        monster1 = new Monster(m,'s',new Position(2,1), "Lannister Solider", 200, 8, 0,25, 3);
        monster2 = new Monster(m,'s',new Position(1,0), "Lannister Solider", 1, 8, 0,25, 3);
        trap = new Trap(m,'D',new Position(0,0), "Death Trap", 1, 100, 0, 25, 1, 1);
        tiles = new LinkedList<>();
        tiles.addLast(warrior);
        tiles.addLast(empty);
        tiles.addLast(monster1);
        tiles.addLast(monster2);
        tiles.addLast(trap);
        enemies = new LinkedList<>();
        enemies.addLast(monster1);
        enemies.addLast(monster2);
        enemies.addLast(trap);
        gameBoard = new GameBoard(tiles,enemies,warrior);
    }

    @Test
    void combat() {
        warrior.playerTick('d',gameBoard);
        assertEquals(1,warrior.position.getX());
        assertEquals(1,warrior.position.getY());
        assertEquals(2,monster1.position.getX());
        assertEquals(1,monster1.position.getY());
        assertTrue(((Unit) monster1).health_amount<=((Unit) monster1).health_pool);
    }

    @Test
    void combatEnemyDied() {
        warrior.playerTick('w',gameBoard);
        assertEquals(1,warrior.position.getX());
        assertEquals(0,warrior.position.getY());
        assertTrue(((Unit) monster1).health_amount<=((Unit) monster1).health_pool);
        Tile check = gameBoard.Search(new Position(1,1));
        assertTrue(check instanceof Empty);
        assertEquals(25,((Warrior)warrior).experience);
    }

    @Test
    void combatEnemyDiedAndLevelUp() {
        warrior.playerTick('w',gameBoard);
        assertEquals(1,warrior.position.getX());
        assertEquals(0,warrior.position.getY());
        assertTrue(((Unit) monster1).health_amount<=((Unit) monster1).health_pool);
        Tile check = gameBoard.Search(new Position(1,1));
        assertTrue(check instanceof Empty);
        assertEquals(25,((Warrior)warrior).experience);
        warrior.playerTick('a',gameBoard);
        assertEquals(0,warrior.position.getX());
        assertEquals(0,warrior.position.getY());
        Tile check2 = gameBoard.Search(new Position(1,0));
        assertTrue(check2 instanceof Empty);
        assertEquals(0,((Warrior)warrior).experience);
        assertEquals(2,((Warrior)warrior).level);
        assertEquals(330,((Warrior)warrior).health_amount);
        assertEquals(112,((Warrior)warrior).attack_points);
        assertEquals(8,((Warrior)warrior).defense_points);
    }
}