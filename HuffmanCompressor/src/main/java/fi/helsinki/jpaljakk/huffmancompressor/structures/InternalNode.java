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
public class InternalNode implements Node {

    public Node leftChild;
    public Node rightChild;

    /**
     * Constructor for internal node
     *
     * @param leftChild this nodes left child node
     * @param rightChild this nodes right child node
     */
    public InternalNode(Node leftChild, Node rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
}
