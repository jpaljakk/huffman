/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.jpaljakk.huffmancompressor.algorithm;

import fi.helsinki.jpaljakk.huffmancompressor.structures.InternalNode;
import fi.helsinki.jpaljakk.huffmancompressor.structures.Leaf;
import fi.helsinki.jpaljakk.huffmancompressor.structures.List;
import fi.helsinki.jpaljakk.huffmancompressor.structures.Node;

/**
 *
 * @author juha
 *
 * Binary code tree where each leaf codes a symbol
 */
public class CodeTree {

    public InternalNode root;
    private List<List<Integer>> codes;

    /**
     * Codetree that represents optimal binary codes of symbols
     *
     * @param root Root node of binary tree
     * @param symbolLimit Maximum number of symbols
     */
    public CodeTree(InternalNode root, int symbolLimit) {

        this.root = root;

        codes = new List<List<Integer>>();
        for (int i = 0; i < symbolLimit; i++) {
            codes.add(null);
        }
        buildCodeList(root, new List<Integer>());
    }

    private void buildCodeList(Node node, List<Integer> prefix) {
        if (!node.isLeaf()) {
            InternalNode internalNode = (InternalNode) node;

            prefix.add(0);
            buildCodeList(internalNode.leftChild, prefix);
            prefix.remove(prefix.size() - 1);

            prefix.add(1);
            buildCodeList(internalNode.rightChild, prefix);
            prefix.remove(prefix.size() - 1);

        } else if (node.isLeaf()) {
            Leaf leaf = (Leaf) node;
            codes.set(leaf.symbol, new List<Integer>(prefix));
        }
    }

    /**
     * Gives binary code that represents the given symbol
     *
     * @param symbol Symbol which code is asked
     * @return List of integers that represents binary code
     */
    public List<Integer> getCode(int symbol) {
        if (symbol < 0 || codes.get(symbol) == null) {
            throw new IllegalArgumentException("Illegal symbol");
        } else {
            return codes.get(symbol);
        }
    }
}
