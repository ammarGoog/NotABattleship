import java.lang.Math;

public abstract class ScoutBoat extends Boat{
    public ScoutBoat(int teamID, Coordinates location, int direction, int health, int vision){
        super(teamID, location, direction, health, vision, 1);
    }

    public String takeHit(int attacks){
        String message = null;

        for (int i = 0; i < attacks; i++){
            int random = (int) (Math.random() * 4 + 1);
            if (random != 1)
                message = this.getID() + " has avoided the attack!";
            else {
                super.takeHit(1);
                message = this.getID() + " takes 1 damage.";
            }
        }
        return message;
    }
}
