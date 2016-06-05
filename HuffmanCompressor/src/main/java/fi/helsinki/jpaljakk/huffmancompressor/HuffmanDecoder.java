/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor;

import java.io.IOException;

/**
 *
 * @author juha
 *
 * HuffmanDecoder that reads from bit inputstream and decodes codes back to
 * symbols
 */
public class HuffmanDecoder {

    private BitInputStream input;

    public CodeTree codeTree;

    /**
     * Constructor for Huffmandecoder
     *
     * @param in Bitinputstream that decoder reads
     */
    public HuffmanDecoder(BitInputStream in) {

        input = in;
    }

    /**
     * Reads and decodes next symbol
     *
     * @return decoded symbol
     * @throws IOException
     */
    public int read() throws IOException {
        if (codeTree == null) {
            throw new NullPointerException("Codetree is null");
        }

        InternalNode currentNode = codeTree.root;
        while (true) {
            int temp = input.readNoEof();
            Node nextNode;
            if (temp == 0) {
                nextNode = currentNode.leftChild;
            } else if (temp == 1) {
                nextNode = currentNode.rightChild;
            } else {
                throw new AssertionError();
            }

            if (nextNode.isLeaf()) {
                return ((Leaf) nextNode).symbol;
            } else if (!nextNode.isLeaf()) {
                currentNode = (InternalNode) nextNode;
            } else {
                throw new AssertionError();
            }
        }
    }

}
