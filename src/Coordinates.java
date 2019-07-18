public class Coordinates{

    private int x;
    private int y;

    Coordinates(){
        x = 0;
        y = 0;
    }

    Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setCoordinates(int y, int x){
        this.x = x;
        this.y = y;
    }

    public String toString(){                       //review output of this method

        char[] row = new char[26];

        for(int i = 0; i < row.length; i++){
            row[i] = (char)(65 + i);
        }

        return  "" + row[y] + getX();
    }
}
