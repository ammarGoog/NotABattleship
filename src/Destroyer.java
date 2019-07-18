public class Destroyer extends Boat implements Attacker {

    public Destroyer(int teamID, Coordinates location, int direction){
        super(teamID, location, direction, 2, 1, 2);
    }

    public String getID(){
        return "D" + this.getTeam();
    }

    public String getActions(){
        return ("Choose any of the following actions for the Destroyer:\n" +
                "\n" +
                " 1. Move\n" +
                "\n" +
                " 2. Turn left\n" +
                "\n" +
                " 3. Turn right\n" +
                "\n" +
                " 4. Attack");
    }

    public String act(int[] choices, World game){
        String response = "";
        for (int choice: choices){
            if (choice == 1)
                response += (this.move(game));

            if (choice == 2) {
                this.turnLeft();
                response += "Turned Left.";
            }
            if (choice == 3) {
                this.turnRight();
                response += "Turned Right.";
            }
            if (choice == 4){
                response += this.attack(game);
            }
        }
        return response;
    }

    public String attack(World game) {

        Coordinates position;

        for (int i = 1; i < this.getVision(); i++) {
            position = game.getAdjacentLocation(this.getLocation(), this.getDirectionInt());
            if (!(game.getOccupant(position) == null || game.getOccupant(position).getTeam() == this.getTeam()))
                return "" + game.getOccupant(position).takeHit(this.getStrength());
        }
        return "There are no boats in range currently.";
    }

    public String takeHit(int strength){
        if(Math.random() > 0.5)
            return this.toString() + "D1 avoids the attack!";
        else
            return super.takeHit(strength);
    }

}
