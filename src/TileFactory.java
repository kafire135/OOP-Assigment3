import java.util.HashMap;

public class TileFactory {
    private HashMap <Integer, Player> playersList;
    private HashMap <Character, Enemy> enemiesMap;
    private Player selected;

    public TileFactory(){
        playersList = initPlayers();
        enemiesMap = initEnemies();
    }

    private HashMap<Character, Enemy> initEnemies() {
        HashMap<Character, Enemy> enemies = new HashMap<>();
        enemies.put('s',new Monster('s',new Position(0,0), "Lannister Solider", 80, 8, 3,25, 3));
        enemies.put('k',new Monster('k',new Position(0,0), "Lannister Knight", 200, 14, 8, 50,   4));
        enemies.put('q',new Monster('q',new Position(0,0), "Queen's Guard", 400, 20, 15, 100,  5));
        enemies.put('z',new Monster('z',new Position(0,0), "Wright", 600, 30, 15,100, 3));
        enemies.put('b',new Monster('b',new Position(0,0), "Bear-Wright", 1000, 75, 30, 250,  4));
        enemies.put('g',new Monster('g',new Position(0,0), "Giant-Wright",1500, 100, 40,500,   5));
        enemies.put('w',new Monster('w',new Position(0,0), "White Walker", 2000, 150, 50, 1000, 6));
//                () -> new Boss('M', "The Mountain", 1000, 60, 25,  500, 6, 5),
//                () -> new Boss('C', "Queen Cersei", 100, 10, 10,1000, 1, 8),
//                () -> new Boss('K', "Night's King", 5000, 300, 150, 5000, 8, 3),
        enemies.put('B',new Trap('B',new Position(0,0), "Bonus Trap", 1, 1, 1, 250,  1, 10));
        enemies.put('Q',new Trap('Q',new Position(0,0), "Queen's Trap", 250, 50, 10, 100, 3, 10));
        enemies.put('D',new Trap('D',new Position(0,0), "Death Trap", 500, 100, 20, 250, 1, 10));
        return enemies;
    }

    private HashMap <Integer, Player> initPlayers() {
        HashMap<Integer,Player> players = new HashMap<>();
        players.put(1,new Warrior(new Position(0,0),"Jon Snow", 300, 30, 4, 3));
        players.put(2,new Warrior(new Position(0,0),"The Hound", 400, 20, 6, 5));
        players.put(3,new Mage(new Position(0,0),"Melisandre", 100, 5, 1, 300, 30, 15, 5, 6));
        players.put(4,new Mage(new Position(0,0),"Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4));
        players.put(5,new Rogue(new Position(0,0),"Arya Stark", 150, 40, 2, 20));
        players.put(6,new Rogue(new Position(0,0),"Bronn", 250, 35, 3, 50));
//                () -> new Hunter("Ygritte", 220, 30, 2, 6)
        return players;
    }

    public HashMap<Integer, Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(HashMap<Integer, Player> playersList) {
        this.playersList = playersList;
    }

    public HashMap<Character, Enemy> getEnemiesMap() {
        return enemiesMap;
    }

    public void setEnemiesMap(HashMap<Character, Enemy> enemiesMap) {
        this.enemiesMap = enemiesMap;
    }

    public Player getSelected() {
        return selected;
    }

    public void setSelected(Player selected) {
        this.selected = selected;
    }

//    public List<Player> listPlayers(){
//        return playersList.stream().map(Supplier::get).collect(Collectors.toList());
//    }
//
//    // TODO: Add additional callbacks of your choice
//
//    public Enemy produceEnemy(char tile, Position position, ...) {
//        ...
//    }
//
//    public Player producePlayer(int idx, ...){
//		...
//    }
//
//    public Empty produceEmpty(Position position, ...){
//        ...
//    }
//
//    public Wall produceWall(Position position, ...){
//        ...
//    }
}