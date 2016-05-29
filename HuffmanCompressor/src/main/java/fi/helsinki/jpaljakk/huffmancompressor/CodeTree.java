package fi.helsinki.jpaljakk.huffmancompressor;

import java.util.ArrayList;
import java.util.List;

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
     * Codetree that represents binary codes of symbols
     * @param root Rootnode of binary tree
     * @param symbolLimit Maximum numbers of symbols
     */
    public CodeTree(InternalNode root, int symbolLimit) {
        
        this.root = root;

        codes = new ArrayList<List<Integer>>(); 
        for (int i = 0; i < symbolLimit; i++) {
            codes.add(null);
        }
        buildCodeList(root, new ArrayList<Integer>());
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
            codes.set(leaf.symbol, new ArrayList<Integer>(prefix));
        } 
    }

    /**
     * Gives binary code that represents symbol
     * @param symbol Number of the symbol
     * @return List of integers that represents binary code
     */
    public List<Integer> getCode(int symbol) {
        if (symbol < 0||codes.get(symbol) == null) {
            throw new IllegalArgumentException("Illegal symbol");
        } else {
            return codes.get(symbol);
        }
    }
}
