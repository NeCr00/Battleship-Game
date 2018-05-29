/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naumaxia;

import java.util.ArrayList;
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
    private static int k=0,j=0,l=0;
    static ArrayList <Tile> tiles = new ArrayList();
    static boolean check=false;
    static Tile start;
    
    
    Tile[][] fire(Tile board[][], int pin[]) {
        shots++;

        System.out.println(name + " ");
        
        if("Computer".equals(name)&& check){
            if(k < tiles.size()){
            pin[0]=tiles.get(k).getX();
            pin[1]=tiles.get(k).getY();}
                 
        }

        if (board[pin[0]][pin[1]].getType() == SHIP) {
            sucshots++;
            board[pin[0]][pin[1]].setType(HIT);
            System.out.print("Hit");
            if("Computer".equals(name)){
                tiles =b.getAdjacentTiles(board[pin[0]][pin[1]], board);
                j++;
                check = true;
            }
        } else if (board[pin[0]][pin[1]].getType() == SEA) {
            miss++;
            board[pin[0]][pin[1]].setType(MISS);
            System.out.print("Miss");
            if("Computer".equals(name) ){
                if(check)
                k++;
                if(k>3||j>1){
                    k=0;
                    j=0;
                    check=false;}
                
            }
        } else {
            rep++;
            
            if (board[pin[0]][pin[1]].getType() == MISS) {
                System.out.print("Already Miss");
            } else {
                System.out.print("Already Hit");
            }
            if("Computer".equals(name) ){
                if(check)
                k++;
                if(k>3||j>1){
                    k=0;
                    j=0;
                    check=false;}
                
            }
            
        }
        System.out.println();
       

        return board;

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
