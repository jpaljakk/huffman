package fi.helsinki.jpaljakk.huffmancompressor;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author juha
 * 
 * HuffmanEncoder encodes and writes to output bitstream
 * 
 */
public class HuffmanEncoder {

    private BitOutputStream output;

    public CodeTree codeTree;

    /**
     * Constructor for HuffmanEncoder
     * @param output BitOutputStream that encoder can write
     */
    public HuffmanEncoder(BitOutputStream output) {        
        this.output = output;
    }

    /**
     * Encode and write symbols to output bitstream
     * @param symbol Symbol that is to be encoded to output bitstream
     * @throws IOException
     */
    public void write(int symbol) throws IOException {
        if (codeTree == null) {
            throw new NullPointerException("Codetree is null");
        }
        List<Integer> bits = codeTree.getCode(symbol);
        for (int b : bits) {
            output.write(b);
        }
    }
}
