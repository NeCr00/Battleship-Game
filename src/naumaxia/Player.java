/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naumaxia;

import static naumaxia.Tile.Type.HIT;
import static naumaxia.Tile.Type.MISS;
import static naumaxia.Tile.Type.SEA;
import static naumaxia.Tile.Type.SHIP;

/**
 *
 * @author giann
 */
public class Player {

    private String name;

    private int shots, miss, sucshots, rep;

    public Player(String name) {
        this.name = name;
        this.shots = 0;
        this.miss = 0;
        this.sucshots = 0;
        this.rep = 0;
    }

    Board b = new Board();

    void placeAllShips() throws OverlapTilesException, OversizeException, AdjacentTilesException {
        b.placeAllShips();
    }

    @SuppressWarnings("empty-statement")
    boolean placeShip(Ship sh, int cor[]) throws OverlapTilesException, AdjacentTilesException, OversizeException {
        return (sh.PlaceShip(b, true));

    }

    Tile[][] fire(Tile b[][], int pin[]) {
        shots++;

        System.out.println(name + " ");

        if (b[pin[0]][pin[1]].getType() == SHIP) {
            sucshots++;
            b[pin[0]][pin[1]].setType(HIT);
            System.out.print("Hit");
        } else if (b[pin[0]][pin[1]].getType() == SEA) {
            miss++;
            b[pin[0]][pin[1]].setType(MISS);
            System.out.print("Miss");
        } else {
            rep++;

            if (b[pin[0]][pin[1]].getType() == MISS) {
                System.out.print("Already Miss");
            } else {
                System.out.print("Already Hit");
            }
        }
        System.out.println();

        return b;

    }

    void getStats() {
        System.out.println("------Player " + name + " stats------");
        System.out.println("Shots :" + shots);
        System.out.println("Hits :" + sucshots);
        System.out.println("Miss :" + miss);
        System.out.println("Repeats :" + rep);
    }

    public String getName() {
        return name;
    }

    public Board getB() {
        return b;
    }

    public void setB(Board b) {
        this.b = b;
    }

}
