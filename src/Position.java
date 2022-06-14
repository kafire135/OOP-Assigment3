import java.util.Objects;

public class Position implements Comparable<Position>{
    private int x;
    private int y;

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    public double distance (Position other){
        double temp=Math.pow(x-other.x,2)+Math.pow(y-other.y,2);
        return Math.sqrt(temp);
    }

    public int compareTo(Position p2){
        if(y>p2.y){
            return 1;
        }
        else if (y<p2.y){
            return -1;
        }
        else {
            if (x>p2.x){
                return 1;
            }
            else if (x<p2.x){
                return -1;
            }
            else {
                return 0;
            }
        }
    }
}
