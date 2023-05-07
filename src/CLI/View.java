package CLI;
import java.util.Iterator;
import java.util.LinkedList;

import Business.Tile;

public class View {

    private MassageCallBack m;

    public View() {
        m = this::print;
    }
    public MassageCallBack getM() {
        return m;
    }
    public void setM(MassageCallBack m) {
        this.m = m;
    }
    public void print (String s){
        System.out.println(s);
    }

    public void printGameBoard(LinkedList<Tile>tiles){
        Iterator<Tile> iterator = tiles.iterator();
        while (iterator.hasNext()){
            Tile tile = iterator.next();
            if (tile.getPosition().getX()==0){
                System.out.println();
            }
            System.out.print(tile);
        }
    }
}
