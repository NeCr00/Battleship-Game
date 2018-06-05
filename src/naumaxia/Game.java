package naumaxia;

import java.util.*;
import java.*;

public class Game {

    public Game() throws OverlapTilesException, OversizeException, AdjacentTilesException {

        // TODO code application logic here
        Scanner sc = new Scanner(System.in); //Scanner 

        Player User = new Player("User");    //Dhmiourgia antikeimenwn Player
        Player Comp = new Player("Computer");

        System.out.println("Do you want to place your ships randomly or manually ? Press Y for randomly placement and N for manually placement : ");
        int pin[] = new int[2];

        if (randomPlace()) {
            User.placeAllShips();      // Kalei thn placeAllShip tou kathe paixti an einai random i topothetisi
            Comp.placeAllShips();
        } else {
            int cor[] = new int[2]; //pinakas syntetagmenwn
            boolean check;  //metavliti elegxou

            System.out.println("Give coordinates X Y for Carrier (size 5)");
            cor = Game.getInput(); // pinakas dyo syntetagmenwn , kalei tin getInput pou epistrefei epishs enan pinaka dyo syntetagmenwn
            Carrier Ca = new Carrier(5, Game.getOrienation(), cor); //Dhmiourgia antikeimenou Carrier
            check = User.placeShip(Ca, cor); // o User kalei tin placeship gia na topothetisi to ploio 
            Board.drawboards(User.getB().getPin(), Comp.getB().getPin()); //ektypwsi pinaka

            System.out.println("Give coordinates X Y for Battleship (size 4)");
            cor = Game.getInput();
            Battleship Ba = new Battleship(4, Game.getOrienation(), cor);
            check = User.placeShip(Ba, cor);
            Board.drawboards(User.getB().getPin(), Comp.getB().getPin());

            System.out.println("Give coordinates X Y for Cruiser (size 3)");
            cor = Game.getInput();
            Cruiser Cr = new Cruiser(3, Game.getOrienation(), cor);
            check = User.placeShip(Cr, cor);
            Board.drawboards(User.getB().getPin(), Comp.getB().getPin());

            System.out.println("Give coordinates X Y for Battleship (size 2)");
            cor = Game.getInput();
            Destroyer De = new Destroyer(2, Game.getOrienation(), cor);
            check = User.placeShip(De, cor);
            Board.drawboards(User.getB().getPin(), Comp.getB().getPin());

            System.out.println("Give coordinates X Y for Battleship (size 3)");
            cor = Game.getInput();
            Submarine Su = new Submarine(3, Game.getOrienation(), cor);
            check = User.placeShip(Su, cor);

            Comp.placeAllShips(); //prospalsi ploiwn toy antikeimenou computer

        }
        Board.drawboards(User.getB().getPin(), Comp.getB().getPin()); // ektypwsi 

        boolean check = true; // metavliti elegxou
        System.out.println("   Game Started");
        do {
            System.out.println("Give coordinates X Y for fire :");
            Comp.getB().setPin(User.fire(Comp.getB().getPin(), getInput())); // Kalei tin synartisi fire me orismata ton pinaka tou computer kai tis syntetagmenes . Epeita i fire epistrefei enan kainourgio pinaka kai ton apothikeuei sto antikeimeno computer
            User.getB().setPin(Comp.fire(User.getB().getPin(), getRandInput())); //Kalei tin synartisi fire pernwntas ton pinaka tou object User kai tis syntetagmenes kai meta tin epeksergasia ton pairnaei sto antikeimno User
            Board.drawboards(User.getB().getPin(), Comp.getB().getPin()); // Ektypwsi pinaka

            if (Comp.getB().allShipsSunk()) {
                System.out.println("You Won !");
                check = false;                          //Elegxos gia to an teleiwse to game
            } else if (User.getB().allShipsSunk()) {
                System.out.println("Game Over !");
                check = false;
            }

        } while (check);

        Comp.getStats();
        User.getStats();    //Ektypwsi statistikwn

    }

    static Scanner sc = new Scanner(System.in);

    //Synartisi pou epistrefei ena pinaka syntetagmenwn
    static int[] getInput() {
Scanner sc = new Scanner(System.in).useDelimiter("\\D*\\D");
        System.out.println("Give valid[X] [Y] coordinates (0-9) for fire :");
        boolean valid = true;
        int pin[] = new int[2];
        do {

            pin[0] = sc.nextInt();
            pin[1] = sc.nextInt();
            if (pin[0] >= 0 && pin[0] < 10 && pin[1] >= 0 && pin[1] < 10) {
                valid = false;
            } else {
                System.out.println("Error ! Try again :");
            }
        } while (valid);

        return pin;
    }

    //Synartisi poy epistrefei enan pinaka tyxaiwn timwn
    static int[] getRandInput() {
        Random r = new Random();
        int pin[] = new int[2];
        pin[0] = r.nextInt(10);
        pin[1] = r.nextInt(10);

        return pin;
    }

    //Synartisi pou epistefei true h false 
    static boolean randomPlace() {
        char answer = ' ';
        boolean check = true;

        while (check) {
            answer = sc.next().charAt(0);
            if (answer == 'N' || answer == 'n' || answer == 'Y' || answer == 'y') {
                check = false;
            } else {
                System.out.println("Wrong input. Try again :");
            }
        }
        if (answer == 'N' || answer == 'n') {
            return false;
        } else {
            return true;
        }
    }

    //Synartisi pou epistefei xarakthra gia tin topothetisi tou ploiou
    static char getOrienation() {
        System.out.println("Press H for Horizontal orienation or V for vertical orienation");
        char answer = ' ';
        boolean check = true;
        while (check) {
            answer = sc.next().charAt(0);
            if (answer == 'H' || answer == 'h' || answer == 'V' || answer == 'v') {
                check = false;
            } else {
                System.out.println("Wrong input. Try again");
                check = true;
            }
        }
        if (answer == 'H' || answer == 'h') {
            return 'H';
        } else {
            return 'V';
        }
    }

    //Synartisi pou epistrefei tyxaia topothetisi ploiou
    static char getRandomOrienation() {
        String chars = "HV";
        Random rnd = new Random();
        char c = chars.charAt(rnd.nextInt(chars.length()));
        return c;

    }

}
