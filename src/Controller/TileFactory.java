package Controller;
import Business.*;
import CLI.*;

import java.util.HashMap;

public class TileFactory {
    private HashMap <Integer, Player> playersList;
    private HashMap <Character, Tile> tileList;
    private Player selected;
    private MassageCallBack m;

    public TileFactory(MassageCallBack m){
        this.m=m;
        playersList = initPlayers();
        tileList = initTiles();
    }

    private HashMap <Integer, Player> initPlayers() {
        HashMap<Integer,Player> players = new HashMap<>();
        players.put(1,new Warrior(m,new Position(0,0),"Jon Snow", 300, 30, 4, 3));
        players.put(2,new Warrior(m,new Position(0,0),"The Hound", 400, 20, 6, 5));
        players.put(3,new Mage(m,new Position(0,0),"Melisandre", 100, 5, 1, 300, 30, 15, 5, 6));
        players.put(4,new Mage(m,new Position(0,0),"Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4));
        players.put(5,new Rogue(m,new Position(0,0),"Arya Stark", 150, 40, 2, 20));
        players.put(6,new Rogue(m,new Position(0,0),"Bronn", 250, 35, 3, 50));
        players.put(7,new Hunter(m,new Position(0,0),"Ygritte", 220, 30, 2, 6));
        return players;
    }

    private HashMap<Character,Tile> initTiles(){
        HashMap<Character, Tile> tiles = new HashMap<>();
        tiles.put('s',new Monster(m,'s',new Position(0,0), "Lannister Solider", 80, 8, 3,25, 3));
        tiles.put('k',new Monster(m,'k',new Position(0,0), "Lannister Knight", 200, 14, 8, 50,   4));
        tiles.put('q',new Monster(m,'q',new Position(0,0), "Queen's Guard", 400, 20, 15, 100,  5));
        tiles.put('z',new Monster(m,'z',new Position(0,0), "Wright", 600, 30, 15,100, 3));
        tiles.put('b',new Monster(m,'b',new Position(0,0), "Bear-Wright", 1000, 75, 30, 250,  4));
        tiles.put('g',new Monster(m,'g',new Position(0,0), "Giant-Wright",1500, 100, 40,500,   5));
        tiles.put('w',new Monster(m,'w',new Position(0,0), "White Walker", 2000, 150, 50, 1000, 6));
        tiles.put('M',new Boss(m,'M',new Position(0,0), "The Mountain", 1000, 60, 25,  500, 6, 5));
        tiles.put('C',new Boss(m,'C',new Position(0,0), "Queen Cersei", 100, 10, 10,1000, 1, 8));
        tiles.put('K',new Boss(m,'K',new Position(0,0), "Night's King", 5000, 300, 150, 5000, 8, 3));
        tiles.put('B',new Trap(m,'B',new Position(0,0), "Bonus Trap", 1, 1, 1, 250,  1, 10));
        tiles.put('Q',new Trap(m,'Q',new Position(0,0), "Queen's Trap", 250, 50, 10, 100, 3, 10));
        tiles.put('D',new Trap(m,'D',new Position(0,0), "Death Trap", 500, 100, 20, 250, 1, 10));
        tiles.put('.',new Empty(new Position(0,0)));
        tiles.put('#',new Wall(new Position(0,0)));
        return tiles;
    }
    public void addPlayerToTileList(int option){
        selected=playersList.get(option);
        tileList.put('@',selected);
        m.send("You have selected:"+"\n"+selected.getName());
    }

    public HashMap<Integer, Player> getPlayersList() {
        return playersList;
    }

    public Player getSelected() {
        return selected;
    }

    public Tile createTile(char option){
        try {
            Tile tile=tileList.get(option);
            if (tile.getTile()=='@'){
                return tile;
            }
            return tile.copy();
        }
        catch (Exception e){
            return null;
        }
    }

}