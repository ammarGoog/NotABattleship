public abstract class Boat{

    private int team;
    private Coordinates location;
    private int direction;
    private int health;
    private int strength;
    private int vision;

    Boat(int teamID, Coordinates location, int direction, int health, int vision, int strength){
        this.team = teamID;
        this.direction = direction;

        if (location.getX() >= 0 && location.getY() >= 0)
            this.location = location;

        this.strength = strength;
        this.health = health;
        this.vision = vision;
    }

    public int getTeam() {return this.team;}

    public Coordinates getLocation() {return this.location;}

    public String getDirection() {
        String s = null;
        switch (this.direction) {
            case 0:
                s= "\u2191";
                break;
            case 1:
                s= "\u2197";
                break;
            case 2:
                s= "\u2192";
                break;
            case 3:
                s= "\u2198";
                break;
            case 4:
                s= "\u2193";
                break;
            case 5:
                s= "\u2199";
                break;
            case 6:
                s= "\u2190";
                break;
            case 7:
                s= "\u2196";
                break;
        }
        return s;
    }

    public int getDirectionInt(){
        return this.direction;
    }

    public void turnLeft() {this.direction -= 1;}

    public void turnRight() {this.direction += 1;}

    public int getHealth() {return this.health;}

    public int getStrength() {return this.strength;}

    public int getVision(){return this.vision;}

    public abstract String getID();                         //ID of the Boat type implement later with specific ships

    public abstract String act(int[] choices, World game);  //reports on the result of the action selected

    public abstract String getActions();                    //returns all the options available to the Boat

    public String move(World map){
        Coordinates toLoc = new Coordinates();
        Coordinates fromLoc = new Coordinates();
        fromLoc = this.location;
        toLoc = map.getAdjacentLocation(this.location, this.direction);


        if (map.isLocationValid(toLoc)){
            if (map.isLocationOccupied(toLoc)){
                return (this.toString() + " cannot move because to " + toLoc.toString() + " is occupied.");
            }
            else{

                map.setOccupant(null, fromLoc);               //TODO: find a way  to do this without accessing private data-field

                map.setOccupant(this, toLoc);
                setLocation(toLoc);

                return (this.toString() + " moves from " + fromLoc.toString() + " to " + toLoc.toString());
            }
        }
        else return (this.toString() + " cannot move off the map.");
    }

    public String takeHit(int strength){
        if (this.strength - strength <= 0){
            this.strength = 0;
            return (this.toString() + " has sunk!");
        }
        else{
            this.strength -= strength;
            return (this.toString() + " takes " + strength + " damage.");
        }
    }

    public void setLocation(Coordinates location){
        this.location.setCoordinates(location.getY(),location.getX());
    }

    public String toString(){
        return this.getID();
    }
}