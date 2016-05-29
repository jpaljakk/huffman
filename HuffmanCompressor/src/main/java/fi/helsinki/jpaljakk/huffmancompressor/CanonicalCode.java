package fi.helsinki.jpaljakk.huffmancompressor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juha
 * 
 * Canonical Huffman Code. Describe length of each symbols binary code
 */
public class CanonicalCode {

    private int[] codeLengths;

    /**
     * Constructor for CanonicalCode
     * @param codeLengths Lengths of Huffman codes
     */
    public CanonicalCode(int[] codeLengths) {
        
        this.codeLengths = codeLengths;        
    }
    
    /**
     * Constructor for CanonicalCode
     * @param tree Huffman binary code tree
     * @param symbolLimit Limit of symbols
     */
    public CanonicalCode(CodeTree tree, int symbolLimit) {
        codeLengths = new int[symbolLimit];
        buildCodeLengths(tree.root, 0);
    }

    private void buildCodeLengths(Node node, int depth) {
        if (!node.isLeaf()) {
            InternalNode internalNode = (InternalNode) node;
            buildCodeLengths(internalNode.leftChild, depth + 1);
            buildCodeLengths(internalNode.rightChild, depth + 1);
        } else if (node.isLeaf()) {
            int symbol = ((Leaf) node).symbol;
            if (codeLengths[symbol] != 0) {
                throw new AssertionError("Symbol has over one code"); 
            }
            if (symbol >= codeLengths.length) {
                throw new IllegalArgumentException("Symbol exceeds limit");
            }
            codeLengths[symbol] = depth;
        } 
    }

    /**
     * Gives limit of symbols
     * @return Limit of symbols
     */
    public int getSymbolLimit() {
        return codeLengths.length;
    }

    /**
     * Gives length of code of the given symbol
     * @param symbol Symbol which code length is wanted
     * @return Length of code of the given symbol
     */
    public int getCodeLength(int symbol) {
        if (symbol < 0 || symbol >= codeLengths.length) {
            throw new IllegalArgumentException("Symbol out of range");
        }
        return codeLengths[symbol];
    }

    /**
     * Builds and returns codetree from this canonical code
     * @return Codetree from this canonical code
     */
    public CodeTree toCodeTree() {
        List<Node> nodes = new ArrayList<Node>();
        for (int i = maximum(codeLengths); i >= 1; i--) {  
            List<Node> newNodes = new ArrayList<Node>();

            for (int j = 0; j < codeLengths.length; j++) {
                if (codeLengths[j] == i) {
                    newNodes.add(new Leaf(j));
                }
            }            
            for (int j = 0; j < nodes.size(); j += 2) {
                newNodes.add(new InternalNode(nodes.get(j), nodes.get(j + 1)));
            }
            nodes = newNodes;
            if (nodes.size() % 2 != 0) {
                throw new IllegalStateException("Doesnt represent Huffman code tree");
            }
        }
        if (nodes.size() != 2) {
            throw new IllegalStateException("Doesnt represent Huffman code tree");
        }
        return new CodeTree(new InternalNode(nodes.get(0), nodes.get(1)), codeLengths.length);
    }

    private static int maximum(int[] array) {
        int result = array[0];
        for (int x : array) {
            result = Math.max(x, result);
        }
        return result;
    }
}
