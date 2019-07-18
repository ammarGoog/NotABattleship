public class Submarine extends ScoutBoat implements Attacker{
    private int numOfTorpedoes;

    public Submarine(int teamID, Coordinates location, int direction, int torpedoes){
        super(teamID, location, direction, 3, 2);
        this.numOfTorpedoes = torpedoes;
    }

    public String getID(){
        return "S" + this.getTeam();
    }

    public String getActions(){
        String message = "Choose any of the following actions for the Submarine:\n" +
                "\n" +
                " 1. Move\n" +
                "\n" +
                " 2. Turn left\n" +
                "\n" +
                " 3. Turn right\n" +
                "\n" +
                " 4. Submerge";
        if (numOfTorpedoes > 0) {
            return (message + "\n" +
                    "\n" +
                    " 5. Fire torpedoes");
        }
        else return message;
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
                response += this.submerge(game);
            }
            if (choice == 5){
                response += this.attack(game);
            }
        }
        return response;
    }

    public String attack(World game) {
        if (numOfTorpedoes > 0) {

            Coordinates position;

            for (int i = 1; i < this.getVision(); i++) {
                position = game.getAdjacentLocation(this.getLocation(), this.getDirectionInt());
                if (!(game.getOccupant(position) == null || game.getOccupant(position).getTeam() == this.getTeam()))
                    return "Fire torpedoes!" + game.getOccupant(position).takeHit((int) (1 + (Math.random() * (game.getOccupant(position).getHealth() - 1))));
            }
            return "There are no boats in range currently.";
        }
        return this.getID() + " has no torpedoes remaining.";
    }


    public String submerge(World game){

        while(true) {
            int randDirection = (int) (1 + (Math.random() * World.NORTHWEST - 1));
            Coordinates toLoc = game.getAdjacentLocation(this.getLocation(), randDirection);
            toLoc = game.getAdjacentLocation(toLoc, randDirection);

            if (game.isLocationValid(toLoc) && !game.isLocationOccupied(toLoc)) {
                this.setLocation(toLoc);
                this.move(game);
                return (this.getID() + " moves from " + this.getLocation().toString() + " to " + toLoc.toString());
            }
        }
    }
}
