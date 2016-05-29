/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor;

/**
 *
 * @author juha
 * 
 */
public class Leaf implements Node {


    public int symbol;

    /**
     *
     * @param symbol
     */
    public Leaf(int symbol) {
        this.symbol = symbol;
    }


    @Override
    public boolean isLeaf() {
        return true;
    }
}
