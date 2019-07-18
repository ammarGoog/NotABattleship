public class AircraftCarrier extends Boat implements Attacker{

    private boolean hasPlanes = true;

    public AircraftCarrier(int teamID, Coordinates location, int direction){
        super(teamID, location, direction, 5, 1, 1);
    }

    public String getID(){
        return "A" + this.getTeam();
    }

    public String getActions(){
        String message = "Choose any of the following actions for the Aircraft Carrier:\n" +
                "\n" +
                " 1. Move\n" +
                "\n" +
                " 2. Turn left\n" +
                "\n" +
                " 3. Turn right";
        if (hasPlanes) {
            return (message + "\n" +
                    "\n" +
                    " 5. Launch planes");
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
                response += this.attack(game);
            }
        }
        return response;
    }

    public String attack(World game){
        String message = "";
        double successRATE = 1;
        Coordinates position;

        while (hasPlanes){
            for (int j = 1; j < this.getVision(); j++){
                for (int i = 0; i < 8; i++) {
                    position = game.getAdjacentLocation(this.getLocation(), i);
                    if (!(game.getOccupant(position) == null || game.getOccupant(position).getTeam() == this.getTeam())){
                        if (successRATE > Math.random()){
                            successRATE *= 0.8;
                            message += " \nAir Raid!" + game.getOccupant(position).takeHit(this.getStrength());
                            continue;
                        }
                        else{
                            hasPlanes = false;
                            break;
                        }
                    }
                }
                break;
            }
            if (successRATE != 1){
                message += "\nThe planes have been destroyed.";
                return message;
            }
            else return "There are no boats in range currently.";
        }
        return message + "A1 has no planes remaining.";
    }
}
