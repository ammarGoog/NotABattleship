public class Battleship extends Boat implements Attacker{

    public Battleship(int teamID, Coordinates location, int direction){
        super(teamID, location, direction, 4, 1, 3);
    }

    public String getID(){
        return "B" + this.getTeam();
    }

    public String getActions(){
        return ("Choose any of the following actions for the Battleship:\n" +
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
                return "Fire cannons!" + game.getOccupant(position).takeHit(this.getStrength()) + game.getOccupant(position).takeHit(this.getStrength());
        }
        return "There are no boats in range currently.";
    }
}
