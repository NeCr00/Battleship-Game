/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naumaxia;

import naumaxia.Tile.Type;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static naumaxia.Tile.Type.HIT;
import static naumaxia.Tile.Type.SEA;
import static naumaxia.Tile.Type.SHIP;

/**
 *
 * @author giann
 */
public class Board {
    
//Variables
    private Tile pin[][] = new Tile[10][10]; // pinakas 2 diastasewn typou Tile 

//Constructor
    public Board() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                pin[i][j] = new Tile(i, j, Type.SEA); // dhmiourgei ena antikeimeno Tile kai to topotheti ston  disdiastato pinaka 
            }
        }

    }
//Setter & Getter
    public Tile[][] getPin() { // methodos pou epistrefei ton pinaka
        return pin;
    }

    public void setPin(Tile[][] pin) { // methodos pou allazei ton pinaka
        this.pin = pin;
    }

//Ektyposi pinaka
   public static void drawboards(Tile board1[][], Tile board2[][]) { // dexetai ton pinaka tou user kai tou computer

JFrame f=new JFrame(); // dhmiourgei ton  aspro pinaka
String[][] data =new String[23][11]; 

String[] column={"","","   ","   ","   ","YOU","   ","   ","   ","   ",""};
        for (int i=1 ; i < 11; i++) {
            data[i][0]=Integer.toString(i-1);
        }
        for (int i =1 ; i < 11; i++) {
            data[0][i]=Integer.toString(i-1);
        }


        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {

                data[i][j]=board1[i-1][j-1].draw(false);
            }

        }
        for (int i =0 ; i < 11; i++) {
            if(i==5){
                data[11][i]="  COMPUTER";
            }
           else data[12][i] ="   ";
        }
        for (int i =13 ; i < 23; i++) {
            data[i][0]=Integer.toString(i-13);
        }
        for (int i =1 ; i < 11; i++) {
            data[12][i]=Integer.toString(i-1);
        }


        for (int i =13 ; i < 23; i++) {
            for (int j = 1; j < 11; j++) {

                data[i][j]=board2[i-13][j-1].draw(true);

            }

        }
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,100,500);
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);
        f.setSize(600,480);
        f.setVisible(true);

    }
  
    //Dhmiourgia antikeimenwn typou ship . Prospelasi ton ploiwn ston pinaka me tin xrisi tis methodou placeship
    void placeAllShips() throws OverlapTilesException, OversizeException, AdjacentTilesException {
        Board b = this; //antikeimeno board
        int cor[] = new int[2]; //pinakas syntetagmenwn

        cor = Game.getRandInput(); //kali tin getRandInput apo tin klasi Game kai gyrnaei ena pinaka tyxaiwn syntetagmenwn
        Carrier Ca = new Carrier(5, Game.getRandomOrienation(), cor); // dhmiourgia antikeimenou Carrier . pernaei ton megethos, tin kateuthinsi kai tis tyxaies syntetagmenes
        Boolean check = false; //metabliti gia ton elegxo epityxhmenhs prospelasis

        while (!check) {  // loop pou elegxei ean itan epityxhmenh h topothetisi tou ploiou
            check = Ca.PlaceShip(b, false);    // kalei tin placeship gia na topothetisi to ploio . gyrnaei true an topothetithike epityxws kai false ean apetyxe
            cor = Game.getRandInput(); // pairnei nees syntetagmenes .
            if (!check) {
                Ca.setCellStart(cor); // theti kainourgies syntetagmenes afou oi prohgoumenes apetyxan
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

    ArrayList<Tile> getAdjacentTiles(Tile t, Tile[][] pin) { // voithitiki methodos pou epistrefei ta geitonika tiles
        ArrayList<Tile> b = new ArrayList();

        if (t.getY() + 1 < 10) {
            b.add(pin[t.getX()][t.getY() + 1]);
        }
                                                                //elegxei an to geitoniko tile yparxei kai den bgainei ektos  kai stin synexeia to prospelazei ston pinaka
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

    Boolean allShipsSunk() { // elegxei an einai vithismena ola ta ploia
        boolean b = true; // metavliti elegxou 
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
