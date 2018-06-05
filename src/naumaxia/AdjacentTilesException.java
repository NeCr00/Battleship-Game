/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naumaxia;

/**
 *
 * @author giann
 */
public class AdjacentTilesException extends Exception {

    AdjacentTilesException() { // kalei ton constructor kai typwnei to mhnyma
        throw new UnsupportedOperationException("Ships have to differ at least one cell"); //To change body of generated methods, choose Tools | Templates.
    }

}
