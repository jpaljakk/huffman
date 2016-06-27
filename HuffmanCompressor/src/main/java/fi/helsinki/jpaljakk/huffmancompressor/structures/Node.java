/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.structures;

/**
 *
 * @author juha
 */
public interface Node {

    /**
     * Tells if node is leaf or internal node
     *
     * @return true if node is leaf or false if internal node
     */
    public boolean isLeaf();
}
