package naumaxia;

import java.util.*;
import java.*;

public class Game {

    public Game() throws OverlapTilesException, OversizeException, AdjacentTilesException {

        // TODO code application logic here
        Scanner sc = new Scanner(System.in);

        Player User = new Player("Ioannis");
        Player Comp = new Player("Computer");

        System.out.println("Do you want to place your ships randomly or manually ? Press Y for randomly placement and N for manually placement : ");
        int pin[] = new int[2];

        if (randomPlace()) {
            User.placeAllShips();
            Comp.placeAllShips();
        } else {
            int cor[] = new int[2];
            boolean check;

            System.out.println("Give coordinates X Y for Carrier (size 5");
            cor = Game.getInput();
            Carrier Ca = new Carrier(5, Game.getOrienation(), cor);
            check = User.placeShip(Ca, cor);
            Board.drawboards(User.getB().getPin(), Comp.getB().getPin());

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

            Comp.placeAllShips();

        }
        Board.drawboards(User.getB().getPin(), Comp.getB().getPin());

        boolean check = true;
        System.out.println("   Game Started");
        do {
            System.out.println("Give coordinates X Y for fire :");
            Comp.getB().setPin(User.fire(Comp.getB().getPin(), getInput()));
            User.getB().setPin(Comp.fire(User.getB().getPin(), getRandInput()));
            Board.drawboards(User.getB().getPin(), Comp.getB().getPin());

            if (Comp.getB().allShipsSunk()) {
                System.out.println("You Won !");
                check = false;
            } else if (User.getB().allShipsSunk()) {
                System.out.println("Game Over !");
            }

        } while (check);
        
        Comp.getStats();
        User.getStats();

    }

    static Scanner sc = new Scanner(System.in);

    static int[] getInput() {

        System.out.println("Give valid[X] [Y] coordinates (0-9) for fire :");
        boolean valid = true;
        int pin[] = new int[2];
        do {

            System.out.println("Give X :");
            pin[0] = sc.nextInt();
            System.out.println("Give Y :");
            pin[1] = sc.nextInt();
            if (pin[0] >= 0 && pin[0] < 10 && pin[1] >= 0 && pin[1] < 10) {
                valid = false;
            } else {
                System.out.println("Error ! Try again :");
            }
        } while (valid);

        return pin;
    }

    static int[] getRandInput() {
        Random r = new Random();
        int pin[] = new int[2];
        pin[0] = r.nextInt(10);
        pin[1] = r.nextInt(10);

        return pin;
    }

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

    static char getRandomOrienation() {
        String chars = "HV";
        Random rnd = new Random();
        char c = chars.charAt(rnd.nextInt(chars.length()));
        return c;

    }

}
