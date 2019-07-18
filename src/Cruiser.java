public class Cruiser extends ScoutBoat {

    public Cruiser(int teamID,  Coordinates location, int direction){
        super(teamID, location, direction, 3, 3);
    }

    public String getID(){
        return "C" + this.getTeam();
    }

    public String getActions(){
        return ("Choose any of the following actions for the Cruiser:\n" +
                "\n" +
                " 1. Move\n" +
                "\n" +
                " 2. Turn left\n" +
                "\n" +
                " 3. Turn right");
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
        }
        return response;
    }
}
