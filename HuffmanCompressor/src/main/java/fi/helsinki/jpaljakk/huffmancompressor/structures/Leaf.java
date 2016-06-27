/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.structures;

/**
 *
 * @author juha
 *
 */
public class Leaf implements Node {

    public int symbol;

    /**
     * Constructor for leaf
     * 
     * @param symbol Symbol that this leaf holds
     */
    public Leaf(int symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
