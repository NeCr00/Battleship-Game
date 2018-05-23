/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naumaxia;

import naumaxia.Tile.Type;
import java.util.*;
import static naumaxia.Tile.Type.HIT;
import static naumaxia.Tile.Type.SEA;
import static naumaxia.Tile.Type.SHIP;

/**
 *
 * @author giann
 */
public class Board {

    private Tile pin[][] = new Tile[10][10];

    public Board() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                pin[i][j] = new Tile(i, j, Type.SEA);
            }
        }

    }

    public Tile[][] getPin() {
        return pin;
    }

    public void setPin(Tile[][] pin) {
        this.pin = pin;
    }

    static void drawboards(Tile pin1[][], Tile pin2[][]) {

        System.out.println("\t" + " ------------- YOU --------------");
        System.out.println("  0\t" + "1\t" + "2\t" + "3\t" + "4\t" + "5\t" + "6\t" + "7\t" + "8\t" + "9\t");

        for (int i = 0; i < 10; i++) {

            System.out.print(i + " ");

            for (int j = 0; j < 10; j++) {
                pin1[i][j].draw(false);
                System.out.print("\t");
            }

            System.out.println();

        }
        System.out.println();

        System.out.println("\t" + " ------------- COMPUTER --------------");
        System.out.println("  0\t" + "1\t" + "2\t" + "3\t" + "4\t" + "5\t" + "6\t" + "7\t" + "8\t" + "9\t");

        for (int i = 0; i < 10; i++) {

            System.out.print(i + " ");

            for (int j = 0; j < 10; j++) {
                pin2[i][j].draw(true);
                System.out.print("\t");
            }

            System.out.println();

        }
    }

    void placeAllShips() throws OverlapTilesException, OversizeException, AdjacentTilesException {
        Board b = this;
        int cor[] = new int[2];

        cor = Game.getRandInput();
        Carrier Ca = new Carrier(5, Game.getRandomOrienation(), cor);
        Boolean check = false;

        while (!check) {
            check = Ca.PlaceShip(b, false);
            cor = Game.getRandInput();
            if (!check) {
                Ca.setCellStart(cor);
            }
        }

        check = false;
        Battleship Ba = new Battleship(4, Game.getRandomOrienation(), cor);
        while (!check) {
            check = Ba.PlaceShip(b, false);
            cor = Game.getRandInput();
            if (!check) {
                Ba.setCellStart(cor);
            }
        }

        check = false;
        Cruiser Cr = new Cruiser(3, Game.getRandomOrienation(), cor);
        while (!check) {
            check = Cr.PlaceShip(b, false);
            cor = Game.getRandInput();
            if (!check) {
                Cr.setCellStart(cor);
            }
        }

        check = false;
        Destroyer De = new Destroyer(2, Game.getRandomOrienation(), cor);
        while (!check) {
            check = De.PlaceShip(b, false);
            cor = Game.getRandInput();
            if (!check) {
                De.setCellStart(cor);
            }
        }

        check = false;
        Submarine Su = new Submarine(3, Game.getRandomOrienation(), cor);
        while (!check) {
            check = Su.PlaceShip(b, false);
            cor = Game.getRandInput();
            if (!check) {
                Su.setCellStart(cor);
            }
        }
    }

    ArrayList<Tile> getAdjacentTiles(Tile t, Tile[][] pin) {
        ArrayList<Tile> b = new ArrayList();

        if (t.getY() + 1 < 10) {
            b.add(pin[t.getX()][t.getY() + 1]);
        }

        if (t.getX() + 1 < 10) {
            b.add(pin[t.getX() + 1][t.getY()]);
        }

        if (t.getY() - 1 >= 0) {
            b.add(pin[t.getX()][t.getY() - 1]);
        }

        if (t.getX() - 1 >= 0) {
            b.add(pin[t.getX() - 1][t.getY()]);
        }

        return b;

    }

    Boolean allShipsSunk() {
        boolean b = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (pin[i][j].getType() == SHIP) {
                    b = false;
                }
            }
        }
        return b;
    }

}
