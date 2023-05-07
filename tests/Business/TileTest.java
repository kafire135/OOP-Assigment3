package Business;

import CLI.MassageCallBack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
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
        warrior = new Warrior(m,new Position(1,1),"Jon Snow", 300, 30, 4, 3);
        empty = new Empty(new Position(0,1));
        wall = new Wall(new Position(2,1));
        monster = new Monster(m,'s',new Position(1,0), "Lannister Solider", 80, 8, 3,25, 3);
        trap = new Trap(m,'D',new Position(0,0), "Death Trap", 500, 100, 20, 250, 1, 1);
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
    void moveTo_empty() {
        warrior.moveTo(empty,gameBoard);
        assertEquals(0,warrior.position.getX());
        assertEquals(1,warrior.position.getY());
        assertEquals(1,empty.position.getX());
        assertEquals(1,empty.position.getY());
    }

    @Test
    void moveTo_wall() {
        warrior.moveTo(wall,gameBoard);
        assertEquals(1,warrior.position.getX());
        assertEquals(1,warrior.position.getY());
        assertEquals(2,wall.position.getX());
        assertEquals(1,wall.position.getY());
    }

    @Test
    void moveTo_enemy() {
        warrior.moveTo(monster,gameBoard);
        assertEquals(1,warrior.position.getX());
        assertEquals(1,warrior.position.getY());
        assertEquals(1,monster.position.getX());
        assertEquals(0,monster.position.getY());
    }

    @Test
    void combat_player_vs_enemy() {
        warrior.combat((Unit)monster,gameBoard);
        assertTrue(((Unit) monster).health_amount<=((Unit) monster).health_pool);
    }

    @Test
    void combat_enemy_vs_player() {
        monster.combat((Unit)warrior,gameBoard);
        assertTrue(((Unit) warrior).health_amount<=((Unit) warrior).health_pool);
    }

    @Test
    void combat_enemy_vs_enemy() {
        monster.combat((Unit)trap,gameBoard);
        assertEquals(((Unit) trap).health_pool, ((Unit) trap).health_amount);
    }

    @Test
    void playerTick_left() {
        warrior.playerTick('a',gameBoard);
        assertEquals(0,warrior.position.getX());
        assertEquals(1,warrior.position.getY());
        assertEquals(1,empty.position.getX());
        assertEquals(1,empty.position.getY());
    }

    @Test
    void playerTick_right() {
        warrior.playerTick('d',gameBoard);
        assertEquals(1,warrior.position.getX());
        assertEquals(1,warrior.position.getY());
        assertEquals(2,wall.position.getX());
        assertEquals(1,wall.position.getY());
    }

    @Test
    void playerTick_up() {
        warrior.playerTick('w',gameBoard);
        assertEquals(1,warrior.position.getX());
        assertEquals(1,warrior.position.getY());
        assertEquals(1,monster.position.getX());
        assertEquals(0,monster.position.getY());
        assertTrue(((Unit) monster).health_amount<=((Unit) monster).health_pool);
    }

    @Test
    //will combat vs player
    void enemyTick_monster() {
        monster.enemyTick(gameBoard);
        assertTrue(((Unit) warrior).health_amount<=((Unit) warrior).health_pool);
    }

    @Test
    void enemyTick_trap() {
        trap.enemyTick(gameBoard);
        assertEquals('.',trap.tile);
        trap.enemyTick(gameBoard);
        assertEquals('D',trap.tile);
    }
}