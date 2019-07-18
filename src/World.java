public class World {

    public static final int NORTH = 0;
    public static final int NORTHEAST = 1;
    public static final int EAST = 2;
    public static final int SOUTHEAST = 3;
    public static final int SOUTH = 4;
    public static final int SOUTHWEST = 5;
    public static final int WEST = 6;
    public static final int NORTHWEST = 7;

    private Boat map[][];                                    //review how map[][] is implemented

    World(int width, int height) {

        if(!(height > 4 && height < 10)) {
            if (height > 10)
                height = 10;
            else height = 4;
        }

        if(!(width > 4 && width < 10)) {
            if (width > 10)
                width = 10;
            else width = 4;
        }

        this.map = new Boat[height][width];


        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                map[row][col] = null;
            }
        }
    }

    public int getHeight(){
        return map.length;
    }

    public int getWidth(){
        return map[getHeight()-1].length;
    }

    public Boat getOccupant(Coordinates coor){
        if (map[coor.getY()][coor.getX()] != null){
            return map[coor.getY()][coor.getX()];
        }
        else {
            return null;
        }
    }

    public boolean isLocationValid(Coordinates coor){
        if (coor.getY() <= getHeight() && coor.getX() <= getWidth()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isLocationOccupied(Coordinates coor){
        if (map[coor.getY()][coor.getX()] != null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean setOccupant(Boat ship, Coordinates location){        //review setOccupant method
        if (!this.isLocationOccupied(location) || ship == null){
            map[location.getY()][location.getX()] = ship;
            return true;
        }
        else{
            return false;
        }
    }

    public Coordinates getAdjacentLocation(Coordinates coor, int x){
        if (this.isLocationValid(coor)) {
            switch (x){
                case 0: coor.setCoordinates(coor.getX(), coor.getY()+ 1);
                break;
                case 1: coor.setCoordinates(coor.getX() + 1, coor.getY()+ 1);
                break;
                case 2: coor.setCoordinates(coor.getX() + 1, coor.getY());;
                break;
                case 3: coor.setCoordinates(coor.getX() + 1, coor.getY() - 1);
                break;
                case 4: coor.setCoordinates(coor.getX(), coor.getY() - 1);
                break;
                case 5: coor.setCoordinates(coor.getX() - 1, coor.getY() - 1);
                break;
                case 6: coor.setCoordinates(coor.getX() - 1, coor.getY() );
                break;
                case 7: coor.setCoordinates(coor.getX() - 1, coor.getY() + 1 );
            }
            return coor;
        }
        else return null;
    }

    public String drawTeamMap(Boat[] teamBoats, int view) {
        String drawMap = "";
        String[][] teamView = new String[this.getHeight()][this.getWidth()];
        int yStart = 0;
        int yEnd = 0;
        int xStart = 0;
        int xEnd = 0;
        Coordinates boatLocation;

        for (int row = 0; row < teamView.length; row++)                   //maybe change 'teamView' here to 'map'
            for (int col = 0; col < teamView[row].length; col++)
                teamView[row][col] = "###";

        if (view != 1) {
            for (int i = 0; i < teamBoats.length; i++) {
                if (teamBoats[i] != null) {
                    Boat b = teamBoats[i];

                    if (b.getHealth() > 0) {
                        Coordinates testLoc = new Coordinates();

                        int yTest = b.getLocation().getY() - b.getVision();
                        int xTest = b.getLocation().getX() - b.getVision();

                        if (xTest < 0) xStart = 0;
                        else xStart = xTest;

                        if (yTest < 0) yStart = 0;
                        else yStart = yTest;

                        yTest = b.getLocation().getY() + b.getVision();
                        xTest = b.getLocation().getX() + b.getVision();

                        if (yTest >= teamView.length)
                            yEnd = teamView.length -1 ;
                        else yEnd = yTest;

                        if (xTest >= teamView[teamView.length -1].length)
                            xEnd = teamView[teamView.length -1].length - 1;
                        else xEnd = xTest;

                        for (int y = yStart; y <= yEnd; y++) {           //changed <= to <
                            for (int x = xStart; x <= xEnd; x++) {       //changed <= to <
                                if ((Boat) map[y][x] == null) {
                                    teamView[y][x] = "~~~";
                                } else if (((Boat) map[y][x]).getHealth() <= 0)
                                    teamView[y][x] = " ~ ";
                                else {
                                    int team;
                                    team = ((Boat) map[y][x]).getTeam();
                                    String teamString = Integer.toString(team);

                                    String id = ((Boat) map[y][x]).getID();

                                    if (view == 2) {
                                        String dirString;
                                        dirString = ((Boat) map[y][x]).getDirection();

                                        teamView[y][x] = dirString + id;
                                    } else {
                                        int health;
                                        health = ((Boat) map[y][x]).getHealth();
                                        String healthString = Integer.toString(health);

                                        teamView[y][x] = healthString + id;
                                    }
                                }
                            }
                        }
                    }
                }

            }

            for (int y = -1; y < this.getHeight(); y++) {
                drawMap += (char) (y + 65) + " ";
                for (int x = 0; x < this.getWidth(); x++) {
                    if (y == -1) {
                        drawMap += " " + (x + 1) + ((x < 9) ? " " : "");
                    } else {
                        drawMap += teamView[y][x];
                    }
                }
                drawMap += "\n";
            }
        }
        return drawMap;
    }
}